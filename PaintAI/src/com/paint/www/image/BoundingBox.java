package com.paint.www.image;

/**
 * This class represents a 'bounding box', used to show an area
 * @author Itai Rivkin-Fish
 * @version 10/8/18
 * 
 */
public class BoundingBox {
	private int x, y, width, height;
	
	public BoundingBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
