package info3.game.model.entities;

import info3.game.automata.ast.Direction;
import info3.game.automaton.MyDirection;
import info3.game.automaton.State;
import info3.game.automaton.action.LsAction;
import info3.game.view.Avatar;

public abstract class Entity {
	final static int SIZEOFCELL = 1;
	int m_x;
	int m_y;
	int m_width;
	int m_height;
	long m_elapseTime;
	MyDirection m_dir;
	// Automaton m_automate; //automate associé
	State m_currentState;// Emilie : TODO gerer les cas null suite a l'automate
	LsAction m_currentAction;

	public Entity(int x, int y, int width, int height) {
		m_elapseTime = 0;
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
	}

	public abstract void step(long elapsed);


	public int getX() {
		System.out.println("Is GetXing");
		return m_x;
	}

	public int getY() {
		System.out.println("Is GetYing");
		return m_y;
	}

	public void Egg() {
		System.out.println("Is Egging");
	}

	public void Get(/* Donner une direction ? */) {
		System.out.println("Is Getting");
	}

	public void Hit(/* Donner une direction ? */) {
		System.out.println("Is Hitting");
	}

	public void Explode() {
		System.out.println("Is Exploding");
	}

	public void Move(MyDirection dir) {
		System.out.println("Is Moving");
		switch (dir) {
			case FRONT:
				switch (m_dir) {
					case NORTH:
						m_y -= SIZEOFCELL;
						break;
					case SOUTH:
						m_y += SIZEOFCELL;
						break;
					case EAST:
						m_x += SIZEOFCELL;
						break;
					case WEST:
						m_x -= SIZEOFCELL;
						break;
					case NORTHEAST:
						m_x += SIZEOFCELL;
						m_y -= SIZEOFCELL;
						break;
					case NORTHWEST:
						m_x -= SIZEOFCELL;
						m_y -= SIZEOFCELL;
						break;
					case SOUTHEAST:
						m_y += SIZEOFCELL;
						m_x += SIZEOFCELL;
						break;
					case SOUTHWEST:
						m_y += SIZEOFCELL;
						m_x -= SIZEOFCELL;
						break;
					default:
						break;
				}
				break;
			case LEFT:
				switch (m_dir) {
					case EAST:
						m_y -= SIZEOFCELL;
						break;
					case WEST:
						m_y += SIZEOFCELL;
						break;
					case SOUTH:
						m_x += SIZEOFCELL;
						break;
					case NORTH:
						m_x -= SIZEOFCELL;
						break;
					case SOUTHEAST:
						m_x += SIZEOFCELL;
						m_y -= SIZEOFCELL;
						break;
					case NORTHWEST:
						m_x -= SIZEOFCELL;
						m_y -= SIZEOFCELL;
						break;
					case SOUTHWEST:
						m_y += SIZEOFCELL;
						m_x += SIZEOFCELL;
						break;
					case NORTHEAST:
						m_y += SIZEOFCELL;
						m_x -= SIZEOFCELL;
						break;
					default:
						break;
				}
				break;
			case RIGHT:
				switch (m_dir) {
					case WEST:
						m_y -= SIZEOFCELL;
						break;
					case EAST:
						m_y += SIZEOFCELL;
						break;
					case NORTH:
						m_x += SIZEOFCELL;
						break;
					case SOUTH:
						m_x -= SIZEOFCELL;
						break;
					case NORTHWEST:
						m_x += SIZEOFCELL;
						m_y -= SIZEOFCELL;
						break;
					case SOUTHWEST:
						m_x -= SIZEOFCELL;
						m_y -= SIZEOFCELL;
						break;
					case NORTHEAST:
						m_y += SIZEOFCELL;
						m_x += SIZEOFCELL;
						break;
					case SOUTHEAST:
						m_y += SIZEOFCELL;
						m_x -= SIZEOFCELL;
						break;
					default:
						break;
				}
				break;
			case NORTH:
				m_y -= SIZEOFCELL;
				break;
			case SOUTH:
				m_y += SIZEOFCELL;
				break;
			case EAST:
				m_x += SIZEOFCELL;
				break;
			case WEST:
				m_x -= SIZEOFCELL;
				break;
			case NORTHEAST:
				m_x += SIZEOFCELL;
				m_y -= SIZEOFCELL;
				break;
			case NORTHWEST:
				m_x -= SIZEOFCELL;
				m_y -= SIZEOFCELL;
				break;
			case SOUTHEAST:
				m_y += SIZEOFCELL;
				m_x += SIZEOFCELL;
				break;
			case SOUTHWEST:
				m_y += SIZEOFCELL;
				m_x -= SIZEOFCELL;
				break;
			default:
				break;
		}
		return;
	}

	public void Pick(/* Donner une direction ? */) {
		System.out.println("Is Picking");
	}

	public void Pop() {
		System.out.println("Is Poping");
	}

	public void Power() {
		System.out.println("Is \"UNLIMITED PAWER!!\"-ing");
	}

	public void Protect() {
		System.out.println("Is Protecting");
	}

	public void Store() {
		System.out.println("Is Storing");
	}

	public void Turn(MyDirection dir) {
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
				switch(m_dir) {
					case NORTH:
						m_dir = MyDirection.WEST;
						break;
					case WEST:
						m_dir = MyDirection.SOUTH;
						break;
					case SOUTH:
						m_dir = MyDirection.EAST;
						break;
					case EAST:
						m_dir = MyDirection.NORTH;
						break;
					case NORTHEAST:
						m_dir = MyDirection.NORTHWEST;
						break;
					case NORTHWEST:
						m_dir = MyDirection.SOUTHWEST;
						break;
					case SOUTHWEST:
						m_dir = MyDirection.SOUTHEAST;
						break;
					case SOUTHEAST:
						m_dir = MyDirection.NORTHEAST;
						break;
					default:
						break;
				}
				break;
			case RIGHT:
				switch(m_dir) {
					case NORTH:
						m_dir = MyDirection.EAST;
						break;
					case WEST:
						m_dir = MyDirection.NORTH;
						break;
					case SOUTH:
						m_dir = MyDirection.WEST;
						break;
					case EAST:
						m_dir = MyDirection.SOUTH;
						break;
					case NORTHEAST:
						m_dir = MyDirection.SOUTHEAST;
						break;
					case SOUTHEAST:
						m_dir = MyDirection.SOUTHWEST;
						break;
					case SOUTHWEST:
						m_dir = MyDirection.NORTHWEST;
						break;
					case NORTHWEST:
						m_dir = MyDirection.NORTHEAST;
						break;
					default:
						break;
				}
				break;
		}
	}

	public void Throw() {
		System.out.println("Is Throwing");
	}

	public void Wait() {
		System.out.println("Is Waiting");
	}

	public void Wizz() {
		System.out.println("Is Wizzing");
	}

	public boolean GetDir(Direction dir) {
		System.out.println("Is Getdiring");
		if (m_dir != null) {
			return m_dir.equals(dir);
		}
		return false;
	}

	public boolean Cell(Direction dir, Entity type) {
		System.out.println("Is Celling");
		return false;
	}

	public boolean GotPower() {
		System.out.println("Is Got\"UNLIMITED PAWER\"-ing");
		return true;
	}

	public boolean GotStuff() {
		System.out.println("Is Gotstuffing");
		return true;
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
	

}
