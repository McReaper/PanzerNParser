package info3.game.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	/* entier pour le nombre de zone à charger dans la grille */
	final static int NB_AREAS = 2;
	/* entier pour le nombre de pattern dans le dossier pattern */
	final static int NB_PATTERNS = 3;

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
		int Max = NB_PATTERNS - 1;
		int Min = 0;
		int patterns_chose = 0;
		int rand = 0;
		List<Pattern> selectedPatterns = new LinkedList<Pattern>();
		while (patterns_chose != NB_AREAS) {
			rand = (int) (Math.random() * (Max - Min));
			if (selectedPatterns != null) {
				Pattern tmp = m_patterns.get(rand);
				if (!selectedPatterns.contains(tmp)) {
					selectedPatterns.add(tmp);
					patterns_chose++;
				}
			}
		}
		sendToModel(selectedPatterns);
	}

	public void sendToModel(List<Pattern> patterns) {
		for (Pattern pattern : patterns) {
			List<Entity> entities = pattern.getEntities();
			for (Entity entity : entities) {
				// TODO Model -> Add Entity
			}
		}
	}

	public void load() {
		String name = "pattern" + Pattern.SIZE + "x" + Pattern.SIZE + "_";
		File f;
		Pattern p;
		int i = 0;
		try {
			while (true) {
				f = new File("patterns/" + name + i + ".txt");
				p = new Pattern();
				p.parse(f);
				m_patterns.add(p);
			}
		} catch (Exception e) {

		}
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

		public static final int SIZE = 3;
		int m_px, m_py;
		List<EntityShade> m_entities;

		Pattern() {
			m_px = 0;
			m_py = 0;
			m_entities = new LinkedList();
		}

		List<Entity> getEntities() {
			ListIterator<EntityShade> iter = m_entities.listIterator();
			EntityShade current;
			List<Entity> realEntities = new LinkedList<Entity>();
			while (iter.hasNext()) {
				current = (EntityShade) iter.next();
				int global_x = current.m_ex + m_px + SIZE;
				int global_y = current.m_ey + m_py + SIZE;
				switch (current.m_type) {
					case WALL:
						// realEntities.add(new Ground(global_x, global_y, width, height));
						break;
					case GROUND:
						// realEntities.add(new Ground(global_x, global_y, width, height));
						break;
					case ENEMY:
						// realEntities.add(new Enemy(global_x, global_y, width, height));
						break;
					case DROPPABLE:
						// realEntities.add(new Droppable(global_x, global_y, width, height,
						// quantity, mtype));
						break;
					case VEIN:
						// realEntities.add(new Vein(global_x, global_y, width, height));
						break;

				}

			}
			return realEntities;
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
			line = null;
			boolean sentinelle = true;
			while (sentinelle) {
				try {
					line = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
					sentinelle = false;
				}
				if (line == null) {
					sentinelle = false;
				}
				name = line.substring(0, 5);
				int i = 6;
				while (line.charAt(i) != ',') {
					i++;
				}
				sx = line.substring(6, i);
				sy = line.substring(i + 1);
				MyEntities type = null;
				switch (name) {
					case "drop1":
						type = MyEntities.DROPPABLE;
						break;
					// TODO
				}
				int x = Integer.parseInt(sx);
				int y = Integer.parseInt(sy);
				if (x < SIZE && x >= 0 && y < SIZE && y >= 0) {
					EntityShade es = new EntityShade(x, y, type);
					m_entities.add(es);
				}
			}
		}

	}

}
