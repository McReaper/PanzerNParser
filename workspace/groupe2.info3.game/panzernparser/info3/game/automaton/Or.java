package info3.game.automaton;

import info3.game.model.entities.*;

public class Or extends Condition{
	public Condition m_left;
	public Condition m_right;
	
	public Or(Condition left, Condition right) {
		m_left = left;
		m_right = right;
	}

	@Override
	public boolean realisable(Entity entity) {
		return (m_left.realisable(entity) || m_right.realisable(entity));
	}

}
