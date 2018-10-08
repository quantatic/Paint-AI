package com.paint.www.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.paint.www.image.Image;
import com.paint.www.image.Layer;

public class PaintPanel extends JPanel{
	
	private static final long serialVersionUID = 7163711786488854179L;
	
	private final Image image;
	private final Layer drawLayer;
	private int currR = 127, currG = 127, currB = 127, currA = 127;
	
	public PaintPanel(int width, int height) {
		image = new Image(width, height);
		drawLayer = new Layer(width, height);
		image.addLayer(drawLayer);
		
		setPreferredSize(new Dimension(width, height));
		DrawListener d = new DrawListener();
		addMouseMotionListener(d);
		addMouseListener(d);
		
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
			new SwingWorker<Object, Object>() {

				@Override
				protected Object doInBackground() throws Exception {
					drawLayer.blendSquareAt(e.getX() - 25, e.getY() - 25, 50, 50, currR, currG, currB, currA);
					return null;
				}
			}.execute();
		}

		/**
		 * @param arg0
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			Random r = new Random();
			currR = r.nextInt(256);
			currG = r.nextInt(256);
			currB = r.nextInt(256);
			currA = r.nextInt(127);
			repaint();
		}
	}
}
