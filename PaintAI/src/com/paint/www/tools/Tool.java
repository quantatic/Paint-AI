package com.paint.www.tools;

import java.awt.Shape;

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
	 * @param scale Scale of image when getting bounding box
	 * @return {@link BoundingBox}
	 */
	public abstract BoundingBox getBoundingBox(int mouseX, int mouseY, double scale);
	
	public BoundingBox getBoundingBox(int mouseX, int mouseY) {
		return getBoundingBox(mouseX, mouseY, 1.0);
	}

	/**
	 * Gets a {@link Shape} that represents this tool's cursor with the given mouse x/y.
	 * @param mouseX the mouse's x position to create this cursor at.
	 * @param mouseY the mouse's y position to create this cursor at.
	 * @return a {@link Shape} representing this Tool's cursor.
	 */
	public Shape getCursor(int mouseX, int mouseY) {
		return getCursor(mouseX, mouseY, 1.0);
	}
	/**
	 * Gets a {@link Shape} that represents this tool's cursor with the given mouse x/y.
	 * @param mouseX the mouse's x position to create this cursor at.
	 * @param mouseY the mouse's y position to create this cursor at.
	 * @param scale the scale of the image's zoom
	 * @return a {@link Shape} representing this Tool's cursor.
	 */
	public abstract Shape getCursor(int mouseX, int mouseY, double scale);
	
}
