package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

public class ShotSlow extends Shot {
	public static final int SHOTSLOW_WIDTH = 1;
	public static final int SHOTSLOW_HEIGHT = 1;

	public static final int SHOTSLOW_HEALTH = 100;
	public static final int SHOTSLOW_SPEED = 250;
	public static final int SHOTSLOW_NUMBER_CASE_LIFE = 5;

	public static final long SHOTSLOW_EXPLODE_TIME = 1000;
	public static final long SHOTSLOW_MOVE_TIME = 200;
	public static final long SHOTSLOW_POP_TIME = 10000;
	public static final long SHOTSLOW_WIZZ_TIME = 1000;

	public ShotSlow(int x, int y, Automaton aut) {
		super(x, y, SHOTSLOW_WIDTH, SHOTSLOW_HEIGHT, aut);
		m_health = SHOTSLOW_HEALTH;
		m_speed = SHOTSLOW_SPEED;
		m_nbCaseLife = SHOTSLOW_NUMBER_CASE_LIFE;
	}

	public void Pop(MyDirection dir) {// pose un droppable
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			m_height *= 2;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Pop;
			m_timeOfAction = SHOTSLOW_POP_TIME;
		}
	}

	public void Wizz(MyDirection dir) {// Change de direction
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
			m_currentActionDir = MyDirection.toAbsolute(m_currentLookAtDir, dir);
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = SHOTSLOW_WIZZ_TIME;
		}
	}

	@Override
	public void Explode() {
		if (m_actionFinished && m_currentAction == LsAction.Explode) {
			this.doExplode();
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Explode;
			m_timeOfAction = SHOTSLOW_EXPLODE_TIME;
		}
	}
}