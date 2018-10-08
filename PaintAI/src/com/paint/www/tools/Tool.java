package com.paint.www.tools;

import com.paint.www.image.BoundingBox;
import com.paint.www.image.Layer;

public abstract class Tool {
	
	public abstract void useTool(int mouseX, int mouseY, Layer layer);
	
	public abstract BoundingBox getBoundingBox(int mouseX, int mouseY);
}
