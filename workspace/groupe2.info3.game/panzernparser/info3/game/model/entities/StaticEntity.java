package info3.game.model.entities;

import info3.game.automaton.Automaton;
import info3.game.model.Model;

/**
 * Une entité static est une entité qui à la base n'est pas sensé pouvoir se
 * mouvoir.
 */
public abstract class StaticEntity extends Entity {

	public StaticEntity(int x, int y, int width, int height, Model model, Automaton aut) {
		super(x, y, width, height, model, aut);
		m_speed = 0;
	}

}
