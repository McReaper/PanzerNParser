package info3.game.automaton.action;

import info3.game.automaton.FunCall;
import info3.game.model.entities.*;

public class Wait extends FunCall{

	public Wait(int percent) {
		super(percent);
	}

	@Override
	public void execut(Entity e) {
		e.Wait();
	}

}
