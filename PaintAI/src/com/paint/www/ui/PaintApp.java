package com.paint.www.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.paint.www.image.Pixel;
import com.paint.www.tools.EraseTool;
import com.paint.www.tools.PencilTool;
import com.paint.www.tools.ToolBox;

public class PaintApp extends JFrame implements Runnable{
	private PaintPanel panel;
	private static final long serialVersionUID = 8730310604205952203L;

	@Override
	public void run() {
		panel = new PaintPanel(1000, 1000);
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AI Paint Application");
		setResizable(false);
		
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);
		setFocusable(true);
		
		KeyListener s = new SelectorListener();
		addKeyListener(s);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new PaintApp());
	}
	
	private class SelectorListener extends KeyAdapter {

		/**
		 * @param arg0
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_A) {
				ToolBox.setEquippedTool(new PencilTool(new Pixel(30, 30, 30, 70), 20));
			}
			
			if(e.getKeyCode() == KeyEvent.VK_S) {
				ToolBox.setEquippedTool(new EraseTool(20));
			}
			
			if(e.getKeyCode() == KeyEvent.VK_EQUALS || e.getKeyCode() == KeyEvent.VK_ADD) {
				panel.updateScaledPanelImage(10);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == KeyEvent.VK_UNDERSCORE) {
				panel.updateScaledPanelImage(-10);
			}
		}
	}
}
