package info3.game.myAutotmaton;

import info3.game.model.Entity;

public class And extends Condition{

	public Condition m_left;
	public Condition m_right;
	
	@Override
	public boolean realisable(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
