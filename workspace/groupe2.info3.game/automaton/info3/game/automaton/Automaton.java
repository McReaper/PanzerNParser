package info3.game.automaton;

import java.util.List;
import java.util.ListIterator;

import info3.game.model.entities.Entity;

public class Automaton {
	State m_state;
	List<Mode> m_modes;
	String m_name;

	Automaton(String name, List<Mode> modes, State state) {
		m_state = state;
		m_modes = modes;
		m_name = name;
	}

	public State getState() {
		return m_state;
	}

	public String getName() {
		return m_name;
	}

	public State step(Entity e) {
		ListIterator<Mode> iter = m_modes.listIterator();
		Mode currentMode;
		State currentState = e.getState();
		while (iter.hasNext()) {
			currentMode = (Mode) iter.next();
			if (currentState.equals(currentMode.m_state)) {
				return currentMode.step(e);
			}
		}
		return null;

	}
}
