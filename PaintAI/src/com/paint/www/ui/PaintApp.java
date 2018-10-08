package com.paint.www.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PaintApp extends JFrame implements Runnable{

	private static final long serialVersionUID = 8730310604205952203L;

	@Override
	public void run() {
		add(new PaintPanel(1000, 1000));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AI Paint Application");
		setResizable(false);
		
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);
		setFocusable(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new PaintApp());
	}

}
