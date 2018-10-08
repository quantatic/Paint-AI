package com.paint.www.image;

import java.util.ArrayList;
import java.util.List;

public class Image{
	
	private List<Layer> layerList; //upper-most layer is at end of list
	private int width, height;
	
	private static final int BACKGROUND_CHECKERBOARD_SQUARE_SIZE = 5;
	private static final Pixel BACKGROUND_CHECKERBOARD_WHITE_COLOR = new Pixel(255, 255, 255, 255);
	private static final Pixel BACKGROUND_CHECKERBOARD_GRAY_COLOR = new Pixel(200, 200, 200, 255);
	
	public Image(int width, int height) {
		this.layerList = new ArrayList<Layer>();
	
		Layer bottomLayer = new Layer(width, height);
		for(int y = 0; y < bottomLayer.getHeight(); y++) {
			for(int x = 0; x < bottomLayer.getWidth(); x++) {
				if(((x / BACKGROUND_CHECKERBOARD_SQUARE_SIZE) + (y / BACKGROUND_CHECKERBOARD_SQUARE_SIZE)) % 2 == 0) {
					bottomLayer.getPixelAt(x, y).becomeCopyOf(BACKGROUND_CHECKERBOARD_WHITE_COLOR);
				} else {
					bottomLayer.getPixelAt(x, y).becomeCopyOf(BACKGROUND_CHECKERBOARD_GRAY_COLOR);
				}
			}
		}
		
		this.layerList.add(bottomLayer);
		this.width = width;
		this.height = height;
	}
	/**
	 * Adds a {@link Layer} as the upper-most {@link Layer}
	 * @param addLayer {@link Layer} to add
	 */
	public void addLayer(Layer addLayer) {
		addLayerAt(getNumberLayers(), addLayer);
	}
	/**
	 * Adds a {@link Layer} at the specified index
	 * @param index index to add at
	 * @param addLayer {@link Layer} to be added
	 */
	public void addLayerAt(int index, Layer addLayer) {
		if(index > layerList.size()) {
			throw new IllegalArgumentException("Index is bigger than the layer size");
		}
		layerList.add(index, addLayer);
	}
	/**
	 * This function removes a {@link Layer} at a specific index
	 * @param index index to remove at
	 */
	public void removeLayerAt(int index) {
		if(index >= layerList.size()) {
			throw new IllegalArgumentException("Index is bigger than the layer size");
		}
		layerList.remove(index);
	}
	
	/**
	 * 
	 * @param layerIndex index of {@link Layer} to find
	 * @return {@link Layer} at the specified index
	 */
	public Layer getLayerAt(int layerIndex) {
		return layerList.get(layerIndex);
	}
	
	public int getNumberLayers() {
		return layerList.size();
	}

	
	public Pixel getPixelAt(int x, int y) {
		if(x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("Cannot get pixel at (" + x + ", " + y + ")"
					+ " in Image with dimensions " + getWidth() + "x" + getHeight());
		}
		
		Pixel resultPixel = new Pixel();
		for(Layer thisLayer : layerList) {
			if(x < thisLayer.getWidth() && y < thisLayer.getHeight()) {
				
				Pixel layerPixel = thisLayer.getPixelAt(x, y);
				resultPixel = layerPixel.blendOver(resultPixel);
			}
		}
		
		return resultPixel;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
