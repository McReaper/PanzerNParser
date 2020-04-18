package info3.game.model;

import java.util.LinkedList;

import info3.game.model.entities.Entity;

public class Model {
	Grid m_grid;
	LinkedList<Entity> m_entities;
	// TODO : Ajouter la liste des Automates
	
	public Model() {
		//Génère la grille du jeu et toutes les entités ducoup.
	}

	public void step() {
		//Effectue un pas de simulation
	}
}
