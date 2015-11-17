package _2Draw.Panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import Server.Circle;
import Server.FillStyle;
import Server.Shape;
import Server.ShapeInterface;
import Server.Square;
import _2Draw.Game.Game;

public class CanvasPanel extends Panel implements KeyListener, MouseInputListener, ActionListener {
	/* -----------------------------------------------------------------------------------------------------
	 *  Class variables
	 * -----------------------------------------------------------------------------------------------------
	 */
	private ArrayList<Shape> listShapes = new ArrayList<Shape>();
	private Shape activeShape = null;
	private ToolPanel toolPanel;
	private JPanel rightPanel;
	ShapeInterface shapesX;// = new Shapes();
	ShapeInterface shapesY;// = new Shapes();
	
	/* ------------------------------------------------------------------------------------------------------
	 * Constructor
	 * ------------------------------------------------------------------------------------------------------
	 */
	public CanvasPanel(ToolPanel toolPanel, JPanel rightPanel){
		this.setPreferredSize(new Dimension(500, 500));
		this.setBackground(Color.WHITE);
		this.toolPanel = toolPanel;
		this.rightPanel = rightPanel; 
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		toolPanel.addConfirmationListener(this);
        ApplicationTimer timer = new ApplicationTimer(this);
	}
	
	/* ------------------------------------------------------------------------------------------------------
	 * Getters and Setters
	 * ------------------------------------------------------------------------------------------------------
	 */
	public Shape getActiveShape() {
		if (activeShape != null)
			return activeShape;
		else return null;
	}

	public void setActiveShape(Shape activeShape) {
		this.activeShape = activeShape;
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
	/**
	 * When dragging the mouse, the active shape is dragged along.
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
		if(e.getSource().equals(ToolPanel.colorButton)){
			CardLayout cl = (CardLayout)(rightPanel.getLayout());
			cl.show(rightPanel, Game.COLORPICKER);
		}
		
		// Actions that can only be performed when there is an active shape available 
		if( activeShape != null){
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

				}catch (Exception ex) {
					System.out.println("ShapeClient exception: " + ex);
					ex.printStackTrace();
				}
				//activeShape = null;	
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
		g.setColor(shape.getColor());
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
