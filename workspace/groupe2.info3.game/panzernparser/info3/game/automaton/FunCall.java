package info3.game.automaton;

import info3.game.model.entities.Entity;

public abstract class FunCall {
	
	int m_percent;

	public FunCall(int percent) {
		m_percent = percent;
	}
	
	public abstract void execut(Entity e);
	
}
