package info3.game.model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import info3.game.automaton.*;
import info3.game.model.entities.Entity;

public class Model {

	// Controller m_controller; //pour envoyer des information utiles.
	Grid m_grid;
	LinkedList<Entity> m_entities;
	List<Automaton> m_automatons;

	public Model() {
		// Génère la liste des automates
		try {
			m_automatons = new ArrayList<Automaton>();
			String path = "../../automate.gal";
			File repertoire = new File(path);
			String[] fils = repertoire.list();
			for (String curFil : fils) {
				AST myAST = AutomataParser.from_file(path + "/" + curFil);
				System.out.println("un autre de fait");
				m_automatons.addAll((List<Automaton>) myAST.accept(new BotBuilder()));
      } 
			/* si un seul fichier .gal commentez début try et décomenter fin*/
//			AST myAST = AutomataParser.from_file("../../automate.gal/automton.gal");
//			m_automatons = (List<Automaton>) myAST.accept(new BotBuilder());
		} catch (Exception e) {
			System.out.println("fichier non trouvé pour la création des automates");
			e.printStackTrace();
		}
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

}
