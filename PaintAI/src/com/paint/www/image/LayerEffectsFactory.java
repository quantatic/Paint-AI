package com.paint.www.image;

/**
 * 
 * @author Itai Rivkin-Fish
 * @version 10/6/18
 * 
 */
public class LayerEffectsFactory {
	
	
	/**
	 * This function returns a new, scaled {@link Layer} at the input dimensions
	 * @param l {@link Layer} to scale
	 * @param newWidth width of new {@link Layer}
	 * @param newHeight height of new {@link Layer}
	 * @return {@link Layer} with new size
	 */
	public static Layer getScaledLayer(Layer l, int newWidth, int newHeight){
		Layer scaledLayer = new Layer(newWidth, newHeight);
		for(int x = 0; x < newWidth; x++){
			for(int y = 0; y < newHeight; y++){
				int sourceX = (int) Math.round((double) x / newWidth * l.getWidth());
				sourceX = Math.min(sourceX, l.getWidth() - 1);
				
				int sourceY = (int) Math.round((double) y / newHeight * l.getHeight());
				sourceY = Math.min(sourceY, l.getHeight() - 1);
				
				scaledLayer.getPixelAt(x, y).becomeCopyOf(l.getPixelAt(sourceX, sourceY));
			}
		}
		return scaledLayer;
	}
	
	/**
	 * This function creates a vertical gradient
	 * @param width Width of gradient 
	 * @param height Height of gradient
	 * @param topPixel {@link Pixel} at top of gradient
	 * @param bottomPixel {@link Pixel} at bottom of gradient
	 * @param alpha Alpha value of the gradient
	 * @return {@link Layer} {@link Layer} with new gradient changes
	 */
	public static Layer createVerticalGradient(int width, int height, Pixel topPixel, Pixel bottomPixel,int alpha) {
		return createFullGradient(width, height, topPixel, topPixel, bottomPixel, bottomPixel, alpha);
	}
	/**
	 * This function creates a horizontal gradient
	 * @param width Width of gradient 
	 * @param height Height of gradient
	 * @param leftPixel {@link Pixel} at left of gradient
	 * @param rightPixel {@link Pixel} at right of gradient
	 * @param alpha Alpha value of the gradient
	 * @return {@link Layer} {@link Layer} with new gradient changes
	 */
	public static Layer createHorizontalGradient(int width, int height, Pixel leftPixel, Pixel rightPixel, int alpha) {
		return createFullGradient(width, height, leftPixel, rightPixel, rightPixel, leftPixel, alpha);
	}
	/**
	 * This function creates a 4 color gradient
	 * @param width Width of gradient 
	 * @param height Height of gradient
	 * @param upperLeft {@link Pixel} at upper-left of gradient
	 * @param upperRight {@link Pixel} at upper-right of gradient
	 * @param lowerRight {@link Pixel} at lower-right of gradient
	 * @param lowerLeft {@link Pixel} at lower-left of gradient
	 * @param alpha Alpha value of the gradient
	 * @return {@link Layer} {@link Layer} with new gradient changes
	 */
	public static Layer createFullGradient(int width, int height, Pixel upperLeft, Pixel upperRight, Pixel lowerRight, Pixel lowerLeft, int alpha ) {
		if(upperLeft == null || upperRight == null || lowerLeft == null || lowerRight == null) {
			throw new IllegalArgumentException("No null pixels");
		}
		Layer gradient = new Layer(width, height);
		Pixel newPixel, leftPixel, rightPixel;
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				double yBlendFactor = ((double)y/(height-1));
				double xBlendFactor = ((double)x/(width-1));
				leftPixel = blendPixelColors(upperLeft, lowerLeft, yBlendFactor, alpha);
				rightPixel = blendPixelColors(upperRight, lowerRight, yBlendFactor, alpha);
				newPixel = blendPixelColors(leftPixel, rightPixel, xBlendFactor, alpha);
				gradient.getPixelAt(x, y).becomeCopyOf(newPixel);
			}
		}
		
		return gradient;
	}
	/**
	 * This function blends the colors ONLY of two pixels by a factor, 0 means no changes, 1 means replace
	 * @param mainPixel {@link Pixel} to be blended with
	 * @param toBlendWith {@link Pixel} to blend with
	 * @param factor Value between 0-1 to blend
	 * @return {@link Pixel} with blend
	 */
	private static Pixel blendPixelColors(Pixel mainPixel, Pixel toBlendWith, double factor, int alpha) {
		
		double diff_red = toBlendWith.getRed() - mainPixel.getRed();
		double diff_green = toBlendWith.getGreen() - mainPixel.getGreen();
		double diff_blue = toBlendWith.getBlue() - mainPixel.getBlue();
		
		int newRed = (int) (mainPixel.getRed()+(diff_red * factor));
		int newGreen = (int) (mainPixel.getGreen()+(diff_green * factor));
		int newBlue = (int) (mainPixel.getBlue()+(diff_blue * factor));
		return new Pixel(newRed, newGreen, newBlue, alpha);
	}
}
