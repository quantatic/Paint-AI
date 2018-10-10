package com.paint.www.tools;

import java.util.List;
import java.awt.Shape;

import com.paint.www.image.BoundingBox;
import com.paint.www.image.Layer;
import com.paint.www.image.Pixel;

public interface ToolShape {
	public Shape getCursor(int x, int y, double scale);
	
	public List<Pixel> getPixels(int x, int y, double scale, Layer layer);
	
	public BoundingBox getBoundingBox(int x, int y);
}
