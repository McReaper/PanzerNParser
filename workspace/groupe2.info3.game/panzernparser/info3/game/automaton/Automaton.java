package info3.game.automaton;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import info3.game.model.entities.*;

public class Automaton {
	State m_state;
	LinkedList<Mode> m_modes;
	String m_name;

	Automaton(String name, LinkedList<Mode> modes, State state) {
		m_state = state;
		m_modes = modes;
		m_name = name;
	}

	boolean step(Entity e) {
		ListIterator<Mode> iter = m_modes.listIterator();
		Mode current;
		while (iter.hasNext()) {
			current = (Mode) iter.next();
			if (current.step(e)) {
				return true;
			}
		}
		return false;

	}
}
