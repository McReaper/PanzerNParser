package info3.game.model;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.LinkedList;

import info3.game.automaton.LsKey;
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

	public static Model m_model;

	// Controller m_controller; //pour envoyer des information utiles.
	Grid m_grid;
	public LinkedList<LsKey> m_keyPressed;
	private int m_nb_entities;
	HashMap<EntityFactory.MyEntities, LinkedList<Entity>> m_entities;

	public Model() {
		// Génère la liste des automates
		m_model = this;
		int nb_entities =0;
		m_keyPressed = new LinkedList<LsKey>();
		 m_entities = new HashMap<EntityFactory.MyEntities, LinkedList<Entity>>();
		for (MyEntities entityType : MyEntities.values()) {
			m_entities.put(entityType, new LinkedList<Entity>());
		}
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

	}

	public void step(long elapsed) {
		// Effectue un pas de simulation sur chaque entités
		for (MyEntities entity : m_entities.keySet()) {
			LinkedList<Entity> iter_list = m_entities.get(entity);
			for (Entity ent : iter_list) {
				ent.step(elapsed);
			}
		}

	}

	public static Model getModel() {
		return m_model;
	}

	public Grid getGrid() {
		return m_grid;
	}

	/*
	 * TODO : pas sur de la methode ci-dessous, est-ce interessant de return la
	 * HashMap ?
	 */
	public HashMap<EntityFactory.MyEntities, LinkedList<Entity>> getAllEntities() {
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
		}

	}

	public int getNb_entities() {
		return m_nb_entities;
	}
	
	/*
	 * return une liste d'entité correspondant à la categorie d'entité
	 */
	public LinkedList<Entity> getEntities(MyEntities entityType) {
		return m_entities.get(entityType);
	}

}
