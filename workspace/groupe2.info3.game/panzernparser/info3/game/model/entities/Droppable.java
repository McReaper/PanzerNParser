package info3.game.model.entities;

import info3.game.model.Material.MaterialType;
import info3.game.model.Model;

public class Droppable extends StaticEntity {

	int m_quantity; // quantité de matériaux lachés
	MaterialType m_mType; // Type de matériel laché

	public Droppable(int x, int y, int width, int height, int quantity, MaterialType mtype, Model model) {
		super(x, y, width, height, model);
		m_quantity = quantity;
		m_mType = mtype;
	}

	@Override
	public void step(long elapsed) {
		// TODO Auto-generated method stub

	}
}
