/**
 * @file Human.java
 * @author Zongbo Xu
 * @date 5 December 2015
 *
 * A class for Human players.
 */ 

package game;

public class Human extends Player  {
	private boolean m_playersTurn = true;

	public Human(String name) {
		super(name);
	}
	
	public void takeTurn() {
		m_playersTurn = true;
	}

	public boolean getm_playersTurn() {
		return m_playersTurn;
	}

	

}
