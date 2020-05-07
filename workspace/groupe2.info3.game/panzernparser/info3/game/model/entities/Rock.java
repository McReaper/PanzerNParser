package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;

public class Rock extends StaticEntity{

	public final static int ROCK_WIDTH = 2;
	public final static int ROCK_HEIGHT = 2;

	public static final long ROCK_WAIT_TIME = 4000;
	
	public Rock(int x, int y, Automaton aut) {
		super(x, y, ROCK_WIDTH, ROCK_HEIGHT, aut);
		this.setCategory(MyCategory.O);
	}

	@Override
	public void Wait() {
		if (m_actionFinished && m_currentAction == LsAction.Wait) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = null;
			m_currentAction = LsAction.Wait;
			m_timeOfAction = ROCK_WAIT_TIME;
		}
	}
	
	@Override
	public void collide(int damage) {}
	
	@Override
	public boolean isShown() {
		return Model.getModel().getVisionType() == VisionType.TANK;
	}
	
}
