package info3.game.myAutotmaton;

import info3.game.model.Entity;

public class MyDir extends Condition{
	
	Direction m_direction;
	
	public public MyDir(Direction dir) {
		m_direction= dir;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean realisable(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
