package _2Draw.Panels;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Server.Shape;

public interface ShapeInterface extends Remote  {
	public void addShape(Shape S) throws RemoteException;
	public ArrayList<Shape> getShapes() throws RemoteException;;
}
