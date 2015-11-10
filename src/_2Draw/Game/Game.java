package _2Draw.Game;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import _2Draw.Panels.*;

public class Game {
	/* 
	 * Class Variables
	 */
	private JFrame gameFrame;
	
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
		ToolPanel toolPanel = new ToolPanel(canvasPanel);
		JPanel containerPanel = new JPanel();
		
		//add the panels to the frame
		containerPanel.add(toolPanel);		
		containerPanel.add(canvasPanel);
		gameFrame.add(containerPanel);
		BoxLayout ls = new BoxLayout(containerPanel, BoxLayout.X_AXIS);
		containerPanel.setLayout(ls);

		gameFrame.pack();
		
		canvasPanel.requestFocus();
		
		
		
	}
		
		
	
	
}
