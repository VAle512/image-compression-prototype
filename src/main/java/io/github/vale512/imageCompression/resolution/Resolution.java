package io.github.vale512.imageCompression.resolution;

/**
 * A resolution, represented by a couple of width and height.
 */
public class Resolution {
	
	/**
	 * Standard width and height separator <i>(i.e. 75<b>x</b>75)</i>.
	 */
	public static final String RESOLUTIONS_DELIMITER = "x";
	private int width;
	private int height;
	
	public Resolution(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Return the string representing this resolutions:
	 * width and height separated by {@link Resolution#RESOLUTIONS_DELIMITER}.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.width);
		sb.append(RESOLUTIONS_DELIMITER);
		sb.append(this.height);
		return sb.toString();
	}
}
