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
		
		if(path.lastIndexOf(".") == -1 || !path.substring(path.lastIndexOf(".")).equals(".ppm")) {
			throw new IllegalArgumentException("Given filename must have an extension of .ppm");
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			writer.write("P3");
			writer.write('\n');
			writer.write(image.getWidth() + " " + image.getHeight());
			writer.write('\n');
			writer.write("255");
			writer.write('\n');
			
			for(int y = 0; y < image.getHeight(); y++) {
				for(int x = 0; x < image.getWidth(); x++) {
					Pixel thisPixel = image.getPixelAt(x, y);
					
					writer.write(Integer.toString(thisPixel.getRed()) + " ");
					writer.write(Integer.toString(thisPixel.getGreen()) + " ");
					writer.write(Integer.toString(thisPixel.getBlue()) + " ");
					
				}
				
				writer.write('\n');
			}
			
			writer.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Error opening/writing the given file");
		}	
	}
	
	public static void main(String[] args) {
		Image img = new Image(400, 400);
		
		Layer layer1 = new Layer(400, 400, 0);
		Layer layer2 = new Layer(400, 400, 0);
		Layer layer3 = new Layer(400, 400, 0);
		
		for(int y = 100; y < 200; y++) {
			for(int x = 100; x < 200; x++) {
				Pixel thisPixel = layer1.getPixelAt(x, y);
				Pixel overPixel = new Pixel(255, 0, 0, 255);
				thisPixel.becomeCopyOf(overPixel.blendOver(thisPixel));
			}
		}
		
		for(int y = 150; y < 250; y++) {
			for(int x = 150; x < 250; x++) {
				Pixel thisPixel = layer2.getPixelAt(x, y);
				Pixel overPixel = new Pixel(0, 255, 0, 127);
				thisPixel.becomeCopyOf(overPixel.blendOver(thisPixel));
			}
		}
		
		for(int y = 200; y < 300; y++) {
			for(int x = 200; x < 300; x++) {
				Pixel thisPixel = layer3.getPixelAt(x, y);
				Pixel overPixel = new Pixel(0, 0, 255, 255);
				thisPixel.becomeCopyOf(overPixel.blendOver(thisPixel));
			}
		}
		
		img.addLayer(layer1);
		img.addLayer(layer2);
		img.addLayer(layer3);
		
		writeImage(img, "out.ppm");
	}
}
