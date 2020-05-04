package info3.game.model.entities;

import java.util.LinkedList;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;

public abstract class Shot extends MovingEntity {

	// entité qui a tiré ce shot
	private Entity m_owner;

	public Shot(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
		m_category = MyCategory.M;
		m_uncrossables = new LinkedList<MyCategory>();
	}

	public void setOwner(Entity ent) {
		m_owner = ent;
	}

	public Entity getOwner() {
		return m_owner;
	}

}
