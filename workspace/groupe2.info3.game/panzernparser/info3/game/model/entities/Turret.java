package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.model.Tank;

/**
 * Classe du canon du tank
 */
public class Turret extends MovingEntity {

	public final static int TURRET_WIDTH = 1;
	public final static int TURRET_HEIGHT = 1;

	public static final int TURRET_HEALTH = 100;
	public static final int TURRET_SPEED = 100;

	public static final long TURRET_EGG_TIME = 1000;
	public static final long TURRET_GET_TIME = 1000;
	public static final long TURRET_HIT_TIME = 1000;
	public static final long TURRET_JUMP_TIME = 1000;
	public static final long TURRET_EXPLODE_TIME = 1000;
	public static final long TURRET_MOVE_TIME = 1000;
	public static final long TURRET_PICK_TIME = 1000;
	public static final long TURRET_POP_TIME = 10000;
	public static final long TURRET_POWER_TIME = 1000;
	public static final long TURRET_PROTECT_TIME = 1000;
	public static final long TURRET_STORE_TIME = 1000;
	public static final long TURRET_TURN_TIME = 0;
	public static final long TURRET_THROW_TIME = 1000;
	public static final long TURRET_WAIT_TIME = 50;
	public static final long TURRET_WIZZ_TIME = 1000;

	private Tank m_tank;
	// public int m_range;

//	private boolean m_hasControl;
//	/*
//	 * flags pour savoir si le body TankBody bouge et si oui dans quelle direction
//	 * et a quel point en est-il ?
//	 */
//	private boolean m_bodyMoving;// si tankBody utilise la fonction Move()
//	private double m_BodyProgress;// progression du body
//	private MyDirection m_bodyDir;// La direction dans laquelle va le body

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


//	@Override
//	public void step(long elapsed) {
//		if (m_currentAction != null) {
//			if (m_elapseTime > m_timeOfAction) {
//				m_elapseTime = 0;
//				m_currentAction = null;
//			} else {
//				m_elapseTime += elapsed;
//			}
//		} else {
//			this.setState(m_automate.step(this));
//		}
//	}

//	@Override
//	public void Egg(MyDirection dir) {
//		m_timeOfAction = TURRET_MOVE_TIME;
//		System.out.println("Egg !");
//		super.Egg(dir);
//	}
//
//	@Override
//	public void Explode() {
//		m_timeOfAction = TURRET_EXPLODE_TIME;
//		System.out.println("Explode !");
//		super.Explode();
//	}
//
//	@Override
//	public void Get(MyDirection dir) {
//		m_timeOfAction = TURRET_GET_TIME;
//		System.out.println("Get !");
//		super.Get(dir);
//	}
//
//	@Override
//	public void Hit(MyDirection dir) {
//		if (m_tank.hasControl()) {
//			m_timeOfAction = TURRET_HIT_TIME;
//// 		System.out.println("Hit ! " + dir);
////		if (dir == null) {
////			dir = m_currentLookAtDir;
////		}
//			super.Hit(dir);
//			System.out.println("Canon HIT dans la direction " + dir);
//		}
//	}
//
//	@Override
//	public void Jump(MyDirection dir) {
//		m_timeOfAction = TURRET_JUMP_TIME;
//		System.out.println("Jump !");
//		super.Jump(dir);
//	}
//
//	@Override
//	public void Move(MyDirection dir) {
//		m_timeOfAction = TURRET_MOVE_TIME;
//		System.out.println("Move !");
//		super.Move(dir);
//	}
//
//	@Override
//	public void Pick(MyDirection dir) {
//		m_timeOfAction = TURRET_PICK_TIME;
//		System.out.println("Pick !");
//		super.Pick(dir);
//	}
//
//	@Override
//	public void Pop(MyDirection dir) {
//		if (m_tank.hasControl()) {
//			// TODO : changer d'arme
//			m_timeOfAction = TURRET_POP_TIME;
//			System.out.println("Pop : Changement d'arme !");
//			super.Pop(dir);
//		}
//	}
//
//	@Override
//	public void Power() {
//		m_timeOfAction = TURRET_POWER_TIME;
//		System.out.println("Power !");
//		super.Power();
//	}
//
//	@Override
//	public void Protect(MyDirection dir) {
//		m_timeOfAction = TURRET_PROTECT_TIME;
//		System.out.println("Protect !");
//		super.Protect(dir);
//	}
//
//	@Override
//	public void Store(MyDirection dir) {
//		m_timeOfAction = TURRET_STORE_TIME;
//		System.out.println("Store !");
//		super.Store(dir);
//	}
//
//	@Override
//	public void Throw(MyDirection dir) {
//		m_timeOfAction = TURRET_THROW_TIME;
//		System.out.println("Throw !");
//		super.Throw(dir);
//	}
//
//	@Override
//	public void Turn(MyDirection dir, int angle) {
//		if (m_tank.hasControl()) {
//			m_timeOfAction = TURRET_TURN_TIME;
//			System.out.println("Canon tourne dans la direction" + dir);
//			super.Turn(dir, angle);
//		}
//	}
//
//	@Override
//	public void Wait() {
//		m_timeOfAction = TURRET_WAIT_TIME;
//		// System.out.println("Wait !");
//		super.Wait();
//	}
//
//	@Override
//	public void Wizz(MyDirection dir) {
//		if (m_tank.hasControl()) {
//			// TODO : recharger le cannon
//			m_timeOfAction = TURRET_WIZZ_TIME;
//			System.out.println("Wizz : rechargement du canon !");
//			super.Wizz(dir);
//		}
//	}

}
