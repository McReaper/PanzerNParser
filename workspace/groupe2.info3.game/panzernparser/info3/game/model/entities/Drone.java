package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Grid.Coords;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.EntityFactory.MyEntities;

public class Drone extends MovingEntity {
	public final static int DRONE_WIDTH = 1;
	public final static int DRONE_HEIGHT = 1;

	public static final int DRONE_HEALTH = 100;
	public static final int DRONE_SPEED = 1;

	public static int MARKER_MAX = 3;

	public static final long DRONE_EGG_TIME = 1000;
	public static final long DRONE_GET_TIME = 1000;
	public static final long DRONE_HIT_TIME = 1000;
	public static final long DRONE_JUMP_TIME = 50;
	public static final long DRONE_EXPLODE_TIME = 1000;
	public static final long DRONE_MOVE_TIME = 1000;
	public static final long DRONE_PICK_TIME = 1000;
	public static final long DRONE_POP_TIME = 1000;
	public static final long DRONE_POWER_TIME = 1000;
	public static final long DRONE_PROTECT_TIME = 1000;
	public static final long DRONE_STORE_TIME = 1000;
	public static final long DRONE_TURN_TIME = 50;
	public static final long DRONE_THROW_TIME = 1000;
	public static final long DRONE_WAIT_TIME = 50;
	public static final long DRONE_WIZZ_TIME = 1000;

	private int m_nbMarkers;
	VisionType m_currentVisionType;

	public Drone(int x, int y, int width, int height, int health, int speed, Automaton aut) {
		super(x, y, width, height, health, speed, aut);
		m_category = MyCategory.AT;
		m_nbMarkers = 0;
		m_lengthOfView = 10;
		m_currentVisionType = VisionType.RESSOURCES;
		m_category = MyCategory.V;
	}

	@Override
	public boolean Closest(MyDirection dir, MyCategory type) {
		if (type == MyCategory.C && Model.getModel().getClue() != null)
			return true;
		// return super.Closest(dir, type);
		return false;// temporaire
	}


	private void switchVision() {
		if (m_currentVisionType == VisionType.RESSOURCES)
			m_currentVisionType = VisionType.ENEMIES;
		else
			m_currentVisionType = VisionType.RESSOURCES;
	}
	
	public VisionType getVisionType() {
		return m_currentVisionType;
	}

	private boolean hasControl() {
		return !Model.getModel().isPlayingTank();
	}

	@Override
	public void Hit(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Hit) {
			Coords c = Model.getModel().getClue();
			Marker marker = (Marker) EntityFactory.newEntity(MyEntities.Marker, (int) c.X, (int) c.Y);
			if (m_nbMarkers == MARKER_MAX)
				Model.getModel().update(marker);
			else {
				Model.getModel().addEntity(marker);
				m_nbMarkers++;
			}
			System.out.println("Hit du drone depot de marker !");
			Model.getModel().cleanClue();
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Hit;
			m_timeOfAction = DRONE_HIT_TIME;
		}
	}

	@Override
	public void Move(MyDirection dir) {
		if (this.hasControl()) {
			if (m_actionFinished && m_currentAction == LsAction.Move) {
				System.out.println("Le drone avance !");
				this.doMove(dir);
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				MyDirection absoluteDir = MyDirection.toAbsolute(m_currentActionDir, dir);
				switch (absoluteDir) {
					case NORTH:
					case EAST:
					case WEST:
					case SOUTH:
						m_timeOfAction = DRONE_MOVE_TIME;
						break;
					case NORTHEAST:
					case NORTHWEST:
					case SOUTHEAST:
					case SOUTHWEST:
						m_timeOfAction = (long) Math.sqrt(2 * DRONE_MOVE_TIME * DRONE_MOVE_TIME);

				}
				m_currentActionDir = dir;
				m_currentAction = LsAction.Move;
			}
		}
	}

	@Override
	public void Pop(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			switchVision();
			System.out.println("Switch de vision !");
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = DRONE_POP_TIME;
		}
	}

	@Override
	public void Turn(MyDirection dir, int angle) {
		if (m_actionFinished && m_currentAction == LsAction.Turn) {
			System.out.println("Le drone tourne !");
			this.doTurn(dir);
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Turn;
			m_timeOfAction = DRONE_TURN_TIME;
		}
	}

	@Override
	public void Wizz(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			Model.getModel().switchControl();
			System.out.println("DRONE fait le wizz !");
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = DRONE_WIZZ_TIME;
		}
	}

	@Override
	public boolean Key(LsKey key) {
		if (hasControl())
			return super.Key(key);

		return false;
	}

	public void growViewPort() {
		// m_range++;
	}

	public void reduceViewPort() {
		// m_range--;
	}

}
