package info3.game.myAutotmaton;

import info3.game.model.Entity;

public abstract class FunCall {
	
	int m_percent;

	public FunCall(int percent) {
		m_percent = percent;
	}
	
	public abstract void execut(Entity e);
	
}
