package com.paint.www.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

import com.paint.www.image.Image;
import com.paint.www.image.Layer;
<<<<<<< HEAD
import com.paint.www.image.LayerEffectsFactory;
import com.paint.www.image.Pixel;
import com.paint.www.image.ToolBox;
=======
import com.paint.www.image.Pixel;
>>>>>>> branch 'dev' of https://github.com/quixotrykd/Paint-AI.git

public class PaintPanel extends JPanel{
	
	private static final long serialVersionUID = 7163711786488854179L;
	
	private final Image image;
	private final Layer drawLayer;
<<<<<<< HEAD
=======
	private int currR = 50, currG = 100, currB = 150, currA = 255;
	private BufferedImage panelImage;
>>>>>>> branch 'dev' of https://github.com/quixotrykd/Paint-AI.git
	
	public PaintPanel(int width, int height) {
		image = new Image(width, height);
<<<<<<< HEAD
		drawLayer = LayerEffectsFactory.createVerticalGradient(width, height, new Pixel(255, 127, 0 ,255), new Pixel(0, 127, 255, 255), 255);//new Layer(width, height);
=======
		panelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		drawLayer = new Layer(width, height);
>>>>>>> branch 'dev' of https://github.com/quixotrykd/Paint-AI.git
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
<<<<<<< HEAD
			new SwingWorker<Object, Object>() {

				@Override
				protected Object doInBackground() throws Exception {
					ToolBox.useEquippedTool(e.getX(), e.getY(), drawLayer);
					//drawLayer.blendSquareAt(e.getX() - 25, e.getY() - 25, 50, 50, currR, currG, currB, currA);
					return null;
				}
			}.execute();
=======
			drawLayer.blendSquareAt(e.getX() - 25, e.getY() - 25, 50, 50, currR, currG, currB, currA);
			updatePanelImage(e.getX() - 100, e.getY() - 100, 200, 200);
			repaint();
>>>>>>> branch 'dev' of https://github.com/quixotrykd/Paint-AI.git
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
<<<<<<< HEAD
			repaint();
=======
			Random r = new Random();
			currR = r.nextInt(256);
			currG = r.nextInt(256);
			currB = r.nextInt(256);
			currA = 50 + r.nextInt(50);
>>>>>>> branch 'dev' of https://github.com/quixotrykd/Paint-AI.git
		}
	}
}
