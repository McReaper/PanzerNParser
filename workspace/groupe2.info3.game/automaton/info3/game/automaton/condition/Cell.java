package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.model.entities.*;

public class Cell extends Condition {

	MyDirection m_direction;
	MyCategory m_entity;
	int m_distance;

	public Cell(MyDirection direction, MyCategory entity, int dist) {
		m_direction = direction;
		m_entity = entity;
		m_distance = dist;
	}

	@Override
	public boolean realisable(Entity entity) {
		return entity.Cell(m_direction, m_entity, m_distance);
	}

}
