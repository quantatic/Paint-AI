package com.paint.www.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Image implements Observer{
	
	private List<Layer> layerList;
	private Layer currentImage;
	
	
	public Image() {
		this(0,0);
	}
	
	public Image(int width, int height) {
		this.layerList = new ArrayList<Layer>();
		this.layerList.add(new Layer(width, height, 255));
		this.currentImage = this.getRenderImage();
	}
	/**
	 * Adds a {@link Layer} as the upper-most {@link Layer}
	 * @param addLayer {@link Layer} to add
	 */
	public void addLayer(Layer addLayer) {
		addLayerAt(0, addLayer);
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
		this.currentImage = this.getRenderImage();
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
		this.currentImage = this.getRenderImage();
	}
	
	/**
	 * This function merges all the layers from the top down in order to create a single layer that is what will be displayed
	 * @return {@link Layer} merged Layer
	 */
	private Layer getRenderImage() {
		Layer finalLayer = new Layer(0, 0, 0);
		for(Layer currentLayer : layerList) {
			finalLayer = finalLayer.mergeOver(currentLayer);
		}
		return finalLayer;
	}
	/**
	 * 
	 * @return {@link Layer} at the top
	 */
	public Layer getTopLayer() {
		return getLayerAt(0);
	}
	/**
	 * 
	 * @return {@link Layer} at the bottom 
	 */
	public Layer getBottomLayer() {
		return getLayerAt(layerList.size() - 1);
	}
	/**
	 * 
	 * @param layerIndex index of {@link Layer} to find
	 * @return {@link Layer} at the specified index
	 */
	public Layer getLayerAt(int layerIndex) {
		return layerList.get(layerIndex);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.currentImage = this.getRenderImage();
	}
	
	public Pixel getPixelAt(int x, int y) {
		return currentImage.getPixelAt(x, y);
	}
	
	public int getWidth() {
		return currentImage.getWidth();
	}
	
	public int getHeight() {
		return currentImage.getHeight();
	}
}
