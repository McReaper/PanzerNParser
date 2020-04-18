package info3.game.myAutotmaton;

import info3.game.model.Entity;

public class Transition {

	State m_target;
	Condition m_condition;
	Action m_action;

	public Transition(Condition condition, Action action, State target) {
		m_condition = condition;
		m_action = action;
		m_target = target;
	}
	
	boolean realisable(Entity e) {
		return m_condition.realisable(e);
	}
	
	boolean execute(Entity e) {
		return m_action.execute(e);
	}

}
