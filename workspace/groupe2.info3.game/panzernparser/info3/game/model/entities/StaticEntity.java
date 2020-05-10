package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.action.LsAction;

/**
 * Une entité static est une entité qui à la base n'est pas sensé pouvoir se
 * mouvoir.
 */
public abstract class StaticEntity extends Entity {
	public static final long STATIC_WAIT_TIME = 2000;

	public StaticEntity(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
		m_speed = 1000;
	}
	
	@Override
	public void Wait() {
		if (m_actionFinished && m_currentAction == LsAction.Wait) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = null;
			m_currentAction = LsAction.Wait;
			m_timeOfAction = STATIC_WAIT_TIME;
		}
	}
}
