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

public class WeaponBasic extends Weapon{
	public static final int WEAPONBASIC_MAG_CAPACITY = 10;
	
	public WeaponBasic() {
		super();
	}

	@Override
	public void fire(MyDirection dir) {
		// creation du shot en fonction de l'arme
			int pos_x = m_x;
			int pos_y = m_y;
			Entity ent = EntityFactory.newEntity(MyEntities.ShotSlow, pos_x, pos_y);
					break;
			// Donne la direction de regard et d'action
			ent.setLookDir(MyDirection.toAbsolute(m_turret.getLookAtDir(), dir));
			ent.setActionDir(MyDirection.toAbsolute(m_turret.getCurrentActionDir(), dir));

			// Donne l'entité qui l'a tiré (ici le tankBody)
			((Shot) ent).setOwner(Model.getModel().getTank().getBody());

		
	}
	
}
