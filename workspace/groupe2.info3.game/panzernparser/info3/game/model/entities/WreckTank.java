package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.MaterialType;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.EntityFactory.MyEntities;

public class WreckTank extends StaticEntity {

	public final static int WRECKTANK_WIDTH = 3;
	public final static int WRECKTANK_HEIGHT = 3;

	public static final int WRECKTANK_HEALTH = 150;
	public static final int WRECKTANK_SPEED = 100;
	public static final int WRECKTANK_DROP_QUANTITY = 10;
	
	public static final long WRECKTANK_EGG_TIME = 1000;
	public static final long WRECKTANK_GET_TIME = 1000;
	public static final long WRECKTANK_HIT_TIME = 1000;
	public static final long WRECKTANK_JUMP_TIME = 1000;
	public static final long WRECKTANK_EXPLODE_TIME = 1000;
	public static final long WRECKTANK_MOVE_TIME = 2000;
	public static final long WRECKTANK_PICK_TIME = 1000;
	public static final long WRECKTANK_POP_TIME = 10000;
	public static final long WRECKTANK_POWER_TIME = 1000;
	public static final long WRECKTANK_PROTECT_TIME = 1000;
	public static final long WRECKTANK_STORE_TIME = 1000;
	public static final long WRECKTANK_TURN_TIME = 1000;
	public static final long WRECKTANK_THROW_TIME = 1000;
	public static final long WRECKTANK_WAIT_TIME = 1000;
	public static final long WRECKTANK_WIZZ_TIME = 1000;
	
	int m_quantity;
	/**
	 * En fonction de lookAtDir, la vue affichera différentes représentation
	 */
	public WreckTank(int x, int y, Automaton aut) {
		super(x, y, WRECKTANK_WIDTH, WRECKTANK_HEIGHT, aut);
		m_health = WRECKTANK_HEALTH;
		m_maxHealth = WRECKTANK_HEALTH;
		this.setCategory(MyCategory.O);
		m_currentLookAtDir = (Math.random() > 0.5) ? MyDirection.SOUTH : MyDirection.WEST;
		m_stuff = true; // Contient des ressources
		m_quantity = WRECKTANK_DROP_QUANTITY + (WRECKTANK_DROP_QUANTITY/2)* Model.getModel().getLevel();
	}

	@Override
	public boolean isShown() {
		// TODO update en fonction des sprites dispo
		return (Model.getModel().getVisionType() == VisionType.TANK);
	}

	@Override
	public void Egg(MyDirection dir) {
		// On ignore dir ici (tout le temps HERE)...
		if (m_actionFinished && m_currentAction == LsAction.Egg) {
			m_actionFinished = false;
			m_currentAction = null;
			// plus d'autre droppables a donner
			m_stuff = false;

		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Egg;
			m_timeOfAction = DEFAULT_EGG_TIME;

			// creation de la ressource a répendre
			Entity ent = EntityFactory.newEntity(MyEntities.Droppable, m_x + (m_width/2), m_y + (m_height/2));
			((Droppable) ent).setMaterialType(MaterialType.ELECTRONIC);
			
			((Droppable) ent).setQuantity(m_quantity);

			Model.getModel().getScore().scoreWreckTank();
		}
	}
	
	@Override
	public void Explode() {
		if (m_actionFinished && m_currentAction == LsAction.Explode) {
			this.setCategory(MyCategory.V);
			m_currentActionDir = null;
			switch (m_currentLookAtDir) {
				case SOUTH:
					m_currentLookAtDir = MyDirection.NORTH;
					break;
				case WEST:
					m_currentLookAtDir = MyDirection.EAST;
					break;
				default:
					break;
			}
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			if (isNoisy())
				Model.getModel().addSound("explosion");
			m_currentAction = LsAction.Explode;
			m_timeOfAction = WRECKTANK_EXPLODE_TIME;
		}
	}
	
	@Override
	public void Wait() {
		if (m_actionFinished && m_currentAction == LsAction.Wait) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = null;
			m_currentAction = LsAction.Wait;
			m_timeOfAction = WRECKTANK_WAIT_TIME;
		}
	}
	
}
