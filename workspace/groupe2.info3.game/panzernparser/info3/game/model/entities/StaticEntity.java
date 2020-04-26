package info3.game.model.entities;

import info3.game.automaton.Automaton;

/**
 * Une entité static est une entité qui à la base n'est pas sensé pouvoir se
 * mouvoir.
 */
public abstract class StaticEntity extends Entity {

	public StaticEntity(int x, int y, int width, int height, Automaton aut) {
		super(x, y, width, height, aut);
		m_speed = 0; // Valeur par défaut pour une entité statique
	}

}
