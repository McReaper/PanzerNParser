package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;

public abstract class Shot extends MovingEntity{

	// entité qui a tiré ce shot
	private Entity m_owner;

	public Shot(int x, int y, int width, int height, Automaton aut) {
		// TODO
		super(x, y, width, height, aut);
		m_category = MyCategory.M;
	}

	@Override
	public abstract void Move(MyDirection dir);

	@Override
	public abstract void Explode();

	public void setOwner(Entity ent) {
		m_owner = ent;
	}

	public Entity getOwner() {
		return m_owner;
	}

}
