package _2Draw.Game;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Server.Player;
import Server.PlayerInterface;
import Server.Players;
import _2Draw.Panels.CanvasPanel;
import _2Draw.Panels.ColorChooser;
import _2Draw.Panels.LoginPanel;
import _2Draw.Panels.ToolPanel;

public class Game implements ActionListener{
	/* ------------------------------------------------------------------------------------------------------
	 * Class Variables
	 * ------------------------------------------------------------------------------------------------------
	 */
	private JFrame gameFrame;
	private CardLayout cl = new CardLayout();
	private CardLayout c2 = new CardLayout();
	private JPanel rightPanel = new JPanel();
	private Player thisPlayer;
	private ArrayList<Player> players;
	private PlayerInterface playerI;
	private JPanel containerPanel;
	private JPanel gamePanel;
	public final static String CANVAS = "CanvasPanel";
    public final static String COLORPICKER = "ColorPicker";
	public final static String LOGIN = "LoginPanel";
    public final static String GAME = "GamePanel";
	
	/* ------------------------------------------------------------------------------------------------------
	 * Constructors
	 * ------------------------------------------------------------------------------------------------------
	 */
	public Game()  {
		// Create a frame
		gameFrame = new JFrame();		
		
		// Create a new Player
		thisPlayer = createNewPlayer();
		
		// Create a panel for logging in
		containerPanel = new JPanel();		
		LoginPanel loginPanel = new LoginPanel();
		gamePanel = new JPanel();
		containerPanel.setLayout(c2);
		containerPanel.add(loginPanel, LOGIN);
		containerPanel.add(gamePanel, GAME);
		c2.show(containerPanel, LOGIN);
		
		// Add confirmation listener
		loginPanel.addConfirmationListener(this);
		
		// Create the left half of the canvas: tool panel. Right half is initiated as a class variable
		ToolPanel leftPanel = new ToolPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		// Create the right half of the canvas: and add color chooser panel and canvas panel	
		rightPanel.setLayout(cl);
		CanvasPanel canvasPanel = new CanvasPanel(leftPanel, rightPanel, thisPlayer);	
		ColorChooser colorChooser = new ColorChooser(leftPanel, rightPanel, canvasPanel, thisPlayer);
		rightPanel.add(canvasPanel, CANVAS);
		rightPanel.add(colorChooser, COLORPICKER);
		cl.show(rightPanel, CANVAS);
		
		// Add the panels to container panel and add that to the frame
		gamePanel.add(leftPanel);		
		gamePanel.add(rightPanel);
		
		// Add the container Panel to the gameFrame
		gameFrame.add(containerPanel);
		gameFrame.setResizable(false);
		gameFrame.pack();
		gameFrame.setVisible(true);
		
		// Add an option that checks 
		gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(gameFrame, 
						"Are you sure you want to quit?", "Confirm close", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
							removePlayer(thisPlayer);
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
	 * This function allows the right panel to switch between the Color picker panel and the canvas panel.
	 * @param canvas the panel to switch to (either the color picker or the canvas)
	 */

	public void changeLayoutLogin() {
		c2.show(containerPanel, GAME);
	}
	
	/**
	 * Method to remove this player form the game on the server. Called before the client is closed
	 */
	public void removePlayer(Player thisPlayer){
		try {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			Registry registry = LocateRegistry.getRegistry();
			
			Remote r = registry.lookup("Players");
			playerI = (PlayerInterface)r;
			
			playerI.removePlayer(thisPlayer);
		} catch (Exception ex) {
			System.out.println("ShapeClient exception: " + ex);
			ex.printStackTrace();
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
			int playerNumber = Players.playerNumber;
			
			thisPlayer = new Player();
			
			thisPlayer.setIndex(playerNumber);
			playerI.addPlayer(thisPlayer);
		}catch (Exception ex) {
			System.out.println("ShapeClient exception: " + ex);
			ex.printStackTrace();
		}
		return thisPlayer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource().equals(LoginPanel.confirmButton)){
			thisPlayer.setName(LoginPanel.userNameField.getText());
			changeLayoutLogin();
		}
	}
	



}
