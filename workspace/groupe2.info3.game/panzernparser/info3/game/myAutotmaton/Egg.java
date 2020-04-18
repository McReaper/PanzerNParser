package info3.game.myAutotmaton;

import info3.game.model.Entity;

public class Egg extends FunCall {

	public Egg(int percent) {
		super(percent);
	}

	@Override
	public void execut(Entity e) {
		System.out.println("J'execute egg");
	}
}
