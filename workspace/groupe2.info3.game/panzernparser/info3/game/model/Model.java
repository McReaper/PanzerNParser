package info3.game.model;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.LinkedList;

import info3.game.automaton.LsKey;
import info3.game.automaton.MyCategory;
import info3.game.model.Grid.Coords;
import info3.game.model.entities.AutomaticTurret;
import info3.game.model.entities.Drone;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.Mud;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;
import info3.game.model.upgrades.Upgrade;
import info3.game.model.upgrades.UpgradeAutomaticSubmachine;
import info3.game.model.upgrades.UpgradeDroneUsage;
import info3.game.model.upgrades.UpgradeDroneVision;
import info3.game.model.upgrades.UpgradeHealTank;
import info3.game.model.upgrades.UpgradeMarkersCount;
import info3.game.model.upgrades.UpgradeMiningTime;
import info3.game.model.upgrades.UpgradeTankDamage;
import info3.game.model.upgrades.UpgradeTankLife;
import info3.game.model.upgrades.UpgradeTankShotsCapacity;
import info3.game.model.upgrades.UpgradeTankSpeed;
import info3.game.model.upgrades.UpgradeWeapon;

public class Model {

	public static final int IN_PLAY = 0;
	public static final int RELOADING_MAP = 1;
	public static final int RELOAD_TIME = 3000;

	public enum VisionType {
		TANK, RESSOURCES, ENEMIES;
	}

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
	private int m_reloadingState;
	private boolean m_hasReloaded;
	private long m_reloadElapsed;
	private int m_level;
	private boolean m_gameOver;

	/**
	 * Fonction qui g??re le singleton du mod??le (??vite de cr??er plusieurs mod??les).
	 * 
	 * @return le mod??le
	 */
	public static Model getModel() {
		if (self == null)
			new Model();
		return self;
	}

	/**
	 * au rythme des ticks du gamecanvas.
	 * 
	 * @param elapsed
	 */
	public void step(long elapsed) {
		if (!m_gameOver) {
			if (m_reloadingState == IN_PLAY) {
				m_time += elapsed;
				// Effectue un pas de simulation sur chaque entit??s
				for (Entity entity : getAllEntities()) {
					entity.step(elapsed);
				}
				m_tank.step();
				m_collisionManager.controlCollisionsShotsEntity();
				m_score.updateTime();
			}

			if (needRegeneration() || m_reloadingState == RELOADING_MAP) {
				m_reloadingState = RELOADING_MAP;
				m_reloadElapsed += elapsed;
				if (m_reloadElapsed >= RELOAD_TIME) {
					m_reloadingState = IN_PLAY;
					m_hasReloaded = false;
					m_reloadElapsed = 0;
				} else if (m_reloadElapsed >= RELOAD_TIME / 3 && !m_hasReloaded) {
					try {
						m_hasReloaded = true;
						m_playingTank = true;
						reset();
					} catch (Exception e) {
						e.printStackTrace();
						System.exit(1);
					}
				}
			}
		}
	}

	/**
	 * Cr??ation du mod??le (l'univers du jeu)
	 */
	private Model() {
		self = this; // Pour ??viter des appels r??cursifs infinis.

		// Initialisation des sons a jouer depuis le controller
		m_soundsToPlay = new LinkedList<String>();
		// Temps de jeu ??coul??
		m_time = 0;

		// Cr??ation de la liste des touches enfonc??es connues du mod??le.
		m_keyPressed = new LinkedList<LsKey>();

		// Cr??ation de la liste des entit??s :
		m_level = 1;
		m_entities = new HashMap<EntityFactory.MyEntities, LinkedList<Entity>>();
		for (MyEntities entityType : MyEntities.values()) {
			m_entities.put(entityType, new LinkedList<Entity>());
		}

		// Creation de la classe CollisionEntity
		m_collisionManager = new CollisionManager();

		// G??n??re la grille du jeu qui va cr??er a son tour toutes les entit??s et mettre
		// la liste des entit??s ?? jour.
		try {
			m_grid = new Grid();
			m_grid.generate();
		} catch (UnexpectedException e) {
			e.printStackTrace();
			System.err.println("Impossible de cr??er la grille !");
			System.exit(1);
		}

		// Cr??ation du score du jeu.
		m_hasReloaded = false;
		m_reloadElapsed = 0;
		m_gameOver = false;
	}

///////* REGENERATION MAP *///////

	/* regarde si la map a besoin d'??tre r??generer */
	private boolean needRegeneration() {
		return getEntities(MyEntities.EnemyBasic).size() <= 4 && getEntities(MyEntities.EnemyLevel2).size() <= 1
				&& getEntities(MyEntities.EnemyBoss).size() <= 0;
	}

	public double getReloadProgress() {
		return (double) m_reloadElapsed / RELOAD_TIME;
	}

	/* vide la liste d'entit?? */
	private void reset() throws UnexpectedException {
		/* reinitialisation des entit??s */
		m_entities = new HashMap<EntityFactory.MyEntities, LinkedList<Entity>>();
		for (MyEntities entityType : MyEntities.values()) {
			m_entities.put(entityType, new LinkedList<Entity>());
		}
		TankBody body = m_tank.getBody();
		switch(body.getHasChangedSpeed()) {
			case -1:
				body.setSpeed(body.getSpeed() / Mud.MUD_COEFFICIENT_SPEED);
				break;
			case 1:
				body.setSpeed(body.getSpeed() * Mud.MUD_COEFFICIENT_SPEED);
				break;
			case 0:
				break;
		}
		body.setHasChangedSpeed(0);
		m_level++;
		m_grid.emptyGrid();
		m_grid.load();
		m_grid.generate();
		m_grid.sendToModel();
		regeneratePlayer();

		/* Vider la liste des sons */
		m_soundsToPlay = new LinkedList<String>();
	}

	private void regeneratePlayer() {
		if (getEntities(MyEntities.TankBody).size() != 1) {
			System.err.println("Il semblerait que la grille ne comporte pas de TankBody...");
			System.exit(1);
		}

		TankBody newTankBody = (TankBody) getEntities(MyEntities.TankBody).get(0);

		int x = newTankBody.getX();
		int y = newTankBody.getY();
		m_tank.getBody().setPosition(x, y); // /!\ La m??thode setPosition travail avec la grille
		m_tank.relocateParts();

		this.removeEntity(m_tank.getBody()); // /!\ On a donc besoin de les retirer avant de les remettre
		this.removeEntity(m_tank.getTurret());
		this.removeEntity(m_tank.getAutoTurret());
		this.addEntity(m_tank.getBody());
		this.addEntity(m_tank.getTurret());
		this.addEntity(m_tank.getAutoTurret());
		this.addEntity(m_drone);
		m_drone.resetMarkers();

		this.removeEntity(newTankBody);
	}

	///////////////////////////////////////////////
	private void initUpgrades() {
		m_uniqUpgrade = new LinkedList<Upgrade>();
		m_uniqUpgrade.add(new UpgradeDroneVision(m_tank, m_drone));
		m_uniqUpgrade.add(new UpgradeAutomaticSubmachine(m_tank));

		m_statUpgrade = new LinkedList<Upgrade>();
		m_statUpgrade.add(new UpgradeHealTank(m_tank, m_drone));
		m_statUpgrade.add(new UpgradeDroneUsage(m_tank, m_drone));
		m_statUpgrade.add(new UpgradeMarkersCount(m_tank, m_drone));
		m_statUpgrade.add(new UpgradeMiningTime(m_tank));
		m_statUpgrade.add(new UpgradeWeapon(m_tank));
		m_statUpgrade.add(new UpgradeTankDamage(m_tank));
		m_statUpgrade.add(new UpgradeTankLife(m_tank));
		m_statUpgrade.add(new UpgradeTankShotsCapacity(m_tank));
		m_statUpgrade.add(new UpgradeTankSpeed(m_tank));
	}

	//////// Gestion du passage drone/tank ////////

	public boolean isPlayingTank() {
		return m_playingTank;
	}

	public void switchControl() {
		m_playingTank = !m_playingTank;
		if (m_playingTank) {
			cleanClue();
		} else {
			int droneTPX = m_tank.getBody().getX();
			int droneTPY = m_tank.getBody().getY();
			// Le drone apparait au niveau du tank
			m_drone.setPosition(droneTPX, droneTPY);
			// et avec direction d'action et de regard identique ?? celle du tank
			m_drone.setActionDir(m_tank.getBody().getCurrentActionDir());
			m_drone.setLookDir(m_tank.getBody().getLookAtDir());
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

	public int getReloadingState() {
		return m_reloadingState;
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

	public int getLevel() {
		return m_level;
	}

	public HashMap<EntityFactory.MyEntities, LinkedList<Entity>> getHashEntities() {
		return m_entities;
	}

	/**
	 * return une liste d'entit?? correspondant ?? la categorie d'entit??
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

	public void addEntity(Entity e) {
		getEntities(EntityFactory.getMyEntities(e)).add(e);
		m_grid.addEntity(e);
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
		if (entities.size() == 0) return null;
		Entity closest = entities.get(0);
		double min_dist = m_grid.distanceXAtPow2(closest.getX(), x) + m_grid.distanceYAtPow2(closest.getY(), y);
		for (Entity curr : entities) {
			double dist = m_grid.distanceXAtPow2(closest.getX(), x) + m_grid.distanceYAtPow2(closest.getY(), y);
			if (dist < min_dist) {
				closest = curr;
			}
		}
		return closest;
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
				System.exit(1);
			}
		} else if (!isStat && isUniq) {
			try {
				upgrade.activate();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				System.exit(1);
			}
		} else {
			System.err.println("L'upgrade pass?? en param??tre n'est pas connue du model.");
			System.exit(1);
		}
	}

	////////////////////////////////////////////////

	public void setGameOver(boolean bool) {
		m_gameOver = bool;
	}

	public boolean getGameOver() {
		return m_gameOver;
	}

	public static void restart() {
		self = null;
		getModel();
	}

	public void launch() {
		m_score = new Score();
		
		m_grid.sendToModel();

		if (getEntities(MyEntities.TankBody).size() != 1) {
			System.err.println("Il semblerait que la grille ne comporte pas de TankBody...");
			System.exit(1);
		}

		// Cr??ation du Tank et du Drone :
		TankBody body = (TankBody) getEntities(MyEntities.TankBody).get(0);
		AutomaticTurret autTurret = (AutomaticTurret) EntityFactory.newEntity(MyEntities.AutomaticTurret, body.getX(),
				body.getY());
		Turret turret = (Turret) EntityFactory.newEntity(MyEntities.Turret, body.getX(), body.getY());
		m_drone = (Drone) EntityFactory.newEntity(MyEntities.Drone, body.getX(), body.getY());
		m_tank = new Tank(body, turret, autTurret);
		m_playingTank = true;

		// Initialisation des upgrades
		initUpgrades();
	}

}
