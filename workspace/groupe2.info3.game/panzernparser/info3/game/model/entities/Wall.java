package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.MaterialType;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.EntityFactory.MyEntities;

public class Wall extends StaticEntity{

	public final static int WALL_WIDTH = 1;
	public final static int WALL_HEIGHT = 1;

	public static final int WALL_HEALTH = 100;
	public static final int WALL_SPEED = 100;

	public static final long WALL_EGG_TIME = 1000;
	public static final long WALL_GET_TIME = 1000;
	public static final long WALL_HIT_TIME = 1000;
	public static final long WALL_JUMP_TIME = 1000;
	public static final long WALL_EXPLODE_TIME = 1000;
	public static final long WALL_MOVE_TIME = 2000;
	public static final long WALL_PICK_TIME = 1000;
	public static final long WALL_POP_TIME = 10000;
	public static final long WALL_POWER_TIME = 1000;
	public static final long WALL_PROTECT_TIME = 1000;
	public static final long WALL_STORE_TIME = 1000;
	public static final long WALL_TURN_TIME = 1000;
	public static final long WALL_THROW_TIME = 1000;
	public static final long WALL_WAIT_TIME = 4000;
	public static final long WALL_WIZZ_TIME = 1000;
	
	
	public Wall(int x, int y, Automaton aut) {
		super(x, y, WALL_WIDTH, WALL_HEIGHT, aut);
		this.setCategory(MyCategory.O);
	}

	@Override
	public void Wait() {
		if (m_actionFinished && m_currentAction == LsAction.Wait) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			//m_currentActionDir = null;
			m_currentAction = LsAction.Wait;
			m_timeOfAction = WALL_WAIT_TIME;
		}
	}
	
	@Override
	public void collide(int damage) {}
	
	@Override
	public boolean isShown() {
		return Model.getModel().getVisionType() == VisionType.TANK;
	}
	
	@Override
	public void Wizz(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
			Droppable a = (Droppable) EntityFactory.newEntity(MyEntities.Droppable, m_x+1, m_y);
			a.setMaterialType(MaterialType.ELECTRONIC);
			a.setQuantity(1);
		} else if (m_currentAction == null) {
			//m_currentActionDir = null;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = 0;
		}
	}
	
	@Override
	public void Pop(MyDirection dir) {
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			Droppable a = (Droppable) EntityFactory.newEntity(MyEntities.Droppable, m_x+1, m_y);
			a.setMaterialType(MaterialType.MINERAL);
			a.setQuantity(1);
		} else if (m_currentAction == null) {
			//m_currentActionDir = null;
			m_currentAction = LsAction.Pop;
			m_timeOfAction = 0;
		}
	}
	
}
