package info3.game.model.entities;

import info3.game.automaton.Automaton;

public abstract class MovingEntity extends Entity {

	int m_health;

	public MovingEntity(int x, int y, int width, int height, int health, int speed, Automaton aut) {
		super(x, y, width, height, aut);
		m_health = health;
		m_speed = speed;
	}

	@Override
	public boolean GotPower() {
		return m_health > 0;
	}

}
