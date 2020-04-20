package info3.game.model.entities;

/**
 * Une entité représentant un tir simple, infligeant des dégats à d'autre
 * MovingEntity.
 */
public class Shot extends MovingEntity {

	public Shot(int x, int y, int width, int height, int health, double timetotravel) {
		super(x, y, width, height, health, timetotravel);
		// TODO Auto-generated constructor stub
	}

	int m_damage;

	@Override
	public void step(long elapsed) {
		// TODO Auto-generated method stub

	}

}
