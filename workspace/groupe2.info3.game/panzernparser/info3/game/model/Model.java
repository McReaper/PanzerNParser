package info3.game.model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import info3.game.automaton.*;
import info3.game.automaton.LsKey;
import info3.game.model.entities.Enemy;
import info3.game.model.entities.Entity;

public class Model {

	public static Model m_model;

	// Controller m_controller; //pour envoyer des information utiles.
	Grid m_grid;
	LinkedList<Entity> m_entities;
	public LinkedList<LsKey> m_keyPressed;

	public Model() {
		// Génère la liste des automates
		m_model = this;
		m_keyPressed = new LinkedList<LsKey>();
		m_entities = new LinkedList<Entity>();
		
		// Génère la grille du jeu qui va créer a son tour toutes les entités et mettre
		// la liste des entités à jour. La grille doit connaitre ses patterns lors de sa
		// création, le model doit donc lui donner.
		
		// Version de test ci-dessous :
		m_grid = new Grid(this);
		
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
