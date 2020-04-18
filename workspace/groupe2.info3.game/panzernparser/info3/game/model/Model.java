package info3.game.model;

import java.util.LinkedList;

import info3.game.controller.Controller;
import info3.game.model.entities.Entity;

public class Model {

	// Controller m_controller; //pour envoyer des information utiles.
	Grid m_grid;
	EntityFactory m_factory;
	LinkedList<Entity> m_entities;
	// TODO : Ajouter la liste des Automates

	public Model() {
		// Génère la grille du jeu qui va créer a son tour toutes les entités. La grille
		// doit connaitre ses patterns lors de sa création, le model doit donc lui
		// donner.
	}

	public void step() {
		// Effectue un pas de simulation

	}
}
