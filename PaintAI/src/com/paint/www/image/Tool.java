package com.paint.www.image;

public abstract class Tool {
	
	public abstract void useTool(int mouseX, int mouseY, Layer layer);
	
	public abstract BoundingBox getBoundingBox(int mouseX, int mouseY);
}
