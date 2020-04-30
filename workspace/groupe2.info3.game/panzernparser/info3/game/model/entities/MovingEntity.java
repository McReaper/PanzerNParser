package info3.game.model.entities;

import info3.game.automaton.Automaton;

public abstract class MovingEntity extends Entity {
	
	protected int m_health;
	protected int m_speed;

	public static final int DEFAULT_SPEED = 1000;

	public MovingEntity(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
		m_speed = DEFAULT_SPEED;
	}

	
	
	public void collide(int dammage) {
		m_health -= dammage;
	}
	
	public boolean GotPower() {
		return (m_health >0);
	}

}
