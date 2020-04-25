package info3.game.model;

import java.rmi.UnexpectedException;
import java.util.LinkedList;

import info3.game.automaton.LsKey;
import info3.game.model.entities.Entity;

public class Model {

	public static Model m_model;

	// Controller m_controller; //pour envoyer des information utiles.
	Grid m_grid;
	LinkedList<Entity> m_entities;
	public LinkedList<LsKey> m_keyPressed;
	Entity m_player;
	public Model() {
		// Génère la liste des automates
		m_model = this;
		m_keyPressed = new LinkedList<LsKey>();
		m_entities = new LinkedList<Entity>();
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
		for (Entity entity : m_entities) {
			entity.step(elapsed);
		}
	}

	public static Model getModel() {
		return m_model;
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
