package com.paint.www.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.paint.www.image.BoundingBox;
import com.paint.www.image.Image;
import com.paint.www.image.Layer;
import com.paint.www.image.LayerEffectsFactory;
import com.paint.www.image.Pixel;
import com.paint.www.io.ImageReader;
import com.paint.www.tools.ToolBox;

public class PaintPanel extends JPanel{
	
	private static final long serialVersionUID = 7163711786488854179L;
	
	private final Image image;
	private final Layer drawLayer;
	private BufferedImage panelImage;
	private BufferedImage scaledPanelImage;
	private int mouseX, mouseY;
	private double scale;
	
	public PaintPanel(int width, int height) {
		scale = 1;
		
		drawLayer = ImageReader.loadImageIntoLayer("out.png");
		width = drawLayer.getWidth();
		height = drawLayer.getHeight();
		image = new Image(width, height);
		
		
		//drawLayer = LayerEffectsFactory.createVerticalGradient(width, height, new Pixel(255, 127, 0, 255), new Pixel(0, 127, 255, 255), 255);
		panelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		updateScaledPanelImage(scale);
		
		image.addLayer(drawLayer);
		updatePanelImage(new BoundingBox(0, 0, width, height));
		
		setPreferredSize(new Dimension(width, height));
		DrawListener d = new DrawListener();
		addMouseMotionListener(d);
		
		setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.drawImage(scaledPanelImage, 0, 0, this);
		g2d.setColor(Color.BLACK);
		

		if(ToolBox.getEquippedTool() != null) { //if we have an equipped tool at all
			Shape cursor = ToolBox.getEquippedTool().getCursor(mouseX, mouseY, scale);
			g2d.draw(cursor);
		}
	}
	
	/**
	 * Updates the current {@link BufferedImage} for this Panel, only for a given area.
	 * @param x the x value of the start of the square representing the area to update.
	 * @param y the y value of the start of the square representing the area to update.
	 * @param width
	 * @param height
	 */
	private void updatePanelImage(BoundingBox b) {
		int x = b.getX(), y = b.getY(), width = b.getWidth(), height = b.getHeight();
		
		for(int tmpY = y; tmpY < y + height; tmpY++) {
			for(int tmpX = x; tmpX < x + height; tmpX++) {
				if(tmpX >= 0 && tmpX < image.getWidth() && tmpY >= 0 && tmpY < image.getHeight()) {
					Pixel thisPixel = image.getPixelAt(tmpX, tmpY);
					int thisARGB = thisPixel.getARGB();
					panelImage.setRGB(tmpX, tmpY, thisARGB);
				}
			}
		}
		
		x = (int) (x * scale);
		y = (int) (y * scale);
		width = (int) (width * scale);
		height = (int) (height * scale);
		for(int tmpY = y; tmpY < y + height; tmpY++) {
			for(int tmpX = x; tmpX < x + height; tmpX++) {
				if(tmpX >= 0 && tmpX < image.getWidth() && tmpY >= 0 && tmpY < image.getHeight()) {
					Pixel thisPixel = image.getPixelAt((int) (tmpX / scale), (int) (tmpY / scale));
					int thisARGB = thisPixel.getARGB();
					scaledPanelImage.setRGB(tmpX, tmpY, thisARGB);
				}
			}
		}

	}
	
	public void updateScaledPanelImage(int zoomScaleIncreaseAsPercentOfHundred) {
		double trueScale = (zoomScaleIncreaseAsPercentOfHundred/ 100.0);
		updateScaledPanelImage(trueScale + scale);
	}
	
	public void updateScaledPanelImage(double scale) {
		if(scale <= 0.01) {
			throw new IllegalArgumentException("Scale must be greater than 0.01");
		}
		this.scale = scale;
		scaledPanelImage = getScaledBufferedImage(panelImage, scale);
		repaint();
	}
	
	private BufferedImage getScaledBufferedImage(BufferedImage image, double scale) {
		java.awt.Image toolkitImage = image.getScaledInstance((int)(scale*image.getWidth()), (int)(scale*image.getHeight()), 
				java.awt.Image.SCALE_SMOOTH );
		int width = toolkitImage.getWidth(null);
		int height = toolkitImage.getHeight(null);
		BufferedImage newImage = new BufferedImage(width, height, 
			      BufferedImage.TYPE_INT_ARGB);
		Graphics g = newImage.getGraphics();
		g.drawImage(toolkitImage, 0, 0, null);
		g.dispose();
		return newImage;
	}
	
	
	private class DrawListener extends MouseAdapter {
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
			new SwingWorker<Object, Object>() {
				
				@Override
				protected Object doInBackground() throws Exception {
					int mouseX = (int) (e.getX() / scale);
					int mouseY = (int) (e.getY() / scale);
					ToolBox.useEquippedTool(mouseX, mouseY, scale, drawLayer);
					BoundingBox toUpdate = ToolBox.getEquippedTool().getToolShape().getBoundingBox(mouseX, mouseY);
					updatePanelImage(toUpdate);
					repaint();
					return null;
				}
			}.execute();
			
			mouseX = e.getX();
			mouseY = e.getY();
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			repaint();
		}
	}
}
