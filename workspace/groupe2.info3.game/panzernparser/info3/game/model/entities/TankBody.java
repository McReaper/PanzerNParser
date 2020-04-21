package info3.game.model.entities;

import info3.game.model.Model;

/**
 * Chassis du tank
 */
public class TankBody extends MovingEntity {

	public TankBody(int x, int y, int width, int height, int health, int speed, Model model) {
		super(x, y, width, height, health, speed, model);
		// TODO Auto-generated constructor stub
	}

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
