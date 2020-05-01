package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

public class Trou extends Ground {
	private boolean m_stuff;

	public Trou(int x, int y, Automaton aut) {
		super(x, y, aut);
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

	public void setStuff(boolean bool) {
		m_stuff = bool;
	}

	@Override
	public boolean GotStuff() {
		return m_stuff;
	}

	@Override
	public boolean GotPower() {
		System.out.println();
		return m_health > 0;
	}
}
