package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;

/**
 * Un marqueur est disposé par le drone lors d'un clic dans le canvas
 */
public class Marker extends StaticEntity {
	
	public final static int MARKER_WIDTH = 1;
	public final static int MARKER_HEIGHT = 1;

	public static final long MARKER_EGG_TIME = 1000;
	public static final long MARKER_GET_TIME = 1000;
	public static final long MARKER_HIT_TIME = 1000;
	public static final long MARKER_JUMP_TIME = 1000;
	public static final long MARKER_EXPLODE_TIME = 1000;
	public static final long MARKER_MOVE_TIME = 200;
	public static final long MARKER_PICK_TIME = 1000;
	public static final long MARKER_POP_TIME = 10000;
	public static final long MARKER_POWER_TIME = 1000;
	public static final long MARKER_PROTECT_TIME = 1000;
	public static final long MARKER_STORE_TIME = 1000;
	public static final long MARKER_TURN_TIME = 1000;
	public static final long MARKER_THROW_TIME = 1000;
	public static final long MARKER_WAIT_TIME = 50;
	public static final long MARKER_WIZZ_TIME = 1000;


	public Marker(int x, int y, int width, int height, Model model, Automaton aut) {
		super(x, y, width, height, model, aut);
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
		m_timeOfAction = MARKER_MOVE_TIME;
		System.out.println("Egg !");
		super.Egg(dir);
	}
	
	@Override
	public void Explode() {
		m_timeOfAction = MARKER_EXPLODE_TIME;
		System.out.println("Explode !");
		super.Explode();
	}
	
	@Override
	public void Get(MyDirection dir) {
		m_timeOfAction = MARKER_GET_TIME;
		System.out.println("Get !");
		super.Get(dir);
	}

	@Override
	public void Hit(MyDirection dir) {
		m_timeOfAction = MARKER_HIT_TIME;
		System.out.println("Hit !");
		super.Hit(dir);
	}
	
	@Override
	public void Jump(MyDirection dir) {
		m_timeOfAction = MARKER_JUMP_TIME;
		System.out.println("Jump !");
		super.Jump(dir);
	}
	
	@Override
	public void Move(MyDirection dir) {
		m_timeOfAction = MARKER_MOVE_TIME;
		System.out.println("Move !");
		super.Move(dir);
	}

	@Override
	public void Pick(MyDirection dir) {
		m_timeOfAction = MARKER_PICK_TIME;
		System.out.println("Pick !");
		super.Pick(dir);
	}
	

	@Override
	public void Pop(MyDirection dir) {
		m_timeOfAction = MARKER_POP_TIME;
		System.out.println("Pop !");
		super.Pop(dir);
	}

	@Override
	public void Power() {
		m_timeOfAction = MARKER_POWER_TIME;
		System.out.println("Power !");
		super.Power();
	}
	

	@Override
	public void Protect(MyDirection dir) {
		m_timeOfAction = MARKER_PROTECT_TIME;
		System.out.println("Protect !");
		super.Protect(dir);
	}

	@Override
	public void Store(MyDirection dir) {
		m_timeOfAction = MARKER_STORE_TIME;
		System.out.println("Store !");
		super.Store(dir);
	}

	@Override
	public void Throw(MyDirection dir) {
		m_timeOfAction = MARKER_THROW_TIME;
		System.out.println("Throw !");
		super.Throw(dir);
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		m_timeOfAction = MARKER_TURN_TIME;
		System.out.println("Turn !");
		super.Turn(dir, angle);
		m_currentActionDir = m_currentLookAtDir;//l'action se fait dans la direction dans laquelle on regarde
	}

	@Override
	public void Wait() {
		m_timeOfAction = MARKER_WAIT_TIME;
		System.out.println("Wait !");
		super.Wait();
	}

	@Override
	public void Wizz(MyDirection dir) {
		m_timeOfAction = MARKER_WIZZ_TIME;
		System.out.println("Wizz !");
		super.Wizz(dir);
	}

}
