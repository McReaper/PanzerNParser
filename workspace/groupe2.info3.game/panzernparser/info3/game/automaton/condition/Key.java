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
		//System.out.println("Is this the real life? Is this just fantasy?");
		if (entity.m_model.m_keyPressed.contains(m_key)) {
			//System.out.println("Call in the landside...");
			return true;
		}
		return false;
	}

}
