package _2Draw.Panels;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import _2Draw.Shapes.Shape;

public class ShapePanel extends JPanel implements MouseInputListener {
	/* ------------------------------------------------------------------------------------------------------
	 * Class Variables
	 * ------------------------------------------------------------------------------------------------------
	 */
	private JPanel shapePanel = new JPanel();
	private Shape shape;
	private int locationX, locationY;
	
	
	/* ------------------------------------------------------------------------------------------------------
	 * The Constructors
	 * ------------------------------------------------------------------------------------------------------
	 */
	public ShapePanel(int x, int y, Shape shape){
		this.setOpaque(true);
		this.shape = shape;
		this.locationX = x;
		this.locationY = y;
		this.setFocusable(true);
		this.requestFocus();

	}
	


	/* ------------------------------------------------------------------------------------------------------
	 * Class Methods
	 * ------------------------------------------------------------------------------------------------------
	 */
	public void paint(Graphics g){
		g.drawOval(locationX, locationY, 50, 50);
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();		
		int y = e.getY();
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
