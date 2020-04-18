package info3.game.automaton.action;

import info3.game.automaton.MyDirection;
import info3.game.automaton.FunCall;
import info3.game.model.entities.*;

public class Protect extends FunCall {

	MyDirection m_direction;

	public Protect(int percent, MyDirection dir) {
		super(percent);
		m_direction = dir;
	}

	@Override
	public void execut(Entity e) {

	}
}