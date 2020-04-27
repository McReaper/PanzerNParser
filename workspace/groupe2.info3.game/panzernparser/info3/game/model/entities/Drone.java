package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.LsKey;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;

public class Drone extends MovingEntity {
	public final static int DRONE_WIDTH = 1;
	public final static int DRONE_HEIGHT = 1;

	public static final int DRONE_HEALTH = 100;
	public static final int DRONE_SPEED = 1;

	public static final long DRONE_EGG_TIME = 1000;
	public static final long DRONE_GET_TIME = 1000;
	public static final long DRONE_HIT_TIME = 1000;
	public static final long DRONE_JUMP_TIME = 50;
	public static final long DRONE_EXPLODE_TIME = 1000;
	public static final long DRONE_MOVE_TIME = 1000;
	public static final long DRONE_PICK_TIME = 1000;
	public static final long DRONE_POP_TIME = 10000;
	public static final long DRONE_POWER_TIME = 1000;
	public static final long DRONE_PROTECT_TIME = 1000;
	public static final long DRONE_STORE_TIME = 1000;
	public static final long DRONE_TURN_TIME = 50;
	public static final long DRONE_THROW_TIME = 1000;
	public static final long DRONE_WAIT_TIME = 50;
	public static final long DRONE_WIZZ_TIME = 1000;

	private enum VisionType {
		RESSOURCES, ENEMIES;
	}

	VisionType m_currentVisionType;

	public Drone(int x, int y, int width, int height, int health, int speed, Automaton aut) {
		super(x, y, width, height, health, speed, aut);
		m_currentVisionType = VisionType.RESSOURCES;
	}

	private void switchVision() {
		if (m_currentVisionType == VisionType.RESSOURCES)
			m_currentVisionType = VisionType.ENEMIES;
		else
			m_currentVisionType = VisionType.RESSOURCES;
	}

	private boolean hasControl() {
		return !Model.getModel().isPlayingTank();
	}

	@Override
	public void Hit(MyDirection dir) {
			if (m_actionFinished && m_currentAction == LsAction.Hit) {
				System.out.println("Pose un marqueur !");
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
				m_currentActionDir = dir;
				m_currentAction = LsAction.Move;
				m_timeOfAction = DRONE_MOVE_TIME;
			}
		}
	}

	@Override
	public void Pop(MyDirection dir) {
			if (m_actionFinished && m_currentAction  == LsAction.Pop) {
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
