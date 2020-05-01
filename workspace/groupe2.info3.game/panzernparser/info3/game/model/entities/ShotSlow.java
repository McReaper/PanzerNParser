package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.EntityFactory.MyEntities;

public class ShotSlow extends Shot {
	public static final int SHOTSLOW_WIDTH = 1;
	public static final int SHOTSLOW_HEIGHT = 1;

	public static final int SHOTSLOW_HEALTH = 100;
	public static final int SHOTSLOW_SPEED = 2000;
	public static final int SHOTSLOW_NUMBER_CASE_LIFE = 10;

	public static final long SHOTSLOW_EGG_TIME = 1000;
	public static final long SHOTSLOW_GET_TIME = 2000;
	public static final long SHOTSLOW_HIT_TIME = 1000;
	public static final long SHOTSLOW_JUMP_TIME = 1000;
	public static final long SHOTSLOW_EXPLODE_TIME = 1000;
	public static final long SHOTSLOW_MOVE_TIME = 200;
	public static final long SHOTSLOW_PICK_TIME = 1000;
	public static final long SHOTSLOW_POP_TIME = 10000;
	public static final long SHOTSLOW_POWER_TIME = 1000;
	public static final long SHOTSLOW_PROTECT_TIME = 1000;
	public static final long SHOTSLOW_STORE_TIME = 1000;
	public static final long SHOTSLOW_TURN_TIME = 1000;
	public static final long SHOTSLOW_THROW_TIME = 1000;
	public static final long SHOTSLOW_WAIT_TIME = 50;
	public static final long SHOTSLOW_WIZZ_TIME = 1000;

	public static final int SHOTSLOW_DAMMAGE_DEALT = 50;

	public ShotSlow(int x, int y, Automaton aut) {
		super(x, y, SHOTSLOW_WIDTH, SHOTSLOW_HEIGHT, aut);
		m_health = SHOTSLOW_HEALTH;
		m_dammage_dealt = SHOTSLOW_DAMMAGE_DEALT;
		m_speed = SHOTSLOW_SPEED;
	}
	
	@Override
	public void Move(MyDirection dir) {
		m_health -= SHOTSLOW_NUMBER_CASE_LIFE / 2; // TODO : revoir pour la durer de vie des balles dans les diagonales
		super.Move(dir);
	}

//	public void Egg(MyDirection dir) {
//		if (m_actionFinished && m_currentAction == LsAction.Egg) {
//			m_actionFinished = false;
//			m_currentAction = null;
//			Entity ent = EntityFactory.newEntityShot(MyEntities.Shot, this.m_x, m_y, Turret.GUN_BULLET_SLOW);
//			Entity ent2 = EntityFactory.newEntityShot(MyEntities.Shot, this.m_x, m_y, Turret.GUN_BULLET_SLOW);
//
//			// Donne la direction de regard et d'action
//			ent.setLookDir(MyDirection.toAbsolute(m_currentLookAtDir, MyDirection.LEFT));
//			ent.setActionDir(MyDirection.toAbsolute(m_currentActionDir, MyDirection.LEFT));
//
//			// Donne la direction de regard et d'action
//			ent2.setLookDir(MyDirection.toAbsolute(m_currentLookAtDir, MyDirection.RIGHT));
//			ent2.setActionDir(MyDirection.toAbsolute(m_currentActionDir, MyDirection.RIGHT));
//
//			// Donne l'entité qui l'a tiré
//			((Shot) ent).setOwner(this);
//			((Shot) ent2).setOwner(this);
//		} else if (m_currentAction == null) {
//			m_currentAction = LsAction.Egg;
//			m_timeOfAction = SHOTSLOW_EGG_TIME;
//		}
//	}

	@Override
	public void Explode() {
		if (m_actionFinished && m_currentAction == LsAction.Explode) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			this.doExplode();
			m_currentAction = LsAction.Explode;
			m_timeOfAction = SHOTSLOW_EXPLODE_TIME;
		}
	}
}