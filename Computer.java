/**
 * @file Computer.java
 * @author Zongbo Xu
 * @date 5 December 2015
 *
 * A class for computer players.
 */ 

package game;

import java.util.Random;

public class Computer extends Player implements Runnable {
	private boolean m_aiToggled = false;
	private Board m_board;
	private GameController m_gameController;
	private final int SLEEP_TIME = 500;
	private Point[] m_currentPoint;
	
	public boolean toggleAi() {
		m_aiToggled = !m_aiToggled;
		return m_aiToggled;
	}
	
	public Computer(String name, Board board, GameController gc) {
		super(name);
		m_board = board;
		m_gameController = gc;
	}
	
	public boolean isValidPoint(Board board,int x, int y) {
		boolean flag = false;
		m_currentPoint[0].setPoint(x, y);
		for (int i = 0; i < board.getm_validPoints().length; i++) {
			if (board.getm_validPoints()[i] == m_currentPoint[0]) {
				break;
			} else if (board.getm_validPoints()[i] != m_currentPoint[0]) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void run() {
		while (true) {
			while (m_aiToggled) {
				boolean foundValidMove = false;
				do {
					Random rnd = new Random();
					int row = rnd.nextInt(m_board.getm_Board().size());
					int column = rnd.nextInt(m_board.getm_Board().get(row).size());
					Tile randomTile = m_board.getm_Board().get(row).get(column);
				
					if (!randomTile.isMine() && randomTile.isHidden()) {
						foundValidMove = true;
						m_board.revealTile(row, column);
						m_gameController.repaintAll();
						System.out.println("Revealed tile: (" + row + "," + column + ")");
					} else {
						System.out.println("Mine or already revealed tile found. Looping.");
					}
				} while (!foundValidMove);
				
				try {
					Thread.sleep(SLEEP_TIME); //Wait 3 seconds
				} catch (InterruptedException e) {
					System.err.println("Failed to put thread to sleep.");
				}
			}
		}
	}
}
