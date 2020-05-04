package info3.game.model.entities;

import java.util.LinkedList;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;

/**
 * Un marqueur est disposé par le drone lors d'un clic dans le canvas
 */
public class Marker extends StaticEntity {

	public final static int MARKER_WIDTH = 1;
	public final static int MARKER_HEIGHT = 1;

	public static final long MARKER_POP_TIME = 10000;
	public static final long MARKER_WIZZ_TIME = 1000;

	public Marker(int x, int y, Automaton aut) {
		super(x, y, MARKER_WIDTH, MARKER_HEIGHT, aut);
		m_category = MyCategory.C;
	}
	
	@Override
	public void Pop(MyDirection dir) {//Récupère un droppable si il y en a 
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			LinkedList<Entity> entities = Model.getModel().getGrid().getEntityCell(m_x, m_y);
			for (Entity entity : entities) {
				if (entity.getCategory() == MyCategory.P && !entity.equals(this)) {
					Model.getModel().getTank().getInventory().add((Droppable) entity);
					Model.getModel().removeEntity(entity);
					Model.getModel().getScore().scoreDroppable();
				}
			}
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = MARKER_POP_TIME;
		}
	}
	@Override
	public void Wizz(MyDirection dir) {//Grandi
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = MARKER_WIZZ_TIME;
			m_width *=2;
			m_height *=2;
		}
	}

}
