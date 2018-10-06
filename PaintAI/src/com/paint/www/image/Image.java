package com.paint.www.image;

import java.util.ArrayList;
import java.util.List;

public class Image {
	private List<Layer> layerList;
	
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
	}
	/**
	 * This function merges all the layers from the top down in order to create a single layer that is what will be displayed
	 * @return {@link Layer} merged Layer
	 */
	public Layer getRenderImage() {
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
	 * @return {@link Layer} at the specificed index
	 */
	public Layer getLayerAt(int layerIndex) {
		return layerList.get(layerIndex);
	}
}
