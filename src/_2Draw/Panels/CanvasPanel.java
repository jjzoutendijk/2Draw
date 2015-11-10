package _2Draw.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import _2Draw.Shapes.Circle;
import _2Draw.Shapes.Shape;
import _2Draw.Shapes.Square;

public class CanvasPanel extends _2DrawPanel implements KeyListener,MouseInputListener, ActionListener {
	/*
	 *  Class variables
	 */
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Shape activeShape = null;
	private ToolPanel toolpanel;
	
	
	/*
	 * Constructors
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
	
	/*
	 * Getters and Setters
	 */
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	
	/*
	 * Class Methods, overrides first
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + "," + y + "; " + this.toolpanel.getActiveButton()); 	
		addActiveShape(this.toolpanel.getActiveButton(),x,y);
		System.out.println("Button: " + this.toolpanel.getActiveButton());
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
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
	
	public void paint(Graphics g){
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



}
