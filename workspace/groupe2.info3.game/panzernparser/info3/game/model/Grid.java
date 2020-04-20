package info3.game.model;

import java.io.FileInputStream;
import java.util.List;

import info3.game.model.entities.Enemy;
import info3.game.model.entities.Entity;
import info3.game.model.entities.MyEntities;

/**
 * Créer la grille de jeu avec les entités correspondantes en fonction d'un ou
 * plusieurs fichiers patterns.
 */
public class Grid {
	int m_nbCellsX;
	int m_nbCellsY;
	Model m_model;
	List<Pattern> m_patterns;

	public Grid(Model model) {
		// Constructeur (phase de tests) :
		m_nbCellsX = 6;
		m_nbCellsY = 3;
		m_model = model;
		m_model.m_entities.add(new Enemy(1, 1, 1, 1));
	}

	public int getNbCellsX() {
		return m_nbCellsX;
	}

	public int getNbCellsY() {
		return m_nbCellsY;
	}
	
	public void generate() {
		
	}
	
	public void sendToModel() {
		
	}

	private class Pattern {
		
		private class EntityShade {
			MyEntities m_type;
			int m_ex,m_ey;
		}
		
		private final int SIZE = 3;
		int m_px,m_py;
		List<EntityShade> m_entities;
		
		List<Entity> getEntities() {
			return null;
		}
		
		private void parse(FileInputStream is) {
			
		}
		
	}

}
