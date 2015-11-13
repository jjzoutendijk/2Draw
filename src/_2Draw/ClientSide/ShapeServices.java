package _2Draw.ClientSide;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import _2Draw.Shapes.Shape;


public interface ShapeServices extends Remote, Serializable  {
	public void addShape(Shape S) throws RemoteException;
	public ArrayList<Shape> getShapes() throws RemoteException; 
}

