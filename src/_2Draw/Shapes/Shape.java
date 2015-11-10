package _2Draw.Shapes;

import java.awt.Color;


/**
 * This is an abstract superclass of the drawing shapes program
 * 
 * @author Jan Jaap Zoutendijk
 * @version 1.0
 * 
 */
public abstract class Shape {
	/* ------------------------------------------------------------------------------------------------------
	 * Class Variables
	 * ------------------------------------------------------------------------------------------------------
	 */
	private int[] location = new int[2];
	private Color color;
	private FillStyle fillStyle;
	private String type;
	private int size;
	
	/* ------------------------------------------------------------------------------------------------------
	 * The Constructors
	 * ------------------------------------------------------------------------------------------------------
	 */
	public Shape(int x, int y){
		this.location[0] = x;
		this.location[1] = y;		
		// Set the standard style, color and size
		fillStyle = FillStyle.EMPTY;
		color = Color.BLACK;
		size = 50;
	}
	
	
	/* ------------------------------------------------------------------------------------------------------
	 * Getters and setters
	 * ------------------------------------------------------------------------------------------------------
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
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}
	public int getSize(){
		return this.size;
	}
	public void setSize(int size){
		this.size = size;
	}
	
	
	/* ------------------------------------------------------------------------------------------------------
	 * Class Methods
	 * ------------------------------------------------------------------------------------------------------
	 */
	public String printShape(){
		System.out.println("x: " + this.location[0] + "y: " + this.location[1] + "style: " + this.fillStyle);
		return new String("x: " + this.location[0] + "y: " + this.location[1] + "style: " + this.fillStyle); 
	}
	

	
	
}
