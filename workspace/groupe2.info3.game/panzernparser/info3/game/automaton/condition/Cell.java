package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.automaton.MyDirection;
import info3.game.model.entities.*;

public class Cell extends Condition {

	MyDirection m_direction;
	Entity m_entity;
	int m_distance;

	public Cell(MyDirection direction, Entity entity, int dist) {

	}

	@Override
	public boolean realisable(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
