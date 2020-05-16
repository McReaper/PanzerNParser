package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.EntityFactory.MyEntities;

public class ShotBig extends Shot {
	public static final int SHOTBIG_WIDTH = 3;
	public static final int SHOTBIG_HEIGHT = 3;

	public static final int SHOTBIG_HEALTH = 100;
	public static final int SHOTBIG_SPEED = 170;
	public static final int SHOTBIG_NUMBER_CASE_LIFE = 7;

	public static final long SHOTBIG_EXPLODE_TIME = 1000;
	public static final long SHOTBIG_MOVE_TIME = 150;
	public static final long SHOTBIG_POP_TIME = 50;
	public static final long SHOTBIG_WIZZ_TIME = 50;

	public ShotBig(int x, int y, Automaton aut) {
		super(x, y, SHOTBIG_WIDTH, SHOTBIG_HEIGHT, aut);
		m_health = SHOTBIG_HEALTH;
		m_speed = SHOTBIG_SPEED;
		m_nbCaseLife = SHOTBIG_NUMBER_CASE_LIFE;
	}
	
	@Override
	public void Explode() {
		if (m_actionFinished && m_currentAction == LsAction.Explode) {
			this.doExplode();
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Explode;
			m_timeOfAction = SHOTBIG_EXPLODE_TIME;
		}
	}

	public void Pop(MyDirection dir) {//se divise en 3
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			Entity ent = EntityFactory.newEntity(MyEntities.ShotBig, this.m_x, m_y);
			Entity ent2 = EntityFactory.newEntity(MyEntities.ShotBig, this.m_x, m_y);

			// Donne la direction de regard et d'action
			ent.setLookDir(MyDirection.toAbsolute(m_currentLookAtDir, MyDirection.LEFT));
			ent.setActionDir(MyDirection.toAbsolute(m_currentActionDir, MyDirection.LEFT));

			// Donne la direction de regard et d'action
			ent2.setLookDir(MyDirection.toAbsolute(m_currentLookAtDir, MyDirection.RIGHT));
			ent2.setActionDir(MyDirection.toAbsolute(m_currentActionDir, MyDirection.RIGHT));

			// Donne l'entité qui l'a tiré
			((Shot) ent).setOwner(this.getOwner());
			((Shot) ent2).setOwner(this.getOwner());
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Pop;
			m_timeOfAction = SHOTBIG_POP_TIME;
		}
	}
	
	public void Wizz(MyDirection dir) {//Vitesse augmente
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
			m_speed /=2;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = SHOTBIG_WIZZ_TIME;
		}
	}

}
