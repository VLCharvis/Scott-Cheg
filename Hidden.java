/**
 * @file Hidden.java
 * @author Thomas Williams
 * @date 7 December 2015
 *
 * A hidden Tile
 */ 

package game;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Hidden extends Tile {

	private ImageIcon m_hidden;
	
	/**
	 * Hidden Constructor
	 * @param isMine a boolean is mine or not
	 * @param isHidden a boolean is hidden or not
	 * @param isDefused a boolean is defused or not
	*/
	public Hidden(boolean isMine, boolean isHidden,boolean isDefused) {
		// Always set hidden to false as its the hidden tile.
		super(isMine, true,false);
		m_hidden=new ImageIcon("images/hidden.png");
	}
	
	/**
	 *  render the tile
	 *  
	 * @param g a Graphics object used to render
	 * @param x the X coordinate to render at
	 * @param y the Y coordinate to render at
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(m_hidden.getImage(), 
					x * Tile.WIDTH,
					y * Tile.HEIGHT,
					null);
	}

}
