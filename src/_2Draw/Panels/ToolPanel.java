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
	/*
	 * Class Variables 
	 */
	private JToggleButton circleButton;
	private JToggleButton squareButton;
	private JToggleButton triangleButton;
	private ButtonGroup shapeButtons;
	
	private boolean circleActive = false;
	private boolean squareActive = false;
	private boolean triangleActive = false;
	
	
	/*
	 * Constructor
	 * 
	 */
	public ToolPanel(){

		
		// Initialize some values & buttons
		this.setBackground(Color.DARK_GRAY);
		this.setPreferredSize(new Dimension(100, 300));
			
		// Get the images for the buttons
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
		  } catch (IOException ex){} 		
		
		// Create a group to ensure only one button is active
		shapeButtons= new ButtonGroup();
		shapeButtons.add(circleButton);
		shapeButtons.add(squareButton);
		shapeButtons.add(triangleButton);
		circleButton.setSelected(true);		
		
		// Add the buttons to the panel
		this.add(circleButton);
		this.add(squareButton);
		this.add(triangleButton);
		
		// Actions events not currently needed
//		circleButton.addActionListener(new ActionListener() 
//		{
//			/** Included the action listener  */
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Circle active");			
//			}
//			
//		}); 
//		
//		squareButton.addActionListener(new ActionListener() 
//		{
//			/** Included the action listener  */
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Square active");
//			}
//			
//		}); 
//		
//		triangleButton.addActionListener(new ActionListener() 
//		{
//			/** Included the action listener  */
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Triangle active");			
//			}
//			
//		}); 
	}
	
	/*
	 * Getters and setters
	 */
	
	// Return the active button from the ToolPanel
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
	
}
