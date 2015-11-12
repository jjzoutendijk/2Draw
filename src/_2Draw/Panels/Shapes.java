package _2Draw.Panels;

import java.util.ArrayList;

import _2Draw.Shapes.Shape;

public class Shapes {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public void addShape(Shape s)
	{
		shapes.add(s);
	}
	
	public int getSize(){
		return this.shapes.size();
	}
	
	public Shape getShape(int i){
		return shapes.get(i);
	}
	

}
