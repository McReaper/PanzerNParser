package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

public class Hole extends StaticEntity {
	
	public final static int HOLE_WIDTH = 1;
	public final static int HOLE_HEIGHT = 1;

	public static final long HOLE_POP_TIME = 10000;
	public static final long HOLE_WIZZ_TIME = 1000;
	
	public Hole(int x, int y, Automaton aut) {
		super(x, y, HOLE_WIDTH, HOLE_HEIGHT, aut);
		m_stuff = false;
	}

	@Override
	public void Pop(MyDirection dir) {//Se creuse
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			m_stuff = false;
			m_health = 0;
			
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = TankBody.TANKBODY_POP_TIME;
		}
	}
	@Override
	public void Wizz(MyDirection dir) {//Grandi
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = HOLE_WIZZ_TIME;
			m_width *=2;
			m_height *=2;
		}
	}
}
