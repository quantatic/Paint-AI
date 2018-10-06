package com.paint.www.image;

import static org.junit.Assert.*;

import org.junit.Test;
import com.paint.www.image.*;

public class PixelTests {

	@Test
	public void test() {
		Pixel whitePixel = new Pixel(0, 0, 0, 255);
		Pixel blackPixel = new Pixel(255, 255, 255, 255);
		Pixel semiOpaqueRed = new Pixel(255, 0, 0, 128); 
		Pixel semiOpaqueBlue = new Pixel(0, 0, 255, 128);

		assertEquals(whitePixel, whitePixel.blendOver(blackPixel));
		assertEquals(blackPixel, blackPixel.blendOver(whitePixel));
		System.out.println(semiOpaqueRed.blendOver(semiOpaqueBlue));
		
	}

}
