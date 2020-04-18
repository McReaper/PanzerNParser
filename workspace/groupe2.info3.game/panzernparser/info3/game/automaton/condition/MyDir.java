package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.automaton.Direction;
import info3.game.model.entities.*;

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
