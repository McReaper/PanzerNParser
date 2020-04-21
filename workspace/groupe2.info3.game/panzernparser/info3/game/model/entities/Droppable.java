package info3.game.model.entities;

import info3.game.model.Material.MaterialType;

public class Droppable extends StaticEntity {
	/*Champs pour donner size par defaut dans la EntityFactory */
	final static int DROPPABLE_WIDTH = 1;
	final static int DROPPABLE_HEIGHT = 1;
	
	int m_quantity; // quantité de matériaux lachés
	MaterialType m_mType; // Type de matériel laché

	public Droppable(int x, int y, int width, int height, int quantity, MaterialType mtype) {
		super(x, y, width, height);
		m_quantity = quantity;
		m_mType = mtype;
	}

	@Override
	public void step(long elapsed) {
		// TODO Auto-generated method stub

	}
}
