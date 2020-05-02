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
//	public enum TypeWeapon {
//		GUN_BULLET_SLOW ,GUN_BULLET_FAST ,GUN_BIG_BULLET ;
//	}
	private Turret m_turret;
	private int m_x;//TODO renomer si besoin
	private int m_y;//enplacement de la turret
	private int m_mag_capacity;//capacité chargeur (nombre de shots dedans)
	private long m_reload;//temps de rechargement
	private MyEntities m_typeBullet;

		public Weapon(int mag_capacity, int reload, MyEntities typeBullet) {
			m_x = m_turret.getX();
			m_y = m_turret.getY();
			m_reload = reload;
			m_mag_capacity = mag_capacity;
			m_typeBullet = typeBullet;
		}
		
		public MyEntities getTypeBullet() {
			return m_typeBullet;
		}
		
		public void setTypeBullet(MyEntities type) {
			m_typeBullet = type;
		}
		
		public void upgradeMagazin(int capacity) {
			m_mag_capacity += capacity;
		}
		
		public void upgradeMagazin(long reload) {
			m_reload -= reload;
		}
		
		public void fire() {
		// creation du shot en fonction de l'arme
//					int pos_x = m_x;
//					int pos_y = m_y;
//					Entity ent;
//					switch (m_typeShot) {
//						case Turret.GUN_BIG_BULLET:
//							pos_x = m_x + m_width / 2 - ShotBig.SHOTBIG_WIDTH / 2;
//							pos_y = m_y + m_height / 2 - ShotBig.SHOTBIG_HEIGHT / 2;
//							ent = EntityFactory.newEntity(MyEntities.ShotBig, pos_x, pos_y);
//							break;
//						case GUN_BULLET_FAST:
//							pos_x = m_x + m_width / 2 - ShotFast.SHOTFAST_WIDTH / 2;
//							pos_y = m_y + m_height / 2 - ShotFast.SHOTFAST_HEIGHT / 2;
//							ent = EntityFactory.newEntity(MyEntities.ShotFast, pos_x, pos_y);
//							break;
//						case GUN_BULLET_SLOW:
//							pos_x = m_x + m_width / 2 - ShotSlow.SHOTSLOW_WIDTH / 2;
//							pos_y = m_y + m_height / 2 - ShotSlow.SHOTSLOW_HEIGHT / 2;
//							ent = EntityFactory.newEntity(MyEntities.ShotSlow, pos_x, pos_y);
//							default :
//								ent = EntityFactory.newEntity(MyEntities.ShotSlow, pos_x, pos_y);
//							break;
//
//					}
//					// Donne la direction de regard et d'action
//					ent.setLookDir(MyDirection.toAbsolute(this.m_currentLookAtDir, dir));
//					ent.setActionDir(MyDirection.toAbsolute(this.m_currentActionDir, dir));
//
//					// Donne l'entité qui l'a tiré (ici le tankBody)
//					((Shot) ent).setOwner(m_tank.getBody());

		}
}
