package info3.game.model.entities;

import java.util.LinkedList;

import info3.game.automaton.Automaton;
import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Grid;
import info3.game.model.Grid.Coords;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.EntityFactory.MyEntities;

public class Drone extends MovingEntity {
	public final static int DRONE_WIDTH = 3;
	public final static int DRONE_HEIGHT = 3;

	public static final int DRONE_HEALTH = 300000;
	public static final int DRONE_SPEED = 150;

	public static final int INIT_MARKER_MAX = 2;
	public static final int DRONE_FOV = 13;

	public static final long DRONE_HIT_TIME = 300;
	public static final long DRONE_JUMP_TIME = 100;
	public static final long DRONE_MOVE_TIME = 1000;
	public static final long DRONE_POP_TIME = 300;
	public static final long DRONE_POWER_TIME = 1000;
	public static final long DRONE_TURN_TIME = 0;
	public static final long DRONE_WAIT_TIME = 50;
	public static final long DRONE_WIZZ_TIME = 1000;

	public static final int DRONE_RECHARGE = 28;
	public static final int DRONE_DECHARGE = 35;

	private int m_nbMarkers;
	private int m_maxMarkers;
	private VisionType m_currentVisionType;

	public Drone(int x, int y, Automaton aut) {
		super(x, y, DRONE_WIDTH, DRONE_HEIGHT, aut);
		m_category = MyCategory.V;
		m_nbMarkers = 0;
		m_range = DRONE_FOV;
		m_maxMarkers = INIT_MARKER_MAX;
		m_currentVisionType = VisionType.RESSOURCES;
		m_speed = DRONE_SPEED;
		m_uncrossables = new LinkedList<MyCategory>();
		m_maxHealth = DRONE_HEALTH;
		m_health = DRONE_HEALTH;
		m_stuff = false; // pour l'upgrade.
		m_moveSound = "droneMove2";
	}

	@Override
	public void step(long elapsed) {
		if (hasControl()) {
			double val = DRONE_DECHARGE * elapsed * (1.0 + ((double)(m_range - DRONE_FOV) / 10.0));
			m_health -= val;
			if (!GotPower()) {
				m_health = 0;
			}
		} else if (m_health < m_maxHealth) {
			double val = DRONE_RECHARGE * elapsed;
			m_health += val;
			if (m_health > m_maxHealth)
				m_health = m_maxHealth;
		}
		super.step(elapsed);
	}

	@Override
	public boolean Closest(MyDirection dir, MyCategory type) {
		return (type == MyCategory.C && Model.getModel().getClue() != null);
	}

	@Override
	public boolean GotStuff() {
		if (Model.getModel().getKeyPressed().contains(LsKey.AU) && m_range >= MAX_RANGE
				|| Model.getModel().getKeyPressed().contains(LsKey.AD) && m_range <= MIN_RANGE) {
			return false;
		}
		return super.GotStuff();
	}

	@Override
	public boolean isShown() {
		return (Model.getModel().getVisionType() != VisionType.TANK);
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
		if (hasControl()) {
			if (m_actionFinished && m_currentAction == LsAction.Hit) {
				Coords c = Model.getModel().getClue();
				Grid grid = Model.getModel().getGrid();
				LinkedList<Entity> lsmarker = grid.getEntityCells((int) c.X - 1, (int) c.Y - 1, 3, 3, MyEntities.Marker);
				if (lsmarker.size() != 0) {
					for (Entity entity : lsmarker) {
						Model.getModel().removeEntity(entity);
						m_nbMarkers--;
					}
				} else {
					EntityFactory.newEntity(MyEntities.Marker, (int) c.X, (int) c.Y);
					Model.getModel().addSound("poseMarker");
					if (m_nbMarkers == m_maxMarkers)
						Model.getModel().removeEntity(Model.getModel().getEntities(MyEntities.Marker).get(0));
					else {
						m_nbMarkers++;
					}
				}
				Model.getModel().cleanClue();
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				m_currentActionDir = dir;
				m_currentAction = LsAction.Hit;
				m_timeOfAction = DRONE_HIT_TIME;
			}
		}
	}

	@Override
	public void Move(MyDirection dir) {
		if (this.hasControl()) {
			super.Move(dir);
		}
	}

	@Override
	public void Pop(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			switchVision();
			Model.getModel().addSound("bipDrone2");
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
			this.doTurn(dir);
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Turn;
			m_timeOfAction = DRONE_TURN_TIME;
		}
	}

	public void Wait() {
		if (m_actionFinished && m_currentAction == LsAction.Wait) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wait;
			m_timeOfAction = DRONE_WAIT_TIME;
		}
	}

	@Override
	public void Wizz(MyDirection dir) {
		if (hasControl()) {
			if (m_actionFinished && m_currentAction == LsAction.Wizz) {
				Model.getModel().switchControl();
				m_actionFinished = false;
				m_currentAction = null;
			} else if (m_currentAction == null) {
				Model.getModel().addSound("droneToTank");
				m_currentActionDir = dir;
				m_currentAction = LsAction.Wizz;
				m_timeOfAction = DRONE_WIZZ_TIME;
			}
		}
	}

	@Override
	public void Jump(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Jump) {
			switch (dir) {
				case FRONT:
					growViewPort();
					break;
				case BACK:
					reduceViewPort();
					break;
				default:
					break;
			}
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			switch (dir) {
				case FRONT:
					if (m_range < MAX_RANGE) {
						m_currentActionDir = dir;
						m_currentAction = LsAction.Jump;
						m_timeOfAction = DRONE_JUMP_TIME;
					}
					break;
				case BACK:
					if (m_range > MIN_RANGE) {
						m_currentActionDir = dir;
						m_currentAction = LsAction.Jump;
						m_timeOfAction = DRONE_JUMP_TIME;
					}
					break;
				default:
					break;
			}
		}
	}

	@Override
	public boolean Key(LsKey key) {
		if (hasControl())
			return super.Key(key);
		return false;
	}

	public void increaseMarksNb() {
		m_nbMarkers++;
	}

	public void decreaseMarksNb() {
		m_nbMarkers--;
	}

	public int getNbMarker() {
		return m_nbMarkers;
	}

	public int getMaxMarkers() {
		return m_maxMarkers;
	}

	public void resetMarkers() {
		m_nbMarkers = 0;
	}

	public void setMaxMarkers(int maxCount) {
		m_maxMarkers = maxCount;
	}

}
