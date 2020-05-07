package info3.game.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.LinkedList;
import java.util.List;

import info3.game.GameConfiguration;
import info3.game.automaton.MyDirection;
import info3.game.model.entities.Enemy;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory;
import info3.game.model.entities.EntityFactory.MyEntities;

/**
 * Créer la grille de jeu avec les entités correspondantes en fonction d'un ou
 * plusieurs fichiers patterns.
 */
public class Grid {
	List<Pattern> m_patterns;
	Pattern patTank;
	LinkedList<Entity>[][] m_entityGrid;

	/* entier pour le nombre de zone à charger dans la grille */
	final static int TAILLE_MAP = 3;

	@SuppressWarnings("unchecked")
	public Grid() throws UnexpectedException {
		// Constructeur (phase de tests) :
		m_patterns = new LinkedList<Pattern>();
		load();

		// Création de la grille d'entité m_entityGrid[x][y]
		m_entityGrid = new LinkedList[getNbCellsX()][getNbCellsY()];
		for (int i = 0; i < m_entityGrid.length; i++) {
			for (int j = 0; j < m_entityGrid[0].length; j++) {
				m_entityGrid[i][j] = new LinkedList<Entity>();
			}
		}
	}

	public void addEntity(Entity entity) {
		int width = entity.getWidth();
		int height = entity.getHeight();
		int x = entity.getX();
		int y = entity.getY();
		for (int i = x; i < x + width; i++) {
			for (int j = y; j < y + height; j++) {
				m_entityGrid[realX(i)][realY(j)].add(entity);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void emptyGrid() {
		m_entityGrid = new LinkedList[getNbCellsX()][getNbCellsY()];
		for (int i = 0; i < m_entityGrid.length; i++) {
			for (int j = 0; j < m_entityGrid[0].length; j++) {
				m_entityGrid[i][j] = new LinkedList<Entity>();
			}
		}
	}

	public void removeEntity(Entity entity) {
		int width = entity.getWidth();
		int height = entity.getHeight();
		int x = entity.getX();
		int y = entity.getY();
		for (int i = x; i < x + width; i++) {
			for (int j = y; j < y + height; j++) {
				m_entityGrid[realX(i)][realY(j)].remove(entity);
			}
		}
	}

	/**
	 * Cette fonction sert de callback lorsque l'entité se d'une seule case dans une
	 * direction
	 * 
	 * @param entity L'entité qui se déplace
	 * @param dir    La direction de son déplacement
	 */

	public void moved(Entity entity, MyDirection dir) {
		int width = entity.getWidth();
		int height = entity.getHeight();
		int x = entity.getX();
		int y = entity.getY();
		switch (dir) {
			case NORTH:
				for (int i = x; i < x + width; i++) {
					m_entityGrid[realX(i)][realY(y - 1)].add(entity);
					m_entityGrid[realX(i)][realY(y + height - 1)].remove(entity);
				}
				break;
			case SOUTH:
				for (int i = x; i < x + width; i++) {
					m_entityGrid[realX(i)][realY(y + height)].add(entity);
					m_entityGrid[realX(i)][realY(y)].remove(entity);
				}
				break;
			case EAST:
				for (int i = y; i < y + height; i++) {
					m_entityGrid[realX(x + width)][realY(i)].add(entity);
					m_entityGrid[realX(x)][realY(i)].remove(entity);
				}
				break;
			case WEST:
				for (int i = y; i < y + height; i++) {
					m_entityGrid[realX(x - 1)][realY(i)].add(entity);
					m_entityGrid[realX(x + width - 1)][realY(i)].remove(entity);
				}
				break;
			case NORTHWEST:
				for (int i = x; i < x + width - 1; i++) {
					m_entityGrid[realX(i)][realY(y - 1)].add(entity);
					m_entityGrid[realX(i)][realY(y + height - 1)].remove(entity);
				}
				for (int i = y; i < y + height - 1; i++) {
					m_entityGrid[realX(x - 1)][realY(i)].add(entity);
					m_entityGrid[realX(x + width - 1)][realY(i)].remove(entity);
				}
				m_entityGrid[realX(x - 1)][realY(y - 1)].add(entity);
				m_entityGrid[realX(x + width - 1)][realY(y + height - 1)].remove(entity);
				break;
			case NORTHEAST:
				for (int i = x + 1; i < x + width; i++) {
					m_entityGrid[realX(i)][realY(y - 1)].add(entity);
					m_entityGrid[realX(i)][realY(y + height - 1)].remove(entity);
				}
				for (int i = y; i < y + height - 1; i++) {
					m_entityGrid[realX(x + width)][realY(i)].add(entity);
					m_entityGrid[realX(x)][realY(i)].remove(entity);
				}
				m_entityGrid[realX(x + width)][realY(y - 1)].add(entity);
				m_entityGrid[realX(x)][realY(y + height - 1)].remove(entity);
				break;
			case SOUTHWEST:
				for (int i = x; i < x + width - 1; i++) {
					m_entityGrid[realX(i)][realY(y + width)].add(entity);
					m_entityGrid[realX(i)][realY(y)].remove(entity);
				}
				for (int i = y + 1; i < y + height; i++) {
					m_entityGrid[realX(x - 1)][realY(i)].add(entity);
					m_entityGrid[realX(x + width - 1)][realY(i)].remove(entity);
				}
				m_entityGrid[realX(x - 1)][realY(y + height)].add(entity);
				m_entityGrid[realX(x + width - 1)][realY(y)].remove(entity);
				break;
			case SOUTHEAST:
				for (int i = x + 1; i < x + width; i++) {
					m_entityGrid[realX(i)][realY(y + height)].add(entity);
					m_entityGrid[realX(i)][realY(y)].remove(entity);
				}
				for (int i = y + 1; i < y + height; i++) {
					m_entityGrid[realX(x + width)][realY(i)].add(entity);
					m_entityGrid[realX(x)][realY(i)].remove(entity);
				}
				m_entityGrid[realX(x + width)][realY(y + height)].add(entity);
				m_entityGrid[realX(x)][realY(y)].remove(entity);
				break;
			default:
				System.err.println("Le callBack a été appelé avec une mauvaise directions");
		}
	}

	public double distanceXAtPow2(int a, int b) {
		double baicDst = Math.pow(a - b, 2);
		double toreDst = Math.min(a, b);
		toreDst += this.getNbCellsX() - Math.max(a, b);
		toreDst = Math.pow(toreDst, 2);
		return Math.min(baicDst, toreDst);
	}

	public double distanceYAtPow2(int a, int b) {
		double baicDst = Math.pow(a - b, 2);
		double toreDst = Math.min(a, b);
		toreDst += this.getNbCellsY() - Math.max(a, b);
		toreDst = Math.pow(toreDst, 2);
		return Math.min(baicDst, toreDst);
	}

	/**
	 * Cette fonction sert de callback lorsque l'entité se déplace de plus d'une
	 * case
	 * 
	 * @param entity L'entité qui se téléporte
	 * @param fromX  La coordonnée X de départ
	 * @param fromY  La coordonnée Y de départ
	 * @param toX    La coordonnée X de d'arrivée
	 * @param toY    La coordonnée Y de d'arrivée
	 */

	public void teleported(Entity entity, int fromX, int fromY, int toX, int toY) {
		int width = entity.getWidth();
		int height = entity.getHeight();
		for (int i = fromX; i < fromX + width; i++) {
			for (int j = fromY; j < fromY + height; j++) {
				m_entityGrid[realX(i)][realY(j)].remove(entity);
			}
		}
		for (int i = toX; i < toX + width; i++) {
			for (int j = toY; j < toY + height; j++) {
				m_entityGrid[realX(i)][realY(j)].add(entity);
			}
		}
	}

	public LinkedList<Entity> getEntityCell(int x, int y) {
		int rx = realX(x);
		int ry = realY(y);
		return new LinkedList<Entity>(m_entityGrid[rx][ry]);
	}

	public LinkedList<Entity> getEntityCells(int x, int y, int w, int h) {
		LinkedList<Entity> entity = new LinkedList<Entity>();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				entity.addAll(getEntityCell(x + i, y + j));
			}
		}
		return entity;
	}

	public LinkedList<Entity> getEntityCell(int x, int y, MyEntities type) {
		LinkedList<Entity> lsCell = getEntityCell(x, y);
		if (type == null) {
			return lsCell;
		}
		LinkedList<Entity> lsType = new LinkedList<Entity>();
		for (Entity e : lsCell) {
			if (EntityFactory.getMyEntities(e) == type) {
				lsType.add(e);
			}
		}
		return lsType;
	}

	public LinkedList<Entity> getEntityCells(int x, int y, int w, int h, MyEntities type) {
		LinkedList<Entity> lsCell = getEntityCells(x, y, w, h);
		if (type == null) {
			return lsCell;
		}
		LinkedList<Entity> lsType = new LinkedList<Entity>();
		for (Entity e : lsCell) {
			if (EntityFactory.getMyEntities(e) == type) {
				lsType.add(e);
			}
		}
		return lsType;
	}

	/**
	 * /!\ Cette fonction est pour le debug UNIQUEMENT, vérifier bien de ne la
	 * laisser nullpart dans des versions finies. (pour la trouver ctrl+shift+G)
	 */
	public void TESTPRINT() {
		System.out.println();
		System.out.println("###############################################");
		for (int i = 0; i < m_entityGrid[0].length; i++) {
			System.out.println();
			for (int j = 0; j < m_entityGrid.length; j++) {
				System.out.print(m_entityGrid[j][i].size());
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
							System.err.println("pattern avec parser nul");
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
			patTank = selectedPatterns.get(0);
			sendToModel(selectedPatterns);
		} else {
			throw new UnexpectedException("Not Enough Patterns to continue");
		}
	}

	public void sendToModel(List<Pattern> patterns) {
		for (Pattern pattern : patterns) {
			pattern.buildEntities();
		}
	}

	public void load() {
		int lvl = Model.getModel().getLevel();
		String name = "pattern" + Pattern.SIZE + "x" + Pattern.SIZE + "_";
		String namePatTank = "patTank" + Pattern.SIZE + "x" + Pattern.SIZE + "_";
		File f;
		Pattern p;
		try {
			File repository = new File(GameConfiguration.PATTERN_PATH);
			String[] fileList = repository.list();
			for (int j = 0; j < fileList.length; j++) {
				// Sélection du niveau de pattern :
				int random_level;
				if (lvl <= Pattern.PATTERN_MIN_LEVEL) {
					random_level = (Math.random() <= 0.8) ? lvl : lvl + 1;
				} else if (lvl >= Pattern.PATTERN_MAX_LEVEL) {
					random_level = (Math.random() <= 0.8) ? lvl : lvl - 1;
				} else {
					random_level = (int) ((Math.random() <= 0.7) ? lvl : ((lvl - 1) + (Math.random() * (lvl + 1))));
				}
				String lvlName = name + random_level;
				String lvlPatTank = namePatTank + random_level;
				String file = fileList[j];
				file = file.replaceAll("_\\d_", "_"+random_level+"_");
				String subFile = file.substring(0, file.length() - 7);
				if (subFile.equals(lvlName)) {
					p = new Pattern();
					String path = GameConfiguration.PATTERN_PATH + file;
					f = new File(path);
					p.parse(f);
					m_patterns.add(p);
				} else if (subFile.equals(lvlPatTank)) {
					p = new Pattern();
					String path = GameConfiguration.PATTERN_PATH + file.replaceAll("_\\d_", "_"+lvl+"_");
					f = new File(path);
					p.parse(f);
					patTank = p;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR : Something went wrong.");
		}
	}

	private class Pattern {

		public static final int SIZE = 20;
		public static final int PATTERN_MIN_LEVEL = 1;
		public static final int PATTERN_MAX_LEVEL = 3;

		private class EntityShade {

			MyEntities m_type;
			int m_ex, m_ey;

			public EntityShade(int x, int y, MyEntities type) {
				m_ex = x;
				m_ey = y;
				m_type = type;
			}

		}

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

		void buildEntities() {
			for (EntityShade entityShade : m_entitieShades) {
				int global_x = entityShade.m_ex + m_px * SIZE;
				int global_y = entityShade.m_ey + m_py * SIZE;
				EntityFactory.newEntity(entityShade.m_type, global_x, global_y);
			}
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
						type = MyEntities.EnemyBasic;
						break;
					case "enem2":
						type = MyEntities.EnemyLevel2;
						break;
					case "vein1":
						type = MyEntities.Vein;
						break;
					case "chas1":
						type = MyEntities.TankBody;
						break;
					case "wall1":
						type = MyEntities.Wall;
						break;
					case "rock1":
						type = MyEntities.Rock;
						break;
					case "wrck1":
						type = MyEntities.WreckTank;
						break;
					case "mud_1":
						type = MyEntities.Mud;
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
}
