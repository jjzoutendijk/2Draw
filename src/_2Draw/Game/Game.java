package _2Draw.Game;

import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Server.Player;
import Server.PlayerInterface;
import _2Draw.Panels.CanvasPanel;
import _2Draw.Panels.ColorChooser;
import _2Draw.Panels.ToolPanel;

public class Game {
	/* ------------------------------------------------------------------------------------------------------
	 * Class Variables
	 * ------------------------------------------------------------------------------------------------------
	 */
	private JFrame gameFrame;
	private CardLayout cl = new CardLayout();
	private JPanel rightPanel = new JPanel();
	private Player p1;
	private ArrayList<Player> players;
	private PlayerInterface playerI;
	public final static String CANVAS = "CanvasPanel";
    public final static String COLORPICKER = "ColorPicker";
	
	/* ------------------------------------------------------------------------------------------------------
	 * Constructors
	 * ------------------------------------------------------------------------------------------------------
	 */
	public Game()  {
		// Create a frame
		gameFrame = new JFrame();
		//gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create a player
		this.p1 = createNewPlayer();
		
		// ContainerPanel contains the left and right panels
		JPanel containerPanel = new JPanel();
		
		// Create the left half of the canvas: tool panel. Right half is initiated as a class variable
		ToolPanel leftPanel = new ToolPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		// Create the right half of the canvas: and add color chooser panel and canvas panel	
		rightPanel.setLayout(cl);
		CanvasPanel canvasPanel = new CanvasPanel(leftPanel, rightPanel, p1);	
		ColorChooser colorChooser = new ColorChooser(leftPanel, rightPanel, canvasPanel, p1);
		rightPanel.add(canvasPanel, CANVAS);
		rightPanel.add(colorChooser, COLORPICKER);
		cl.show(rightPanel, CANVAS);
		
		// Add the panels to container panel and add that to the frame
		containerPanel.add(leftPanel);		
		containerPanel.add(rightPanel);
		gameFrame.add(containerPanel);
		gameFrame.setResizable(false);
		gameFrame.pack();
		gameFrame.setVisible(true);
		
		// Add an option that checks 
		gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(gameFrame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	removePlayer();
		            System.exit(0);
		        }
		    }
		});
	}
	
	/**
	 * This function allows the right panel to switch between the Color picker panel and the canvas panel.
	 * @param canvas the panel to switch to (either the color picker or the canvas)
	 */

	public void changeLayout(String canvas) {
		if(canvas.equals(COLORPICKER)){
			cl.show(rightPanel, COLORPICKER);
		}
		else if(canvas.equals(CANVAS)){
			cl.show(rightPanel, CANVAS);
		}	
	}
	
	/**
	 * Method that creates a new player and adds it in the players list on the server
	 */
	public Player createNewPlayer(){
		try {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			Registry registry = LocateRegistry.getRegistry();
			
			Remote r = registry.lookup("Players");
			playerI = (PlayerInterface)r;
			int numberOfPlayers = playerI.numberOfPlayers();
			System.out.println("Current number of players: " + numberOfPlayers);
			int playerNumber =+ numberOfPlayers;
			
			p1 = new Player();
			p1.setIndex(playerNumber);
			playerI.addPlayer(p1);
		}catch (Exception ex) {
			System.out.println("ShapeClient exception: " + ex);
			ex.printStackTrace();
		}
		return p1;
	}
	
	/**
	 * Method to remove this player form the game on the server. Called before the client is closed
	 */
	public void removePlayer(){
		try {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			Registry registry = LocateRegistry.getRegistry();
			
			Remote r = registry.lookup("Players");
			playerI = (PlayerInterface)r;
			
			playerI.removePlayer(this.p1);
		} catch (Exception ex) {
			System.out.println("ShapeClient exception: " + ex);
			ex.printStackTrace();
		}
	}


}
