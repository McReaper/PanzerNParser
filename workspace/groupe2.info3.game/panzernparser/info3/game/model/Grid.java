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
import info3.game.model.entities.EntityFactory;
import info3.game.model.entities.EntityFactory.MyEntities;

/**
 * Créer la grille de jeu avec les entités correspondantes en fonction d'un ou
 * plusieurs fichiers patterns.
 */
public class Grid {
	Model m_model;
	List<Pattern> m_patterns;

	/* entier pour le nombre de zone à charger dans la grille */
	final static int TAILLE_MAP = 2;

	public Grid(Model model) {
		// Constructeur (phase de tests) :
		m_model = model;
		load();
		generate();
	}

	public int getNbCellsX() {
		return TAILLE_MAP*Pattern.SIZE;
	}

	public int getNbCellsY() {
		return TAILLE_MAP*Pattern.SIZE;
	}

	public void generate() {
		int Max = m_patterns.size() - 1;
		int patterns_chose = 0;
		int rand = 0;
		List<Pattern> patternSelector = new LinkedList<Pattern>();
		patternSelector.addAll(m_patterns);
		List<Pattern> selectedPatterns = new LinkedList<Pattern>();
		if(Max >= TAILLE_MAP*TAILLE_MAP) {
			for(int i = 0; i < TAILLE_MAP; i++) {
				for (int j = 0; j < TAILLE_MAP; j++) {
					rand = (int) (Math.random() * (Max - patterns_chose));
					Pattern tmp = patternSelector.get(rand);
					tmp.setPosition(i, j);
					selectedPatterns.add(tmp);
					patternSelector.remove(tmp);
					patterns_chose++;
				}
			}
			sendToModel(selectedPatterns);
		} else {
			System.out.println("ERROR : Not Enough Patterns to continue");
		}
	}

	public void sendToModel(List<Pattern> patterns) {
		for (Pattern pattern : patterns) {
			List<Entity> entities = pattern.getEntities();
			for (Entity entity : entities) {
				System.out.println("Send " + EntityFactory.name(entity) + " : " + entity.getX() + ","+ entity.getY());
			}
		}
	}

	public void load() {
		String name = "pattern" + Pattern.SIZE + "x" + Pattern.SIZE + "_";
		File f;
		Pattern p;
		int i = 0;
		try {
			File repository = new File("patterns");
			String[] fileList = repository.list();
			for (int j = 0; j < fileList.length; j++) {
				String file = fileList[i];
				String subFile = file.substring(0, file.length()-5);
				if(subFile.equals(name)) {
					p = new Pattern();
					f = new File("patterns/"+file);
					p.parse(f);
					m_patterns.add(p);					
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR : Something went wrong.");
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
		List<EntityShade> m_entitieShades;

		public Pattern() {
			m_px = 0;
			m_py = 0;
			m_entitieShades = new LinkedList<EntityShade>();
		}
		
		public void setPosition(int x, int y) {
			m_px = x;
			m_py = y;
		}

		List<Entity> getEntities() {
			List<Entity> realEntities = new LinkedList<Entity>();
			for (EntityShade entityShade : m_entitieShades) {
				int global_x = entityShade.m_ex + m_px * SIZE;
				int global_y = entityShade.m_ey + m_py * SIZE;
				EntityFactory.newEntity(entityShade.m_type,global_x,global_y);				
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
					case "enem1":
						type = MyEntities.ENEMY;
						break;
					case "vein1":
						type = MyEntities.VEIN;
						break;
				}
				int x = Integer.parseInt(sx);
				int y = Integer.parseInt(sy);
				if (x < SIZE && x >= 0 && y < SIZE && y >= 0) {
					EntityShade es = new EntityShade(x, y, type);
					m_entitieShades.add(es);
				}
			}
		}

	}

}
