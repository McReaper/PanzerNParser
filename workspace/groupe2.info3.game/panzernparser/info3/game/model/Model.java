package info3.game.model;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.LinkedList;

import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.model.Grid.Coords;
import info3.game.model.entities.Drone;
import info3.game.model.entities.Droppable;
import info3.game.model.entities.Enemy;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.Ground;
import info3.game.model.entities.Marker;
import info3.game.model.entities.Shot;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;
import info3.game.model.entities.Vein;

public class Model {

	public enum VisionType {
		TANK, RESSOURCES, ENEMIES;
	}

	private static Model self; // Singleton du model
	// Controller m_controller; //pour envoyer des information utiles.
	private Grid m_grid;
	private Tank m_tank;
	private Drone m_drone;
	private HashMap<EntityFactory.MyEntities, LinkedList<Entity>> m_entities;
	private LinkedList<LsKey> m_keyPressed;
	private CollisionManager m_collisionManager;
	private boolean m_playingTank;
	private int m_nbEntities;
	private Coords m_clue;
	public LinkedList<String> m_sounds;

	/**
	 * Création du modèle (l'univers du jeu)
	 */
	private Model() {
		// Création de la liste des touches enfoncées.
		m_keyPressed = new LinkedList<LsKey>();
		// Création de la liste des entités :
		m_entities = new HashMap<EntityFactory.MyEntities, LinkedList<Entity>>();
		for (MyEntities entityType : MyEntities.values()) {
			m_entities.put(entityType, new LinkedList<Entity>());
		}
		m_nbEntities = 0;
		
		//Creation de la classe CollisionEntity
		m_collisionManager = new CollisionManager();

		// Génère la grille du jeu qui va créer a son tour toutes les entités et mettre
		// la liste des entités à jour. La grille doit connaitre ses patterns lors de sa
		// création, le model doit donc lui donner.
		try {
			m_grid = new Grid(this);
		} catch (UnexpectedException e) {
			e.printStackTrace();
			System.err.println("Impossible de créer la grille !");
			// La il faudrait sortir du programme, en appelant le controller, pour arrêter
			// la musique et les autres exécutions auxiliaires en cours.
		}

		if (getEntities(MyEntities.TankBody).size() != 1 || getEntities(MyEntities.Turret).size() != 1
				|| getEntities(MyEntities.Drone).size() != 1) {
			System.err.println("Il semblerait que la grille comporte plusieurs Drone ou Tank...");
			// La il faudrait sortir du programme, en appelant le controller, pour arrêter
			// la musique et les autres exécutions auxiliaires en cours.
		}

		TankBody body = (TankBody) getEntities(MyEntities.TankBody).get(0);
		Turret turret = (Turret) getEntities(MyEntities.Turret).get(0);
		m_tank = new Tank(body, turret);
		m_drone = (Drone) getEntities(MyEntities.Drone).get(0);
		m_playingTank = true;
		m_sounds = new LinkedList<String>();

	}

	public void update(Marker marker) {
	getEntities( MyEntities.Marker).add(marker);
	getEntities( MyEntities.Marker).remove(0);
	}
	
	public void addClue(Coords c) {
		if (c != null)
			m_clue = c;
	}

	public Coords getClue() {
		return m_clue;
	}

	public void cleanClue() {
		m_clue = null;
	}

	/**
	 * Fonction qui gère le singleton du modèle (évite de créer plusieurs modèles).
	 * 
	 * @return le modèle
	 */
	public static Model getModel() {
		if (self == null)
			self = new Model();
		return self;
	}

	public void step(long elapsed) {
		// Effectue un pas de simulation sur chaque entités
		for (Entity entity : getAllEntities()) {
			entity.step(elapsed);
		}
		m_tank.step();
		m_collisionManager.controlCollisionsShotsEntity();
	}

	//////// Gestion du passage drone/tank ////////

	public boolean isPlayingTank() {
		return m_playingTank;
	}

	public void switchControl() {
		m_playingTank = !m_playingTank;
		if (m_playingTank) {
//			m_tank.showTank(true);
//			m_drone.showEntity(false);
			System.out.println("Contrôles données au TANK");
		} else {
			// Le drone apparait au niveau du tank
			m_drone.setX(m_tank.getBody().getX());
			m_drone.setY(m_tank.getBody().getY());

			// Le drone apparait avec direction d'action et de regard identique à celle du
			// tank
			m_drone.setActionDir(m_tank.getBody().getCurrentActionDir());
			m_drone.setLookDir(m_tank.getBody().getLookAtDir());
//			m_tank.showTank(false);
//			m_drone.showEntity(true);
			System.out.println("Contrôles données au DRONE");
		}
	}

	public VisionType getVisionType() {
		if (m_playingTank) {
			return VisionType.TANK;
		}
		return m_drone.getVisionType();
	}

	public Entity getPlayed() {
		if (isPlayingTank()) {
			return m_tank.getBody();
		} else {
			return m_drone;
		}
	}

	/////////////////////////////////////////////////

	public Grid getGrid() {
		return m_grid;
	}

	public HashMap<EntityFactory.MyEntities, LinkedList<Entity>> getHashEntities() {
		return m_entities;
	}

	/**
	 * return une liste d'entité correspondant à la categorie d'entité
	 */
	public LinkedList<Entity> getEntities(MyEntities entityType) {
		return m_entities.get(entityType);
	}

	public LinkedList<Entity> getAllEntities() {
		LinkedList<Entity> entities = new LinkedList<Entity>();
		for (MyEntities entityType : MyEntities.values()) {
			entities.addAll(getEntities(entityType));
		}
		return entities;
	}

	public void addKeyPressed(LsKey temp) {
		if (!m_keyPressed.contains(temp))
			m_keyPressed.add(temp);
		return;
	}

	public void removeKeyPressed(LsKey temp) {
		if (m_keyPressed.contains(temp))
			m_keyPressed.remove(temp);
		return;
	}

	public LinkedList<LsKey> getKeyPressed() {
		return m_keyPressed;
	}

	public void addEntity(Entity e) {
		//TODO : peut etre opti avec la factory.
		if (e instanceof Droppable) {
			getEntities(MyEntities.Droppable).add(e);
		} else if (e instanceof Drone) {
			getEntities(MyEntities.Drone).add(e);
		} else if (e instanceof Enemy) {
			getEntities(MyEntities.Enemy).add(e);
		} else if (e instanceof Vein) {
			getEntities(MyEntities.Vein).add(e);
		} else if (e instanceof Ground) {
			getEntities(MyEntities.Ground).add(e);
		} else if (e instanceof Marker) {
			getEntities(MyEntities.Marker).add(e);
		} else if (e instanceof Shot) {
			getEntities(MyEntities.Shot).add(e);
		} else if (e instanceof TankBody) {
			getEntities(MyEntities.TankBody).add(e);
		} else if (e instanceof Turret) {
			getEntities(MyEntities.Turret).add(e);
		} else {
			throw new IllegalArgumentException("Entité non reconnue !");
		}
		m_nbEntities++;
	}
	
	public void removeEntity(Entity e) {
		if (e instanceof Droppable) {
			getEntities(MyEntities.Droppable).remove(e);
		} else if (e instanceof Drone) {
			getEntities(MyEntities.Drone).remove(e);
		} else if (e instanceof Enemy) {
			getEntities(MyEntities.Enemy).remove(e);
		} else if (e instanceof Vein) {
			getEntities(MyEntities.Vein).remove(e);
		} else if (e instanceof Ground) {
			getEntities(MyEntities.Ground).remove(e);
		} else if (e instanceof Marker) {
			getEntities(MyEntities.Marker).remove(e);
		} else if (e instanceof Shot) {
			getEntities(MyEntities.Shot).remove(e);
		} else if (e instanceof TankBody) {
			getEntities(MyEntities.TankBody).remove(e);
		} else if (e instanceof Turret) {
			getEntities(MyEntities.Turret).remove(e);
		} else {
			throw new IllegalArgumentException("Entité non reconnue !");
		}
		m_nbEntities++;
	}

	public int getNbEntities() {
		return m_nbEntities;
	}

	public boolean isInRadius(LinkedList<Coords> radius, Entity entity) {
		for (Coords coord : radius) {
			if (entity.isInMe(coord.X, coord.Y))
				return true;
		}
		return false;
	}

	public Entity closestEntity(LinkedList<Entity> entities, int x, int y) {
		Entity closest = entities.get(0);
		double min_dist = distanceXAtPow2(closest.getX(), x) + distanceYAtPow2(closest.getY(), y);
		for (Entity curr : entities) {
			double dist = distanceXAtPow2(closest.getX(), x) + distanceYAtPow2(closest.getY(), y);
			if (dist < min_dist) {
				closest = curr;
			}
		}
		return closest;
	}
	
	public double distanceXAtPow2(int a, int b) {
		double baicDst = Math.pow(a - b, 2);
		double toreDst = Math.min(a, b);
		toreDst += m_grid.getNbCellsX() -  Math.max(a, b);
		toreDst = Math.pow(toreDst, 2);
		return Math.min(baicDst, toreDst);
	}
	
	public double distanceYAtPow2(int a, int b) {
		double baicDst = Math.pow(a - b, 2);
		double toreDst = Math.min(a, b);
		toreDst += m_grid.getNbCellsY() -  Math.max(a, b);
		toreDst = Math.pow(toreDst, 2);
		return Math.min(baicDst, toreDst);
	}

	public LinkedList<Entity> getCategoried(MyCategory type) {
		LinkedList<Entity> entities_to_return = new LinkedList<Entity>();
		for (Entity entity : getAllEntities()) {
			if (entity.getCategory() == type) {
				entities_to_return.add(entity);
			}
		}
		return entities_to_return;
	}


}
