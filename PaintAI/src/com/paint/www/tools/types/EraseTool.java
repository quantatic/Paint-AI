package com.paint.www.tools.types;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.List;

import com.paint.www.image.BoundingBox;
import com.paint.www.image.Layer;
import com.paint.www.image.Pixel;
import com.paint.www.tools.Tool;
import com.paint.www.tools.ToolShape;

/**
 * Represents a {@link Tool} that is a Pencil, drawing in a circle.
 * @author Itai Rivkin-Fish
 * @version 10/8/18
 */
public class EraseTool extends Tool{
	
	public EraseTool(ToolShape toolShape) {
		super(toolShape);
	}
	
	@Override
	public void useTool(int mouseX, int mouseY, double scale, Layer layer) {
		List<Pixel> toErase = getToolShape().getPixels(mouseX, mouseY, scale, layer);
		Pixel erasedPixel = new Pixel(0, 0, 0, 0);
		for(Pixel p : toErase) {
			p.becomeCopyOf(erasedPixel);
		}
	}
}
