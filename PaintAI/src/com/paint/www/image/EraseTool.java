package com.paint.www.image;
/**
 * Represents a {@link Tool} that is a Pencil, drawing in a circle.
 * @author Itai Rivkin-Fish
 * @version 10/8/18
 */
public class EraseTool extends Tool{
	private double radius;
	
	public EraseTool(double radius) {
		this.radius = radius;
	}
	@Override
	public void useTool(int mouseX, int mouseY, Layer layer) {
		int newX, newY;
		Pixel alteredPixel, currentPixel;
		boolean isWithinRadius;
		double distanceSquared;
		double radiusSquared = Math.pow(radius, 2);
		int newAlpha;
		
		for(int x = (int) -radius; x <= radius; x++) {
			for(int y = (int) -radius; y <= radius; y++) {
				
				newX = mouseX + x;
				newY = mouseY + y;
				
				distanceSquared = (Math.pow(newX-mouseX,2) + Math.pow(newY-mouseY, 2));
				isWithinRadius =  distanceSquared <= radiusSquared;
				
				if(isWithinRadius && layer.isLegalCoordinate(newX, newY)) {
					currentPixel = layer.getPixelAt(newX, newY);
					newAlpha = (int) (currentPixel.getAlpha() * ((distanceSquared / radiusSquared)));
					alteredPixel = new Pixel(currentPixel.getRed(), currentPixel.getGreen(), currentPixel.getBlue(), newAlpha);
					layer.getPixelAt(newX, newY).becomeCopyOf(alteredPixel);
				}
			}
		}
	}

}
