package com.paint.www.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.paint.www.image.Image;
import com.paint.www.image.Layer;

public class PaintPanel extends JPanel{
	
	private static final long serialVersionUID = 7163711786488854179L;
	
	private final Image image;
	private final Layer drawLayer;
	
	public PaintPanel(int width, int height) {
		image = new Image(width, height);
		drawLayer = new Layer(width, height);
		image.addLayer(drawLayer);
		
		setPreferredSize(new Dimension(width, height));
		addMouseMotionListener(new DrawListener());
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		BufferedImage im = image.getBufferedImage();
		g2d.drawImage(im, 0, 0, this);
	}
	
	private class DrawListener extends MouseAdapter {
		
		@Override
		public void mouseDragged(MouseEvent e) {
			drawLayer.blendSquareAt(e.getX() - 25, e.getY() - 25, 50, 50, 127, 127, 127, 127);
			repaint();
		}
	}
}
