package _2Draw.Game;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import _2Draw.Panels.CanvasPanel;
import _2Draw.Panels.ColorChooser;
import _2Draw.Panels.ToolPanel;

public class Game{
	/* ------------------------------------------------------------------------------------------------------
	 * Class Variables
	 * ------------------------------------------------------------------------------------------------------
	 */
	private JFrame gameFrame;
	private CardLayout cl = new CardLayout();
	private JPanel rightPanel = new JPanel();
	public final static String CANVAS = "CanvasPanel";
    public final static String COLORPICKER = "ColorPicker";
	
	/* ------------------------------------------------------------------------------------------------------
	 * Constructors
	 * ------------------------------------------------------------------------------------------------------
	 */
	public Game()  {
		// Create a frame
		gameFrame = new JFrame();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ContainerPanel contains the left and right panels
		JPanel containerPanel = new JPanel();
		
		// Create the left half of the canvas: tool panel. Right half is initiated as a class variable
		ToolPanel leftPanel = new ToolPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		// Create the right half of the canvas: and add color chooser panel and canvas panel	
		rightPanel.setLayout(cl);
		CanvasPanel canvasPanel = new CanvasPanel(leftPanel, rightPanel);	
		ColorChooser colorChooser = new ColorChooser(leftPanel, rightPanel, canvasPanel);
		rightPanel.add(canvasPanel, CANVAS);
		rightPanel.add(colorChooser, COLORPICKER);
		cl.show(rightPanel, CANVAS);
		
		// Add the panels to container panel and add that to the frame
		containerPanel.add(leftPanel);		
		containerPanel.add(rightPanel);
		gameFrame.add(containerPanel);
		gameFrame.setResizable(false);
		gameFrame.pack();
		gameFrame.setVisible(true);
		
	}
	
	/**
	 * This function allows the right panel to switch between the Color picker panel and the canvas panel.
	 * @param canvas the panel to switch to (either the color picker or the canvas)
	 */

	public void changeLayout(String canvas) {
		if(canvas.equals(COLORPICKER)){
			cl.show(rightPanel, COLORPICKER);
		}
		else if(canvas.equals(CANVAS)){
			cl.show(rightPanel, CANVAS);
		}	
	}
			
	
}
