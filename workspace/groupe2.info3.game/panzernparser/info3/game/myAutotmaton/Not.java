package info3.game.myAutotmaton;

import info3.game.model.Entity;

public class Not extends Condition{
	public Condition m_exp;
	
	@Override
	public boolean realisable(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}
}
