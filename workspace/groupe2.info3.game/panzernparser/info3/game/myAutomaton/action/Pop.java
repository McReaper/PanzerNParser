package info3.game.myAutomaton.action;

import info3.game.model.Entity;
import info3.game.myAutotmaton.Direction;
import info3.game.myAutotmaton.FunCall;

public class Pop extends FunCall {

	Direction m_direction;

	public Pop(int percent, Direction dir) {
		super(percent);
		m_direction = dir;
	}

	@Override
	public void execut(Entity e) {

	}
}