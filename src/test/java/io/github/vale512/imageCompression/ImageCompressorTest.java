package io.github.vale512.imageCompression;

import static io.github.vale512.imageCompression.ImageCompressor.resize;
import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.vale512.imageCompression.resolution.Resolution;

public class ImageCompressorTest {

	private Path input;
	private Path output;
	private static final int WIDTH = 75;
	private static final int HEIGHT = 75;
	private static final Resolution RESOLUTION = new Resolution(WIDTH, HEIGHT);
	
	@Before
	public void setUp() throws Exception {
		this.input= Paths.get("src/test/resources/samples/lenna512x512.jpg");
		this.output = File.createTempFile("tmp", ".jpg").toPath();
	}

	@After
	public void tearDown() throws Exception {
		this.output.toFile().delete();
	}

	@Test
	public void testResize() throws IOException {
		resize(this.input, this.output, RESOLUTION);
		BufferedImage image = ImageIO.read(this.output.toFile());
		assertEquals(WIDTH, image.getWidth());
		assertEquals(HEIGHT, image.getHeight());
	}

}
