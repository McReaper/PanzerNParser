package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.model.entities.*;

public class Closest extends Condition {

	MyDirection m_direction;
	MyCategory m_entity;

	public Closest(MyCategory entity, MyDirection dir) {
		m_direction = dir;
		m_entity = entity;
	}

	@Override
	public boolean realisable(Entity entity) {
		return entity.Closest(m_direction,m_entity);
	}

}
