package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.model.entities.Entity;

public class False extends Condition {
	public False() {
	}

	@Override
	public boolean realisable(Entity entity) {
		return false;
	}
}
