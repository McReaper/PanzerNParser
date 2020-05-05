package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.EntityFactory.MyEntities;

/**
 * Représente une unité immobile comme un trou, un arbre, un caillou, ...
 */
public class Ground extends StaticEntity {

	private static final int GROUND_WIDTH = 1;
	private static final int GROUND_HEIGHT = 1;
	
	public static final long GROUND_POP_TIME = 10000;
	public static final long GROUND_WIZZ_TIME = 1000;

	public Ground(int x, int y, Automaton aut) {
		super(x, y, GROUND_WIDTH, GROUND_HEIGHT, aut);
		m_category = MyCategory.V;
	}
	
	@Override
	public void collide(int dammage) {
			super.collide(dammage);
	}
	
	public void Pop(MyDirection dir) {//Lui donne la catégorie Enemy
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = GROUND_POP_TIME;
			m_category = MyCategory.A;
		}
	}
	
	public void Wizz(MyDirection dir) {//lui permet de tirer
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = GROUND_WIZZ_TIME;

			// creation du shot
			Entity ent = EntityFactory.newEntity(MyEntities.ShotSlow, this.m_x, m_y);

			// Donne la direction de regard et d'action
			ent.setLookDir(this.m_currentLookAtDir);
			ent.setActionDir(this.m_currentActionDir);

			// Donne l'entité qui l'a tiré
			((Shot) ent).setOwner(this);
		}
	}
	

}
