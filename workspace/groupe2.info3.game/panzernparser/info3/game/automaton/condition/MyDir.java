package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.automaton.MyDirection;
import info3.game.model.entities.*;

public class MyDir extends Condition{
	
	MyDirection m_direction;
	
	public MyDir(MyDirection dir) {
		m_direction= dir;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean realisable(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
