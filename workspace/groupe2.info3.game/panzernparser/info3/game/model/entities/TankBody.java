package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.model.Model;
import info3.game.model.Tank;

/**
 * Chassis du tank
 */
public class TankBody extends MovingEntity {

	public final static int TANKBODY_WIDTH = 1;
	public final static int TANKBODY_HEIGHT = 1;

	public static final int TANKBODY_HEALTH = 100;
	public static final int TANKBODY_SPEED = 100;

	public static final long TANKBODY_EGG_TIME = 1000;
	public static final long TANKBODY_GET_TIME = 1000;
	public static final long TANKBODY_HIT_TIME = 1000;
	public static final long TANKBODY_JUMP_TIME = 50;
	public static final long TANKBODY_EXPLODE_TIME = 1000;
	public static final long TANKBODY_MOVE_TIME = 1000;
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

	@Override
	public void step(long elapsed) {
		if (m_currentAction != null) {
			if (m_elapseTime > m_timeOfAction) {
				m_elapseTime = 0;
				m_currentAction = null;
			} else {
				m_elapseTime += elapsed;
			}
		} else {
			this.setState(m_automate.step(this));
		}
	}

	public void setTank(Tank tank) {
		m_tank = tank;
	}
	
	@Override
	public void Egg(MyDirection dir) {
		m_timeOfAction = TANKBODY_MOVE_TIME;
		System.out.println("Egg !");
		super.Egg(dir);
	}

	@Override
	public void Explode() {
		m_timeOfAction = TANKBODY_EXPLODE_TIME;
		System.out.println("Explode !");
		super.Explode();
	}

	@Override
	public void Get(MyDirection dir) {
		m_timeOfAction = TANKBODY_GET_TIME;
		System.out.println("Get !");
		super.Get(dir);
	}

	@Override
	public void Hit(MyDirection dir) {
		m_timeOfAction = TANKBODY_HIT_TIME;
		System.out.println("Hit !");
		super.Hit(dir);
	}

	@Override
	public void Jump(MyDirection dir) {
		m_timeOfAction = TANKBODY_JUMP_TIME;
		System.out.println("TankBody is Jumping !");
		super.Jump(dir);
	}

	@Override
	public void Move(MyDirection dir) {
		m_timeOfAction = TANKBODY_MOVE_TIME;
		System.out.println("Move !");
		super.Move(dir);
	}

	@Override
	public void Pick(MyDirection dir) {
		m_timeOfAction = TANKBODY_PICK_TIME;
		System.out.println("Pick !");
		super.Pick(dir);
	}

	@Override
	public void Pop(MyDirection dir) {
		m_timeOfAction = TANKBODY_POP_TIME;
		System.out.println("Pop !");
		super.Pop(dir);
	}

	@Override
	public void Power() {
		m_timeOfAction = TANKBODY_POWER_TIME;
		System.out.println("Power !");
		super.Power();
	}

	@Override
	public void Protect(MyDirection dir) {
		if (m_tank.hasControl()) {
			m_timeOfAction = TANKBODY_PROTECT_TIME;
			System.out.println("Protect !");
			super.Protect(dir);
		}
	}

	@Override
	public void Store(MyDirection dir) {
		m_timeOfAction = TANKBODY_STORE_TIME;
		System.out.println("Store !");
		super.Store(dir);
	}

	@Override
	public void Throw(MyDirection dir) {
		m_timeOfAction = TANKBODY_THROW_TIME;
		System.out.println("Throw !");
		super.Throw(dir);
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		if (m_tank.hasControl()) {
			m_timeOfAction = TANKBODY_TURN_TIME;
			System.out.println("Turn !");
			super.Turn(dir, angle);
		}
	}

	@Override
	public void Wait() {
		m_timeOfAction = TANKBODY_WAIT_TIME;
		// System.out.println("Wait !");
		super.Wait();
	}

	@Override
	public void Wizz(MyDirection dir) {
		if (m_tank.hasControl()) {
			m_timeOfAction = TANKBODY_WIZZ_TIME;
			Model.getModel().switchControl();
			System.out.println("TANK fait le wizz");
			super.Wizz(dir);
		}
	}
}
