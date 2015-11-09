package _2Draw.Shape;

import java.awt.Color;


/**
 * 
 * @author Jan Jaap Zoutendijk
 * 
 */
public abstract class Shape {
	
	private int[] location;
	private Color color;
	private FillStyle fillStyle;
	
	
	/*
	 * The Constructors
	 */
	public Shape(int[] location){
		// Initiate the location
		this.location[0] = location[0];
		this.location[1] = location[1];
		
		// Set the standard style and color
		fillStyle = FillStyle.EMPTY;
		color = Color.BLACK;		
	}
	
	
	/*
	 * Getters and setters
	 */
	public int[] getLocation() {
		return location;
	}
	public void setLocation(int[] location) {
		this.location = location;
	}	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public FillStyle getFillStyle() {
		return fillStyle;
	}
	public void setFillStyle(FillStyle fillStyle) {
		this.fillStyle = fillStyle;
	}
	
	
	/*
	 * Class Methods
	 */
	public void confirmShape(){
		
	}
	
	
}
