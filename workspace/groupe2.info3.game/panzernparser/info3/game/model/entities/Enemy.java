package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.MaterialType;
import info3.game.model.Model;

public class Enemy extends MovingEntity {
	/*
	 * Champs pour donner size par defaut dans la EntityFactory. A voir si on donne
	 * des size differentes pour des categories d'ennemis differents
	 */
	public final static int ENEMY_WIDTH = 1;
	public final static int ENEMY_HEIGHT = 1;

	public static final int ENEMY_HEALTH = 100;
	public static final int ENEMY_SPEED = 100;

	public static final long ENEMY_EGG_TIME = 1000;
	public static final long ENEMY_GET_TIME = 1000;
	public static final long ENEMY_HIT_TIME = 1000;
	public static final long ENEMY_JUMP_TIME = 1000;
	public static final long ENEMY_EXPLODE_TIME = 1000;
	public static final long ENEMY_MOVE_TIME = 1000;
	public static final long ENEMY_PICK_TIME = 1000;
	public static final long ENEMY_POP_TIME = 10000;
	public static final long ENEMY_POWER_TIME = 1000;
	public static final long ENEMY_PROTECT_TIME = 1000;
	public static final long ENEMY_STORE_TIME = 1000;
	public static final long ENEMY_TURN_TIME = 0;
	public static final long ENEMY_THROW_TIME = 1000;
	public static final long ENEMY_WAIT_TIME = 50;
	public static final long ENEMY_WIZZ_TIME = 1000;

	boolean m_triggered; // indique si l'ennemi a détecté le joueur ou non.
	Droppable m_drops;

	public Enemy(int x, int y, int width, int height, Model model, Automaton aut) {
		super(x, y, width, height, ENEMY_HEALTH, ENEMY_SPEED, model, aut);
		m_triggered = false; // Valeur par défaut
//		m_drops = new Droppable(this.m_x, this.m_y, 1, 1, 1, MaterialType.ELECTRONIC, model);
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

	@Override
	public void Egg(MyDirection dir) {
		m_timeOfAction = ENEMY_MOVE_TIME;
		System.out.println("Egg !");
		super.Egg(dir);
	}

	@Override
	public void Explode() {
		m_timeOfAction = ENEMY_EXPLODE_TIME;
		System.out.println("Explode !");
		super.Explode();
	}

	@Override
	public void Get(MyDirection dir) {
		m_timeOfAction = ENEMY_GET_TIME;
		System.out.println("Get !");
		super.Get(dir);
	}

	@Override
	public void Hit(MyDirection dir) {
		m_timeOfAction = ENEMY_HIT_TIME;
		System.out.println("Hit !");
		super.Hit(dir);
	}

	@Override
	public void Jump(MyDirection dir) {
		m_timeOfAction = ENEMY_JUMP_TIME;
		System.out.println("Jump !");
		super.Jump(dir);
	}

	@Override
	public void Move(MyDirection dir) {
		m_timeOfAction = ENEMY_MOVE_TIME;
		System.out.println("Move !");
		super.Move(dir);
	}

	@Override
	public void Pick(MyDirection dir) {
		m_timeOfAction = ENEMY_PICK_TIME;
		System.out.println("Pick !");
		super.Pick(dir);
	}

	@Override
	public void Pop(MyDirection dir) {
		m_timeOfAction = ENEMY_POP_TIME;
		System.out.println("Pop !");
		super.Pop(dir);
	}

	@Override
	public void Power() {
		m_timeOfAction = ENEMY_POWER_TIME;
		System.out.println("Power !");
		super.Power();
	}

	@Override
	public void Protect(MyDirection dir) {
		m_timeOfAction = ENEMY_PROTECT_TIME;
		System.out.println("Protect !");
		super.Protect(dir);
	}

	@Override
	public void Store(MyDirection dir) {
		m_timeOfAction = ENEMY_STORE_TIME;
		System.out.println("Store !");
		super.Store(dir);
	}

	@Override
	public void Throw(MyDirection dir) {
		m_timeOfAction = ENEMY_THROW_TIME;
		System.out.println("Throw !");
		super.Throw(dir);
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		m_timeOfAction = ENEMY_TURN_TIME;
		System.out.println("Turn !");
		super.Turn(dir, angle);
	}

	@Override
	public void Wait() {
		m_timeOfAction = ENEMY_WAIT_TIME;
		//System.out.println("Wait !");
		super.Wait();
	}

	@Override
	public void Wizz(MyDirection dir) {
		m_timeOfAction = ENEMY_WIZZ_TIME;
		System.out.println("Wizz !");
		super.Wizz(dir);
	}

}
