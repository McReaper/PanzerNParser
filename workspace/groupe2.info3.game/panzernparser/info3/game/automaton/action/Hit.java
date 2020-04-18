package info3.game.automaton.action;

import info3.game.automaton.Direction;
import info3.game.automaton.FunCall;
import info3.game.model.entities.*;

public class Hit extends FunCall {

	Direction m_direction;

	public Hit(int percent, Direction dir) {
		super(percent);
		m_direction = dir;
	}

	@Override
	public void execut(Entity e) {

	}
}
