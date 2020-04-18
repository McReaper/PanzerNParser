package info3.game.myAutotmaton;

import info3.game.model.Entity;

public class Mode {
	Behaviour m_behaviour;
	State m_state;

	Mode(Behaviour behaviour, State state) {
		m_behaviour = behaviour;
		m_state = state;
	}

	boolean step(Entity e) {
		return false;
	}
}
