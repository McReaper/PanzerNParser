package info3.game.automaton.action;

import info3.game.automaton.MyDirection;
import info3.game.automaton.FunCall;
import info3.game.model.entities.*;

public class Store extends FunCall{

	MyDirection m_direction;
	public Store(int percent, MyDirection dir) {
		super(percent);
		m_direction = dir;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execut(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
