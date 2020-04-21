package info3.game.automaton.action;

import info3.game.automaton.MyDirection;
import info3.game.automaton.FunCall;
import info3.game.model.entities.*;

public class Egg extends FunCall {

	MyDirection m_direction;
	
	public Egg(int percent, MyDirection dir) {
		super(percent);
		m_direction= dir;
	}

	@Override
	public void execut(Entity e) {
		e.Egg(m_direction);
	}
}
