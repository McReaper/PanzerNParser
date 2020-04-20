package info3.game.automaton;

import info3.game.model.entities.*;

public class Not extends Condition{
	public Condition m_exp;
	
	public Not(Condition expression) {
		m_exp = expression;
	}

	@Override
	public boolean realisable(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}
}
