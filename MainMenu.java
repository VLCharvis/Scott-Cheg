/**
 * @file MainMenu.java
 * @author Eromosele Gideon
 * @date 7 December 2015
 *
 * Handles creation of the main menu
 * and user input on the menu.
 */

package main;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import game.Board;
import game.Human;
import game.Player;

/* 
 * Suppress serial ID warning as ID would not
 * match coding conventions.
 */
@SuppressWarnings("serial")
public class MainMenu extends JPanel implements MouseListener, KeyListener {

	private JTextField m_userNameText;
	private JTextField m_boardSizeText;
	private JTextField m_totalMinesText;
	private JFrame m_frame;
	private Kablewie m_kablewie;

	/**
	 * Constructor that sets variable values
	 * and starts display of the menu.
	 * 
	 * @param frame a JFrame that the menu can be attached to.
	 * @param kablewie the instance of Kablewie which started this Menu.
	 */
	public MainMenu(JFrame frame, Kablewie kablewie) {
		// Set Class variables
		this.m_frame = frame;
		this.m_kablewie = kablewie;
		
		// Display the menu
		display();
		
		// Repaint the frame so it displays the menu
		frame.validate();
		frame.repaint();
	}

	/**
	 * Create the username input.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createUserName(JPanel gamePanel) {
		JLabel userName = new JLabel("User Name:");
		userName.setBounds(6, 26, 82, 26);
		gamePanel.add(userName);

		m_userNameText = new JTextField();
		m_userNameText.addKeyListener(this);
		m_userNameText.setBorder(null);
		m_userNameText.setBounds(98, 26, 110, 26);
		m_userNameText.setForeground(Color.RED);
		gamePanel.add(m_userNameText);
		m_userNameText.setColumns(10);
	}

	/**
	 * Create the board size input.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createBoardSize(JPanel gamePanel) {
		JLabel boardSize = new JLabel("Board Size:");
		boardSize.setBounds(6, 69, 82, 26);
		gamePanel.add(boardSize);

		m_boardSizeText = new JTextField();
		m_boardSizeText.addKeyListener(this);
		m_boardSizeText.setText("10");
		m_boardSizeText.setBorder(null);
		m_boardSizeText.setBounds(98, 69, 110, 26);
		m_boardSizeText.setForeground(Color.RED);
		gamePanel.add(m_boardSizeText);
		m_boardSizeText.setColumns(10);
	}

	/**
	 * Create the mine input.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createTotalMines(JPanel gamePanel) {
		JLabel totalMines = new JLabel("Total Mines:");
		totalMines.setBounds(6, 116, 82, 26);
		gamePanel.add(totalMines);

		m_totalMinesText = new JTextField();
		m_totalMinesText.addKeyListener(this);
		m_totalMinesText.setText("10");
		m_totalMinesText.setBorder(null);
		m_totalMinesText.setBounds(98, 116, 110, 26);
		m_totalMinesText.setForeground(Color.RED);
		gamePanel.add(m_totalMinesText);
		m_totalMinesText.setColumns(10);
	}

	/**
	 * Create the start game button.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createStartGameBtn(JPanel gamePanel) {
		JButton startGame = new JButton("Start Game");
		startGame.setIcon(null);
		startGame.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 10));
		startGame.setForeground(Color.BLACK);
		startGame.setBackground(Color.DARK_GRAY);
		startGame.setBounds(110, 160, 100, 32);
		gamePanel.add(startGame);

		startGame.addMouseListener(this);		
	}

	/**
	 * Add everything that needs to be asked
	 *  before starting the game in gamePanel
	 * 
	 * @param gamePanel
	 */
	public void createPanel(JPanel gamePanel) {

		gamePanel.setBackground(Color.GRAY);
		gamePanel.setBorder(
				new TitledBorder(null, "Kablewie Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		gamePanel.setBounds(95, 27, 274, 210);
		m_frame.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);
		createUserName(gamePanel);
		createBoardSize(gamePanel);
		createTotalMines(gamePanel);
		createStartGameBtn(gamePanel);
	}

	/**
	 * Create a panel and add this to the main frame.
	 */
	public void display() {
		// Frame options for menu.
		m_frame.setResizable(false);
		m_frame.setSize(440, 320);
		m_frame.setTitle("Kablewie");
		m_frame.setForeground(Color.RED);
		m_frame.setBackground(Color.RED);
		m_frame.getContentPane().setBackground(new Color(153, 180, 209));
		m_frame.getContentPane().setLayout(null);
		
		// Create and add the panel.
		JPanel gamePanel = new JPanel();
		m_frame.addKeyListener(this);
		createPanel(gamePanel);
	}


	public void startGame() {
		String username = m_userNameText.getText();
		int boardSize;
		int numMines;
		if (username.equalsIgnoreCase("")) {
			return;
		}
		try {
			boardSize = Integer.parseInt(m_boardSizeText.getText());
			numMines = Integer.parseInt(m_totalMinesText.getText());
		} catch (Exception e) {
			return;
		}
		if (!(boardSize > 0 && boardSize <= 30)
				|| !(numMines < boardSize * boardSize && numMines <= 150 && numMines > 0)) {
			m_totalMinesText.setText(m_boardSizeText.getText());
			return;
		}
		Board board = new Board(boardSize, boardSize, numMines);
		Player player = new Human(username);
		m_frame.setSize(boardSize * 30 + 50, boardSize * 30 + 105);
		m_frame.setMinimumSize(new Dimension(5 * 30 + 50 + 130, 5 * 30 + 105));
		m_kablewie.startGame(board, player, this);
	}

	/**
	 * Called if the user clicks on the start game button.
	 */
	public void mouseClicked(MouseEvent arg0) {
		startGame();
	}
	
	/**
	 * Called on KeyRelease, start the game if
	 * its the enter key.
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			startGame();
		}
	}

	/*
	 * Methods below not used but included due to implements.
	 */
	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
}