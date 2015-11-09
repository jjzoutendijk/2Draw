package _2Draw.Panels;

import java.awt.Color;
import java.awt.Dimension;

import _2Draw.Shape.Shape;

public class CanvasPanel extends _2DrawPanel {
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
		this.setSize(200, 200);
		this.setBackground(Color.LIGHT_GRAY);
	}
}
