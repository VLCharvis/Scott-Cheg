/**
 * @file Tile.java
 * @author Ethan Davies
 * @date 7 December 2015
 *
 * A class for a generic tile
 */

package game;

import java.awt.*;

public abstract class Tile {
	// Static values for WIDTH and HEIGHT
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	private boolean m_isMine = false;
	private boolean m_isHidden = false;
	private boolean m_isDefused = false;
	
	/**
	 * Tile Constructor
	 * @param isMine a boolean is mine or not
	 * @param isHidden a boolean is hidden or not
	 * @param isDefused a boolean is defused or not
	 */
	public Tile(boolean isMine, boolean isHidden, boolean isDefused){
		this.m_isMine = isMine;
		this.m_isHidden = isHidden;
		this.m_isDefused=isDefused;
	}
	
	/**
	 * @return isMine which is true if the tile is a mine 
	 */
	public boolean isMine() {
		return m_isMine;
	}
	
	/**
	 * @return isMine which is true if the tile is a mine 
	 */
	public boolean isHidden() {
		return m_isHidden;
	}
	
	/**
	 * @return isMine which is true if the tile is a mine 
	 */
	public boolean isDefused() {
		return m_isDefused;
	}
	
	/**
	 * Change the Tile type
	 * 
	 * @param isMine a boolean for if its a mine
	 * @param isHidden a boolean for if it is hidden
	 */
	public void setTileType(boolean isMine, boolean isHidden) {
		this.m_isMine = isMine;
		this.m_isHidden = isHidden;
	}
	
	/**
	 *  render the tile
	 *  
	 * @param g a Graphics object used to render
	 * @param x the X coordinate to render at
	 * @param y the Y coordinate to render at
	 */
	public abstract void render(Graphics g, int x, int y);

}
