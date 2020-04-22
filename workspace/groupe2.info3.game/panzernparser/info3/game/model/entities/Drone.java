package info3.game.model.entities;

import info3.game.automaton.MyDirection;
import info3.game.automaton.State;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;

public class Drone extends MovingEntity {
	public final static int DRONE_WIDTH = 1;
	public final static int DRONE_HEIGHT = 1;

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


	public Drone(int x, int y, int width, int height, int health, int speed, Model model) {
		super(x, y, width, height, health, speed, model);
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
		} else  {
			this.setState(m_automate.step(this));
		}
	}

	
	@Override
	public void Egg(MyDirection dir) {
		m_timeOfAction = DRONE_MOVE_TIME;
		System.out.println("Egg !");
		m_currentAction = LsAction.Egg;
		super.Egg(dir);
	}
	
	@Override
	public void Explode() {
		m_timeOfAction = DRONE_EXPLODE_TIME;
		System.out.println("Explode !");
		m_currentAction = LsAction.Explode;
		super.Explode();
	}
	
	@Override
	public void Get(MyDirection dir) {
		m_timeOfAction = DRONE_GET_TIME;
		System.out.println("Get !");
		m_currentAction = LsAction.Get;
		super.Get(dir);
	}

	@Override
	public void Hit(MyDirection dir) {
		m_timeOfAction = DRONE_HIT_TIME;
		System.out.println("Hit !");
		m_currentAction = LsAction.Hit;
		super.Hit(dir);
	}
	
	@Override
	public void Jump(MyDirection dir) {
		m_timeOfAction = DRONE_JUMP_TIME;
		System.out.println("Jump !");
		m_currentAction = LsAction.Jump;
		super.Jump(dir);
	}
	
	@Override
	public void Move(MyDirection dir) {
		m_timeOfAction = DRONE_MOVE_TIME;
		System.out.println("Move !");
		m_currentAction = LsAction.Move;
		super.Move(dir);
	}

	@Override
	public void Pick(MyDirection dir) {
		m_timeOfAction = DRONE_PICK_TIME;
		System.out.println("Pick !");
		m_currentAction = LsAction.Pick;
		super.Pick(dir);
	}
	

	@Override
	public void Pop(MyDirection dir) {
		m_timeOfAction = DRONE_POP_TIME;
		if (m_currentVisionType == VisionType.ENEMIES)
			m_currentVisionType = VisionType.RESSOURCES;
		if (m_currentVisionType == VisionType.RESSOURCES)
			m_currentVisionType = VisionType.ENEMIES;
	
		System.out.println("Pop !");
		m_currentAction = LsAction.Pop;
		super.Pop(dir);
	}

	@Override
	public void Power() {
		m_timeOfAction = DRONE_POWER_TIME;
		System.out.println("Power !");
		m_currentAction = LsAction.Power;
		super.Power();
	}
	

	@Override
	public void Protect(MyDirection dir) {
		m_timeOfAction = DRONE_PROTECT_TIME;
		System.out.println("Protect !");
		m_currentAction = LsAction.Protect;
		super.Protect(dir);
	}

	@Override
	public void Store(MyDirection dir) {
		m_timeOfAction = DRONE_STORE_TIME;
		System.out.println("Store !");
		m_currentAction = LsAction.Store;
		super.Store(dir);
	}

	@Override
	public void Throw(MyDirection dir) {
		m_timeOfAction = DRONE_THROW_TIME;
		System.out.println("Throw !");
		m_currentAction = LsAction.Throw;
		super.Throw(dir);
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		m_timeOfAction = DRONE_TURN_TIME;
		System.out.println("Turn !");
		m_currentAction = LsAction.Turn;
		super.Turn(dir, angle);
		m_currentActionDir = m_currentLookAtDir;//l'action se fait dans la direction dans laquelle on regarde
	}

	@Override
	public void Wait() {
		m_timeOfAction = DRONE_WAIT_TIME;
		System.out.println("Wait !");
		m_currentAction = LsAction.Wait;
		super.Wait();
	}

	@Override
	public void Wizz(MyDirection dir) {
		m_timeOfAction = DRONE_WIZZ_TIME;
		System.out.println("Wizz !");
		m_currentAction = LsAction.Wizz;
		super.Wizz(dir);
	}

}
