package info3.game.model.entities;

import info3.game.model.Model;

/**
 * Classe du canon du tank
 */
public class Turret extends MovingEntity {

	public Turret(int x, int y, int width, int height, int health, double timetotravel, Model model) {
		super(x, y, width, height, health, timetotravel, model);
		// TODO Auto-generated constructor stub
	}

	int m_range;

	@Override
	public void step(long elapsed) {
		// TODO Auto-generated method stub

	}

}
