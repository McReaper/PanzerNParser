package info3.game.model.entities;

import info3.game.automaton.Automaton;

public abstract class MovingEntity extends Entity {
	
	protected int m_health;
	protected int m_speed;

	public static final int DEFAULT_SPEED = 1000;
	public static final int DEFAULT_HEALTH = 100;
	public static final int DEFAULT_DAMAGE_TAKEN = 100;

	public MovingEntity(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
		m_speed = DEFAULT_SPEED;
		m_health = DEFAULT_SPEED;
		m_dammage_dealt = DEFAULT_DAMAGE_TAKEN;
	}

	
	
	public void collide(int dammage) {
		m_health -= dammage;
	}
	
	public boolean GotPower() {
		return (m_health >0);
	}

}
