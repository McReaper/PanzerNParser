package info3.game.automaton.action;

import info3.game.automaton.Direction;
import info3.game.automaton.FunCall;
import info3.game.model.entities.*;

public class Move extends FunCall {

	Direction m_direction;

	public Move(int percent, Direction dir) {
		super(percent);
		m_direction = dir;
	}

	@Override
	public void execut(Entity e) {
		System.out.println("j'execute move");
	}
}