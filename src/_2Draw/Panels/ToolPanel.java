package _2Draw.Panels;

import java.awt.Color;
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
		try {
			circleButton = new JToggleButton();
			circleButton.setPreferredSize(buttonSize);
		    Image circleImage = ImageIO.read(getClass().getResource("/circle.png"));
		    circleButton.setIcon(new ImageIcon(circleImage));
		    circleButton.setMargin(new Insets(0, 0, 0, 0));
		    
		    squareButton = new JToggleButton();
			squareButton.setPreferredSize(buttonSize);
		    Image squareImage = ImageIO.read(getClass().getResource("/square.png"));
		    squareButton.setIcon(new ImageIcon(squareImage));
		    
		    triangleButton = new JToggleButton();
			triangleButton.setPreferredSize(buttonSize);
		    Image triangleImage = ImageIO.read(getClass().getResource("/triangle.png"));		    
		    triangleButton.setIcon(new ImageIcon(triangleImage));
		    
		    confirmButton = new JButton();
		    confirmButton.setPreferredSize(buttonSize);
		    Image confirmImage = ImageIO.read(getClass().getResource("/confirm.png"));
		    confirmButton.setIcon(new ImageIcon(confirmImage)); 
		  } catch (IOException ex){} 		
		
		redButton = new JToggleButton();
		redButton.setPreferredSize(buttonSize);
		redButton.setBackground(Color.RED);
		redButton.setForeground(Color.RED);
		blueButton = new JToggleButton();
		blueButton.setBackground(Color.BLUE);
		blueButton.setForeground(Color.BLUE);
		blueButton.setPreferredSize(buttonSize);
		greenButton = new JToggleButton();
		greenButton.setPreferredSize(buttonSize);
		greenButton.setBackground(Color.GREEN);
		greenButton.setForeground(Color.GREEN);
		blackButton = new JToggleButton();
		blackButton.setPreferredSize(buttonSize);
		
		
		// Create a group to ensure only one button is active, select circle per default and add to panel
		shapeButtons= new ButtonGroup();
		shapeButtons.add(circleButton);
		shapeButtons.add(squareButton);
		shapeButtons.add(triangleButton);
		
		colorButtons = new ButtonGroup();
		colorButtons.add(blueButton);
		colorButtons.add(redButton);
		colorButtons.add(blackButton);
		colorButtons.add(greenButton);
	
		this.add(circleButton);
		this.add(squareButton);
		this.add(triangleButton);
		this.add(confirmButton);
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
	}
	
	
}
