package com.paint.www.image;

public class Pixel {
	private int colorValues;
	
	public Pixel(char red, char green, char blue, char alpha) {
		setRed(red);
		setGreen(green);
		setBlue(blue);
		setAlpha(alpha);
	}

	public void setAlpha(char alpha) {
		if(alpha < 0 || alpha > 255) {
			throw new IllegalArgumentException("Illegal Alpha Value");
		}
		colorValues = (colorValues & 0xFF_FF_FF_00) | (alpha);
	}

	public void setBlue(char blue) {
		if(blue < 0 || blue > 255) {
			throw new IllegalArgumentException("Illegal Blue Value");
		}
		colorValues = (colorValues & 0xFF_FF_00_FF) | (blue << 8);
	}

	public void setGreen(char green) {
		if(green < 0 || green > 255) {
			throw new IllegalArgumentException("Illegal Green Value");
		}
		colorValues = (colorValues & 0xFF_00_FF_FF) | (green << 16);
	}

	public void setRed(char red) {
		if(red < 0 || red > 255) {
			throw new IllegalArgumentException("Illegal Red Value");
		}
		colorValues = (colorValues & 0x00_FF_FF_FF) | (red << 24);
	}
	
	public char getRed() {
		return  (char) ((colorValues >> 24) & 0xFF);
	}
	public char getGreen() {
		return (char) ((colorValues >> 16) & 0xFF);
	}
	public char getBlue() {
		return (char) ((colorValues >> 8) & 0xFF);
	}
	public char getAlpha() {
		return (char) (colorValues & 0xFF);
	}
}
