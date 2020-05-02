package info3.game.model;

import info3.game.automaton.MyDirection;
import info3.game.model.entities.Turret;

public abstract class Weapon {
	protected Turret m_turret;
	protected int m_magCapacity;
	protected long m_reload;
	protected int m_nbShotsLeft;
	protected int m_y;// enplacement de la turret
	protected int m_x;// TODO renomer si besoin

	public Weapon(int mag_capacity, long reload) {
		m_x = m_turret.getX();
		m_y = m_turret.getY();
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

	public abstract void fire(MyDirection dir);
}
