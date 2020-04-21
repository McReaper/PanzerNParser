package info3.game.model.entities;

import info3.game.automaton.MyDirection;
import info3.game.model.Model;

public abstract class MovingEntity extends Entity {

	int m_health;
	MyDirection m_orientation;
	double m_speed;
	// m_viewPort; //Dans la phase de tests on a pas de viewport...

	public MovingEntity(int x, int y, int width, int height, int health, double timetotravel, Model model) {
		super(x, y, width, height, model);
		m_health = health;
		m_speed = (double) 1 / timetotravel;
		m_orientation = MyDirection.NORTH; // Valeur par défaut
	}

}
