package info3.game.model.entities;

import info3.game.automaton.MyCategory;
import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.State;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;

public abstract class Entity {

	final static int DEFAULT_MOVING_DISTANCE = 1;

	long m_elapseTime;
	LsAction m_currentAction;
	long m_timeOfAction;

	boolean m_displayed; // Indique si il doit etre affiché a l'écran où non.

	int m_x;
	int m_y;
	int m_width;
	int m_height;
	int m_speed;
	MyDirection m_currentLookAtDir;
	MyDirection m_currentActionDir;
	boolean m_stuff; // gotStuff ?
	// Automaton m_automate; //automate associé
	State m_currentState;
	Automaton m_automate; // automate associé
	public Model m_model;

	public Entity(int x, int y, int width, int height, Model model, Automaton aut) {
		m_automate = aut;
		m_currentState = aut.getState();
		
		m_elapseTime = 0;
		m_currentAction = null;

		m_displayed = true;

		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;

		m_model = model;
		m_currentLookAtDir = MyDirection.NORTH; // par défaut
		m_currentActionDir = null; // par défaut

		m_timeOfAction = 0;

	}

	public abstract void step(long elapsed);

	public double getActionProgress() {
		if (m_currentAction != null) {
			return ((double) m_elapseTime) / ((double) m_timeOfAction);
		}
		return 0;
	}

	public void setAutomaton(Automaton automate) {
		m_automate = automate;
	}

	public boolean isShown() {
		return m_displayed;
	}

	public State getState() {
		return m_currentState;
	}

	public void setState(State state) {
		if (state != null)
			m_currentState = state;
		else
			System.out.println("setstate null");
	}

	public LsAction getCurrentAction() {
		return m_currentAction;
	}

	public MyDirection getCurrentActionDir() {
		return m_currentActionDir;
	}

	public MyDirection getLookAtDir() {
		return m_currentLookAtDir;
	}

	public int getX() {
		// System.out.println("Is GetXing");
		return m_x;
	}

	public int getY() {
		// System.out.println("Is GetYing");
		return m_y;
	}

	public int getWidth() {
		return m_width;
	}

	public int getHeight() {
		return m_height;
	}

	public void Egg(MyDirection dir) {
		System.out.println("Is Egging");
		m_currentActionDir = dir;
		m_currentAction = LsAction.Egg;
	}

	public void Get(MyDirection dir) {
		System.out.println("Is Getting");
		m_currentActionDir = dir;
		m_currentAction = LsAction.Get;
	}

	public void Hit(MyDirection dir) {
		System.out.println("Is Hitting");
		m_currentActionDir = dir;
		m_currentAction = LsAction.Hit;
	}

	public void Explode() {
		System.out.println("Is Exploding");
		m_currentActionDir = null;
		m_currentAction = LsAction.Explode;
	}

	public void Jump(MyDirection dir) {
		System.out.println("Is Jumping");
		m_currentActionDir = dir;
		m_currentAction = LsAction.Jump;
	}

	public void Move(MyDirection dir) {
		System.out.println("Is Moving to " + dir);
		this.m_currentAction = LsAction.Move;
		m_currentActionDir = dir;
		switch (dir) {
			case FRONT:
				switch (m_currentActionDir) {
					case NORTH:
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case EAST:
						m_x += DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTH:
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case WEST:
						m_x -= DEFAULT_MOVING_DISTANCE;
						break;
					case NORTHEAST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTHEAST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTHWEST:
						m_x -= DEFAULT_MOVING_DISTANCE;
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case NORTHWEST:
						m_x -= DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					default:
						break;
				}
				break;
			case LEFT:
				switch (m_currentActionDir) {
					case NORTH:
						m_x -= DEFAULT_MOVING_DISTANCE;
						break;
					case EAST:
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTH:
						m_x += DEFAULT_MOVING_DISTANCE;
						break;
					case WEST:
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case NORTHEAST:
						m_x -= DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTHEAST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTHWEST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case NORTHWEST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					default:
						break;
				}
				break;
			case RIGHT:
				switch (m_currentActionDir) {
					case NORTH:
						m_x += DEFAULT_MOVING_DISTANCE;
						break;
					case EAST:
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTH:
						m_x -= DEFAULT_MOVING_DISTANCE;
						break;
					case WEST:
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case NORTHEAST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTHEAST:
						m_x -= DEFAULT_MOVING_DISTANCE;
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTHWEST:
						m_x -= DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case NORTHWEST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					default:
						break;
				}
				break;
			case BACK:
				switch (m_currentActionDir) {
					case NORTH:
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case EAST:
						m_x -= DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTH:
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case WEST:
						m_x += DEFAULT_MOVING_DISTANCE;
						break;
					case NORTHEAST:
						m_x -= DEFAULT_MOVING_DISTANCE;
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTHEAST:
						m_x -= DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTHWEST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case NORTHWEST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					default:
						break;
				}
			case NORTH:
				m_y -= DEFAULT_MOVING_DISTANCE;
				break;
			case SOUTH:
				m_y += DEFAULT_MOVING_DISTANCE;
				break;
			case EAST:
				m_x += DEFAULT_MOVING_DISTANCE;
				break;
			case WEST:
				m_x -= DEFAULT_MOVING_DISTANCE;
				break;
			case NORTHEAST:
				m_x += DEFAULT_MOVING_DISTANCE;
				m_y -= DEFAULT_MOVING_DISTANCE;
				break;
			case NORTHWEST:
				m_x -= DEFAULT_MOVING_DISTANCE;
				m_y -= DEFAULT_MOVING_DISTANCE;
				break;
			case SOUTHEAST:
				m_x += DEFAULT_MOVING_DISTANCE;
				m_y += DEFAULT_MOVING_DISTANCE;
				break;
			case SOUTHWEST:
				m_x -= DEFAULT_MOVING_DISTANCE;
				m_y += DEFAULT_MOVING_DISTANCE;
				break;
			default:
				break;
		}
		m_currentActionDir = dir;
		System.out.println("Arrived and facing " + m_currentLookAtDir);
		return;
	}

	public void Pick(MyDirection dir) {
		System.out.println("Is Picking");
		m_currentActionDir = dir;
		m_currentAction = LsAction.Pick;
	}

	public void Pop(MyDirection dir) {
		System.out.println("Is Poping");
		m_currentActionDir = dir;
		m_currentAction = LsAction.Pop;
	}

	public void Power() {
		System.out.println("Is Powering");
		m_currentActionDir = null;
		m_currentAction = LsAction.Power;
	}

	public void Protect(MyDirection dir) {
		System.out.println("Is Protecting");
		m_currentActionDir = dir;
		m_currentAction = LsAction.Protect;
	}

	public void Store(MyDirection dir) {
		System.out.println("Is Storing");
		m_currentActionDir = dir;
		m_currentAction = LsAction.Store;
	}

	public void Turn(MyDirection dir, int angle) {
		System.out.println("Is Turning");
		switch (dir) {
			case NORTH:
			case SOUTH:
			case WEST:
			case EAST:
			case NORTHEAST:
			case NORTHWEST:
			case SOUTHEAST:
			case SOUTHWEST:
				m_currentLookAtDir = dir;
				break;
			case LEFT:
				switch (m_currentLookAtDir) {
					case NORTH:
						m_currentLookAtDir = MyDirection.NORTHWEST;
						break;
					case WEST:
						m_currentLookAtDir = MyDirection.SOUTHWEST;
						break;
					case SOUTH:
						m_currentLookAtDir = MyDirection.SOUTHEAST;
						break;
					case EAST:
						m_currentLookAtDir = MyDirection.NORTHEAST;
						break;
					case NORTHEAST:
						m_currentLookAtDir = MyDirection.NORTH;
						break;
					case NORTHWEST:
						m_currentLookAtDir = MyDirection.WEST;
						break;
					case SOUTHWEST:
						m_currentLookAtDir = MyDirection.SOUTH;
						break;
					case SOUTHEAST:
						m_currentLookAtDir = MyDirection.EAST;
						break;
					default:
						break;
				}
				break;
			case RIGHT:
				switch (m_currentLookAtDir) {
					case NORTH:
						m_currentLookAtDir = MyDirection.NORTHEAST;
						break;
					case EAST:
						m_currentLookAtDir = MyDirection.SOUTH;
						break;
					case SOUTH:
						m_currentLookAtDir = MyDirection.SOUTHWEST;
						break;
					case WEST:
						m_currentLookAtDir = MyDirection.NORTHWEST;
						break;
					case NORTHEAST:
						m_currentLookAtDir = MyDirection.EAST;
						break;
					case SOUTHEAST:
						m_currentLookAtDir = MyDirection.SOUTHEAST;
						break;
					case SOUTHWEST:
						m_currentLookAtDir = MyDirection.WEST;
						break;
					case NORTHWEST:
						m_currentLookAtDir = MyDirection.NORTH;
						break;
					default:
						break;
				}
				break;
			case BACK:
				switch (m_currentLookAtDir) {
					case NORTH:
						m_currentLookAtDir = MyDirection.SOUTH;
						break;
					case EAST:
						m_currentLookAtDir = MyDirection.WEST;
						break;
					case SOUTH:
						m_currentLookAtDir = MyDirection.NORTH;
						break;
					case WEST:
						m_currentLookAtDir = MyDirection.EAST;
						break;
					case NORTHEAST:
						m_currentLookAtDir = MyDirection.SOUTHWEST;
						break;
					case SOUTHEAST:
						m_currentLookAtDir = MyDirection.NORTHWEST;
						break;
					case SOUTHWEST:
						m_currentLookAtDir = MyDirection.NORTHEAST;
						break;
					case NORTHWEST:
						m_currentLookAtDir = MyDirection.SOUTHEAST;
						break;
					default:
						break;
				}
			default:
				break;
		}
		m_currentActionDir = dir;
		m_currentAction = LsAction.Turn;
	}

	public void Throw(MyDirection dir) {
		System.out.println("Is Throwing");
		m_currentActionDir = dir;
		m_currentAction = LsAction.Throw;
	}

	public void Wait() {
		System.out.println("Is Waiting");
		m_currentActionDir = null;
		m_currentAction = LsAction.Wait;
	}

	public void Wizz(MyDirection dir) {
		System.out.println("Is Wizzing");
		m_currentActionDir = dir;
		m_currentAction = LsAction.Wizz;
	}

	public boolean myDir(MyDirection dir) {
		// m_currentActionDir = dir;
		// System.out.println("Is myDiring");
		if (m_currentLookAtDir != null) {
			return m_currentLookAtDir.equals(dir);
		}
		return false;
	}

	public boolean Cell(MyDirection dir, MyCategory type, int dist) {
		// m_currentActionDir = dir;
		return true;
	}

	public boolean GotPower() {
		System.out.println("Is Gotpower-ing");
		return true;
	}

	public boolean GotStuff() {
		System.out.println("Is Gotstuffing");
		return m_stuff;
	}

	public boolean Closest(MyDirection dir, MyCategory type) {
		// m_currentActionDir = dir;
		return false;

	}
}
