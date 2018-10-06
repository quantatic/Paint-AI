package com.paint.www.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.paint.www.image.Image;
import com.paint.www.image.Layer;

public class ImageWriter {
	
	public static void writeImage(Image image, String path) {
		if(image == null ) {
			throw new IllegalArgumentException("Given image cannot be null");
		}
		
		if(path == null) {
			throw new IllegalArgumentException("Given path cannot be null");
		}
			
		Layer toWrite = image.getRenderImage();
		BufferedWriter writer;
		
		try {
			 writer = new BufferedWriter(new FileWriter(path));
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot open file at given path for writing");
		}
		
		Layer renderImage = image.getRenderImage();
		writer.write("P6");
		writer.newLine();
		writer.write(renderedImage = );
		
		for(int )
	}
}
