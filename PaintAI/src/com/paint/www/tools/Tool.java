package com.paint.www.tools;

import com.paint.www.image.BoundingBox;
import com.paint.www.image.Layer;

public abstract class Tool {
	/**
	 * This function uses a tool on a {@link Layer}
	 * @param mouseX X position to use the tool on
	 * @param mouseY Y position to use a tool on
	 * @param layer {@link Layer} to use the tool on
	 */
	public abstract void useTool(int mouseX, int mouseY, Layer layer);
	/**
	 * This function gets a {@link BoundingBox} about a point
	 * @param mouseX X point to get the {@link BoundingBox} about
	 * @param mouseY Y point to get the {@link BoundingBox} about
	 * @return {@link BoundingBox}
	 */
	public abstract BoundingBox getBoundingBox(int mouseX, int mouseY);
}
