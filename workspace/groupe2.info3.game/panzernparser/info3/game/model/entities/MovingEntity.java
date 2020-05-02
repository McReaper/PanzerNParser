package info3.game.model.entities;

import info3.game.automaton.Automaton;

public abstract class MovingEntity extends Entity {

	public static final int DEFAULT_SPEED = 1000;
	public static final int DEFAULT_HEALTH = 100;

	public MovingEntity(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
		m_speed = DEFAULT_SPEED;
		m_health = DEFAULT_SPEED;
	}

}
