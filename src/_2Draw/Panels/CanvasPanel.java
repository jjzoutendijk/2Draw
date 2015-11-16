package _2Draw.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import Server.Circle;
import Server.FillStyle;
import Server.Shape;
import Server.ShapeInterface;
import Server.Square;

public class CanvasPanel extends _2DrawPanel implements KeyListener, MouseInputListener, ActionListener {
	/* -----------------------------------------------------------------------------------------------------
	 *  Class variables
	 * -----------------------------------------------------------------------------------------------------
	 */
	private ArrayList<Shape> listShapes = new ArrayList<Shape>();
	private Shape activeShape = null;
	private ToolPanel toolPanel;
	ShapeInterface shapesX;// = new Shapes();
	ShapeInterface shapesY;// = new Shapes();
	
	/* ------------------------------------------------------------------------------------------------------
	 * Constructor
	 * ------------------------------------------------------------------------------------------------------
	 */
	public CanvasPanel(ToolPanel toolPanel){
		this.setPreferredSize(new Dimension(500, 500));
		this.setBackground(Color.WHITE);
		this.toolPanel = toolPanel;
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		toolPanel.addConfirmationListener(this);
	}
		
	/* ------------------------------------------------------------------------------------------------------
	 * Class Methods, overrides first
	 * ------------------------------------------------------------------------------------------------------
	 */
	
	@Override
	/*
	 * This Class handles the keypressed events. They include
	 * the arrow keys, which reshape the active shape
	 */
	public void keyPressed(KeyEvent e) {	
		if(activeShape != null){
			if(e.getKeyCode() == 38){
				activeShape.increaseHeight();
			}
			else if(e.getKeyCode() == 39){
				activeShape.increaseWidth();
			}
			else if(e.getKeyCode() == 40){
				activeShape.decreaseHeight();
			}
			else if(e.getKeyCode() == 37){
				activeShape.decreaseWidth();
			}
		}
		repaint();
	}
		

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	/**
	 * Refresh the list from the server and paint the list when the canvas is clicked 
	 * @param e
	 */
	public void mouseClicked(MouseEvent e) {
		refreshServerList();
		repaint();
	}
	
	@Override
	/**
	 * Refresh the list from the server and paint the list when the canvas is clicked
	 * @param arg0
	 */
	public void mouseEntered(MouseEvent arg0) {
		refreshServerList();
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	/*
	 * When dragging the mouse, the shape is dragged along.
	 */
	public void mouseDragged(MouseEvent e) {
		if(activeShape != null){
			activeShape.setX(e.getX());
			activeShape.setY(e.getY());
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	
	@Override
	/**
	 * The Actions associated with the buttons on the tool panel.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(toolPanel.circleButton)){
			activeShape = new Circle();
		}
		if(e.getSource().equals(toolPanel.squareButton)){
			activeShape = new Square();
		}


		if( activeShape != null){
			if(e.getSource().equals(toolPanel.redButton)){
				activeShape.setColor(Color.RED);
			}
			else if(e.getSource().equals(toolPanel.whiteButton)){
				activeShape.setColor(Color.WHITE);
			}
			else if(e.getSource().equals(toolPanel.greenButton)){
				activeShape.setColor(Color.GREEN);
			}
			else if(e.getSource().equals(toolPanel.blueButton)){
				activeShape.setColor(Color.BLUE);
			}
			else if(e.getSource().equals(toolPanel.blackButton)){
				activeShape.setColor(Color.BLACK);
			}
			if(e.getSource().equals(toolPanel.noFillButton)){
				activeShape.setFillStyle(Server.FillStyle.EMPTY);
			}
			else if(e.getSource().equals(toolPanel.solidFillButton)){
				activeShape.setFillStyle(Server.FillStyle.SOLID);
			}
			if(e.getSource().equals(toolPanel.confirmButton)){
				try {
					if (System.getSecurityManager() == null) {
						System.setSecurityManager(new SecurityManager());
					}

					Registry registry = LocateRegistry.getRegistry();
					
					Remote r = registry.lookup("Shapes");
					shapesX = (ShapeInterface)r;
					shapesX.addShape(activeShape);
					/*System.out.println("Type on client: " + activeShape.toString());				
					System.out.println("listsize: " + shapesX.getShapes().size());
					int counter = 0;
					for(Shape shapeI : shapesX.getShapes()){
						System.out.println("Shape "+ counter + ": " + shapeI.getType());
						counter++;
					};
					*/
				}catch (Exception ex) {
					System.out.println("ShapeClient exception: " + ex);
					ex.printStackTrace();
				}
				activeShape = null;	
				repaint();						
			}

		}
	}

/**
 * This method sets the graphics color to the proper value, getting the value from the shape and setting it in the graphics object
 * @param g - The graphics object for which the color value will be set
 * @param shape - The shape object which carries the color information
 * @return The return value is the graphics object with the proper color set
 */
	public Graphics setGraphicsColor(Graphics g, Shape shape){
		switch (shape.getColorString()){
			case "white":{
				g.setColor(Color.WHITE);
				break;
			}
			case "red":{
				g.setColor(Color.RED);
				break;
			}
			case "green":{
				g.setColor(Color.GREEN);
				break;
			}
			case "blue":{
				g.setColor(Color.BLUE);
				break;
			}
			default:{
				g.setColor(Color.BLACK);
				break;
			}
		}
	return g;	
	}
	
	/**
	 * This method draws the eventual shape on the canvas
	 * @param g - The graphics needed to draw the shape
	 * @param shape - Carries the information needed to draw the shape
	 */
	public void drawShape(Graphics g, Shape shape){
		if(shape.getFillStyle() == FillStyle.EMPTY){
		
			switch (shape.getType()){
				case "Circle":{
					g.drawOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
					break;
				}
				case "Square":{
					g.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
					break;
				}

			}	
		}
		else{
			switch (shape.getType()){
			case "Circle":{
				g.fillOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
				break;
			}
			case "Square":{
				g.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
				break;
			}
			}	
		}
	}
	
	
	/**
	 * This method paints the components on the canvas. It consists of two parts, one for the active shape, 
	 * and one for the shapes from previous player turns.
	 */
	public void paint(Graphics g){		

		// Part one: to draw the shapes from the array list		
		if(listShapes.size() != 0 ){
			for (int i = 0; i< listShapes.size(); i++){
				g = setGraphicsColor(g, listShapes.get(i));
				drawShape(g, listShapes.get(i));
			}
		}
		// Part two: to draw the active shape
		if(activeShape != null){
			g = setGraphicsColor(g, activeShape);
			drawShape(g, activeShape);
		}
	}
	
	/**
	 * Retrieves the list with shapes from the server and adds them to the list in this class (useful before e.g. repaint method is
	 * called) 
	 */
	public void refreshServerList(){
		try {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			Registry registry = LocateRegistry.getRegistry();
			
			Remote r = registry.lookup("Shapes");
			shapesX = (ShapeInterface)r;
			listShapes = shapesX.getShapes();
		}catch (Exception ex) {
			System.out.println("ShapeClient exception: " + ex);
			ex.printStackTrace();
		}

	}

}
