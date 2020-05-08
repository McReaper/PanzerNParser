package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

public class ShotEnemyLevel2 extends ShotEnemy {
	public static final int SHOTENEMYLEVEL2_WIDTH = 1;
	public static final int SHOTENEMYLEVEL2_HEIGHT = 1;
	
	public static final long SHOTENEMYLEVEL2_SPEED = 100;
	public static final int SHOTENEMYLEVEL2_NUMBER_CASE_LIFE = 7;
	
	public ShotEnemyLevel2(int x, int y, Automaton aut) {
		super(x, y, SHOTENEMYLEVEL2_WIDTH, SHOTENEMYLEVEL2_HEIGHT, aut);
		m_nbCaseLife = SHOTENEMYLEVEL2_NUMBER_CASE_LIFE;
		m_speed = SHOTENEMYLEVEL2_SPEED;
	}

	public void Pop(MyDirection dir) {//devient plus large
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			m_height *=2;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Pop;
			m_timeOfAction = DEFAULT_POP_TIME;
		}
	}
	
	public void Wizz(MyDirection dir) {//s'alloge (width augmente)
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
			m_width *=2;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = DEFAULT_WIZZ_TIME;
		}
	}
}
