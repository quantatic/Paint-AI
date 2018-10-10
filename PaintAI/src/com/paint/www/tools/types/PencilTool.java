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
public class PencilTool extends Tool{
	private Pixel drawPixel;
	
	public PencilTool(ToolShape toolShape, Pixel drawPixel) {
		super(toolShape);
		this.drawPixel = drawPixel;
	}

	@Override
	public void useTool(int mouseX, int mouseY, double scale, Layer layer) {
		List<Pixel> toDrawOn = getToolShape().getPixels(mouseX, mouseY, scale, layer);
		for(Pixel p : toDrawOn) {
			Pixel newPixel = drawPixel.blendOver(p);
			p.becomeCopyOf(newPixel);
		}
	}
}
