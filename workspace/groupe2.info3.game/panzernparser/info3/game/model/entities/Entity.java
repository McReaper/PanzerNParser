package info3.game.model.entities;

import info3.game.automaton.MyCategory;

import java.util.Collection;
import java.util.LinkedList;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.State;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.Coords;

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
	MyCategory m_category; // La catégorie à laquelle l'entité appartient, utilisé principalement dans
													// l'automate dans Cell et Closest

	boolean m_stuff; // TODO : rediscuter de l'utilité de cette variable (pour le gotStuff)

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

	public MyCategory getCategory() {
		return m_category;
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

	public boolean isInMe(double x, double y) {
		return !(x < m_x || y < m_y || y > m_y + m_height - 1 || x > m_x + m_width - 1);
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

	/**
	 * Pour une diretion `dir` à partir de l'entité, on vérifie que la catégorie de
	 * type `type` se trouve bien dans une distance `dist`
	 * 
	 * @param dir  la direction dans laquelle regarder
	 * @param type le type d'entité visé
	 * @param dist la distance maximale
	 * @return vrai si l'entité existe dans cette direction a une distance donnée ou
	 *         moins
	 */
	public boolean Cell(MyDirection dir, MyCategory type, int dist) {
		MyDirection absoluteDir = MyDirection.toAbsolute(getLookAtDir(), dir);

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

	/**
	 * Pour une direction donnée `dir` par rapport à l'entité, on regarde en
	 * fonction de sa distance de vue si la catégorie `type` la plus proche donnée
	 * est dans cette direction
	 * 
	 * @param dir  la direction dans laquelle regarder
	 * @param type la catégorie recherchée
	 * @return vrai si l'entité de type recherché est "proche"
	 */
	public boolean Closest(MyDirection dir, MyCategory type) {
		MyDirection absoluteDir = MyDirection.toAbsolute(getLookAtDir(), dir);

		return false;

	}

	/**
	 * 
	 * @param dir  must be one of thoses : NORTHEAST, SOUTHEAST, SOUTHWEST,
	 *             NORTHWEST
	 * @param dist
	 * @return
	 */
	protected LinkedList<Coords> getCellsInDiagonalDir(MyDirection dir, int dist) {
		double center_x = m_x + (double)(m_width) / 2.0;
		double center_y = m_y + (double)(m_height) / 2.0;
		LinkedList<Coords> cells = new LinkedList<Coords>();

		int x_factor = 1;
		int y_factor = 1;
		switch (dir) {
			case NORTHEAST:
				y_factor = -1;
				break;
			case NORTHWEST:
				y_factor = -1;
				x_factor = -1;
				break;
			case SOUTHEAST:
				break;
			case SOUTHWEST:
				x_factor = -1;
				break;
			default:
				throw new IllegalArgumentException(
						"Cette fonction est appelable uniquement avec les paramètres NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST");
		}

		// Ajout de toutes les cases dans un rectangle donné en dehors de l'entité.
		int side_length = Math.max(m_width, m_height) + dist;
		for (double x = 0; x < side_length; x++) {
			for (double y = 0; y < side_length; y++) {
				double actual_x = m_x + (x * x_factor) + 0.5; // +0.5 pour prendre en compte le milieu de la case
				double actual_y = m_y + (y * y_factor) + 0.5;
				if (!this.isInMe(actual_x, actual_y))
					cells.add(new Coords(actual_x, actual_y));
			}
		}

		Circle stencil = new Circle(center_x, center_y, (double) dist);
		Collection<Coords> coords_to_remove = new LinkedList<Coords>();

		for (Coords coord : cells) {
			if (!stencil.isInMe(coord.X, coord.Y)) {
				coords_to_remove.add(coord);
			}
		}
		cells.removeAll(coords_to_remove);

		return cells;
	}

	protected LinkedList<Coords> getCellsInOrthogonalDir(MyDirection dir, int dist) {
		LinkedList<Coords> cells = new LinkedList<Coords>();

		switch (dir) {
			case NORTH:
				cells.addAll(getCellsInDiagonalDir(MyDirection.NORTHEAST, dist));
				cells.addAll(getCellsInDiagonalDir(MyDirection.NORTHWEST, dist));
				break;
			case WEST:
				cells.addAll(getCellsInDiagonalDir(MyDirection.SOUTHWEST, dist));
				cells.addAll(getCellsInDiagonalDir(MyDirection.NORTHWEST, dist));
				break;
			case EAST:
				cells.addAll(getCellsInDiagonalDir(MyDirection.NORTHEAST, dist));
				cells.addAll(getCellsInDiagonalDir(MyDirection.SOUTHEAST, dist));
				break;
			case SOUTH:
				cells.addAll(getCellsInDiagonalDir(MyDirection.SOUTHEAST, dist));
				cells.addAll(getCellsInDiagonalDir(MyDirection.SOUTHWEST, dist));
				break;
			default:
				throw new IllegalArgumentException(
						"Cette fonction est appelable uniquement avec les paramètres NORTH, EAST, WEST, SOUTH");
		}
		
		//TODO : non-terminé...

		return cells;

	}

	protected static class Circle {

		double m_radius;
		double m_x, m_y;

		public Circle(double x, double y, double r) {
			m_x = x;
			m_y = y;
			m_radius = r;
		}

		public boolean isInMe(double x, double y) {
			return Math.pow((x - m_x), 2) + Math.pow((y - m_y), 2) <= Math.pow(m_radius, 2);
		}
	}
}
