package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.EntityFactory.MyEntities;

public class ShotFast extends Shot {
	public static final int SHOTFAST_WIDTH = 1;
	public static final int SHOTFAST_HEIGHT = 1;

	public static final int SHOTFAST_HEALTH = 100;
	public static final int SHOTFAST_SPEED = 100;
	public static final int SHOTFAST_NUMBER_CASE_LIFE = 10;

	public static final long SHOTFAST_EGG_TIME = 1000;
	public static final long SHOTFAST_GET_TIME = 2000;
	public static final long SHOTFAST_HIT_TIME = 1000;
	public static final long SHOTFAST_JUMP_TIME = 1000;
	public static final long SHOTFAST_EXPLODE_TIME = 1000;
	public static final long SHOTFAST_MOVE_TIME = 200;
	public static final long SHOTFAST_PICK_TIME = 1000;
	public static final long SHOTFAST_POP_TIME = 10000;
	public static final long SHOTFAST_POWER_TIME = 1000;
	public static final long SHOTFAST_PROTECT_TIME = 1000;
	public static final long SHOTFAST_STORE_TIME = 1000;
	public static final long SHOTFAST_TURN_TIME = 1000;
	public static final long SHOTFAST_THROW_TIME = 1000;
	public static final long SHOTFAST_WAIT_TIME = 50;
	public static final long SHOTFAST_WIZZ_TIME = 1000;

	public static final int SHOTFAST_DAMMAGE_DEALT = 100;

	public ShotFast(int x, int y, Automaton aut) {
		super(x, y, SHOTFAST_WIDTH, SHOTFAST_HEIGHT, aut);
		m_health = SHOTFAST_HEALTH;
		m_dammage_dealt = SHOTFAST_DAMMAGE_DEALT;
		m_speed = SHOTFAST_SPEED;
	}

	public void Move(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Move) {
			this.doMove(dir);
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_health -= SHOTFAST_NUMBER_CASE_LIFE;
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
			m_timeOfAction = SHOTFAST_EXPLODE_TIME;
		}
	}

	public void Egg(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Egg) {
			m_actionFinished = false;
			m_currentAction = null;
			Entity ent = EntityFactory.newEntityShot(MyEntities.Shot, this.m_x, m_y, Turret.GUN_BULLET_FAST);
			Entity ent2 = EntityFactory.newEntityShot(MyEntities.Shot, this.m_x, m_y,Turret.GUN_BULLET_FAST);

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
			m_timeOfAction = SHOTFAST_EGG_TIME;
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
			m_timeOfAction = SHOTFAST_EXPLODE_TIME;
		}
	}

}
