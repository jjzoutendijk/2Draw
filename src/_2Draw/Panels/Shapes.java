package _2Draw.Panels;

import java.rmi.RemoteException;
import java.util.ArrayList;

import _2Draw.Shapes.Shape;

public class Shapes implements RemoteServices{
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	
	@Override
	public ArrayList<Shape> getShapes() throws RemoteException {
		return shapes;
	}
	
	@Override
	public void addShape(Shape s) throws RemoteException {
		shapes.add(s);
	}
	
	/*
	public int getSize(){
		return this.shapes.size();
	}
	
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	*/

}
