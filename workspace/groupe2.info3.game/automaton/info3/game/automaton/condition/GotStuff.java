package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.model.entities.*;

public class GotStuff extends Condition{

	public GotStuff() {
	}
	
	@Override
	public boolean realisable(Entity entity) {
		return entity.GotStuff();
	}

}
