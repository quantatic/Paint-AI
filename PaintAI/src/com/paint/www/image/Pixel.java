package com.paint.www.image;
/**
 * This class holds data on individual pixels, storing their RGB and Alpha
 * @author Itai Rivkin-Fish
 * @author Aidan Beggs
 * @version 10/6/18
 * 
 */
public class Pixel {
	/* colorValues is a single int that holds the Red,Green,Blue and Alpha of the Pixel
	 * Red		Green	 Blue	  Alpha	
	 * RRRRRRRR_GGGGGGGG_BBBBBBBB_AAAAAAAA
	 * <- Most significant bit
	 * 			   Least significant bit->
	 */
	private int colorValues;
	
	public Pixel() {
		this(0, 0, 0, 0);
	}
	
	public Pixel(int red, int green, int blue, int alpha) {
		setRed(red);
		setGreen(green);
		setBlue(blue);
		setAlpha(alpha);
	}
	/**
	 * Setter for the Alpha value
	 * @param alpha value between 0-255 to set the alpha as
	 * @throws IllegalArgumentException if alpha is not between 0-255
	 */
	public void setAlpha(int alpha) {
		if(alpha < 0 || alpha > 255) {
			throw new IllegalArgumentException("Illegal Alpha Value");
		}
		/*
		 * Red		Green	 Blue	  Alpha	
		 * 11111111_11111111_11111111_00000000
		 * &
		 * XXXXXXXX_XXXXXXXX_XXXXXXXX_XXXXXXXX
		 * =
		 * XXXXXXXX_XXXXXXXX_XXXXXXXX_00000000
		 * First we clear the value
		 * then
		 * XXXXXXXX_XXXXXXXX_XXXXXXXX_00000000
		 * |
		 * 00000000_00000000_00000000_AAAAAAAA
		 * =
		 * XXXXXXXX_XXXXXXXX_XXXXXXXX_AAAAAAAA
		 * set the alpha
		 */
		colorValues = (colorValues & 0xFF_FF_FF_00) | (alpha);
	}

	public void setBlue(int blue) {
		if(blue < 0 || blue > 255) {
			throw new IllegalArgumentException("Illegal Blue Value");
		}
		colorValues = (colorValues & 0xFF_FF_00_FF) | (blue << 8);
	}

	public void setGreen(int green) {
		if(green < 0 || green > 255) {
			throw new IllegalArgumentException("Illegal Green Value");
		}
		colorValues = (colorValues & 0xFF_00_FF_FF) | (green << 16);
	}

	public void setRed(int red) {
		if(red < 0 || red > 255) {
			throw new IllegalArgumentException("Illegal Red Value");
		}
		colorValues = (colorValues & 0x00_FF_FF_FF) | (red << 24);
	}
	
	public int getRed() {
		return (colorValues >> 24) & 0xFF;
	}
	
	public int getGreen() {
		return (colorValues >> 16) & 0xFF;
	}
	
	public int getBlue() {
		return  (colorValues >> 8) & 0xFF;
	}
	
	public int getAlpha() {
		return colorValues & 0xFF;
	}
	/**
	 * https://en.wikipedia.org/wiki/Alpha_compositing
	 * This function blends this {@link Pixel} over the argument {@link Pixel}
	 * @param lowerPixel {@link Pixel} to blend over 
	 * @return new {@link Pixel} object with the blend
	 */
	public Pixel blendOver(Pixel lowerPixel) {
		if(lowerPixel == null) {
			throw new IllegalArgumentException("lowerPixel can not be null");
		}
		double selfOpacity = getAlpha()/255.0;
		double otherOpacity = lowerPixel.getAlpha()/255.0; 
		int red,green,blue,newOpacity;
		
		red = calculateBlendedChannel(getRed(),lowerPixel.getRed(),selfOpacity,otherOpacity);
		green = calculateBlendedChannel(getGreen(),lowerPixel.getGreen(),selfOpacity,otherOpacity);
		blue = calculateBlendedChannel(getBlue(),lowerPixel.getBlue(),selfOpacity,otherOpacity);
		newOpacity = (int) (255*calculateBlendedOpacity(selfOpacity,otherOpacity));
		return new Pixel(red,green,blue,newOpacity);
	}
	/**
	 * This function returns a new color channel value after painting one color on another
	 * https://en.wikipedia.org/wiki/Alpha_compositing
	 * @param colorTop the color value (0-255) of the top pixel
	 * @param colorBottom the color value (0-255) of the bottom pixel
	 * @param alphaTop the alpha value (0.0-1.0) of the top pixel
	 * @param alphaBottom the alpha value (0.0-1.0) of the bottom pixel
	 * @return new combined color value
	 */
	private int calculateBlendedChannel(int colorTop, int colorBottom, double alphaTop, double alphaBottom) {
		return (int) (((colorTop*alphaTop) + (colorBottom*alphaBottom)*(1-alphaTop))/(alphaTop + (alphaBottom*(1-alphaTop))));
	}
	/**
	 * This function returns a new alpha value after painting one color on another
	 * @param alphaTop the alpha value (0.0-1.0) of the top pixel
	 * @param alphaBottom the alpha value (0.0-1.0) of the bottom pixel
	 * @return new combined alpha value
	 */
	private double calculateBlendedOpacity(double alphaTop, double alphaBottom) {
		return alphaTop + alphaBottom*(1-alphaTop);
	}
	
}
