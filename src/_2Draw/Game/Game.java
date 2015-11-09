package _2Draw.Game;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

import _2Draw.Panels.*;

public class Game {
	/* 
	 * Class Variables
	 */
	private JFrame gameFrame;
	private FlowLayout layout;
	
	/*
	 * 
	 */
	Game(){
		//create a frame and panels and set to the proper size
		gameFrame = new JFrame();
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create the canvas and tool panels
		CanvasPanel canvasPanel = new CanvasPanel();
		ToolPanel toolPanel = new ToolPanel();
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(layout);

		
		//add the panels to the frame
		containerPanel.add(canvasPanel);
		containerPanel.add(toolPanel);
		gameFrame.add(containerPanel);

		gameFrame.pack();
		
		
		
	}
		
		
	
	
}
