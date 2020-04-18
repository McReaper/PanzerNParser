package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.automaton.Direction;
import info3.game.model.entities.*;

public class Cell extends Condition {

	Direction m_direction;
	Entity m_entity;
	int m_distance;

	public Cell(Direction direction, Entity entity, int dist) {

	}

	@Override
	public boolean realisable(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
