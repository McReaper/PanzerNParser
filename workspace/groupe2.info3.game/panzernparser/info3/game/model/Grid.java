package info3.game.model;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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
			int m_ex, m_ey;

			public EntityShade(int x, int y, MyEntities type) {
				m_ex = x;
				m_ey = y;
				m_type = type;
			}

		}

		private final int SIZE = 3;
		int m_px, m_py;
		List<EntityShade> m_entities;

		List<Entity> getEntities() {
			ListIterator<EntityShade> iter = m_entities.listIterator();
			EntityShade current;
			List<Entity> realEntities = new LinkedList<Entity>();
			while (iter.hasNext()) {
				current = (EntityShade) iter.next();
				switch (current.m_type) {
					case WALL:
						// créer une classe Obstacle ? se servir du Ground ?
						// TODO
						break;
					case GROUND:
						// realEntities.add(new Ground(current.m_ex, current.m_ey, width, height));
						break;
					case ENEMY:
						// realEntities.add(new Enemy(current.m_ex, current.m_ey, width, height));
						break;
					case DROPPABLE:
						// realEntities.add(new Droppable(current.m_ex, current.m_ey, width, height,
						// quantity, mtype));
						break;
					case VEIN:
						// realEntities.add(new Vein(current.m_ex, current.m_ey, width, height));
						break;

				}

			}
			return realEntities;
		}

		private void parse(FileInputStream is) {

		}

	}

}
