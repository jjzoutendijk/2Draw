package _2Draw.Panels;

import java.rmi.Remote;

public interface RemoteServices extends Remote {
	public void addShape();
	public Shapes getShapes();
}
