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
		//create a frame and panels and set to the proper size
		gameFrame = new JFrame();
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//containerPanel contains the left and right panels
		JPanel containerPanel = new JPanel();
		
		//create the left half of the canvas: tool panel
		ToolPanel leftPanel = new ToolPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		//create the right half of the canvas: color chooser and canvas panel	
		rightPanel.setLayout(cl);
		CanvasPanel canvasPanel = new CanvasPanel(leftPanel, rightPanel);	
		ColorChooser colorChooser = new ColorChooser(leftPanel, rightPanel, canvasPanel);
		rightPanel.add(canvasPanel, CANVAS);
		rightPanel.add(colorChooser, COLORPICKER);
		cl.show(rightPanel, CANVAS);
		
		
		//add the panels to the frame
		containerPanel.add(leftPanel);		
		containerPanel.add(rightPanel);
		gameFrame.add(containerPanel);
		gameFrame.setResizable(false);
		gameFrame.pack();
		gameFrame.setVisible(true);
		
		
	}

	public void changeLayout(String canvas) {
		if(canvas.equals(COLORPICKER)){
			cl.show(rightPanel, COLORPICKER);
		}
		else if(canvas.equals(CANVAS)){
			cl.show(rightPanel, CANVAS);
		}
		
	}
		
		
	
	
}
