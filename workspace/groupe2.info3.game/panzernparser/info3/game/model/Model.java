package info3.game.model;

import java.util.LinkedList;

import info3.game.automaton.LsKey;
import info3.game.model.entities.Entity;

public class Model {

	// Controller m_controller; //pour envoyer des information utiles.
	Grid m_grid;
	LinkedList<Entity> m_entities;
	LinkedList<LsKey> m_keyPressed;
	// TODO : Ajouter la liste des Automates

	public Model() {
		// Génère la grille du jeu qui va créer a son tour toutes les entités et mettre
		// la liste des entités à jour. La grille doit connaitre ses patterns lors de sa
		// création, le model doit donc lui donner.
		m_entities = new LinkedList<Entity>();
		
		// Version de test ci-dessous :
		m_grid = new Grid(this);
	}

	public void step(long elapsed) {
		// Effectue un pas de simulation sur chaque entités
		for (Entity entity : m_entities) {
			entity.step(elapsed);
		}
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
	
}
