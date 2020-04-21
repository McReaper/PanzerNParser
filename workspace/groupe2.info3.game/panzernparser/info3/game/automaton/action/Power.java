package info3.game.automaton.action;

import info3.game.automaton.FunCall;
import info3.game.model.entities.*;

public class Power extends FunCall{

	public Power(int percent) {
		super(percent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execut(Entity e) {
		e.Power();
	}

}
