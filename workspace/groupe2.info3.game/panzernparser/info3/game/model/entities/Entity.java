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
	
	boolean m_displayed; // Indique si il doit etre affiché a l'écran où non.
	
	int m_x;
	int m_y;
	int m_width;
	int m_height;
	MyDirection m_currentActionDir;
	MyDirection m_dir;
	boolean m_stuff; // gotStuff ?
	// Automaton m_automate; //automate associé
	State m_currentState;// Emilie : TODO gerer les cas null suite a l'automate
	Automaton m_automate; //automate associé
	public Model m_model;

	public Entity(int x, int y, int width, int height, Model model) {
		m_elapseTime = 0;
		m_currentAction = null;
		
		m_displayed = true;
		
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
		
		m_model = model;
		m_dir = MyDirection.NORTH; //par défault
	}

	public abstract void step(long elapsed);
	
	public void setAutomaton(Automaton automate) {
		m_automate=automate;
	}

	public boolean isShown() {
		return m_displayed;
	}

	public State getState() {
		return m_currentState;
	}

	/*
	 * Emilie : Ceci est une fonction temporaire pour pouvoir gerer les test sur les
	 * automates sans parser
	 */
	public void setState(State state) {
		m_currentState = state;
	}
	
	public LsAction getCurrentAction() {
		return m_currentAction;
	}
	
	public MyDirection getCurrentActionDir() {
		return m_currentActionDir;
	}
	
	public MyDirection getLookAtDir() {
		return m_dir;
	}
	
	public abstract double getActionProgress();
	
	public int getX() {
		//System.out.println("Is GetXing");
		return m_x;
	}

	public int getY() {
		//System.out.println("Is GetYing");
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
	}

	public void Get(MyDirection dir) {
		System.out.println("Is Getting");
	}

	public void Hit(MyDirection dir) {
		System.out.println("Is Hitting");
	}

	public void Explode() {
		System.out.println("Is Exploding");
	}

	public void Jump(MyDirection dir) {
		System.out.println("Is Jumping");
	}

	public void Move(MyDirection dir) {
		System.out.println("Is Moving to " + dir);
		this.m_currentAction = LsAction.Move;
		switch (dir) {
			case FRONT:
				switch (m_dir) {
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
				switch (m_dir) {
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
				switch (m_dir) {
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
				switch (m_dir) {
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
		System.out.println("Arrived and facing " + m_dir);
		return;
	}

	public void Pick(MyDirection dir) {
		System.out.println("Is Picking");
	}

	public void Pop(MyDirection dir) {
		System.out.println("Is Poping");
	}

	public void Power() {
		System.out.println("Is \"UNLIMITED PAWER!!\"-ing");
	}

	public void Protect(MyDirection dir) {
		System.out.println("Is Protecting");
	}

	public void Store(MyDirection dir) {
		System.out.println("Is Storing");
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
				m_dir = dir;
				break;
			case LEFT:
				switch (m_dir) {
					case NORTH:
						m_dir = MyDirection.NORTHWEST;
						break;
					case WEST:
						m_dir = MyDirection.SOUTHWEST;
						break;
					case SOUTH:
						m_dir = MyDirection.SOUTHEAST;
						break;
					case EAST:
						m_dir = MyDirection.NORTHEAST;
						break;
					case NORTHEAST:
						m_dir = MyDirection.NORTH;
						break;
					case NORTHWEST:
						m_dir = MyDirection.WEST;
						break;
					case SOUTHWEST:
						m_dir = MyDirection.SOUTH;
						break;
					case SOUTHEAST:
						m_dir = MyDirection.EAST;
						break;
					default:
						break;
				}
				break;
			case RIGHT:
				switch (m_dir) {
					case NORTH:
						m_dir = MyDirection.NORTHEAST;
						break;
					case EAST:
						m_dir = MyDirection.SOUTH;
						break;
					case SOUTH:
						m_dir = MyDirection.SOUTHWEST;
						break;
					case WEST:
						m_dir = MyDirection.NORTHWEST;
						break;
					case NORTHEAST:
						m_dir = MyDirection.EAST;
						break;
					case SOUTHEAST:
						m_dir = MyDirection.SOUTHEAST;
						break;
					case SOUTHWEST:
						m_dir = MyDirection.WEST;
						break;
					case NORTHWEST:
						m_dir = MyDirection.NORTH;
						break;
					default:
						break;
				}
				break;
			case BACK:
				switch (m_dir) {
					case NORTH:
						m_dir = MyDirection.SOUTH;
						break;
					case EAST:
						m_dir = MyDirection.WEST;
						break;
					case SOUTH:
						m_dir = MyDirection.NORTH;
						break;
					case WEST:
						m_dir = MyDirection.EAST;
						break;
					case NORTHEAST:
						m_dir = MyDirection.SOUTHWEST;
						break;
					case SOUTHEAST:
						m_dir = MyDirection.NORTHWEST;
						break;
					case SOUTHWEST:
						m_dir = MyDirection.NORTHEAST;
						break;
					case NORTHWEST:
						m_dir = MyDirection.SOUTHEAST;
						break;
					default:
						break;
				}
			default:
				break;
		}
	}

	public void Throw(MyDirection dir) {
		System.out.println("Is Throwing");
	}

	public void Wait() {
		System.out.println("Is Waiting");
	}

	public void Wizz(MyDirection dir) {
		System.out.println("Is Wizzing");
	}

	public boolean myDir(MyDirection dir) {
		//System.out.println("Is myDiring");
		if (m_dir != null) {
			return m_dir.equals(dir);
		}
		return false;
	}

	public boolean Cell(MyDirection dir, MyCategory type, int dist) {
		return true;
	}

	public boolean GotPower() {
		System.out.println("Is Got\"UNLIMITED PAWER\"-ing");
		return true;
	}

	public boolean GotStuff() {
		System.out.println("Is Gotstuffing");
		return m_stuff;
	}

	public boolean Closest(MyDirection dir, MyCategory type) {
		return false;

	}
}
