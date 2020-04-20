package info3.game.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
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
			int m_ex, m_ey;
		}

		private final int SIZE = 3;
		int m_px, m_py;
		List<EntityShade> m_entities;

		Pattern() {
			m_px = 0;
			m_py = 0;
			m_entities = new LinkedList();
		}

		List<Entity> getEntities() {
			return null;
		}

		private void parse(File file) {
			FileReader fr = null;
			try {
				fr = new FileReader(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr);
			String line, name, sx, sy;	
			boolean sentinelle = true;
			while (sentinelle) {
				try {
					line = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
					sentinelle = false;
				}
				if(line == null) {
					sentinelle = false;
				}
				name = line.substring(0, 5);
				int i = 6;
				while (line.charAt(i) != ',') {
					i++;
				}
				sx = line.substring(6, i);
				sy = line.substring(i + 1);
				MyEntities type;
				switch (name) {
					case "drop1":
						type = MyEntities.DROPPABLE;
						break;
					// TODO
				}
				int x = Integer.parseInt(sx);
				int y = Integer.parseInt(sy);
				if (x < SIZE && x >= 0 && y < SIZE && y >= 0) {
					EntityShade es = new EntityShade(type, x, y);
					m_entities.add(es);
				}
			}
		}

	}

}
