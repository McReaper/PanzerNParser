package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;

/**
 * Chassis du tank
 */
public class TankBody extends MovingEntity {
	public final static int TANK_BODY_WIDTH = 1;
	public final static int TANK_BODY_HEIGHT = 1;
	
	public static final int TANK_BODY_HEALTH = 100;
	public static final int TANK_BODY_SPEED = 100;

	public static final long TANK_BODY_EGG_TIME = 1000;
	public static final long TANK_BODY_GET_TIME = 1000;
	public static final long TANK_BODY_HIT_TIME = 1000;
	public static final long TANK_BODY_JUMP_TIME = 1000;
	public static final long TANK_BODY_EXPLODE_TIME = 1000;
	public static final long TANK_BODY_MOVE_TIME = 200;
	public static final long TANK_BODY_PICK_TIME = 1000;
	public static final long TANK_BODY_POP_TIME = 10000;
	public static final long TANK_BODY_POWER_TIME = 1000;
	public static final long TANK_BODY_PROTECT_TIME = 1000;
	public static final long TANK_BODY_STORE_TIME = 1000;
	public static final long TANK_BODY_TURN_TIME = 1000;
	public static final long TANK_BODY_THROW_TIME = 1000;
	public static final long TANK_BODY_WAIT_TIME = 50;
	public static final long TANK_BODY_WIZZ_TIME = 1000;

	public TankBody(int x, int y, int width, int height, int health, int speed, Model model, Automaton aut) {
		super(x, y, width, height, health, speed, model, aut);
	}

	@Override
	public void step(long elapsed) {
		if (m_currentAction != null ) {
			if (m_elapseTime > m_timeOfAction) {
				m_elapseTime = 0;
				m_currentAction = null;
			} else {
				m_elapseTime += elapsed;
			}
		} else  {
			this.setState(m_automate.step(this));
		}
	}

	@Override
	public void Egg(MyDirection dir) {
		m_timeOfAction = TANK_BODY_MOVE_TIME;
		System.out.println("Egg !");
		super.Egg(dir);
	}
	
	@Override
	public void Explode() {
		m_timeOfAction = TANK_BODY_EXPLODE_TIME;
		System.out.println("Explode !");
		super.Explode();
	}
	
	@Override
	public void Get(MyDirection dir) {
		m_timeOfAction = TANK_BODY_GET_TIME;
		System.out.println("Get !");
		super.Get(dir);
	}

	@Override
	public void Hit(MyDirection dir) {
		m_timeOfAction = TANK_BODY_HIT_TIME;
		System.out.println("Hit !");
		super.Hit(dir);
	}
	
	@Override
	public void Jump(MyDirection dir) {
		m_timeOfAction = TANK_BODY_JUMP_TIME;
		System.out.println("Jump !");
		super.Jump(dir);
	}
	
	@Override
	public void Move(MyDirection dir) {
		m_timeOfAction = TANK_BODY_MOVE_TIME;
		System.out.println("Move !");
		super.Move(dir);
	}

	@Override
	public void Pick(MyDirection dir) {
		m_timeOfAction = TANK_BODY_PICK_TIME;
		System.out.println("Pick !");
		super.Pick(dir);
	}
	

	@Override
	public void Pop(MyDirection dir) {
		m_timeOfAction = TANK_BODY_POP_TIME;
		System.out.println("Pop !");
		super.Pop(dir);
	}

	@Override
	public void Power() {
		m_timeOfAction = TANK_BODY_POWER_TIME;
		System.out.println("Power !");
		super.Power();
	}
	

	@Override
	public void Protect(MyDirection dir) {
		m_timeOfAction = TANK_BODY_PROTECT_TIME;
		System.out.println("Protect !");
		super.Protect(dir);
	}

	@Override
	public void Store(MyDirection dir) {
		m_timeOfAction = TANK_BODY_STORE_TIME;
		System.out.println("Store !");
		super.Store(dir);
	}

	@Override
	public void Throw(MyDirection dir) {
		m_timeOfAction = TANK_BODY_THROW_TIME;
		System.out.println("Throw !");
		super.Throw(dir);
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		m_timeOfAction = TANK_BODY_TURN_TIME;
		System.out.println("Turn !");
		super.Turn(dir, angle);
		m_currentActionDir = m_currentLookAtDir;//l'action se fait dans la direction dans laquelle on regarde
	}

	@Override
	public void Wait() {
		m_timeOfAction = TANK_BODY_WAIT_TIME;
		System.out.println("Wait !");
		super.Wait();
	}

	@Override
	public void Wizz(MyDirection dir) {
		m_timeOfAction = TANK_BODY_WIZZ_TIME;
		System.out.println("Wizz !");
		super.Wizz(dir);
	}

}
