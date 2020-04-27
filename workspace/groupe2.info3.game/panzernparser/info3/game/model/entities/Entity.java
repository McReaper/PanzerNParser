package info3.game.model.entities;

import info3.game.automaton.MyCategory;
import info3.game.automaton.Automaton;
import info3.game.automaton.LsKey;
import info3.game.automaton.MyDirection;
import info3.game.automaton.State;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;

public abstract class Entity {

	final static int DEFAULT_MOVING_DISTANCE = 1;

	public static final long DEFAULT_EGG_TIME = 1000;
	public static final long DEFAULT_GET_TIME = 1000;
	public static final long DEFAULT_HIT_TIME = 1000;
	public static final long DEFAULT_JUMP_TIME = 1000;
	public static final long DEFAULT_EXPLODE_TIME = 1000;
	public static final long DEFAULT_MOVE_TIME = 1000;
	public static final long DEFAULT_PICK_TIME = 1000;
	public static final long DEFAULT_POP_TIME = 1000;
	public static final long DEFAULT_POWER_TIME = 1000;
	public static final long DEFAULT_PROTECT_TIME = 1000;
	public static final long DEFAULT_STORE_TIME = 1000;
	public static final long DEFAULT_TURN_TIME = 1000;
	public static final long DEFAULT_THROW_TIME = 1000;
	public static final long DEFAULT_WAIT_TIME = 1000;
	public static final long DEFAULT_WIZZ_TIME = 1000;

	protected long m_elapseTime;
	protected LsAction m_currentAction;
	protected long m_timeOfAction;
	protected boolean m_displayed; // Indique si il doit etre affiché a l'écran où non.
	protected int m_x;
	protected int m_y;
	protected int m_width;
	protected int m_height;
	protected int m_speed;
	protected MyDirection m_currentLookAtDir;
	protected MyDirection m_currentActionDir;
	protected boolean m_stuff; // gotStuff ?
	protected State m_currentState;
	protected Automaton m_automate; // automate associé
	protected boolean m_actionFinished;

	public Entity(int x, int y, int width, int height, Automaton aut) {
		m_automate = aut;
		m_currentState = aut.getState();

		m_elapseTime = 0;
		m_currentAction = null;
		m_timeOfAction = 0;

		m_displayed = true;

		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;

		m_currentLookAtDir = MyDirection.NORTH; // par défaut
		m_currentActionDir = null; // par défaut

		m_actionFinished = true;
		
	}

	public void step(long elapsed) {
		if (m_currentAction == null) {
			this.setState(m_automate.step(this));
		} else {
			if (m_elapseTime < m_timeOfAction) {
				m_elapseTime += elapsed;
			} else {
				LsAction finishedAction = m_currentAction;
				m_elapseTime = 0;
				m_currentAction = null;
				m_actionFinished = true;

				// Mission accomplie, on rappel l'action en cours pour lui signaler son
				// accomplissement.
				switch (finishedAction) {
					case Egg:
						this.Egg(m_currentActionDir);
						break;
					case Explode:
						this.Explode();
						break;
					case Get:
						this.Get(m_currentActionDir);
						break;
					case Hit:
						this.Hit(m_currentActionDir);
						break;
					case Jump:
						this.Jump(m_currentActionDir);
						break;
					case Move:
						this.Move(m_currentActionDir);
						break;
					case Pick:
						this.Pick(m_currentActionDir);
						break;
					case Pop:
						this.Pop(m_currentActionDir);
						break;
					case Power:
						this.Power();
						break;
					case Protect:
						this.Protect(m_currentActionDir);
						break;
					case Store:
						this.Store(m_currentActionDir);
						break;
					case Throw:
						this.Throw(m_currentActionDir);
						break;
					case Turn:
						this.Turn(m_currentLookAtDir, 0 /* On prend pas compte de l'angle */);
						break;
					case Wait:
						this.Wait();
						break;
					case Wizz:
						this.Wizz(m_currentActionDir);
						break;
					default:
						throw new IllegalStateException("Etat inconnu");
				}
			}
		}
	}

	public double getActionProgress() {
		if (m_currentAction != null) {
			return ((double) m_elapseTime) / ((double) m_timeOfAction);
		}
		return 0;
	}

	public void setPosition(int x, int y) {
		m_x = x;
		m_y = y;
	}

	public boolean isShown() {
		return m_displayed;
	}

	public void showEntity(boolean b) {
		m_displayed = b;
	}

	public State getState() {
		return m_currentState;
	}

	public void setState(State state) {
		if (state != null)
			m_currentState = state;
		else
			System.err.println("setstate null !");
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

	//// METHODES DE L'AUTOMATE ////

	/// Actions :

	public void Egg(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Egging");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Egg;
			m_timeOfAction = DEFAULT_EGG_TIME;
		}
	}

	public void Get(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Getting");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Get;
			m_timeOfAction = DEFAULT_GET_TIME;
		}
	}

	public void Hit(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Hitting");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			if (dir != null)
				m_currentActionDir = dir;
			m_currentAction = LsAction.Hit;
			m_timeOfAction = DEFAULT_HIT_TIME;
		}
	}

	public void Explode() {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Exploding");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = null;
			m_currentAction = LsAction.Explode;
			m_timeOfAction = DEFAULT_EXPLODE_TIME;
		}
	}

	public void Jump(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Jumping");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Jump;
			m_timeOfAction = DEFAULT_JUMP_TIME;
		}
	}

	public void Move(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Moving to " + dir);
			m_actionFinished = false;
			this.doMove(dir);
			m_currentActionDir = dir;
			m_x = Model.getModel().getGrid().realX(m_x);
			m_y = Model.getModel().getGrid().realY(m_y);
			System.out.println("Arrived and facing " + m_currentLookAtDir);
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			this.m_currentAction = LsAction.Move;
			m_timeOfAction = DEFAULT_MOVE_TIME;
		}

	}

	public void doMove(MyDirection dir) {
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
	}
	
	public void Pick(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Picking");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pick;
			m_timeOfAction = DEFAULT_MOVE_TIME;
		}
	}

	public void Pop(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Poping");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = DEFAULT_POP_TIME;
		}
	}

	public void Power() {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Powering");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = null;
			m_currentAction = LsAction.Power;
			m_timeOfAction = DEFAULT_POWER_TIME;
		}
	}

	public void Protect(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Protecting");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Protect;
			m_timeOfAction = DEFAULT_PROTECT_TIME;
		}
	}

	public void Store(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Storing");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Store;
			m_timeOfAction = DEFAULT_STORE_TIME;
		}
	}

	public void Turn(MyDirection dir, int angle) {

		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Turning");
			m_actionFinished = false;
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
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Turn;
			m_timeOfAction = DEFAULT_TURN_TIME;
		}
	}

	public void Throw(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Throwing");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Throw;
			m_timeOfAction = DEFAULT_THROW_TIME;
		}
	}

	public void Wait() {
		if (m_actionFinished && m_currentAction == null) {
			//System.out.println("Is Waiting");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = null;
			m_currentAction = LsAction.Wait;
			m_timeOfAction = DEFAULT_WAIT_TIME;
		}
	}

	public void Wizz(MyDirection dir) {
		if (m_actionFinished && m_currentAction == null) {
			System.out.println("Is Wizzing");
			m_actionFinished = false;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = DEFAULT_WIZZ_TIME;
		}
	}

	/// Conditions :

	public boolean myDir(MyDirection dir) {
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
		// System.out.println("Is Gotpower-ing");
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

	public boolean Key(LsKey m_key) {
		if (Model.getModel().getKeyPressed().contains(m_key))
			return true;
		return false;
	}
}
