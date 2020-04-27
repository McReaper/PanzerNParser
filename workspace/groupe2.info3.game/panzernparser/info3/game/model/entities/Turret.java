package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Tank;

/**
 * Classe du canon du tank
 */
public class Turret extends MovingEntity {

	public final static int TURRET_WIDTH = 2;
	public final static int TURRET_HEIGHT = 2;

	public static final int TURRET_HEALTH = 100;
	public static final int TURRET_SPEED = 100;

	public static final long TURRET_EGG_TIME = 1000;
	public static final long TURRET_GET_TIME = 1000;
	public static final long TURRET_HIT_TIME = 300;
	public static final long TURRET_JUMP_TIME = 1000;
	public static final long TURRET_EXPLODE_TIME = 1000;
	public static final long TURRET_MOVE_TIME = 1000;
	public static final long TURRET_PICK_TIME = 1000;
	public static final long TURRET_POP_TIME = 10000;
	public static final long TURRET_POWER_TIME = 1000;
	public static final long TURRET_PROTECT_TIME = 1000;
	public static final long TURRET_STORE_TIME = 1000;
	public static final long TURRET_TURN_TIME = 200;
	public static final long TURRET_THROW_TIME = 1000;
	public static final long TURRET_WAIT_TIME = 0;
	public static final long TURRET_WIZZ_TIME = 1000;

	private Tank m_tank;

	public Turret(int x, int y, int width, int height, int health, int speed, Automaton aut) {
		super(x, y, width, height, health, speed, aut);
		m_tank = null;
	}

	public void setTank(Tank tank) {
		m_tank = tank;
	}

	public Tank getTank() {
		return m_tank;
	}

	@Override
	public void Hit(MyDirection dir) {
		if (m_tank.hasControl()) {
			if (m_actionFinished && m_currentAction == LsAction.Hit) {
				System.out.println("FIRE !");
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Hit;
				m_timeOfAction = TURRET_HIT_TIME;
			}
		}
	}

	@Override
	public void Pop(MyDirection dir) {
		if (m_tank.hasControl()) {
			if (m_actionFinished && m_currentAction == LsAction.Pop) {
				System.out.println("Changement d'arme !");
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Pop;
				m_timeOfAction = TURRET_POP_TIME;
			}
		}
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		if (m_tank.hasControl()) {
			if (m_actionFinished && m_currentAction == LsAction.Turn) {
				System.out.println("Turret turning !");
				this.doTurn(dir);
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Turn;
				m_timeOfAction = TURRET_TURN_TIME;
			}
		}
	}

	@Override
	public void Wizz(MyDirection dir) {
		if (m_tank.hasControl()) {
			if (m_actionFinished && m_currentAction == LsAction.Wizz) {
				System.out.println("La tourelle recharge.");
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Wizz;
				m_timeOfAction = TURRET_WIZZ_TIME;
			}
		}
	}

}
