package info3.game.model;

import info3.game.automaton.MyDirection;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.Shot;
import info3.game.model.entities.ShotBig;
import info3.game.model.entities.ShotFast;
import info3.game.model.entities.ShotSlow;
import info3.game.model.entities.Turret;

public abstract class Weapon {
	
	protected int m_x;//TODO renomer si besoin
	protected int m_y;//enplacement de la turret
	protected int m_nbShots;
	protected long m_reload;
	protected int m_magCapacity;
	protected MyEntities m_typeBullet;
	protected Turret m_turret;

		public Weapon(MyDirection dir) {
		}
		
		public MyEntities getTypeBullet() {
			return m_typeBullet;
		}
		
		public void setTypeBullet(MyEntities type) {
			m_typeBullet = type;
		}
		
		public abstract void fire();
}
