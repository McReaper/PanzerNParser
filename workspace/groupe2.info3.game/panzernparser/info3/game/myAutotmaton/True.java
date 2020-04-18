package info3.game.myAutotmaton;

import info3.game.model.Entity;

public class True extends Condition{

	public True() {
	}
	
	@Override
	public boolean realisable(Entity entity) {
		return true;
	}

}
