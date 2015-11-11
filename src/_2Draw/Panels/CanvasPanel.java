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
	private ShapePanel shapePanel;
	private boolean active = false;
	//private int[][] coordinatesTriangle = new int[3][2];
	
	/* ------------------------------------------------------------------------------------------------------
	 * Constructor
	 * ------------------------------------------------------------------------------------------------------
	 */
	public CanvasPanel(ToolPanel toolpanel){
		this.setPreferredSize(new Dimension(300, 300));
		this.setBackground(Color.WHITE);
		this.toolpanel = toolpanel;
		this.add(shapePanel);
		
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
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getID());
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
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + "," + y);
		this.shapePanel = new ShapePanel(x, y, this.toolpanel.getActiveButton());
		shapePanel.setVisible(true);
		this.add(shapePanel);
		shapePanel.setLocation(10, 10);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
//		addActiveShape(this.toolpanel.getActiveButton(),x,y);
//		
		

		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		shapes.add(activeShape);
		activeShape = null;	
	}

	
	public void addActiveShape(String shape, int x, int y) {
		//check the selected button
		switch(shape){
		case "Circle":
			activeShape = new Circle(x,y);
			break;
		case "Square":
			activeShape = new Square(x,y);
			break;
		case "Traingle":
			activeShape = new Triangle(x,y);
			break;
		}
		
		
	}
	
	
	/**
	 * This method paints the components on the canvas. It consists of two parts, one for the active shape, 
	 * and one for the shapes from previous player turns.
	 * 
	 * @author Jan Jaap Zoutendijk
	 * @version 1.0
	 */
	public void paint(Graphics g){
		// Part one: to draw the active shape
		if(activeShape != null){
			switch (activeShape.getType()){
				case "Circle":{
					g.drawOval((activeShape.getLocation())[0], (activeShape.getLocation())[1], 50, 50);
					break;
				}
				case "Square":{
					g.drawRect((activeShape.getLocation())[0], (activeShape.getLocation())[1], 50, 50);
					break;
				}
				case "Triangle":{
					g.drawRect((activeShape.getLocation())[0], (activeShape.getLocation())[1], 50, 50);
					break;
				}
			}	
		}
		
		// Part two: to draw the shapes from the array list
		if(shapes.size() != 0 ){
			for (Shape shapeX : shapes){	
				switch(shapeX.getType()){
					case "Circle":{
						g.drawOval((shapeX.getLocation())[0], (shapeX.getLocation())[1], 50, 50);
						break;
					}
					case "Square":{
						g.drawRect((shapeX.getLocation())[0], (shapeX.getLocation())[1], 50, 50);
						break;
					}
					case "Triangle":{
						g.drawRect((shapeX.getLocation())[0], (shapeX.getLocation())[1], 50, 50);
						break;
					}
				}
			}
		}
	}
	
	


	//http://zetcode.com/tutorials/javaswingtutorial/resizablecomponent/

}
