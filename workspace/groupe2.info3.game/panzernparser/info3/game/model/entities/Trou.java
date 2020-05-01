package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

public class Trou extends Ground {

	public Trou(int x, int y, Automaton aut) {
		super(x, y, aut);
		m_stuff = false;
	}

	@Override
	public void Pop(MyDirection dir) {
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
}
