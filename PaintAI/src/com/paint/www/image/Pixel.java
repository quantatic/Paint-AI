package com.paint.www.image;

public interface Pixel {
	
	
	public double getOpacity();
	public char getRed();
	public char getGreen();
	public char getBlue();
	public Pixel blend();
}
