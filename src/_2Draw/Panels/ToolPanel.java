package _2Draw.Panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

/**
 * The tool panel is the panel on the left hand side in the application. The tools are displayed on this panel and 
 * the users can select one tool at a time. 
 * @author Jan Jaap Zoutendijk
 * @version 2.0
 *
 */
public class ToolPanel extends Panel {
	/* ------------------------------------------------------------------------------------------------------
	 * Class Variables 
	 * ------------------------------------------------------------------------------------------------------
	 */
	JToggleButton circleButton;
	JToggleButton squareButton;
	JToggleButton whiteButton;
	public static JToggleButton colorButton;
	JToggleButton noFillButton;
	JToggleButton solidFillButton;
	JButton confirmButton;
	private JLabel shapeLabel;
	private JLabel colorLabel;
	private JLabel fillStyleLabel;
	private ButtonGroup shapeButtons;
	private ButtonGroup colorButtons;
	private ButtonGroup fillStyleButtons;
	private static Dimension buttonSize = new Dimension(30,30);


	/* ------------------------------------------------------------------------------------------------------
	 * Constructor
	 * ------------------------------------------------------------------------------------------------------
	 */ 
	public ToolPanel(){

		// Initialize some values & buttons
		this.setBackground(Color.DARK_GRAY);
		this.setPreferredSize(new Dimension(100, 500));
		this.setSize(100, 300);		

		// Initialize the buttons using the class method
		circleButton = createAToggleButton("/circle.png");
		squareButton = createAToggleButton("/square.png");
		confirmButton = createAButton("/confirm.png");
		
		colorButton = createAToggleButton("/color_picker.png");
		
		noFillButton = createAToggleButton("/noFill.png");
		solidFillButton = createAToggleButton("/SolidFill.png");
		
		// Create the JLabels
		shapeLabel = createALabel("Shape");
		colorLabel = createALabel("Color");
		fillStyleLabel = createALabel("Fill Style");

		// Create groups to ensure only one button is active
		shapeButtons= new ButtonGroup();
		shapeButtons.add(circleButton);
		shapeButtons.add(squareButton);

		
		fillStyleButtons = new ButtonGroup();
		fillStyleButtons.add(solidFillButton);
		fillStyleButtons.add(noFillButton);

		// Add the buttons to the panel
		this.add(shapeLabel);
		this.add(circleButton);
		this.add(squareButton);
		this.add(colorLabel);
		this.add(colorButton);
		this.add(fillStyleLabel);
		this.add(noFillButton);
		this.add(solidFillButton);
		this.add(confirmButton);
		

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
		colorButton.addActionListener(listener);
		noFillButton.addActionListener(listener);
		solidFillButton.addActionListener(listener);
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
	
	/**
	 * Methods to create a label with the proper settings
	 * @param text
	 * @return
	 */
	private JLabel createALabel(String text){
		JLabel tempLabel = new JLabel(text);
		tempLabel.setForeground(Color.WHITE);
		return tempLabel;
	}





}
