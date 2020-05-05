package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.MaterialType;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;

public class Droppable extends StaticEntity {
	public final static int DROPPABLE_WIDTH = 1;
	public final static int DROPPABLE_HEIGHT = 1;

	public static final long DROPPABLE_POP_TIME = 10000;
	public static final long DROPPABLE_WIZZ_TIME = 1000;
	
	public static final int DROPPABLE_QUANTITY  =1;

	int m_quantity; // quantité de matériaux lachés
	private MaterialType m_mType; // Type de matériel laché

	public Droppable(int x, int y, MaterialType mtype, Automaton aut) {
		super(x, y, DROPPABLE_WIDTH, DROPPABLE_HEIGHT,  aut);
		m_quantity = DROPPABLE_QUANTITY;
		m_mType = mtype;
		m_category = MyCategory.P;
	}

	@Override
	public void Wizz(MyDirection dir) {//se déplace dans la direction indiqué
		if (m_actionFinished && m_currentAction == LsAction.Wizz) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = DROPPABLE_WIZZ_TIME;
			Model.getModel().getGrid().moved(this, dir);
		}
	}
	

	@Override
	public void Pop(MyDirection dir) {//se déplace dans la direction opposée
		if (m_actionFinished && m_currentAction == LsAction.Pop) {
			m_actionFinished = false;
			m_currentAction = null;
		} else if (m_currentAction == null) {
			m_currentActionDir = dir;
			m_currentAction = LsAction.Wizz;
			m_timeOfAction = DROPPABLE_POP_TIME;
			dir = MyDirection.toAbsolute(m_currentActionDir, dir);
			Model.getModel().getGrid().moved(this, MyDirection.toAbsolute(dir, MyDirection.BACK));
		}
	}
	
	@Override
	public boolean isShown() {
		return (Model.getModel().getVisionType() != VisionType.ENEMIES);
	}
	
	public MaterialType getMType() {
		return m_mType;
	}
	
	public void setMaterialType(MaterialType type) {
		m_mType = type;
	}

	public int getQuantity() {
		return m_quantity;
	}
	
	public void setQuantity(int quantity) {
		m_quantity = quantity;
	}

}
