package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.LsKey;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Tank;

/**
 * Chassis du tank
 */
public class TankBody extends MovingEntity {

	public final static int TANKBODY_WIDTH = 3;
	public final static int TANKBODY_HEIGHT = 3;

	public static final int TANKBODY_HEALTH = 100;
	public static final int TANKBODY_SPEED = 100;

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
	// public int m_range;

	public TankBody(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, Tank.TANK_HEALTH, Tank.TANK_SPEED, aut);
		m_tank = null;
	}

	public void setTank(Tank tank) {
		m_tank = tank;
	}

	@Override
	public void Move(MyDirection dir) {
			if (m_actionFinished && m_currentAction == LsAction.Move) {
				System.out.println("Tank fait le move !");
				this.doMove(dir);
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Move;
				m_timeOfAction = TANKBODY_MOVE_TIME;
			}
	}

	@Override
	public void Pop(MyDirection dir) {
			if (m_actionFinished && m_currentAction == LsAction.Pop) {
				System.out.println("Tank fait le Pop !");
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
				System.out.println("Tank fait le Turn !");
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
				System.out.println("TANK fait le wizz");
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Wizz;
				m_timeOfAction = TANKBODY_WIZZ_TIME;
			}
	}
	
	@Override
	public boolean Key(LsKey key) {
		if (m_tank.hasControl())
			return super.Key(key);
		return false;
	}
}
