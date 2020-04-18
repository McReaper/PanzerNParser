package info3.game.myAutomaton.condition;

import info3.game.model.Entity;
import info3.game.myAutotmaton.Condition;

public class True extends Condition{

	public True() {
	}
	
	@Override
	public boolean realisable(Entity entity) {
		return true;
	}

}
