package com.paint.www.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.paint.www.image.Image;
import com.paint.www.image.Layer;
import com.paint.www.image.Pixel;

/**
 * Class with helper methods to enable writing an image to a file.
 * @author Aidan Beggs
 */
public class ImageWriter {
	
	/**
	 * Attempts to write the given image to the given file.
	 * @param image the image to write to the given file.
	 * @param path the file to write the given image to.
	 */
	public static void writeImage(Image image, String path) {
		if(image == null) {
			throw new IllegalArgumentException("Given image cannot be null");
		}
		
		if(path == null) {
			throw new IllegalArgumentException("Given path cannot be null");
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			writer.write("P6");
			writer.newLine();
			writer.write(image.getWidth() + " " + image.getHeight());
			
			for(int y = 0; y < image.getHeight(); y++) {
				for(int x = 0; x < image.getWidth(); x++) {
					Pixel thisPixel = image.getPixelAt(x, y);
					double alphaModifier = thisPixel.getAlpha() / 255.0;
					writer.write((int)(thisPixel.getRed() * alphaModifier));
					writer.write((int)(thisPixel.getGreen() * alphaModifier));
					writer.write((int)(thisPixel.getBlue() * alphaModifier));
				}
			}
			
			writer.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Error opening/writing the given file");
		}	
	}
}
