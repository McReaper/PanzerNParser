package info3.game.automaton;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import info3.game.model.entities.*;

public class Behaviour {

	List<Transition> m_transitions;

	public Behaviour(List<Transition> transition) {
		m_transitions = transition;
	}
	
	boolean step (Entity e) {
		ListIterator<Transition> iter = m_transitions.listIterator();
		Transition current;
		while (iter.hasNext()) {
			current = (Transition) iter.next();
			if (current.realisable(e)) {
				current.execute(e);
				return true;
			}
		}
		return false;
	}

}
