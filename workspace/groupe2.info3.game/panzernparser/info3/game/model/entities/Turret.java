package info3.game.model.entities;

/**
 * Classe du canon du tank
 */
public class Turret extends MovingEntity {

	public Turret(int x, int y, int width, int height, int health, double timetotravel) {
		super(x, y, width, height, health, timetotravel);
		// TODO Auto-generated constructor stub
	}

	int m_range;

	@Override
	public void step(long elapsed) {
		// TODO Auto-generated method stub

	}

}
