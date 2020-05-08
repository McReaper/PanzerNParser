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
	public static final int VEIN_DROP_QUANTITY_MIN = 5;
	public static final int VEIN_DROP_QUANTITY_MAX = 10;
	public static final double QUANTITY_FACTOR = 0.5;

	public static final long VEIN_EGG_TIME = 1000;
	public static final long VEIN_POP_TIME = 10000;
	public static final long VEIN_WIZZ_TIME = 1000;
	public static final long VEIN_WAIT_TIME = 0;

	int m_quantity;// quantité de ressource créée

	public Vein(int x, int y, Automaton aut) {
		super(x, y, VEIN_WIDTH, VEIN_HEIGHT, aut);
		m_category = MyCategory.G;
		m_stuff = false;
		int rand = (int) ((Math.random() * ((VEIN_DROP_QUANTITY_MAX - VEIN_DROP_QUANTITY_MIN) + 1)) + VEIN_DROP_QUANTITY_MIN);
		m_quantity = (int) (rand + (rand * QUANTITY_FACTOR * (Model.getModel().getLevel() - 1)));
	}

	@Override
	public boolean isShown() {
		return (Model.getModel().getVisionType() == VisionType.RESSOURCES);
	}

	@Override
	public void Egg(MyDirection dir) {
		// On ignore dir ici (tout le temps HERE)...
		if (m_actionFinished && m_currentAction == LsAction.Egg) {
			m_actionFinished = false;
			m_currentAction = null;
			// plus d'autre droppables a donner
			m_stuff = false;

			// autodestruction via automate
			m_health = 0;

		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Egg;
			m_timeOfAction = Model.getModel().getTank().getMiningTime();

			// creation de la ressource a répendre
			if (dir == null || dir == MyDirection.HERE) {
				Entity ent = EntityFactory.newEntity(MyEntities.Droppable, m_x, m_y);
				((Droppable) ent).setMaterialType(MaterialType.MINERAL);
				((Droppable) ent).setQuantity(m_quantity);
			} else {
				int posX = getXCaseDir(dir);
				int posY = getYCaseDir(dir);
				Entity ent = EntityFactory.newEntity(MyEntities.Droppable, posX, posY);
				((Droppable) ent).setMaterialType(MaterialType.MINERAL);
				((Droppable) ent).setQuantity(m_quantity);

			}
			Model.getModel().getScore().scoreVein();
		}
	}

	@Override
	public void Pop(MyDirection dir) {// devient plus large
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
			m_height *= 2;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Pop;
			m_timeOfAction = VEIN_POP_TIME;
		}
	}

	@Override
	public void Wizz(MyDirection dir) {// s'alloge (width augmente)
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
			m_width *= 2;
		} else if (m_currentAction == null) {
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = VEIN_WIZZ_TIME;
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
			m_timeOfAction = VEIN_WAIT_TIME;
		}
	}

	@Override
	public void step(long elapsed) {
		m_displayed = (Model.getModel().getVisionType() == VisionType.RESSOURCES);
		super.step(elapsed);
	}
}
