package com.paint.www.image;

public class PencilTool extends Tool{
	
	private double radius;
	private Pixel drawPixel;
	
	public PencilTool(Pixel drawPixel, double radius) {
		this.drawPixel = drawPixel;
		this.radius = radius;
	}
	@Override
	public void useTool(int mouseX, int mouseY, Layer layer) {
		for(int x = (int) -radius; x <= radius; x++) {
			for(int y = (int) -radius; y <= radius; y++) {
				boolean isWithinRadius = (Math.pow(x-mouseX,2) + Math.pow(y-mouseY, 2) <= Math.pow(radius, 2));
				if(isWithinRadius && layer.isLegalCoordinate(x, y)) {
					layer.getPixelAt(x, y).becomeCopyOf(drawPixel);
				}
			}
		}
	}

}
