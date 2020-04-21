package info3.game.model.entities;

import info3.game.model.Model;

/**
 * Une entité représentant un tir simple, infligeant des dégats à d'autre
 * MovingEntity.
 */
public class Shot extends MovingEntity {
	/* Champs pour donner size par defaut dans la EntityFactory */
	/* a voir comment les shots sont créés (factory ?)*/
	final static int SHOT_WIDTH = 1;
	final static int SHOT_HEIGHT = 3;

	public Shot(int x, int y, int width, int height, int health, int speed, Model model) {
		super(x, y, width, height, health, speed, model);
		// TODO Auto-generated constructor stub
	}

	int m_damage;

	@Override
	public void step(long elapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getActionProgress() {
		// TODO Auto-generated method stub
		return 0;
	}

}
