/**
 * @file Revealed.java
 * @author Anshul Kumar
 * @date 5 December 2015
 *
 * A Revealed Tile
 */ 

package game;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Revealed extends Tile {
	
	private int m_nearbyMines;
	private final ImageIcon m_revealedImage;
	
	/**
	 * Constructor
	 * 
	 * @param isMine a boolean is mine or not
	 * @param isHidden a boolean is hidden or not
	 * @param isDefused a boolean is defused or not
	 */
	public Revealed(boolean isMine, boolean isHidden, boolean isDefused) {
		// Always set hidden to false as its the revealed tile.
		super(isMine, false, isDefused);
		m_revealedImage = new ImageIcon("images/revealed.png");
	}

	/**
	 * @return the mines near by the tile calls it
	 */
	public int getm_NearByMines() {
		return m_nearbyMines;
	}
	
	/**
	 * draw the number of mines near by the tile which is clicked
	 */
	public void render(Graphics g, int x, int y) {

		g.drawImage(m_revealedImage.getImage(),
					x * Tile.WIDTH,
					y * Tile.HEIGHT,
					null);
		
		if (m_nearbyMines > 0) {
			/*
			 * using Spacing so that the number is drawn in the center
			 */
			int spacingX = 10;
			int spacingY = 19;
			int fontSize = 15;
			g.setFont(new Font("Time new roman", Font.BOLD, fontSize));
			g.drawString(Integer.toString(m_nearbyMines),
											x * Tile.WIDTH + spacingX,
											y * Tile.HEIGHT + spacingY);
		}
	}

	/**
	 * Calculates the number of mines around a tile
	 * 
	 * @param tile a Tile thats being checked
	 * @param tileArround an ArrayList of tiles that are around the tile
	 */
	private int calculateNearbyMines(Tile tile, ArrayList<Tile> tileArround) {
		if (tile.isMine()) {
			m_nearbyMines = -1;
		} else {
			int nearbyMine = 0;
			for (int i = 0; i < tileArround.size(); i++) {
				if (tileArround.get(i).isMine()) {
					nearbyMine++;
				}
			}
			m_nearbyMines = nearbyMine;
		}
		return m_nearbyMines;
	}

	/**
	 * Gets the tiles that are around a set position
	 * 
	 * @param board a Board object that contains all the tiles
	 * @param i the row to get around
	 * @param j the column to get around
	 * @return
	 */
	private ArrayList<Tile> getTileArround(ArrayList<ArrayList<Tile>> board,
											int i, int j) {

		int prevrow = i - 1;
		int prevrcol = j - 1;
		int nextrow = i + 1;
		int nextcol = j + 1;
		
		// it run a for loop all around the tile i,j
		ArrayList<Tile> t2 = new ArrayList<Tile>();
		
		for (int k = prevrow; k <= nextrow; ++k) {
			for (int m = prevrcol; m <= nextcol; ++m) {
				
				if (!(	k < 0 ||
						m < 0 || 
						k >= board.size() 
						|| m >= board.get(0).size())) {
					/*
					 * before adding it the condition makes sure that it is not
					 * out of bound of the board
					 */
					t2.add(board.get(k).get(m));
				}
			}
		}
		
		return t2;
	}

	/**
	 * A recursive to reveal empty tiles
	 * 
	 * @param board a Board object that contains all the tiles
	 * @param i the row to get around
	 * @param j the column to get around
	 */
	public void revealPosition(ArrayList<ArrayList<Tile>> board,
								int i, int j) {
		
		if (	i < 0 
				|| j < 0 
				|| i >= board.size() 
				|| j >= board.get(0).size() 
				|| !(board.get(i).get(j).isHidden())) {
			// Escape if true
			return;
		}
		
		ArrayList<Tile> tileArround = getTileArround(board, i, j);
		
		if (calculateNearbyMines(board.get(i).get(j), tileArround) == 0) {
			
			board.get(i).remove(j);
			board.get(i).add(j, new Revealed(false, false, false));
			Revealed r = (Revealed) board.get(i).get(j);
			
			r.calculateNearbyMines(r, getTileArround(board, i, j));
			
			revealPosition(board, i - 1, j - 1);
			revealPosition(board, i - 1, j);
			revealPosition(board, i - 1, j + 1);
			revealPosition(board, i, j - 1);
			revealPosition(board, i, j + 1);
			revealPosition(board, i + 1, j - 1);
			revealPosition(board, i + 1, j);
			revealPosition(board, i + 1, j + 1);
			
		} else {
			
			board.get(i).remove(j);
			board.get(i).add(j, new Revealed(false, false, false));
			Revealed r = (Revealed) board.get(i).get(j);
			r.calculateNearbyMines(r, getTileArround(board, i, j));
			
		}
	}

}
