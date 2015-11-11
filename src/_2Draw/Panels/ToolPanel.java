package _2Draw.Panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

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
	JToggleButton whiteButton;
	JToggleButton redButton;
	JToggleButton blueButton;
	JToggleButton greenButton;
	JToggleButton blackButton;
	JButton confirmButton;
	private ButtonGroup shapeButtons;
	private ButtonGroup colorButtons;
	private static Dimension buttonSize = new Dimension(30,30);


	/* ------------------------------------------------------------------------------------------------------
	 * Constructor
	 * ------------------------------------------------------------------------------------------------------
	 */ 
	public ToolPanel(){

		// Initialize some values & buttons
		this.setBackground(Color.DARK_GRAY);
		this.setPreferredSize(new Dimension(100, 300));
		this.setSize(100, 300);		

		// Initialize the buttons using the class method
		circleButton = createAToggleButton("/circle.png");
		squareButton = createAToggleButton("/square.png");
		triangleButton = createAToggleButton("/triangle.png");
		confirmButton = createAButton("/confirm.png");
		
		whiteButton = createAToggleButton("/white.png");
		redButton = createAToggleButton("/red.png");
		blueButton = createAToggleButton("/blue.png");
		greenButton = createAToggleButton("/green.png");
		blackButton = createAToggleButton("/black.png");


		// Create groups to ensure only one button is active
		shapeButtons= new ButtonGroup();
		shapeButtons.add(circleButton);
		shapeButtons.add(squareButton);
		shapeButtons.add(triangleButton);

		colorButtons = new ButtonGroup();
		colorButtons.add(whiteButton);
		colorButtons.add(blueButton);
		colorButtons.add(redButton);
		colorButtons.add(blackButton);
		colorButtons.add(greenButton);

		// Add the buttons to the panel
		this.add(circleButton);
		this.add(squareButton);
		this.add(triangleButton);
		this.add(confirmButton);
		this.add(whiteButton);
		this.add(redButton);
		this.add(blackButton);
		this.add(blueButton);
		this.add(greenButton);

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
		squareButton.addActionListener(listener);
		redButton.addActionListener(listener);
		whiteButton.addActionListener(listener);
		greenButton.addActionListener(listener);
		blueButton.addActionListener(listener);
	}
	
	/**
	 * A method to create the toggle button that fulfills the ToggleButtons standards for the application
	 * @param image String location
	 * @return the toggle button with the image and correct layout
	 */
	private JToggleButton createAToggleButton(String image) {
		try{
			Image buttonImage =  ImageIO.read(getClass().getResource(image)); 	        
			JToggleButton tempButton = new JToggleButton();
			tempButton.setPreferredSize(buttonSize);
			tempButton.setIcon(new ImageIcon(buttonImage));
			tempButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			tempButton.setMargin(new Insets(0, 0, 0, 0));
			return tempButton;
		} catch (IOException ex){} 	
		return null;
	}
	
	/**
	 * A method to create button that fulfills the buttons standards for the application
	 * @param image String location
	 * @return the button with the correct layout
	 */
	private JButton createAButton(String image) {
		try{
			Image buttonImage =  ImageIO.read(getClass().getResource(image)); 	        
			JButton tempButton = new JButton();
			tempButton.setPreferredSize(buttonSize);
			tempButton.setIcon(new ImageIcon(buttonImage));
			tempButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			tempButton.setMargin(new Insets(0, 0, 0, 0));
			return tempButton;
		} catch (IOException ex){} 	
		return null;
	}



}
