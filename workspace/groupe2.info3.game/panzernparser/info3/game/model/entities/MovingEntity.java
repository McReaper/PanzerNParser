package info3.game.model.entities;

import info3.game.automaton.Automaton;

public abstract class MovingEntity extends Entity {
	
	
	protected int m_dammage_taken;
	protected int m_health;
	protected int m_speed;

	public static final int DEFAULT_SPEED = 1000;

	public MovingEntity(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
		m_speed = DEFAULT_SPEED;
	}

	
	
	public void collide() {
		m_health -= m_dammage_taken;
	}
	
	public boolean GotPower() {
		return (m_health >0);
	}

}
