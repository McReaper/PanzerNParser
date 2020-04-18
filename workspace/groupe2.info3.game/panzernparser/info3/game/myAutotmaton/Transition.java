package info3.game.myAutotmaton;

public class Transition {

	State m_target;
	Condition m_condition;
	Action m_action;

	public Transition(Condition condition, Action action, State target) {
		m_condition = condition;
		m_action = action;
		m_target = target;
	}

}
