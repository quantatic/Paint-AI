package com.paint.www.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.paint.www.image.BoundingBox;
import com.paint.www.image.Image;
import com.paint.www.image.Layer;
import com.paint.www.image.LayerEffectsFactory;
import com.paint.www.image.Pixel;
import com.paint.www.tools.ToolBox;

public class PaintPanel extends JPanel{
	
	private static final long serialVersionUID = 7163711786488854179L;
	
	private final Image image;
	private final Layer drawLayer;
	private BufferedImage panelImage;
	private int mouseX, mouseY;
	
	public PaintPanel(int width, int height) {
		image = new Image(width, height);
		drawLayer = LayerEffectsFactory.createVerticalGradient(width, height, new Pixel(255, 127, 0, 255), new Pixel(0, 127, 255, 255), 255);
		panelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		//drawLayer = new Layer(width, height);
		image.addLayer(drawLayer);
		updatePanelImage(0, 0, width, height);
		
		setPreferredSize(new Dimension(width, height));
		DrawListener d = new DrawListener();
		addMouseMotionListener(d);
		addMouseListener(d);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.drawImage(panelImage, 0, 0, this);
		g2d.setColor(Color.BLACK);
		
		if(ToolBox.getEquippedTool() != null) {
			BoundingBox cursorBox = ToolBox.getEquippedTool().getBoundingBox(mouseX, mouseY);
			Shape cursor = new Ellipse2D.Double(cursorBox.getX(), cursorBox.getY(), cursorBox.getWidth(), cursorBox.getHeight());
			g2d.draw(cursor);
		}
	}
	
	private void updatePanelImage(int x, int y, int width, int height) {
		for(int tmpY = y; tmpY < y + height; tmpY++) {
			for(int tmpX = x; tmpX < x + height; tmpX++) {
				if(tmpX >= 0 && tmpX < image.getWidth() && tmpY >= 0 && tmpY < image.getHeight()) {
					Pixel thisPixel = image.getPixelAt(tmpX, tmpY);
					int thisARGB = (thisPixel.getAlpha() << 24) | (thisPixel.getRed() << 16) | (thisPixel.getGreen() << 8) | (thisPixel.getBlue());
					panelImage.setRGB(tmpX, tmpY, thisARGB);
				}
			}
		}
	}
	
	
	
	private class DrawListener extends MouseAdapter {
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
			new SwingWorker<Object, Object>() {
				
				@Override
				protected Object doInBackground() throws Exception {
					int mouseX = e.getX();
					int mouseY = e.getY();
					ToolBox.useEquippedTool(mouseX, mouseY, drawLayer);
					BoundingBox toUpdate = ToolBox.getEquippedTool().getBoundingBox(mouseX, mouseY);
					updatePanelImage(toUpdate.getX(), toUpdate.getY(), toUpdate.getWidth(), toUpdate.getHeight());
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
