package info3.game.model;

import java.util.HashMap;

import info3.game.model.entities.Droppable;

/**
 * L'invetaire contient une liste de matériaux.
 */
public class Inventory {
	
	private static int MAX_RES = 10000;
	
	HashMap<MaterialType, Integer> m_materialList;
	
	public Inventory() {
		m_materialList = new HashMap<MaterialType, Integer>();
		for (MaterialType matType : MaterialType.values()) {
			m_materialList.put(matType, 0);
		}
	}
	
	public void add(Droppable newMat) {
		MaterialType type = newMat.getMType();
		Integer quantity = m_materialList.get(type);
		quantity += newMat.getQuantity();
		if(quantity > MAX_RES) quantity = MAX_RES;
		m_materialList.replace(type, quantity);
	}
	
	public void add(MaterialType type, int q) {
		Integer quantity = m_materialList.get(type);
		quantity += q;
		if(quantity > MAX_RES) quantity = MAX_RES;
		m_materialList.replace(type, quantity);
	}
	
	public void add(MaterialType type) {
		Integer quantity = m_materialList.get(type);
		quantity += 1;
		if(quantity > MAX_RES) quantity = MAX_RES;
		m_materialList.replace(type, quantity);
	}
	
	public boolean possesses(MaterialType type, int q) {
		Integer quantity = m_materialList.get(type);
		return quantity >= q;
	}
	
	public boolean possesses(MaterialType type) {
		Integer quantity = m_materialList.get(type);
		return quantity > 0;
	}
	
	public boolean used(MaterialType type, int q) {
		Integer quantity = m_materialList.get(type);
		quantity -= q;
		if (quantity < 0) {
			return false;
		}
		m_materialList.replace(type, quantity);
		return true;
	}
	
	public boolean used(MaterialType type1, int q1, MaterialType type2, int q2) {
		if (type1 == type2) {
			return used(type1, q1+q2);
		}
		Integer quantity1 = m_materialList.get(type1);
		quantity1 -= q1;
		if (quantity1 < 0) {
			return false;
		}
		Integer quantity2 = m_materialList.get(type2);
		quantity2 -= q2;
		if (quantity2 < 0) {
			return false;
		}
		m_materialList.replace(type1, quantity1);
		m_materialList.replace(type2, quantity2);
		return true;
	}
	
	public int getQuantity(MaterialType type) {
		return this.m_materialList.get(type);
	}
	
}
