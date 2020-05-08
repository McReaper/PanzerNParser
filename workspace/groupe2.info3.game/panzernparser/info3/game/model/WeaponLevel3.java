package info3.game.model;

import info3.game.automaton.MyDirection;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.Shot;
import info3.game.model.entities.ShotBig;
import info3.game.model.entities.Turret;

public class WeaponLevel3 extends Weapon{
	public static final int WEAPONLEVEL3_MAG_CAPACITY = 3;
	public static final long WEAPONLEVEL3_RELOAD = 5000;

	public WeaponLevel3(Turret t) {
		super(WEAPONLEVEL3_MAG_CAPACITY, WEAPONLEVEL3_RELOAD, t);
	}

	@Override
	public void fire(MyDirection dir) {	
		if (m_nbShotsLeft >0) {
			int pos_x = m_turret.getXCaseDir(dir);
			int pos_y = m_turret.getYCaseDir(dir);
			switch (MyDirection.toAbsolute(m_turret.getLookAtDir(), dir)) {
				case EAST:
					pos_y -= ShotBig.SHOTBIG_HEIGHT/2;
					break;
				case NORTH:
					pos_x -= ShotBig.SHOTBIG_WIDTH/2;
					break;
				case NORTHEAST:
					pos_x -= ShotBig.SHOTBIG_WIDTH/2;
					pos_y -= ShotBig.SHOTBIG_HEIGHT/2;
					break;
				case SOUTH:
					pos_x -= ShotBig.SHOTBIG_WIDTH/2;
					break;
				case SOUTHEAST:
					pos_x -= ShotBig.SHOTBIG_WIDTH/2;
					pos_y -= ShotBig.SHOTBIG_HEIGHT/2;
					break;
				case SOUTHWEST:
					pos_x -= ShotBig.SHOTBIG_WIDTH/2;
					pos_y -= ShotBig.SHOTBIG_HEIGHT/2;
					break;
				case WEST:
					pos_y -= ShotBig.SHOTBIG_HEIGHT/2;
					break;
				default:
					break;
			}
			Entity ent = EntityFactory.newEntity(MyEntities.ShotBig, pos_x, pos_y);
			// Donne la direction de regard et d'action
			ent.setLookDir(MyDirection.toAbsolute(m_turret.getLookAtDir(), dir));
			ent.setActionDir(MyDirection.toAbsolute(m_turret.getCurrentActionDir(), dir));
			ent.setDamage(m_turret.getDamageDealt());
			// Donne l'entité qui l'a tiré (ici le tankBody)
			((Shot) ent).setOwner(Model.getModel().getTank().getBody());
		}
		m_nbShotsLeft--;
		needsReload();
	}
}
