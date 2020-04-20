package info3.game.model.entities;

import info3.game.automaton.MyDirection;

public abstract class MovingEntity extends Entity {

	int m_health;
	MyDirection m_orientation;
	double m_speed;
	// m_viewPort; //Dans la phase de tests on a pas de viewport...

	public MovingEntity(int x, int y, int width, int height, int health, double timetotravel) {
		super(x, y, width, height);
		m_health = health;
		m_speed = (double) 1 / timetotravel;
		m_orientation = MyDirection.NORTH; // Valeur par défaut
	}

}
