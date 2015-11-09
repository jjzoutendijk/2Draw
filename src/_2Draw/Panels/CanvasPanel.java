package _2Draw.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import _2Draw.Shapes.Shape;

public class CanvasPanel extends _2DrawPanel implements KeyListener,MouseListener {
	/*
	 *  Class variables
	 */
	private Shape[] shapes;


	
	/*
	 * Getters and Setters
	 */
	public Shape[] getShapes() {
		return shapes;
	}

	public void setShapes(Shape[] shapes) {
		this.shapes = shapes;
	}
	
	
	
	/*
	 * Constructors
	 */
	public CanvasPanel(){
		this.setPreferredSize(new Dimension(300, 300));
		this.setBackground(Color.WHITE);
		addMouseListener(this);
	}
	
	/*
	 * Class Methods
	 */

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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
	    int x=e.getX();
	    int y=e.getY();
	    System.out.println(x+","+y);//these co-ords are relative to the component
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
