package info3.game.automaton;

import info3.game.model.entities.*;

public class And extends Condition{

	public Condition m_left;
	public Condition m_right;
	
	public And(Condition left, Condition right) {
		m_left = left;
		m_right = right;
	}

	@Override
	public boolean realisable(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
