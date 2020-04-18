package info3.game.model;

public class Model {
	Grid m_grid;
	// TODO : Ajouter la liste des Automates

	public Model() {
		// Génère la grille du jeu et toutes les entités ducoup.
	}

	public void tick(long elapsed) {
		m_grid.tick(elapsed);
	}
}
