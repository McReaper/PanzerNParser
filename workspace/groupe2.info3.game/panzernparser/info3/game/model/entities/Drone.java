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
	public final static int DRONE_WIDTH = 5;
	public final static int DRONE_HEIGHT = 5;

	public static final int DRONE_HEALTH = 300000;
	public static final int DRONE_SPEED = 200;

	public static final int MARKER_MAX = 3;
	public static final int DRONE_FOV = 10;

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

	public static final int DRONE_DAMMAGE_DEALT = 0;
	public static final int DRONE_RECHARGE = 10;
	public static final int DRONE_DESCHARGE = 30;

	int m_nbMarkers;
	private VisionType m_currentVisionType;

	public Drone(int x, int y, Automaton aut) {
		super(x, y, DRONE_WIDTH, DRONE_HEIGHT, aut);
		m_category = MyCategory.V;
		m_nbMarkers = 0;
		m_range = DRONE_FOV;
		m_currentVisionType = VisionType.RESSOURCES;
		m_damage_dealt = DRONE_DAMAGE_DEALT;
		m_speed = DRONE_SPEED;
		m_uncrossables = new LinkedList<MyCategory>();
		m_maxHealth = DRONE_HEALTH;
		m_health = 0;
	}
	
	@Override
	public void step(long elapsed) {
		if(hasControl()) {
			m_health -= DRONE_DESCHARGE * elapsed;
			if (!GotPower()) {
				m_health = 0;
			}
		}else if (m_health < m_maxHealth) {
			m_health += DRONE_RECHARGE * elapsed;
		}
		System.out.println("drone health : "+m_health);
		super.step(elapsed);
	}

	@Override
	public boolean Closest(MyDirection dir, MyCategory type) {
		return (type == MyCategory.C && Model.getModel().getClue() != null);
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
				if (m_nbMarkers == MARKER_MAX)
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

	@Override
	public void Wizz(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			Model.getModel().switchControl();
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
		m_range++;
	}

	public void reduceViewPort() {
		m_range--;
	}

}
