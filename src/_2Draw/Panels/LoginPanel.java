package _2Draw.Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginPanel extends JPanel {
	
	private JLabel welkomLabel = new JLabel("Welkom bij 2Draw");
	private JLabel userNameLabel = new JLabel("Kies uw gebruikersnaam:");
	public static JTextField userNameField = new JTextField();
	public static JButton confirmButton = new JButton("Bevestig");

	public LoginPanel(){
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		userNameField.setMaximumSize(new Dimension(300,50));
		
		welkomLabel.setAlignmentX(CENTER_ALIGNMENT);
		userNameLabel.setAlignmentX(CENTER_ALIGNMENT);
		userNameField.setAlignmentX(CENTER_ALIGNMENT);
		confirmButton.setAlignmentX(CENTER_ALIGNMENT);
		welkomLabel.setAlignmentY(CENTER_ALIGNMENT);
		userNameLabel.setAlignmentY(CENTER_ALIGNMENT);
		userNameField.setAlignmentY(CENTER_ALIGNMENT);
		confirmButton.setAlignmentY(CENTER_ALIGNMENT);
		
		this.add(welkomLabel);
		this.add(userNameLabel);
		this.add(userNameField);
		this.add(confirmButton);
	}
	
	public void addConfirmationListener(ActionListener listener){
		confirmButton.addActionListener(listener);
	}
	
}
