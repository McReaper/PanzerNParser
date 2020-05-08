package info3.game.model;

import info3.game.automaton.MyDirection;
import info3.game.model.entities.Turret;

public abstract class Weapon {
	protected Turret m_turret;
	protected int m_magCapacity;
	protected long m_reload;
	protected int m_nbShotsLeft;

	public Weapon(int mag_capacity, long reload, Turret t) {
		m_turret = t;
		m_reload = reload;
		m_magCapacity = mag_capacity;
		m_nbShotsLeft = m_magCapacity;
	}
	
	public void reload() {
		m_nbShotsLeft = m_magCapacity;
		m_turret.setStuff(true);
	}

	public void improveReload(long improvement) {
		m_reload -= improvement;
	}

	public void improveMagazin(double factor) {
		m_magCapacity *= factor;
	}
	
	public long getReloadTime() {
		return m_reload;
	}

// declenche le signal de rechargement
	public void needsReload() {
		if (isEmpty())
			m_turret.setStuff(false);
	}

//regarde si chargeur est vide
	public boolean isEmpty() {
		return m_nbShotsLeft <= 0;
	}
	
	public int getCapacity() {
		return m_magCapacity;
	}
	
	public int getNbShotLeft() {
		return m_nbShotsLeft;
	}
 	
	public abstract void fire(MyDirection dir);
}
