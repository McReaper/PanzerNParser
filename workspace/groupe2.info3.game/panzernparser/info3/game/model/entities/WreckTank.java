package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

public class WreckTank extends StaticEntity {

	public final static int WRECKTANK_WIDTH = 3;
	public final static int WRECKTANK_HEIGHT = 3;

	public static final int WRECKTANK_HEALTH = 100;
	public static final int WRECKTANK_SPEED = 100;

	public static final long WRECKTANK_EGG_TIME = 1000;
	public static final long WRECKTANK_GET_TIME = 1000;
	public static final long WRECKTANK_HIT_TIME = 1000;
	public static final long WRECKTANK_JUMP_TIME = 1000;
	public static final long WRECKTANK_EXPLODE_TIME = 1000;
	public static final long WRECKTANK_MOVE_TIME = 2000;
	public static final long WRECKTANK_PICK_TIME = 1000;
	public static final long WRECKTANK_POP_TIME = 10000;
	public static final long WRECKTANK_POWER_TIME = 1000;
	public static final long WRECKTANK_PROTECT_TIME = 1000;
	public static final long WRECKTANK_STORE_TIME = 1000;
	public static final long WRECKTANK_TURN_TIME = 1000;
	public static final long WRECKTANK_THROW_TIME = 1000;
	public static final long WRECKTANK_WAIT_TIME = 5000;
	public static final long WRECKTANK_WIZZ_TIME = 1000;

	public boolean m_isExploded;
	
	public WreckTank(int x, int y, Automaton aut) {
		super(x, y, WRECKTANK_WIDTH, WRECKTANK_HEIGHT, aut);
		this.setCategory(MyCategory.O);
		m_isExploded = false;
	}

	@Override
	public boolean isShown() {// TODO update en fonction des sprites dispo
		return false;
	}

	@Override
	public void Pop(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_isExploded = true;
			m_timeOfAction = WRECKTANK_POP_TIME;
		}
	}

	@Override
	public void Wizz(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			setCategory(MyCategory.V);
			m_timeOfAction = WRECKTANK_WIZZ_TIME;
		}
	}

}
