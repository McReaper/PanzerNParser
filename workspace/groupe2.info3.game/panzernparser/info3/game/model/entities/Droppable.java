package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.model.MaterialType;

public class Droppable extends StaticEntity {
	public final static int DROPPABLE_WIDTH = 1;
	public final static int DROPPABLE_HEIGHT = 1;

	public static final long DROPPABLE_EGG_TIME = 1000;
	public static final long DROPPABLE_GET_TIME = 1000;
	public static final long DROPPABLE_HIT_TIME = 1000;
	public static final long DROPPABLE_JUMP_TIME = 1000;
	public static final long DROPPABLE_EXPLODE_TIME = 1000;
	public static final long DROPPABLE_MOVE_TIME = 2000;
	public static final long DROPPABLE_PICK_TIME = 1000;
	public static final long DROPPABLE_POP_TIME = 10000;
	public static final long DROPPABLE_POWER_TIME = 1000;
	public static final long DROPPABLE_PROTECT_TIME = 1000;
	public static final long DROPPABLE_STORE_TIME = 1000;
	public static final long DROPPABLE_TURN_TIME = 1000;
	public static final long DROPPABLE_THROW_TIME = 1000;
	public static final long DROPPABLE_WAIT_TIME = 10000;
	public static final long DROPPABLE_WIZZ_TIME = 1000;

	int m_quantity; // quantité de matériaux lachés
	private MaterialType m_mType; // Type de matériel laché

	public Droppable(int x, int y, MaterialType mtype, Automaton aut) {
		super(x, y, DROPPABLE_WIDTH, DROPPABLE_HEIGHT,  aut);
		m_quantity = 1; //TODO : revoir
		m_mType = mtype;
		m_category = MyCategory.P;
	}

	public MaterialType getMType() {
		return m_mType;
	}

	public int getQuantity() {
		return m_quantity;
	}
	
	public void setQuantity(int quantity) {
		m_quantity = quantity;
	}

}
