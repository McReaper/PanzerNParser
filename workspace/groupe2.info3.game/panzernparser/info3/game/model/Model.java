package info3.game.model;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.LinkedList;

import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.model.Grid.Coords;
import info3.game.model.entities.Drone;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.upgrades.Upgrade;
import info3.game.model.upgrades.UpgradeAutomaticSubmachine;
import info3.game.model.upgrades.UpgradeDroneUsage;
import info3.game.model.upgrades.UpgradeDroneVision;
import info3.game.model.upgrades.UpgradeMarkersCount;
import info3.game.model.upgrades.UpgradeMiningTime;
import info3.game.model.upgrades.UpgradeShot;
import info3.game.model.upgrades.UpgradeTankDamage;
import info3.game.model.upgrades.UpgradeTankLife;
import info3.game.model.upgrades.UpgradeTankShotsCapacity;
import info3.game.model.upgrades.UpgradeTankSpeed;
import info3.game.model.entities.Marker;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;

public class Model {

	public enum VisionType {
		TANK, RESSOURCES, ENEMIES;
	}
	
	public static final int IN_PLAY = 0;
	public static final int RELOADING_MAP = 1;
	public static final int RELOAD_TIME = 3000;

	private static Model self; // Singleton du model

	private Grid m_grid;
	private Tank m_tank;
	private Drone m_drone;
	private HashMap<EntityFactory.MyEntities, LinkedList<Entity>> m_entities;
	private LinkedList<LsKey> m_keyPressed;
	private CollisionManager m_collisionManager;
	private boolean m_playingTank;
	private Coords m_clue;
	private long m_time;
	private LinkedList<String> m_soundsToPlay;
	private LinkedList<Upgrade> m_statUpgrade;
	private LinkedList<Upgrade> m_uniqUpgrade;
	private Score m_score;
	private int m_state;
	private long m_reloadElapsed;

	/**
	 * Fonction qui gère le singleton du modèle (évite de créer plusieurs modèles).
	 * 
	 * @return le modèle
	 */
	public static Model getModel() {
		if (self == null)
			new Model();
		return self;
	}

	/**
	 * Création du modèle (l'univers du jeu)
	 */
	private Model() {
		self = this; // Pour éviter des appels récursifs infinis.

		//Initialisation des sons a jouer depuis le controller
		m_soundsToPlay = new LinkedList<String>();
		
		//Temps de jeu écoulé
		m_time = 0;
		
		// Création de la liste des touches enfoncées connues du modèle.
		m_keyPressed = new LinkedList<LsKey>();

		// Création de la liste des entités :
		m_entities = new HashMap<EntityFactory.MyEntities, LinkedList<Entity>>();
		for (MyEntities entityType : MyEntities.values()) {
			m_entities.put(entityType, new LinkedList<Entity>());
		}

		// Creation de la classe CollisionEntity
		System.out.println("Création de la classe CollisionManager");
		m_collisionManager = new CollisionManager();
		System.out.println("CollisionManager créé !");

		// Génère la grille du jeu qui va créer a son tour toutes les entités et mettre
		// la liste des entités à jour.
		try {
			m_grid = new Grid();
			m_grid.generate();
		} catch (UnexpectedException e) {
			e.printStackTrace();
			System.err.println("Impossible de créer la grille !");
			System.exit(-1);
		}

		if (getEntities(MyEntities.TankBody).size() != 1 || getEntities(MyEntities.Turret).size() != 1
				|| getEntities(MyEntities.Drone).size() != 1) {
			System.err.println("Il semblerait que la grille comporte plusieurs Drone ou Tank...");
			System.exit(-1);
		}

		//Création du Tank et du Drone :
		TankBody body = (TankBody) getEntities(MyEntities.TankBody).get(0);
		Turret turret = (Turret) getEntities(MyEntities.Turret).get(0);
		m_tank = new Tank(body, turret);
		m_drone = (Drone) getEntities(MyEntities.Drone).get(0);
		m_playingTank = true;
		
		//Initialisation des upgrades
		m_uniqUpgrade = new LinkedList<Upgrade>();
		m_statUpgrade = new LinkedList<Upgrade>();
		initUpgrades();
		
		//Création du score du jeu.
		m_score = new Score();
		
	}
	
	private void initUpgrades() {
		m_uniqUpgrade.add(new UpgradeDroneVision(m_tank, m_drone));
		m_uniqUpgrade.add(new UpgradeAutomaticSubmachine(m_tank));
		m_statUpgrade.add(new UpgradeDroneUsage(m_tank, m_drone));
		m_statUpgrade.add(new UpgradeMarkersCount(m_tank, m_drone));
		m_statUpgrade.add(new UpgradeMiningTime(m_tank));
		m_statUpgrade.add(new UpgradeShot(m_tank));
		m_statUpgrade.add(new UpgradeTankDamage(m_tank));
		m_statUpgrade.add(new UpgradeTankLife(m_tank));
		m_statUpgrade.add(new UpgradeTankShotsCapacity(m_tank));
		m_statUpgrade.add(new UpgradeTankSpeed(m_tank));
	}

	public void step(long elapsed) {
		m_time += elapsed;
		// Effectue un pas de simulation sur chaque entités
		for (Entity entity : getAllEntities()) {
			entity.step(elapsed);
		}
		m_tank.step();
		m_collisionManager.controlCollisionsShotsEntity();
		m_score.updateTime();
		if(reloadNecessary() || m_state == RELOADING_MAP) {
			m_state = RELOADING_MAP;
			m_reloadElapsed += elapsed;
			if(m_reloadElapsed >= RELOAD_TIME) {
				m_state = IN_PLAY;
			}
		}
	}

	//////// Gestion du passage drone/tank ////////

	private boolean reloadNecessary() {
		return m_time > 5000 && m_time < 5500;
	}
	
	public double getReloadProgress() {
		return (double)m_reloadElapsed/RELOAD_TIME;
	}

	public boolean isPlayingTank() {
		return m_playingTank;
	}

	public void switchControl() {
		m_playingTank = !m_playingTank;
		if (m_playingTank) {
			m_drone.showEntity(false);
		} else {
			int droneTPX = m_tank.getBody().getX();
			int droneTPY = m_tank.getBody().getY();
			// Le drone apparait au niveau du tank
			m_drone.setPosition(droneTPX, droneTPY);
			// et avec direction d'action et de regard identique à celle du tank
			m_drone.setActionDir(m_tank.getBody().getCurrentActionDir());
			m_drone.setLookDir(m_tank.getBody().getLookAtDir());
			m_drone.showEntity(true);
		}
	}

	public VisionType getVisionType() {
		if (m_playingTank) {
			return VisionType.TANK;
		}
		return getDrone().getVisionType();
	}

	public Entity getPlayed() {
		if (isPlayingTank()) {
			return m_tank.getBody();
		} else {
			return getDrone();
		}
	}

	public Tank getTank() {
		return m_tank;
	}

	public Drone getDrone() {
		return m_drone;
	}

	public long getTime() {
		return m_time;
	}

	public LinkedList<Upgrade> getStatUpgrade() {
		return m_statUpgrade;
	}

	public LinkedList<Upgrade> getUniqUpgrade() {
		return m_uniqUpgrade;
	}

	public Score getScore() {
		return m_score;
	}
	
	public int getState() {
		return m_state;
	}

	/////////////////////////////////////////////////

	public void addSound(String soundName) {
		m_soundsToPlay.add(soundName);
	}

	public LinkedList<String> getSounds() {
		return m_soundsToPlay;
	}

	////////////////////////////////////////////////

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

	////////////////////////////////////////////////

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

	////////////////////////////////////////////////

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

	public void removeEntity(Entity e) {
		getEntities(EntityFactory.getMyEntities(e)).remove(e);
		m_grid.removeEntity(e);
	}

	public boolean isInRadius(LinkedList<Coords> radius, Entity entity) {
		for (Coords coord : radius) {
			if (entity.isInMe((int) coord.X, (int) coord.Y))
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
		toreDst += m_grid.getNbCellsX() - Math.max(a, b);
		toreDst = Math.pow(toreDst, 2);
		return Math.min(baicDst, toreDst);
	}

	public double distanceYAtPow2(int a, int b) {
		double baicDst = Math.pow(a - b, 2);
		double toreDst = Math.min(a, b);
		toreDst += m_grid.getNbCellsY() - Math.max(a, b);
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

	public void performUpgrade(Upgrade upgrade) {
		boolean isStat = m_statUpgrade.contains(upgrade);
		boolean isUniq = m_uniqUpgrade.contains(upgrade);
		if (isStat && !isUniq) {
			try {
				upgrade.improve();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		} else if (!isStat && isUniq) {
			try {
				upgrade.activate();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		} else {
			System.err.println("L'upgrade passé en paramètre n'est pas connue du model.");
			System.exit(-1);
		}
	}

}
