package _2Draw.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import _2Draw.Shapes.Circle;
import _2Draw.Shapes.Shape;
import _2Draw.Shapes.Square;

public class CanvasPanel extends _2DrawPanel implements KeyListener, MouseInputListener, ActionListener {
	/* -----------------------------------------------------------------------------------------------------
	 *  Class variables
	 * -----------------------------------------------------------------------------------------------------
	 */
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Shape activeShape = null;
	private ToolPanel toolpanel;

	
	/* ------------------------------------------------------------------------------------------------------
	 * Constructor
	 * ------------------------------------------------------------------------------------------------------
	 */
	public CanvasPanel(ToolPanel toolpanel){
		this.setPreferredSize(new Dimension(300, 300));
		this.setBackground(Color.WHITE);
		this.toolpanel = toolpanel;
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		toolpanel.addConfirmationListener(this);
	}
	
	/* -----------------------------------------------------------------------------------------------------
	 * Getters and Setters
	 * -----------------------------------------------------------------------------------------------------
	 */
	public ArrayList<Shape> getShapes() {
		return shapes;
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
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {	
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
	/*
	 * The Actions associated with the buttons on the tool panel.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(toolpanel.circleButton)){
			activeShape = new Circle();
		}
		if(e.getSource().equals(toolpanel.squareButton)){
			activeShape = new Square();
		}
		if(e.getSource().equals(toolpanel.confirmButton)){
			shapes.add(activeShape);
			activeShape = null;
		}
		repaint();
		
	}

	
	/*
	 * This method paints the components on the canvas. It consists of two parts, one for the active shape, 
	 * and one for the shapes from previous player turns.
	 */
	public void paint(Graphics g){
		// Part one: to draw the active shape
		if(activeShape != null){
			switch (activeShape.getType()){
				case "Circle":{
					g.drawOval(activeShape.getX(), activeShape.getY(), activeShape.getWidth(), activeShape.getHeight());
					break;
				}
				case "Square":{
					g.drawRect(activeShape.getX(), activeShape.getY(), activeShape.getWidth(), activeShape.getHeight());
					break;
				}
				case "Triangle":{
					g.drawRect(activeShape.getX(), activeShape.getY(), activeShape.getWidth(), activeShape.getHeight());
					break;
				}
			}	
		}
		
		// Part two: to draw the shapes from the array list
		if(shapes.size() != 0 ){
			for (Shape shapeX : shapes){	
				switch(shapeX.getType()){
					case "Circle":{
						g.drawOval(shapeX.getX(),shapeX.getY() , shapeX.getWidth(), shapeX.getHeight());
						break;
					}
					case "Square":{
						g.drawRect(shapeX.getX(),shapeX.getY() , shapeX.getWidth(), shapeX.getHeight());
						break;
					}
					case "Triangle":{
						g.drawRect(shapeX.getX(),shapeX.getY() , shapeX.getWidth(), shapeX.getHeight());
						break;
					}
				}
			}
		}
	}
}
