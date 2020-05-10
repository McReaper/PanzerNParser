package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

public abstract class MovingEntity extends Entity {

	public static final int DEFAULT_SPEED = 1000;
	public double progressMoveAtDie = -1;
	public MyDirection directionMoveAtDie;

	public MovingEntity(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
		m_speed = DEFAULT_SPEED;
	}

	@Override
	public void collide(int damage) {
		super.collide(damage);
		if (m_health <= 0) {
			if (m_currentAction == LsAction.Move && progressMoveAtDie == -1) {
				progressMoveAtDie = this.getActionProgress();
				directionMoveAtDie = MyDirection.toAbsolute(m_currentLookAtDir, m_currentActionDir);
			}
			m_elapseTime = m_timeOfAction + 1;
		}
	}

}
