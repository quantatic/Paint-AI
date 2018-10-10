package com.paint.www.tools.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import com.paint.www.image.BoundingBox;
import com.paint.www.image.Layer;
import com.paint.www.image.Pixel;
import com.paint.www.tools.ToolShape;

public class CircleTool implements ToolShape{
	private double radius;

	public CircleTool(double radius) {
		this.radius = radius;
	}
	
	@Override
	public Shape getCursor(int x, int y, double scale) {
		double scaledRadius = this.radius * scale;
		return new Ellipse2D.Double(x - scaledRadius, y - scaledRadius, scaledRadius * 2, scaledRadius * 2);
	}

	@Override
	public List<Pixel> getPixels(int x, int y, double scale, Layer layer) {
		List<Pixel> result = new ArrayList<>();
		int newX, newY;
		double distanceSquared;
		double radiusSquared = Math.pow(radius, 2);
		
		for(int xx = (int) -radius; xx <= radius; xx++) {
			for(int yy = (int) -radius; yy <= radius; yy++) {
				newX = x + xx;
				newY = y + yy;
				
				distanceSquared = (Math.pow(newX-x,2) + Math.pow(newY-y, 2));
				
				if(distanceSquared <= radiusSquared && layer.isLegalCoordinate(newX, newY)) {
					result.add(layer.getPixelAt(newX, newY));
				}
			}
		}
		
		return result;
	}

	@Override
	public BoundingBox getBoundingBox(int x, int y) {
		return new BoundingBox((int)(x - this.radius), (int)(y - this.radius), (int)(this.radius * 2), (int)(this.radius * 2));
	}
}
