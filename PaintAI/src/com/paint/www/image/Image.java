package com.paint.www.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Image implements Observer{
	private List<Layer> layerList;
	private Layer currentImage;
	
	public Image() {
		this(null);
	}
	
	public Image(List<Layer> layerList) {
		if(layerList == null) {
			this.layerList = new ArrayList<Layer>();
			this.layerList.add(new Layer(0, 0));
		}else {
			this.layerList = layerList;
		}
		
		for(Layer l : this.layerList) {
			l.addObserver(this);
		}
		this.currentImage = this.getRenderImage();
	}
	/**
	 * This function merges all the layers from the top down in order to create a single layer that is what will be displayed
	 * @return {@link Layer} merged Layer
	 */
	private Layer getRenderImage() {
		Layer finalLayer = new Layer(0, 0);
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
