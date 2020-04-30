package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.EntityFactory.MyEntities;

public class ShotBig extends Shot {
	public static final int SHOTBIG_WIDTH = 3;
	public static final int SHOTBIG_HEIGHT = 3;

	public static final int SHOTBIG_HEALTH = 100;
	public static final int SHOTBIG_SPEED = 100;
	public static final int SHOTBIG_NUMBER_CASE_LIFE = 10;

	public static final long SHOTBIG_EGG_TIME = 1000;
	public static final long SHOTBIG_GET_TIME = 2000;
	public static final long SHOTBIG_HIT_TIME = 1000;
	public static final long SHOTBIG_JUMP_TIME = 1000;
	public static final long SHOTBIG_EXPLODE_TIME = 1000;
	public static final long SHOTBIG_MOVE_TIME = 200;
	public static final long SHOTBIG_PICK_TIME = 1000;
	public static final long SHOTBIG_POP_TIME = 10000;
	public static final long SHOTBIG_POWER_TIME = 1000;
	public static final long SHOTBIG_PROTECT_TIME = 1000;
	public static final long SHOTBIG_STORE_TIME = 1000;
	public static final long SHOTBIG_TURN_TIME = 1000;
	public static final long SHOTBIG_THROW_TIME = 1000;
	public static final long SHOTBIG_WAIT_TIME = 50;
	public static final long SHOTBIG_WIZZ_TIME = 1000;

	public static final int SHOTBIG_DAMMAGE_DEALT = 100;

	public ShotBig(int x, int y, Automaton aut) {
		super(x, y, SHOTBIG_WIDTH, SHOTBIG_HEIGHT, aut);
		m_health = SHOTBIG_HEALTH;
		m_dammage_dealt = SHOTBIG_DAMMAGE_DEALT;
		m_speed = SHOTBIG_SPEED;
	}

	public void Move(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Move) {
			this.doMove(dir);
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_health -= SHOTBIG_NUMBER_CASE_LIFE;
			MyDirection absoluteDir = MyDirection.toAbsolute(m_currentActionDir, dir);
			switch (absoluteDir) {
				case NORTH:
				case EAST:
				case WEST:
				case SOUTH:
					m_timeOfAction = m_speed;
					break;
				case NORTHEAST:
				case NORTHWEST:
				case SOUTHEAST:
				case SOUTHWEST:
					m_timeOfAction = (long) Math.sqrt(2 * m_speed * m_speed);
				default:
					break;
			}
			m_currentActionDir = dir;
			m_currentAction = LsAction.Move;
		}
	}

	public void Explode(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Explode) {
			this.doExplode();
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Explode;
			m_timeOfAction = SHOTBIG_EXPLODE_TIME;
		}
	}

	public void Egg(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Egg) {
			m_actionFinished = false;
			m_currentAction = null;
			Entity ent = EntityFactory.newEntityShot(MyEntities.Shot, this.m_x, m_y, Turret.GUN_BIG_BULLET);
			Entity ent2 = EntityFactory.newEntityShot(MyEntities.Shot, this.m_x, m_y,Turret.GUN_BIG_BULLET);

			// Donne la direction de regard et d'action
			ent.setLookDir(MyDirection.toAbsolute(m_currentLookAtDir, MyDirection.LEFT));
			ent.setActionDir(MyDirection.toAbsolute(m_currentActionDir, MyDirection.LEFT));

			// Donne la direction de regard et d'action
			ent2.setLookDir(MyDirection.toAbsolute(m_currentLookAtDir, MyDirection.RIGHT));
			ent2.setActionDir(MyDirection.toAbsolute(m_currentActionDir, MyDirection.RIGHT));

			// Donne l'entité qui l'a tiré
			((Shot) ent).setOwner(this);
			((Shot) ent2).setOwner(this);
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Egg;
			m_timeOfAction = SHOTBIG_EGG_TIME;
		}
	}

	@Override
	public void Explode() {
		if (m_actionFinished && m_currentAction == LsAction.Explode) {
			this.doExplode();
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Explode;
			m_timeOfAction = SHOTBIG_EXPLODE_TIME;
		}
	}

}
