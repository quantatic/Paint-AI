package com.paint.www.tools;

import java.awt.Shape;
import java.util.List;

import com.paint.www.image.BoundingBox;
import com.paint.www.image.Layer;
import com.paint.www.image.Pixel;

public abstract class Tool {
	
	private ToolShape toolShape;
	
	public Tool(ToolShape toolShape) {
		this.toolShape = toolShape;
	}
	
	public abstract void useTool(int mouseX, int mouseY, double scale, Layer layer);
	
	public Shape getCursor(int x, int y, double scale) {
		return toolShape.getCursor(x, y, scale);
	}
	
	public BoundingBox getBoundingBox(int x, int y) {
		return this.toolShape.getBoundingBox(x, y);
	}
	
	public ToolShape getToolShape() {
		return this.toolShape;
	}
}
