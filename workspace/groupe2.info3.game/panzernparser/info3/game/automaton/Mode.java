package info3.game.automaton;

import info3.game.model.entities.Entity;

public class Mode {
	Behaviour m_behaviour;
	State m_state;

	Mode(Behaviour behaviour, State state) {
		m_behaviour = behaviour;
		m_state = state;
	}

	State step(Entity e) {
		return m_behaviour.step(e);
	}
}
