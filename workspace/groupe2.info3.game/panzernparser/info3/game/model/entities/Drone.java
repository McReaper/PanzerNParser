package info3.game.model.entities;

import info3.game.automaton.State;
import info3.game.model.Model;

public class Drone extends MovingEntity {

	public Drone(int x, int y, int width, int height, int health, int speed, Model model) {
		super(x, y, width, height, health, speed, model);
		// TODO Auto-generated constructor stub
	}

	private enum VisionType {
		RESSOURCES, ENEMIES;
	}

	VisionType m_visionType;

	@Override
	public void step(long elapsed) {
		State newState = m_automate.step(this);
		if (newState != null)
			this.setState(newState);
	}

	@Override
	public double getActionProgress() {
		// TODO Auto-generated method stub
		return 0;
	}

}
