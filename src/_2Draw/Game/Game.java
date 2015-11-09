package _2Draw.Game;

import java.awt.LayoutManager;

import javax.swing.JFrame;

import _2Draw.Panels.CanvasPanel;

public class Game {
	/* 
	 * Class Variables
	 */
	private JFrame gameFrame;
	private LayoutManager layout;
	
	/*
	 * 
	 */
	Game(){
		//create a frame and panels and set to the proper size
		gameFrame = new JFrame();
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		CanvasPanel canvasPanel = new CanvasPanel();
		canvasPanel.setLayout(layout);
		
		//add the panels to the frame
		gameFrame.add(canvasPanel);
		gameFrame.pack();
		
		
		
	}
		
		
	
	
}
