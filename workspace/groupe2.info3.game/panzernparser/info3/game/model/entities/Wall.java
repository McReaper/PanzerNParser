package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.action.LsAction;

public class Wall extends StaticEntity{

	public final static int WALL_WIDTH = 2;
	public final static int WALL_HEIGHT = 2;

	public static final int WALL_HEALTH = 100;
	public static final int WALL_SPEED = 100;

	public static final long WALL_EGG_TIME = 1000;
	public static final long WALL_GET_TIME = 1000;
	public static final long WALL_HIT_TIME = 1000;
	public static final long WALL_JUMP_TIME = 1000;
	public static final long WALL_EXPLODE_TIME = 1000;
	public static final long WALL_MOVE_TIME = 2000;
	public static final long WALL_PICK_TIME = 1000;
	public static final long WALL_POP_TIME = 10000;
	public static final long WALL_POWER_TIME = 1000;
	public static final long WALL_PROTECT_TIME = 1000;
	public static final long WALL_STORE_TIME = 1000;
	public static final long WALL_TURN_TIME = 1000;
	public static final long WALL_THROW_TIME = 1000;
	public static final long WALL_WAIT_TIME = 4000;
	public static final long WALL_WIZZ_TIME = 1000;
	
	
	public Wall(int x, int y, Automaton aut) {
		super(x, y, WALL_WIDTH, WALL_HEIGHT, aut);
		this.setCategory(MyCategory.O);
	}

	@Override
	public void Wait() {
		if (m_actionFinished && m_currentAction == LsAction.Wait) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = null;
			m_currentAction = LsAction.Wait;
			m_timeOfAction = WALL_WAIT_TIME;
		}
	}
	
}
