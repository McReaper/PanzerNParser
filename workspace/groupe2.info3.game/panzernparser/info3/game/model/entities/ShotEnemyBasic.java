package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

public class ShotEnemyBasic extends ShotEnemy{

	public static final int SHOTENEMYBASIC_WIDTH = 1;
	public static final int SHOTENEMYBASIC_HEIGHT = 1;
	
	public static final long SHOTENEMYBASIC_SPEED = 200;
	public static final int SHOTENEMYBASIC_NUMBER_CASE_LIFE = 5;
	
	public ShotEnemyBasic(int x, int y, Automaton aut) {
		super(x, y, SHOTENEMYBASIC_WIDTH, SHOTENEMYBASIC_HEIGHT, aut);
		m_speed = SHOTENEMYBASIC_SPEED;
		m_nbCaseLeft = SHOTENEMYBASIC_NUMBER_CASE_LIFE*2;
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
