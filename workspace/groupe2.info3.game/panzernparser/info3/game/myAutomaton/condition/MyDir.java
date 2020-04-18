package info3.game.myAutomaton.condition;

import info3.game.model.Entity;
import info3.game.myAutotmaton.Condition;
import info3.game.myAutotmaton.Direction;

public class MyDir extends Condition{
	
	Direction m_direction;
	
	public MyDir(Direction dir) {
		m_direction= dir;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean realisable(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
