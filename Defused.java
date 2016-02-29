/**
 * @file Defused.java
 * @author Josh Townsend
 * @date 7 December 2015
 *
 * A Defused Tile
 */

package game;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Defused extends Tile {

	private ImageIcon m_defused;

	/**
	 * Constructor
	 * 
	 * @param isMine a boolean is mine or not
	 * @param isHidden a boolean is hidden or not
	 * @param isDefused a boolean is defused or not
	 */
	public Defused(boolean isMine, boolean isHidden, boolean isDefused) {
		super(isMine, isHidden, isDefused);
		m_defused = new ImageIcon("images/defused.png");
	}

	/**
	 *  render the tile
	 *  
	 * @param g a Graphics object used to render
	 * @param x the X coordinate to render at
	 * @param y the Y coordinate to render at
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(m_defused.getImage(),
					x * Tile.WIDTH,
					y * Tile.HEIGHT,
					null);
	}

}
