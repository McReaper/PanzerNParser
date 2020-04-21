package info3.game.automaton.condition;

import info3.game.automaton.Condition;
import info3.game.automaton.LsKey;
import info3.game.model.entities.*;

public class Key extends Condition {

	LsKey m_key;

	public Key(LsKey key) {
		m_key = key;
	}

	@Override
	public boolean realisable(Entity entity) {
		if (entity.m_model.m_keyPressed.contains(m_key))
			return true;
		return false;
	}

}
