package info3.game.model.entities;

import info3.game.model.Model;

/**
 * Une entité représentant un tir simple, infligeant des dégats à d'autre
 * MovingEntity.
 */
public class Shot extends MovingEntity {

	public Shot(int x, int y, int width, int height, int health, double timetotravel, Model model) {
		super(x, y, width, height, health, timetotravel, model);
		// TODO Auto-generated constructor stub
	}

	int m_damage;

	@Override
	public void step(long elapsed) {
		// TODO Auto-generated method stub

	}

}
