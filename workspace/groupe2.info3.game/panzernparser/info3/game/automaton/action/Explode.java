package info3.game.automaton.action;

import info3.game.automaton.FunCall;
import info3.game.model.entities.*;

public class Explode extends FunCall{

	public Explode(int percent) {
		super(percent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execut(Entity e) {
		System.out.println("J'execute Explode");
		
	}

}
