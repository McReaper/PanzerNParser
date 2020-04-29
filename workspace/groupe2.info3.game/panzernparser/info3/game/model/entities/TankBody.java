package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Tank;

/**
 * Chassis du tank
 */
public class TankBody extends MovingEntity {

	public final static int TANKBODY_WIDTH = Tank.TANK_WIDTH;
	public final static int TANKBODY_HEIGHT = Tank.TANK_HEIGHT;

	public static final int TANKBODY_HEALTH = Tank.TANK_HEALTH;
	public static final int TANKBODY_SPEED = Tank.TANK_SPEED;

	public static final long TANKBODY_EGG_TIME = 1000;
	public static final long TANKBODY_GET_TIME = 1000;
	public static final long TANKBODY_HIT_TIME = 1000;
	public static final long TANKBODY_JUMP_TIME = 1000;
	public static final long TANKBODY_EXPLODE_TIME = 1000;
	public static final long TANKBODY_MOVE_TIME = 800;
	public static final long TANKBODY_PICK_TIME = 1000;
	public static final long TANKBODY_POP_TIME = 10000;
	public static final long TANKBODY_POWER_TIME = 1000;
	public static final long TANKBODY_PROTECT_TIME = 1000;
	public static final long TANKBODY_STORE_TIME = 1000;
	public static final long TANKBODY_TURN_TIME = 0;
	public static final long TANKBODY_THROW_TIME = 1000;
	public static final long TANKBODY_WAIT_TIME = 50;
	public static final long TANKBODY_WIZZ_TIME = 1000;

	private Tank m_tank;

	public TankBody(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, Tank.TANK_HEALTH, Tank.TANK_SPEED, aut);
		m_tank = null;
		m_category = MyCategory.AT;
	}

	public void setTank(Tank tank) {
		m_tank = tank;
	}

	@Override
	public void Move(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Move) {
			this.doMove(dir);
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			MyDirection absoluteDir = MyDirection.toAbsolute(m_currentActionDir, dir);
			switch (absoluteDir) {
				case NORTH:
				case EAST:
				case WEST:
				case SOUTH:
					m_timeOfAction = TANKBODY_MOVE_TIME;
					break;
				case NORTHEAST:
				case NORTHWEST:
				case SOUTHEAST:
				case SOUTHWEST:
					m_timeOfAction = (long) Math.sqrt(2 * TANKBODY_MOVE_TIME * TANKBODY_MOVE_TIME);
				default:
					break;
			}
			m_currentActionDir = dir;
			m_currentAction = LsAction.Move;
		}
	}

	@Override
	public void Pop(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = TANKBODY_POP_TIME;
		}
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		if (m_actionFinished && m_currentAction == LsAction.Turn) {
			this.doTurn(dir);
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Turn;
			m_timeOfAction = TANKBODY_TURN_TIME;
		}
	}

	@Override
	public void Wizz(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			Model.getModel().switchControl();
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = TANKBODY_WIZZ_TIME;
		}
	}

	@Override
	public void Explode() {
		/*
		 * TODO faire un GAME OVER
		 */
		if (m_actionFinished && m_currentAction == LsAction.Explode) {
			// m_tank.doExplode();
			m_tank.setLife(Tank.TANK_HEALTH);// je redonne de la vie le temps qu'on a pas fait le cas de GAME OVER
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Explode;
			m_timeOfAction = TANKBODY_EXPLODE_TIME;
		}
	}

	@Override
	public boolean Key(LsKey key) {
		if (m_tank.hasControl())
			return super.Key(key);
		return false;
	}

	@Override
	public void collide() {
		m_tank.getDamages(50 /*
													 * TODO (Victor) : d'un point de vue objet il faudrait que le collide passe un
													 * objet.
													 */);
	}
	
	@Override
	public boolean GotPower() {
		return m_tank.gotPower();
	}

}
