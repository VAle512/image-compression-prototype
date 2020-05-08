package io.github.vale512.imageCompression;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import io.github.vale512.imageCompression.resolution.Resolution;

public class ImageCompressor {
	
	/**
	 * Resize the image in the input path, saving it into the output path.
	 * 
	 * @param input Input path.
	 * @param output Output path.
	 * @param resolution New resolution.
	 * @throws IOException
	 */
	public static void resize(Path input, Path output, Resolution resolution) throws IOException {
		BufferedImage image = ImageIO.read(input.toFile());
		final int width = resolution.getWidth();
		final int height = resolution.getHeight();
		Image resized = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics2D g2D = createGraphics2D(newImage);
		g2D.drawImage(resized, 0, 0, null);
		g2D.dispose();
		writeImage(newImage, output);
	}
	
	private static Graphics2D createGraphics2D(BufferedImage image) {
		Graphics2D g2D = image.createGraphics();
		g2D.setComposite(AlphaComposite.Src);
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		return g2D;
	}
	
	private static void writeImage(BufferedImage image, Path output) throws IOException {
		output.toFile().createNewFile();
		ImageIO.write(image, "jpg", output.toFile());		
	}
}
