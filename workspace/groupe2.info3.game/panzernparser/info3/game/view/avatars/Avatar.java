package info3.game.view.avatars;

import java.awt.Graphics;

import info3.game.automaton.MyDirection;
import info3.game.model.entities.Entity;
import info3.game.view.Animation;

/**
 * Aspect graphique de l'entité dans le jeu (sprite 2D + Animation pour chaque
 * actions)
 */
public abstract class Avatar {
	Animation m_animation;

	public Avatar(Animation animation) {
		m_animation = animation;
	}

	public int progressivePaintY(MyDirection e_absoluteActionDir, int y, double progress, int case_height) {
		switch (e_absoluteActionDir) {
			case NORTH:
			case NORTHEAST:
			case NORTHWEST:
				y -= (case_height * progress) - case_height;
				break;
			case SOUTH:
			case SOUTHEAST:
			case SOUTHWEST:
				y += (case_height * progress) - case_height;
				break;
			default:
				break;
		}
		return y;
	}

	public int progressivePaintX(MyDirection e_absoluteActionDir, int x, double progress, int case_width) {
		switch (e_absoluteActionDir) {
			case EAST:
			case NORTHEAST:
			case SOUTHEAST:
				x += (case_width * progress) - case_width;
				break;
			case WEST:
			case NORTHWEST:
			case SOUTHWEST:
				x -= (case_width * progress) - case_width;
				break;
			default:
				break;
		}
		return x;
	}

	/**
	 * Fonction qui dessine l'Avatar d'une entité à l'écran.
	 * 
	 * @param g           zone de dessin
	 * @param case_width  largeur d'une case dans la vue
	 * @param case_height hauteur d'une case dans la vue
	 */

	public abstract void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height);

}
