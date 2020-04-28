package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.model.entities.*;

public class True extends Condition{

	public True() {
	}
	
	@Override
	public boolean realisable(Entity entity) {
		return true;
	}

}
