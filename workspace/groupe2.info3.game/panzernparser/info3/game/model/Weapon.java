package info3.game.model;

import info3.game.automaton.MyDirection;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.Shot;
import info3.game.model.entities.ShotBig;
import info3.game.model.entities.ShotFast;
import info3.game.model.entities.ShotSlow;

public abstract class Weapon {
//	public enum TypeWeapon {
//		GUN_BULLET_SLOW ,GUN_BULLET_FAST ,GUN_BIG_BULLET ;
//	}
	
	private int m_x;//TODO renomer si besoin
	private int m_y;//enplacement de la turret
	private int m_nbShots;
	private long m_reload;
	private MyEntities m_typeBullet;

		public Weapon() {
		}
		
		public MyEntities getTypeBullet() {
			return m_typeBullet;
		}
		
		public void setTypeBullet(MyEntities type) {
			m_typeBullet = type;
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
