package _2Draw.Panels;

import java.awt.Graphics;

import javax.swing.JPanel;

public class ShapePanel extends JPanel {
	private JPanel shapePanel = new JPanel();
	
	public ShapePanel(){
		this.setOpaque(true);
	}
	
	public void paint(Graphics g){
		g.drawOval(10, 10, 100, 100);
	}
}
