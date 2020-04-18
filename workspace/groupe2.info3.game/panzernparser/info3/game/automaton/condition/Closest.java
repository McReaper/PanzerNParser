package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.automaton.Direction;
import info3.game.model.entities.*;

public class Closest extends Condition{
	
	Direction m_direction;
	Entity m_entity;
	
	public Closest(Entity entity, Direction dir) {
		m_direction = dir;
		m_entity = entity;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean realisable(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
