package com.paint.www.io;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.paint.www.image.Layer;
import com.paint.www.image.Pixel;

public class ImageReader {
	
	/**
	 * This function converts an image file into a layer to be loaded
	 * @param path Path of file to load
	 * @return {@link Layer} made from loaded file
	 */
	public static Layer loadImageIntoLayer(String path) {
		return bufferedImageToLayer(readImage(path));
	}
	/**
	 * This function converts a bufferedImageinto a Layer
	 * @param image {@link BufferedImage} to convert
	 * @return{@link Layer} made from {@link BufferedImage}
	 */
	public static Layer bufferedImageToLayer(BufferedImage image) {
		Layer newLayer = new Layer(image.getWidth(), image.getHeight());
		for(int x = 0; x < image.getWidth(); x++) {
			for(int y = 0; y < image.getHeight(); y++) {
				Color rgba = new Color(image.getRGB(x, y));
				Pixel newPixel = new Pixel(rgba.getRed(), rgba.getGreen(), rgba.getBlue(), rgba.getAlpha());
				newLayer.getPixelAt(x, y).becomeCopyOf(newPixel);
			}
		}

		
		return newLayer;
	}
	/**
	 * This function converts a file at a path to a {@link BufferedImage}
	 * @param path Path of file to convert
	 * @return {@link BufferedImage} of file
	 */
	public static BufferedImage readImage(String path) {
		if(path == null) {
			throw new IllegalArgumentException("Path can not be null");
		}
		try {
			BufferedImage image = ImageIO.read(new File(path));
			return image;
		} catch (IOException e) {
			throw new IllegalArgumentException("unable to find image file in " + path);
		}
		
	}
}
