package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

public class ShotEnemyLevel2 extends ShotEnemy {
	public static final int SHOTENEMYLEVEL2_WIDTH = 1;
	public static final int SHOTENEMYLEVEL2_HEIGHT = 1;
	
	public static final long SHOTENEMYLEVEL2_SPEED = 50;
	public static final int SHOTENEMYLEVEL2_NUMBER_CASE_LIFE = 4;
	
	public ShotEnemyLevel2(int x, int y, Automaton aut) {
		super(x, y, SHOTENEMYLEVEL2_WIDTH, SHOTENEMYLEVEL2_HEIGHT, aut);
		m_nbCaseLeft = SHOTENEMYLEVEL2_NUMBER_CASE_LIFE*2;
	}
	
	@Override
	public void Move(MyDirection dir) {
		m_nbCaseLeft --;
		if (m_nbCaseLeft <=0) {
			m_health = 0;
		}
		super.Move(dir);
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
