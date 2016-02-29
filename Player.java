/**
 * @file Player.java
 * @author Ceri Westcott
 * @date 5 December 2015
 *
 * A class for a generic player
 */ 

package game;

public class Player {

	private String m_username;
	
	/**
	 * Constructor
	 * 
	 * @param username a String for the players username
	 */
	public Player(String username) {
		this.m_username = username;
	}
	
	protected void takeTurn() {
		// Placeholder for turn
	}
	
	/**
	 * @return the username of the player
	 */
	public String getUsername() {
		return m_username;
	}
	
}
