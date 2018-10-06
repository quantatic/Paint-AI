package com.paint.www.image;

public class Pixel {
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

	public void setAlpha(int alpha) {
		if(alpha < 0 || alpha > 255) {
			throw new IllegalArgumentException("Illegal Alpha Value");
		}
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
}
