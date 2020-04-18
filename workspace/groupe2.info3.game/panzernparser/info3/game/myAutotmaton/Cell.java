package info3.game.myAutotmaton;

import info3.game.model.Entity;

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
