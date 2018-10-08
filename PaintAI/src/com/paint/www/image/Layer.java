package com.paint.www.image;

/**
 * Represents a layer of an image, consisting of {@link Pixel}s.
 * @author Aidan Beggs
 * @author Itai Rivkin-Fish
 * @version 10/6/18
 */
public class Layer{
	
	/** The width of {@link Pixel}s in this layer. */
	private final int width;
	
	/** The height of {@link Pixel}s in this layer. */
	private final int height;
	
	/** The 2d array of {@link Pixel}s that is this layer. */
	private Pixel[][] canvas; //access as [x][y]

	/**
	 * Creates a new Layer from a given width and height, and initializes it with {@link Pixel}s.
	 * @param width the width of {@link Pixel}s in this layer.
	 * @param height the height of {@link Pixel}s in this layer.
	 * @throws IllegalArgumentException if the given width or height is an illegal value.
	 */
	public Layer(int width, int height) {
		if(width < 0) {
			throw new IllegalArgumentException("Width cannot be less than or equal to 0");
		}
		
		if(height < 0) {
			throw new IllegalArgumentException("Height cannot be less than or equal to 0");
		}
		
		this.width = width;
		this.height = height;
		this.canvas = new Pixel[width][height];
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				this.canvas[x][y] = new Pixel(0, 0, 0, 0);
			}
		}
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Pixel getPixelAt(int x, int y) {
		return this.canvas[x][y];
	}
	
	public void blendSquareAt(int originX, int originY, int width, int height, int red, int green, int blue, int alpha) {
		Pixel squarePixel = new Pixel(red, green, blue, alpha);
		for(int y = originY; y < originY + height; y++) {
			for(int x = originX; x < originX + width; x++) {
				if(x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
					Pixel currPixel = getPixelAt(x, y);
					Pixel newPixel = squarePixel.blendOver(currPixel);
					currPixel.becomeCopyOf(newPixel);
				}
			}
		}
	}
}
