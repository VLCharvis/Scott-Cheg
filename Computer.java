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
	
	public boolean toggleAi() {
		m_aiToggled = !m_aiToggled;
		return m_aiToggled;
	}
	
	public Computer(String name, Board board) {
		super(name);
		m_board = board;
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
				
					if (!randomTile.isMine()) {
						foundValidMove = true;
						m_board.revealTile(row, column);
						System.out.println("Revealed tile: (" + row + "," + column + ")");
					} else {
						System.out.println("Mine found. Looping.");
					}
				} while (!foundValidMove);
				
				try {
					Thread.sleep(3000); //Wait 3 seconds
				} catch (InterruptedException e) {
					System.err.println("Failed to put thread to sleep.");
				}
			}
		}
	}
}
