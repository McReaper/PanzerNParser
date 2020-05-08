package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.EntityFactory.MyEntities;

public class ShotFast extends Shot {
	public static final int SHOTFAST_WIDTH = 1;
	public static final int SHOTFAST_HEIGHT = 1;

	public static final int SHOTFAST_HEALTH = 100;
	public static final int SHOTFAST_SPEED = 100;
	public static final int SHOTFAST_NUMBER_CASE_LIFE = 10;

	public static final long SHOTFAST_EXPLODE_TIME = 1000;
	public static final long SHOTFAST_MOVE_TIME = 200;
	public static final long SHOTFAST_POP_TIME = 10000;
	public static final long SHOTFAST_WIZZ_TIME = 1000;

	public static final int SHOTFAST_DAMAGE_DEALT = 10;

	public ShotFast(int x, int y, Automaton aut) {
		super(x, y, SHOTFAST_WIDTH, SHOTFAST_HEIGHT, aut);
		m_health = SHOTFAST_HEALTH;
		m_damage_dealt = SHOTFAST_DAMAGE_DEALT;
		m_speed = SHOTFAST_SPEED;
		m_nbCaseLife = SHOTFAST_NUMBER_CASE_LIFE;
	}
	
	public void Pop(MyDirection dir) {//devient plus large
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			m_height *=2;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Pop;
			m_timeOfAction = SHOTFAST_POP_TIME;
		}
	}
	
	public void Wizz(MyDirection dir) {//s'alloge (width augmente)
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
			m_width *=2;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = SHOTFAST_WIZZ_TIME;
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
			m_timeOfAction = SHOTFAST_EXPLODE_TIME;
		}
	}

}
