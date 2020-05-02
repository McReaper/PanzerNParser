package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.model.MaterialType;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

public class Vein extends StaticEntity {

	public final static int VEIN_WIDTH = 1;
	public final static int VEIN_HEIGHT = 1;

	public static final int VEIN_HEALTH = 100;
	public static final int VEIN_SPEED = 100;

	public static final long VEIN_EGG_TIME = 1000;
	public static final long VEIN_GET_TIME = 1000;
	public static final long VEIN_HIT_TIME = 1000;
	public static final long VEIN_JUMP_TIME = 1000;
	public static final long VEIN_EXPLODE_TIME = 1000;
	public static final long VEIN_MOVE_TIME = 200;
	public static final long VEIN_PICK_TIME = 1000;
	public static final long VEIN_POP_TIME = 10000;
	public static final long VEIN_POWER_TIME = 1000;
	public static final long VEIN_PROTECT_TIME = 1000;
	public static final long VEIN_STORE_TIME = 1000;
	public static final long VEIN_TURN_TIME = 1000;
	public static final long VEIN_THROW_TIME = 1000;
	public static final long VEIN_WAIT_TIME = 5000;
	public static final long VEIN_WIZZ_TIME = 1000;

	public Vein(int x, int y, Automaton aut) {
		super(x, y, VEIN_WIDTH, VEIN_HEIGHT, aut);
		m_category = MyCategory.G;
		m_stuff = false;

	}

	@Override
	public void Egg(MyDirection dir) {
		// On ignore dir ici (tout le temps HERE)...
		if (m_actionFinished && m_currentAction == LsAction.Egg) {
			m_actionFinished = false;
			m_currentAction = null;
			//plus d'autre droppables a donner
			m_stuff = false;
			
			//autodestruction via automate
			m_health = 0;

		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Egg;
			m_timeOfAction = DEFAULT_EGG_TIME;

			// creation de la ressource a répendre
			if (dir == null || dir == MyDirection.HERE) {
				Entity ent = EntityFactory.newEntity(MyEntities.Droppable, m_x, m_y);
				((Droppable) ent).setMaterialType(MaterialType.MINERAL);
				((Droppable) ent).setQuantity(10);//TODO A mettre dans une variable
			} else {
				int posX = getXCaseDir(dir);
				int posY = getYCaseDir(dir);
				Entity ent = EntityFactory.newEntity(MyEntities.Droppable, posX, posY);
				((Droppable) ent).setMaterialType(MaterialType.MINERAL);
				((Droppable) ent).setQuantity(10);// A mettre dans une variable
				
			}
		}
	}

	@Override
	public void step(long elapsed) {
		m_displayed = (Model.getModel().getVisionType() == VisionType.RESSOURCES);
		super.step(elapsed);
	}
}
