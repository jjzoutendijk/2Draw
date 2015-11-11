package _2Draw.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import _2Draw.Shapes.Circle;

/**
 * The tool panel is the panel on the left hand side in the application. The tools are displayed on this panel and 
 * the users can select one tool at a time. 
 * @author Jan Jaap Zoutendijk
 * @version 2.0
 *
 */
public class ToolPanel extends _2DrawPanel {
	/* ------------------------------------------------------------------------------------------------------
	 * Class Variables 
	 * ------------------------------------------------------------------------------------------------------
	 */
	JToggleButton circleButton;
	JToggleButton squareButton;
	JToggleButton triangleButton;
	JButton confirmButton;
	private ButtonGroup shapeButtons;
	
	
	/* ------------------------------------------------------------------------------------------------------
	 * Constructor
	 * ------------------------------------------------------------------------------------------------------
	 */ 
	public ToolPanel(){

		// Initialize some values & buttons
		this.setBackground(Color.DARK_GRAY);
		this.setPreferredSize(new Dimension(100, 300));
		try {
			circleButton = new JToggleButton();
			circleButton.setPreferredSize(new Dimension(60,60));
		    Image circleImage = ImageIO.read(getClass().getResource("/circle.png"));
		    circleButton.setIcon(new ImageIcon(circleImage));
		    circleButton.setMargin(new Insets(0, 0, 0, 0));
		    
		    squareButton = new JToggleButton();
			squareButton.setPreferredSize(new Dimension(60,60));
		    Image squareImage = ImageIO.read(getClass().getResource("/square.png"));
		    squareButton.setIcon(new ImageIcon(squareImage));
		    
		    triangleButton = new JToggleButton();
			triangleButton.setPreferredSize(new Dimension(60,60));
		    Image triangleImage = ImageIO.read(getClass().getResource("/triangle.png"));		    
		    triangleButton.setIcon(new ImageIcon(triangleImage));
		    
		    confirmButton = new JButton();
		    confirmButton.setPreferredSize(new Dimension(60,60));
		    Image confirmImage = ImageIO.read(getClass().getResource("/confirm.png"));
		    confirmButton.setIcon(new ImageIcon(confirmImage)); 
		  } catch (IOException ex){} 		
		
		// Create a group to ensure only one button is active, select circle per default and add to panel
		shapeButtons= new ButtonGroup();
		shapeButtons.add(circleButton);
		shapeButtons.add(squareButton);
		shapeButtons.add(triangleButton);
	
		this.add(circleButton);
		this.add(squareButton);
		this.add(triangleButton);
		this.add(confirmButton);
		
//		// Actions event for storing the activeShape and committing the turn 
//		confirmButton.addActionListener(new ActionListener()
//				
//		{
//			/** Included the action listener  */
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				confirmAction();		
//			}
//			
//		});

	}

	/*------------------------------------------------------------------------------------------------------
	 * Getters and setters
	 * -----------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Return the active button text, so it is clear which button is selected. 
	 * @return The active button in a string
	 */
	public String getActiveButton(){
		if(circleButton.isSelected() == true){
			return "Circle";
		}
		else if(squareButton.isSelected() == true){
			return "Square";
		}
		else if(triangleButton.isSelected() == true){
			return "Triangle"; 
		}
		else return null;
	}
	
	/* ------------------------------------------------------------------------------------------------------
	 * Class Methods
	 * ------------------------------------------------------------------------------------------------------
	 */
	/**
	 * A confirmation listener for the canvasPanel, so the canvasPanel knows when to submit the active shape to the shapes array list
	 * @param listener
	 */
	public void addConfirmationListener(ActionListener listener){
		confirmButton.addActionListener(listener);
		circleButton.addActionListener(listener);
	}
	
	
}
