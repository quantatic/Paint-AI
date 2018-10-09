package com.paint.www.tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import com.paint.www.image.BoundingBox;

public abstract class CircleTool extends Tool{

	public Shape getCursor(int mouseX, int mouseY) {
		return getCursor(mouseX, mouseY, 1.0);
	}
	
	public Shape getCursor(int mouseX, int mouseY, double scale) {
		BoundingBox cursorBox = getBoundingBox(mouseX, mouseY, scale);
		return new Ellipse2D.Double(cursorBox.getX(), cursorBox.getY(), cursorBox.getWidth(), cursorBox.getHeight());
	}
}
