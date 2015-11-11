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
	private int x,y;
	private Color color;
	private FillStyle fillStyle;
	private String type;
	private int height;
	private int width;
	
	
	/* ------------------------------------------------------------------------------------------------------
	 * The Constructors
	 * ------------------------------------------------------------------------------------------------------
	 */
	public Shape(){
		this.x = 10;
		this.y = 10;		
		
		// Set the standard style, color and size
		fillStyle = FillStyle.EMPTY;
		color = Color.BLACK;
		height = 50;
		width = 50;
	}
	
	
	/* ------------------------------------------------------------------------------------------------------
	 * Getters and setters
	 * ------------------------------------------------------------------------------------------------------
	 */

	public Color getColor() {
		return color;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
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
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	
	
	/* ------------------------------------------------------------------------------------------------------
	 * Class Methods
	 * ------------------------------------------------------------------------------------------------------
	 */
	public String printShape(){
		System.out.println("x: " + this.x + "y: " + this.y + "style: " + this.fillStyle);
		return new String("x: " + this.x + "y: " + this.y + "style: " + this.fillStyle); 
	}
	
	public void increaseWidth(){
		this.width += 2;
	}
	public void increaseHeight(){
		this.height += 2;
	}

	public void decreaseWidth(){
		if (this.width > 10){
			this.width -= 2;
		}
	}
	public void decreaseHeight(){
		if (this.height > 10){
			this.height -= 2;
		}
			
	}
	
	
}
