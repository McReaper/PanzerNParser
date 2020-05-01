package info3.game.model;

import info3.game.model.entities.Drone;

public abstract class Upgrade implements UpgradeListener {

	protected Tank m_tank;
	protected Drone m_drone;
	protected int m_level;
	
	public Upgrade(Tank tank, Drone drone) {
		m_tank = tank;
		m_drone = drone;
		m_level = 0;
	}
	
	public void activate() throws IllegalAccessException {
		throw new IllegalAccessException("Vous ne pouvez pas appelé cette méthode sur cette upgrade.");
	}

	public void deactivate() throws IllegalAccessException {
		throw new IllegalAccessException("Vous ne pouvez pas appelé cette méthode sur cette upgrade.");
	}

	public boolean isAvaible() throws IllegalAccessException {
		return false;
	}

	public void improve() throws IllegalAccessException {
		throw new IllegalAccessException("Vous ne pouvez pas appelé cette méthode sur cette upgrade.");
	}
	
	public int getLevel() {
		return m_level;
	}

}
