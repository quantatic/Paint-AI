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
import com.paint.www.image.LayerEffectsFactory;
import com.paint.www.image.Pixel;
import com.paint.www.image.ToolBox;

public class PaintPanel extends JPanel{
	
	private static final long serialVersionUID = 7163711786488854179L;
	
	private final Image image;
	private final Layer drawLayer;
	
	public PaintPanel(int width, int height) {
		image = new Image(width, height);
		drawLayer = LayerEffectsFactory.createVerticalGradient(width, height, new Pixel(255, 127, 0 ,255), new Pixel(0, 127, 255, 255), 255);//new Layer(width, height);
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
					ToolBox.useEquippedTool(e.getX(), e.getY(), drawLayer);
					//drawLayer.blendSquareAt(e.getX() - 25, e.getY() - 25, 50, 50, currR, currG, currB, currA);
					return null;
				}
			}.execute();
		}

		/**
		 * @param arg0
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			repaint();
		}
	}
}
