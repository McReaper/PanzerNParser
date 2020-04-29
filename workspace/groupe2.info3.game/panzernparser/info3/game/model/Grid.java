package info3.game.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.LinkedList;
import java.util.List;

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
	Pattern patTank;

	/* entier pour le nombre de zone à charger dans la grille */
	final static int TAILLE_MAP = 2;

	public Grid(Model model) throws UnexpectedException {
		// Constructeur (phase de tests) :
		m_patterns = new LinkedList<Pattern>();
		m_model = model;
		load();
		generate();
	}
	
	public int realX(int x) {
		x = x % getNbCellsX();
		if (x < 0) {
			x += getNbCellsX();
		}
		return x;
	}
	
	public int realY(int y) {
		y = y % getNbCellsY();
		if (y < 0) {
			y += getNbCellsY();
		}
		return y;
	}

	public int getNbCellsX() {
		return TAILLE_MAP * Pattern.SIZE;
	}

	public int getNbCellsY() {
		return TAILLE_MAP * Pattern.SIZE;
	}

	public void generate() throws UnexpectedException {
		int Max = m_patterns.size() - 1;
		int patterns_chose = 0;
		int rand = 0;
		List<Pattern> patternSelector = new LinkedList<Pattern>();
		patternSelector.addAll(m_patterns);
		List<Pattern> selectedPatterns = new LinkedList<Pattern>();
		if (Max + 1 >= TAILLE_MAP * TAILLE_MAP) {
			for (int i = 0; i < TAILLE_MAP; i++) {
				for (int j = 0; j < TAILLE_MAP; j++) {
					if (i == 0 && j == 0) {
						if (patTank != null) {
							patTank.setPosition(i, j);
							selectedPatterns.add(patTank);
							patterns_chose++;
							patTank = null;
						} else {
							System.out.println("pattern avec parser nul");
						}

					} else {
						rand = (int) (Math.random() * (Max - patterns_chose));
						Pattern tmp = patternSelector.get(rand);
						tmp.setPosition(i, j);
						selectedPatterns.add(tmp);
						patternSelector.remove(tmp);
						patterns_chose++;
					}

				}
			}
			sendToModel(selectedPatterns);
		} else {
			throw new UnexpectedException("Not Enough Patterns to continue");
		}
	}

	public void sendToModel(List<Pattern> patterns) {
		for (Pattern pattern : patterns) {
			List<Entity> entities = pattern.getEntities();
			for (Entity entity : entities) {
				m_model.addEntity(entity);
				// System.out.println("Send " + EntityFactory.name(entity) + " : " +
				// entity.getX() + "," + entity.getY());
			}
		}
	}

	public void load() {
		String name = "pattern" + Pattern.SIZE + "x" + Pattern.SIZE + "_";
		String namePatTank = "patTank" + Pattern.SIZE + "x" + Pattern.SIZE + "_";
		File f;
		Pattern p;
		try {
			File repository = new File("patterns");
			String[] fileList = repository.list();
			for (int j = 0; j < fileList.length; j++) {
				String file = fileList[j];
				String subFile = file.substring(0, file.length() - 5);
				if (subFile.equals(name)) {
					p = new Pattern();
					String path = "patterns/" + file;
					f = new File(path);
					p.parse(f);
					m_patterns.add(p);
				} else if (subFile.equals(namePatTank)) {
					p = new Pattern();
					String path = "patterns/" + file;
					f = new File(path);
					p.parse(f);
					patTank = p;
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR : Something went wrong.");
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

		public static final int SIZE = 20;
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
				Entity ent = EntityFactory.newEntity(entityShade.m_type, global_x, global_y);
				realEntities.add(ent);
			}
			return realEntities;
		}

		public void parse(File file) throws IOException {
			FileReader fr = null;
			try {
				fr = new FileReader(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BufferedReader br = new BufferedReader(fr);
			String line, name, sx, sy;
			line = null;
			line = br.readLine();
			while (line != null && line.length() > 0) {
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
						type = MyEntities.Droppable;
						break;
					case "enem1":
						type = MyEntities.Enemy;
						break;
					case "vein1":
						type = MyEntities.Vein;
						break;
					case "chas1":
						type = MyEntities.TankBody;
						break;

					case "turr1":
						type = MyEntities.Turret;
						break;

					case "dron1":
						type = MyEntities.Drone;
						break;
				}
				int x = Integer.parseInt(sx);
				int y = Integer.parseInt(sy);
				if (x < SIZE && x >= 0 && y < SIZE && y >= 0) {
					if (type != null) {

						EntityShade es = new EntityShade(x, y, type);
						m_entitieShades.add(es);
					}
				}
				line = br.readLine();
			}
		}
	}

	public static class Coords {

		public double X, Y;

		public Coords(double x, double y) {
			X = x;
			Y = y;
		}

		@Override
		public boolean equals(Object obj) {
			return (((Coords) obj).X == this.X && ((Coords) obj).Y == this.Y);
		}

		public void translate(double offX, double offY) {
			X += offX;
			Y += offY;
		}
	}

}
