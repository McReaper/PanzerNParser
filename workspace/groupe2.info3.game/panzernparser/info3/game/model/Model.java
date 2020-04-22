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
	public List<Automaton> m_automatons;

	public Model() {
		// Génère la liste des automates
		m_model = this;
		m_keyPressed = new LinkedList<LsKey>();
		try {
//			m_automatons = new ArrayList<Automaton>();
//			String path = "gal";
//			File repertoire = new File(path);
//			String[] fils = repertoire.list();
//			BotBuilder bb8 = new BotBuilder();
//			List<Automaton> lsAuto;
//			for (String curFil : fils) {
//				System.out.print(curFil);
//				AST myAST = AutomataParser.from_file(path + "/" + curFil);
//				System.out.println(" fait");
//				lsAuto = (List<Automaton>) myAST.accept(bb8);
//				m_automatons.addAll(lsAuto);
//      } 
			
			/* si un seul fichier .gal commentez début try et décomenter fin */
			BotBuilder bb8 = new BotBuilder();
			AST myAST = AutomataParser.from_file("gal/Drone.gal");
			List<Automaton> lsAuto = (List<Automaton>) myAST.accept(bb8);
			m_automatons = (lsAuto);
		} catch (Exception e) {
			System.out.println();
			System.out.println("fichier non trouvé pour la création des automates");
			e.printStackTrace();
		}
		// Génère la grille du jeu qui va créer a son tour toutes les entités et mettre
		// la liste des entités à jour. La grille doit connaitre ses patterns lors de sa
		// création, le model doit donc lui donner.
		m_entities = new LinkedList<Entity>();
		Automaton drone = m_model.m_automatons.get(0);
		Entity e = new Enemy(5, 5, Enemy.ENEMY_WIDTH, Enemy.ENEMY_HEIGHT, m_model);
		e.setAutomaton(drone);
		e.setState(drone.getState());
		m_entities.add(e);
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
