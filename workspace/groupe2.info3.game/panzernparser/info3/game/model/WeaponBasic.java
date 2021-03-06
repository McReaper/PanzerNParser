package info3.game.model;

import info3.game.automaton.MyDirection;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.Shot;
import info3.game.model.entities.Turret;

public class WeaponBasic extends Weapon {
	public static final int WEAPONBASIC_MAG_CAPACITY = 10;
	public static final long WEAPONBASIC_RELOAD = 3000;

	public WeaponBasic(Turret t) {
		super(WEAPONBASIC_MAG_CAPACITY, WEAPONBASIC_RELOAD, t);
	}

	@Override
	public void fire(MyDirection dir) {
		if (m_nbShotsLeft >0) {
			int pos_x = m_turret.getXCaseDir(dir);
			int pos_y = m_turret.getYCaseDir(dir);
			Entity ent = EntityFactory.newEntity(MyEntities.ShotSlow, pos_x, pos_y);
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
