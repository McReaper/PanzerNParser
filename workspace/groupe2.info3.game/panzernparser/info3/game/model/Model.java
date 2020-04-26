package info3.game.model;

import java.rmi.UnexpectedException;
import java.util.LinkedList;

import info3.game.automaton.LsKey;
import info3.game.model.entities.Drone;
import info3.game.model.entities.Entity;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;

public class Model {

	private static Model self;

	// Controller m_controller; //pour envoyer des information utiles.
	private Grid m_grid;
	private Tank m_tank;
	private Drone m_drone;
	private LinkedList<Entity> m_entities;
	private LinkedList<LsKey> m_keyPressed;
	private boolean m_playingTank;

	public Model getModel() {
		if (self == null)
			self = new Model();
		return self;
	}
	
	private Model() {
		m_keyPressed = new LinkedList<LsKey>();
		m_entities = new LinkedList<Entity>();
		
		// permet la recupération du body et du turret du tank
		int indexOfTankBody = -1;
		int indexOfTurret = -1;
		// Génère la grille du jeu qui va créer a son tour toutes les entités et mettre
		// la liste des entités à jour. La grille doit connaitre ses patterns lors de sa
		// création, le model doit donc lui donner.

		// Version de test ci-dessous :
		try {
			m_grid = new Grid(this);
		} catch (UnexpectedException e) {
			e.printStackTrace();
			System.err.println("Impossible de créer la grille !");
			// La il faudrait sortir du programme, en appelant le controller, pour arrêter
			// la musique et les autres exécutions auxiliaires en cours.
		}
		int i = 0;
		for (Entity entity : m_entities) {
			if (entity instanceof TankBody) {
				indexOfTankBody = i;
			}
			if (entity instanceof Turret) {
				indexOfTurret = i;
			}
			i++;
		}

		if (indexOfTankBody != -1 && indexOfTurret != -1) {
			m_tank = new Tank(m_entities.get(indexOfTankBody), m_entities.get(indexOfTurret));
			m_playingTank = true;
		} else {
			System.out.println("ERROR : Pas de création du TankBody ET du tankChassis");
		}

	}

	public void step(long elapsed) {
		// Effectue un pas de simulation sur chaque entités
		for (Entity entity : m_entities) {
			entity.step(elapsed);
		}
		m_tank.step();
	}

	public Grid getGrid() {
		return m_grid;
	}

	public LinkedList<Entity> getEntities() {
		return m_entities;
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

	public void addEntity(Entity e) {
		m_entities.add(e);
	}

}
