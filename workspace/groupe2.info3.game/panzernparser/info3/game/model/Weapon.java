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

	public void improveReload(long improvement) {
		m_reload -= improvement;
	}

	public void imrpoveMagazin(int improvement) {
		m_magCapacity += improvement;
	}
	public abstract void reloading();//recharge
	public abstract boolean isEmpty();//regarde si chargeur est vide
	public abstract void fire(MyDirection dir);
}
