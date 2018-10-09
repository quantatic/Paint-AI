package com.paint.www.image;

/**
 * This class holds data on individual pixels, storing their RGB and Alpha
 * @author Itai Rivkin-Fish
 * @author Aidan Beggs
 * @version 10/6/18
 * 
 */
public class Pixel{
	/* colorValues is a single int that holds the Red,Green,Blue and Alpha of the Pixel
	 * Alpha	Red		 Green	  Blue	  	
	 * AAAAAAAA_RRRRRRRR_GGGGGGGG_BBBBBBBB
	 * <- Most significant bit
	 * 			   Least significant bit->
	 */
	private int colorValues;
	
	public Pixel(int red, int green, int blue, int alpha) {
		setRed(red);
		setGreen(green);
		setBlue(blue);
		setAlpha(alpha);
	}
	
	public Pixel(int red, int green, int blue) {
		this(red, green, blue, 255);
	}
	
	public Pixel() {
		this(0, 0, 0, 0);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colorValues;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pixel other = (Pixel) obj;
		if (colorValues != other.colorValues)
			return false;
		return true;
	}

	
	/**
	 * Setter for the Alpha value
	 * @param alpha value between 0-255 to set the alpha as
	 * @throws IllegalArgumentException if alpha is not between 0-255
	 */
	private void setAlpha(int alpha) {
		if(alpha < 0 || alpha > 255) {
			throw new IllegalArgumentException("Illegal Alpha Value");
		}
		/*
		 * Alpha	Red 	 Green    Blue	  
		 * 00000000_11111111_11111111_11111111
		 * &
		 * XXXXXXXX_XXXXXXXX_XXXXXXXX_XXXXXXXX
		 * =
		 * 00000000_XXXXXXXX_XXXXXXXX_XXXXXXXX
		 * First we clear the value
		 * then
		 * 00000000_XXXXXXXX_XXXXXXXX_XXXXXXXX
		 * |
		 * AAAAAAAA_00000000_00000000_00000000
		 * =
		 * AAAAAAAA_XXXXXXXX_XXXXXXXX_XXXXXXXX
		 * set the alpha
		 */
		colorValues = (colorValues & 0x00_FF_FF_FF) | (alpha << 24);
	}

	private void setBlue(int blue) {
		if(blue < 0 || blue > 255) {
			throw new IllegalArgumentException("Illegal Blue Value");
		}
		colorValues = (colorValues & 0xFF_FF_FF_00) | (blue);
	}

	private void setGreen(int green) {
		if(green < 0 || green > 255) {
			throw new IllegalArgumentException("Illegal Green Value");
		}
		colorValues = (colorValues & 0xFF_FF_00_FF) | (green << 8);
	}

	private void setRed(int red) {
		if(red < 0 || red > 255) {
			throw new IllegalArgumentException("Illegal Red Value");
		}
		colorValues = (colorValues & 0xFF_00_FF_FF) | (red << 16);
	}
	
	public int getRed() {
		return (colorValues >> 16) & 0xFF;
	}
	
	public int getGreen() {
		return (colorValues >> 8) & 0xFF;
	}
	
	public int getBlue() {
		return  colorValues & 0xFF;
	}
	
	public int getAlpha() {
		return (colorValues >> 24) & 0xFF;
	}
	public int getARGB() {
		return colorValues;
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
		double selfOpacity = getAlpha() / 255.0;
		double otherOpacity = lowerPixel.getAlpha() / 255.0; 
		int red, green, blue, newOpacity;
		
		red = calculateBlendedChannel(getRed(), selfOpacity, lowerPixel.getRed(), otherOpacity);
		green = calculateBlendedChannel(getGreen(), selfOpacity, lowerPixel.getGreen(), otherOpacity);
		blue = calculateBlendedChannel(getBlue(), selfOpacity, lowerPixel.getBlue(), otherOpacity);
		newOpacity = (int)(255 * calculateBlendedOpacity(selfOpacity, otherOpacity));
		return new Pixel(red, green, blue, newOpacity);
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
	private int calculateBlendedChannel(int colorTop, double alphaTop, int colorBottom, double alphaBottom) {
		return (int)(((colorTop * alphaTop) + (colorBottom * alphaBottom) * (1 - alphaTop)) 
				/ (alphaTop + (alphaBottom * (1 - alphaTop))));
	}
	/**
	 * This function returns a new alpha value after painting one color on another
	 * @param alphaTop the alpha value (0.0-1.0) of the top pixel
	 * @param alphaBottom the alpha value (0.0-1.0) of the bottom pixel
	 * @return new combined alpha value
	 */
	private double calculateBlendedOpacity(double alphaTop, double alphaBottom) {
		return alphaTop + alphaBottom * (1 - alphaTop);
	}
	/**
	 * This function converts this pixel to a copy of the other pixel
	 * @param toCopy pixel to become copy of
	 */
	public void becomeCopyOf(Pixel toCopy) {
		setRed(toCopy.getRed());
		setBlue(toCopy.getBlue());
		setGreen(toCopy.getGreen());
		setAlpha(toCopy.getAlpha());
	}
	
	@Override
	public String toString() {
		return "Pixel[ colorValues = " + getRed() + ", " + getGreen() + ", " + getBlue() + ", Opacity: " + getAlpha() + "]";
	}
	
}
