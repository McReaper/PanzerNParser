package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;

public class ShotEnemyBoss extends Shot{
	public static final int SHOTENEMYBOSS_WIDTH = 2;
	public static final int SHOTENEMYBOSS_HEIGHT = 2;

	public static final long SHOTENEMYBOSS_SPEED = 300;
	public static final int SHOTENEMYBOSS_NUMBER_CASE_LIFE = 20;

	public ShotEnemyBoss(int x, int y, Automaton aut) {
		super(x, y, SHOTENEMYBOSS_WIDTH, SHOTENEMYBOSS_HEIGHT, aut);
		m_nbCaseLife = SHOTENEMYBOSS_NUMBER_CASE_LIFE;
		m_speed = SHOTENEMYBOSS_SPEED;
	}

	@Override
	public void Wait() {
		if (m_actionFinished && m_currentAction == LsAction.Wait) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			//m_currentActionDir = null;
			m_currentAction = LsAction.Wait;
			m_timeOfAction = 0;
		}
	}

	public void Pop(MyDirection dir) {// devient plus large
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			m_height *= 2;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Pop;
			m_timeOfAction = DEFAULT_POP_TIME;
		}
	}

	public void Wizz(MyDirection dir) {// s'alloge (width augmente)
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
			m_width *= 2;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = DEFAULT_WIZZ_TIME;
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
			m_timeOfAction = 0;
		}
	}

	@Override
	public void Explode() {
		if (isNoisy())
			Model.getModel().addSound("explosion");
		super.Explode();
	}
}
