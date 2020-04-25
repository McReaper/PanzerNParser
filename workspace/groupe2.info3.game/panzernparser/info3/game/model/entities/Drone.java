package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.State;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Tank;

public class Drone extends MovingEntity {
	public final static int DRONE_WIDTH = 3;
	public final static int DRONE_HEIGHT = 3;

	public static final int DRONE_HEALTH = 100;
	public static final int DRONE_SPEED = 1; // nb de cases en 1 déplacement.

	public static final long DRONE_EGG_TIME = 1000;
	public static final long DRONE_GET_TIME = 1000;
	public static final long DRONE_HIT_TIME = 1000;
	public static final long DRONE_JUMP_TIME = 1000;
	public static final long DRONE_EXPLODE_TIME = 1000;
	public static final long DRONE_MOVE_TIME = 2000;
	public static final long DRONE_PICK_TIME = 1000;
	public static final long DRONE_POP_TIME = 10000;
	public static final long DRONE_POWER_TIME = 1000;
	public static final long DRONE_PROTECT_TIME = 1000;
	public static final long DRONE_STORE_TIME = 1000;
	public static final long DRONE_TURN_TIME = 1000;
	public static final long DRONE_THROW_TIME = 1000;
	public static final long DRONE_WAIT_TIME = 50;
	public static final long DRONE_WIZZ_TIME = 1000;

	VisionType m_currentVisionType;

	public Drone(int x, int y, int width, int height, int health, int speed, Model model, Automaton aut) {
		super(x, y, width, height, health, speed, model, aut);
		m_currentVisionType = VisionType.ENEMIES;
	}

	private enum VisionType {
		RESSOURCES, ENEMIES;
	}

	VisionType m_visionType;

	@Override
	public void step(long elapsed) {
		if (m_currentAction != null ) {
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

	@Override
	public void Egg(MyDirection dir) {
		m_timeOfAction = DRONE_MOVE_TIME;
		System.out.println("Egg !");
		super.Egg(dir);
	}

	@Override
	public void Explode() {
		m_timeOfAction = DRONE_EXPLODE_TIME;
		System.out.println("Explode !");
		super.Explode();
	}

	@Override
	public void Get(MyDirection dir) {
		m_timeOfAction = DRONE_GET_TIME;
		System.out.println("Get !");
		super.Get(dir);
	}

	@Override
	public void Hit(MyDirection dir) {
		m_timeOfAction = DRONE_HIT_TIME;
		System.out.println("Hit !");
		super.Hit(dir);
	}

	@Override
	public void Jump(MyDirection dir) {
		m_timeOfAction = DRONE_JUMP_TIME;
		System.out.println("Jump !");
		super.Jump(dir);
	}

	@Override
	public void Move(MyDirection dir) {
		m_timeOfAction = DRONE_MOVE_TIME;
		System.out.println("Move !");
		super.Move(dir);
	}

	@Override
	public void Pick(MyDirection dir) {
		m_timeOfAction = DRONE_PICK_TIME;
		System.out.println("Pick !");
		super.Pick(dir);
	}

	@Override
	public void Pop(MyDirection dir) {
		m_timeOfAction = DRONE_POP_TIME;
		if (m_currentVisionType == VisionType.ENEMIES)
			m_currentVisionType = VisionType.RESSOURCES;
		else
			m_currentVisionType = VisionType.ENEMIES;

		System.out.println("Pop !");
		super.Pop(dir);
	}

	@Override
	public void Power() {
		m_timeOfAction = DRONE_POWER_TIME;
		System.out.println("Power !");
		super.Power();
	}

	@Override
	public void Protect(MyDirection dir) {
		m_timeOfAction = DRONE_PROTECT_TIME;
		System.out.println("Protect !");
		super.Protect(dir);
	}

	@Override
	public void Store(MyDirection dir) {
		m_timeOfAction = DRONE_STORE_TIME;
		System.out.println("Store !");
		super.Store(dir);
	}

	@Override
	public void Throw(MyDirection dir) {
		m_timeOfAction = DRONE_THROW_TIME;
		System.out.println("Throw !");
		super.Throw(dir);
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		m_timeOfAction = DRONE_TURN_TIME;
		System.out.println("Turn !");
		super.Turn(dir, angle);
		m_currentActionDir = m_currentLookAtDir;// l'action se fait dans la direction dans laquelle on regarde
	}

	@Override
	public void Wait() {
		m_timeOfAction = DRONE_WAIT_TIME;
		System.out.println("Wait !");
		super.Wait();
	}

	@Override
	public void Wizz(MyDirection dir) {
		m_timeOfAction = DRONE_WIZZ_TIME;
		callTank();
		System.out.println("Wizz !");
		super.Wizz(dir);
	}

	@Override
	public boolean GotPower() {
		return (m_model.m_player == Model.PLAYER_DRONE);
			
	}
	
	
	public void growViewPort() {
	}

	public void reduceViewPort() {
	}

	/* Lorsque wizz, on appelle cette fonction */
	public void callTank() {
		m_model.m_player = Model.PLAYER_TANK;
		/*
		 * 
		 * TODO : si le wizz est appelé, on le remove de la liste d'entity de model
		 * -> le update de view n'affichera lus le drone
		 * ->
		 * -> agir sur le viewport
		 * m_model
		 */
	}

}
