package info3.game.automaton;

public class State {
	String m_name;

	State(String name) {
		m_name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof State) {
			if (((State) obj).m_name.equals(m_name)) {
				return true;
			}
		}
		return false;
	}

}
