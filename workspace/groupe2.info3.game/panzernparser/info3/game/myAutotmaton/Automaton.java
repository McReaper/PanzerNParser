package info3.game.myAutotmaton;

import java.util.List;

import info3.game.model.Entity;

public class Automaton {
	State m_state;
	List<Mode> m_modes;
	String m_name;

	Automaton(String name, List<Mode> modes, State state) {
		m_state = state;
		m_modes = modes;
		m_name = name;
	}

	boolean step(Entity e) {
		return false;
	}
}
