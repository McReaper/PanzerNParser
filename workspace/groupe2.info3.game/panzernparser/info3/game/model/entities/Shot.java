package info3.game.model.entities;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;

/**
 * Une entité représentant un tir simple, infligeant des dégats à d'autre
 * MovingEntity.
 */
public class Shot extends MovingEntity {
	/* Champs pour donner size par defaut dans la EntityFactory */
	/* a voir comment les shots sont créés (factory ?)*/
	final static int SHOT_WIDTH = 1;
	final static int SHOT_HEIGHT = 3;

	public static final int SHOT_HEALTH = 100;
	public static final int SHOT_SPEED = 100;

	public static final long SHOT_EGG_TIME = 1000;
	public static final long SHOT_GET_TIME = 1000;
	public static final long SHOT_HIT_TIME = 1000;
	public static final long SHOT_JUMP_TIME = 1000;
	public static final long SHOT_EXPLODE_TIME = 1000;
	public static final long SHOT_MOVE_TIME = 200;
	public static final long SHOT_PICK_TIME = 1000;
	public static final long SHOT_POP_TIME = 10000;
	public static final long SHOT_POWER_TIME = 1000;
	public static final long SHOT_PROTECT_TIME = 1000;
	public static final long SHOT_STORE_TIME = 1000;
	public static final long SHOT_TURN_TIME = 1000;
	public static final long SHOT_THROW_TIME = 1000;
	public static final long SHOT_WAIT_TIME = 50;
	public static final long SHOT_WIZZ_TIME = 1000;
	
	long m_timeOfAction;

	public Shot(int x, int y, int width, int height, int health, int speed, Model model) {
		super(x, y, width, height, health, speed, model);
		// TODO Auto-generated constructor stub
	}

	int m_damage;

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
	public double getActionProgress() {
		if (m_currentAction != null) {
			switch (m_currentAction) {
				case Egg:
					return ((double) m_elapseTime) / ((double) SHOT_EGG_TIME);
				case Explode:
					return ((double) m_elapseTime) / ((double) SHOT_EXPLODE_TIME);
				case Get:
					return ((double) m_elapseTime) / ((double) SHOT_GET_TIME);
				case Hit:
					return ((double) m_elapseTime) / ((double) SHOT_HIT_TIME);
				case Jump:
					return ((double) m_elapseTime) / ((double) SHOT_JUMP_TIME);
				case Move:
					return ((double) m_elapseTime) / ((double) SHOT_MOVE_TIME);
				case Pick:
					return ((double) m_elapseTime) / ((double) SHOT_PICK_TIME);
				case Pop:
					return ((double) m_elapseTime) / ((double) SHOT_POP_TIME);
				case Power:
					return ((double) m_elapseTime) / ((double) SHOT_POWER_TIME);
				case Protect:
					return ((double) m_elapseTime) / ((double) SHOT_PROTECT_TIME);
				case Store:
					return ((double) m_elapseTime) / ((double) SHOT_STORE_TIME);
				case Throw:
					return ((double) m_elapseTime) / ((double) SHOT_THROW_TIME);
				case Turn:
					return ((double) m_elapseTime) / ((double) SHOT_TURN_TIME);
				case Wait:
					return ((double) m_elapseTime) / ((double) SHOT_WAIT_TIME);
				case Wizz:
					return ((double) m_elapseTime) / ((double) SHOT_WIZZ_TIME);
			}
		}
		return 0;
	}
	
	@Override
	public void Egg(MyDirection dir) {
		m_timeOfAction = SHOT_MOVE_TIME;
		System.out.println("Egg !");
		m_currentAction = LsAction.Egg;
		super.Egg(dir);
	}
	
	@Override
	public void Explode() {
		m_timeOfAction = SHOT_EXPLODE_TIME;
		System.out.println("Explode !");
		m_currentAction = LsAction.Explode;
		super.Explode();
	}
	
	@Override
	public void Get(MyDirection dir) {
		m_timeOfAction = SHOT_GET_TIME;
		System.out.println("Get !");
		m_currentAction = LsAction.Get;
		super.Get(dir);
	}

	@Override
	public void Hit(MyDirection dir) {
		m_timeOfAction = SHOT_HIT_TIME;
		System.out.println("Hit !");
		m_currentAction = LsAction.Hit;
		super.Hit(dir);
	}
	
	@Override
	public void Jump(MyDirection dir) {
		m_timeOfAction = SHOT_JUMP_TIME;
		System.out.println("Jump !");
		m_currentAction = LsAction.Jump;
		super.Jump(dir);
	}
	
	@Override
	public void Move(MyDirection dir) {
		m_timeOfAction = SHOT_MOVE_TIME;
		System.out.println("Move !");
		m_currentAction = LsAction.Move;
		super.Move(dir);
	}

	@Override
	public void Pick(MyDirection dir) {
		m_timeOfAction = SHOT_PICK_TIME;
		System.out.println("Pick !");
		m_currentAction = LsAction.Pick;
		super.Pick(dir);
	}
	

	@Override
	public void Pop(MyDirection dir) {
		m_timeOfAction = SHOT_POP_TIME;
		System.out.println("Pop !");
		m_currentAction = LsAction.Pop;
		super.Pop(dir);
	}

	@Override
	public void Power() {
		m_timeOfAction = SHOT_POWER_TIME;
		System.out.println("Power !");
		m_currentAction = LsAction.Power;
		super.Power();
	}
	

	@Override
	public void Protect(MyDirection dir) {
		m_timeOfAction = SHOT_PROTECT_TIME;
		System.out.println("Protect !");
		m_currentAction = LsAction.Protect;
		super.Protect(dir);
	}

	@Override
	public void Store(MyDirection dir) {
		m_timeOfAction = SHOT_STORE_TIME;
		System.out.println("Store !");
		m_currentAction = LsAction.Store;
		super.Store(dir);
	}

	@Override
	public void Throw(MyDirection dir) {
		m_timeOfAction = SHOT_THROW_TIME;
		System.out.println("Throw !");
		m_currentAction = LsAction.Throw;
		super.Throw(dir);
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		m_timeOfAction = SHOT_TURN_TIME;
		System.out.println("Turn !");
		m_currentAction = LsAction.Turn;
		super.Turn(dir, angle);
		m_currentActionDir = m_currentLookAtDir;//l'action se fait dans la direction dans laquelle on regarde
	}

	@Override
	public void Wait() {
		m_timeOfAction = SHOT_WAIT_TIME;
		System.out.println("Wait !");
		m_currentAction = LsAction.Wait;
		super.Wait();
	}

	@Override
	public void Wizz(MyDirection dir) {
		m_timeOfAction = SHOT_WIZZ_TIME;
		System.out.println("Wizz !");
		m_currentAction = LsAction.Wizz;
		super.Wizz(dir);
	}

}
