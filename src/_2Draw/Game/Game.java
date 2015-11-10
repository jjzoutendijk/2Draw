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
		ToolPanel toolPanel = new ToolPanel();
		CanvasPanel canvasPanel = new CanvasPanel(toolPanel);
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
