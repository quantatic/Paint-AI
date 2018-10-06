package com.paint.www.image;

import java.util.Observable;
import java.util.Observer;

/**
 * Represents a layer of an image, consisting of {@link Pixel}s.
 * @author Aidan Beggs
 * @author Itai Rivkin-Fish
 * @version 10/6/18
 */
public class Layer extends Observable implements Observer{
	
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
	 * @param alpha Value of the alpha
	 * @throws IllegalArgumentException if the given width or height is an illegal value.
	 */
	public Layer(int width, int height, int alpha) {
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
				this.canvas[x][y] = new Pixel(255, 255, 255, alpha);
				this.canvas[x][y].addObserver(this);
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
	/**
	 * This function merges this Layer over another, lower Layer
	 * @param lowerLayer The lower Layer to merge
	 * @return new Layer with the merge
	 */
	public Layer mergeOver(Layer lowerLayer) {
		if(lowerLayer == null) {
			throw new IllegalArgumentException("lowerLayer can't be null");
		}
		
		int newWidth = Math.max(getWidth(), lowerLayer.getWidth());
		int newHeight = Math.max(getHeight(), lowerLayer.getHeight());
		Layer newLayer = new Layer(newWidth, newHeight, 0);
		Pixel lowerPixel, upperPixel, mergedPixel;
		for(int x = 0; x < newLayer.getWidth(); x++) {
			for(int y = 0; y < newLayer.getHeight(); y++) {
				if(x < lowerLayer.getWidth() && y < lowerLayer.getHeight()) {
					lowerPixel = lowerLayer.getPixelAt(x,y);
				}else {
					lowerPixel = new Pixel(0, 0, 0, 0);
				}
				
				if(x < getWidth() && y < getHeight()) {
					upperPixel = getPixelAt(x,y);
				}else {
					upperPixel = new Pixel(0, 0, 0, 0);
				}
				
				mergedPixel = upperPixel.blendOver(lowerPixel);
				
				newLayer.getPixelAt(x, y).becomeCopyOf(mergedPixel);
				
			}
		}
		return newLayer;
	}

	@Override
	public void update(Observable o, Object arg) {
		notifyObservers();
	}
}
