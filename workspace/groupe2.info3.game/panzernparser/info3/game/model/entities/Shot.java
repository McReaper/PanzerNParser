package info3.game.model.entities;

import java.util.LinkedList;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;

public abstract class Shot extends MovingEntity {

	// entité qui a tiré ce shot
	private Entity m_owner;
	protected int m_nbCaseCrossedDiad;
	protected int m_nbCaseCrossedRight;
	protected int m_nbCaseLeft;
	protected int m_nbCaseLeftDiag;
	protected int m_nbCaseLife;

	public Shot(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
		m_category = MyCategory.M;
		m_uncrossables = new LinkedList<MyCategory>();
		m_nbCaseCrossedDiad = 0;
		m_nbCaseCrossedRight =0;
	}
	
	@Override
	public void Move(MyDirection dir) {
		super.Move(dir);
		MyDirection absDir = MyDirection.toAbsolute(m_currentActionDir, dir);
		switch(absDir) {
			case NORTH:
			case SOUTH:
			case EAST:
			case WEST:
				m_nbCaseCrossedRight ++;
				break;
			case NORTHEAST:
			case NORTHWEST:
			case SOUTHEAST:
			case SOUTHWEST:
				m_nbCaseCrossedDiad ++;
			default:
				break;
		}
		if ((m_nbCaseCrossedRight + (int) (m_nbCaseCrossedDiad * (Math.sqrt(2))))/2 ==m_nbCaseLife) {
			m_health = 0;
		}
	}
	

	public void setOwner(Entity ent) {
		m_owner = ent;
	}

	public Entity getOwner() {
		return m_owner;
	}

	@Override
	public boolean isShown() {
		return (Model.getModel().getVisionType() == VisionType.TANK);
	}
	
	public void hasKilled(Entity e) {
		if (e.GotPower())
			return;
		else {
			Entity tankBody = Model.getModel().getTank().getBody();
			if (getOwner() == tankBody)
				Model.getModel().getTank().hasKilled(e);
		}
	}

		@Override
	public void collide(int damage) {
		m_health = 0;
	}
}
