package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;

/**
 * Aspect graphique de l'entité dans le jeu (sprite 2D + Animation pour chaque
 * actions)
 */
public class Avatar {
	Animation m_animation;
	Entity m_entity; // TODO : revoir ici pour potentiellement stocker toutes les entités associées
										// au type d'avatar concerné : on aurait pour 2000 entités, seulent 2000 + le
										// nombre de type d'entités en nombre d'objets, au lieu de 4000 objets (avatar +
										// entités).

	public Avatar(Entity entity, Animation animation) {
		m_entity = entity;
		m_animation = animation;
	}
	
	public int progressivePaintY(MyDirection e_absoluteActionDir,int y, double progress, int case_height) {
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

	public void paint(Graphics g, int xcase, int ycase, int case_width, int case_height) {
		MyDirection e_lookAtDir = m_entity.getLookAtDir();
		MyDirection e_actionDir = m_entity.getCurrentActionDir();
		LsAction e_currAction = m_entity.getCurrentAction();
		MyDirection e_absoluteActionDir = MyDirection.toAbsolute(e_lookAtDir, e_actionDir);
		double progress = m_entity.getActionProgress();

		int width = m_entity.getWidth() * case_width;
		int height = m_entity.getHeight() * case_height;
		int x = xcase;																						// deux seul changement
		int y = ycase;

		// Pour réaliser un affichage progressif dans le cas d'un move.
		if (e_currAction == LsAction.Move) {
			progressivePaintX(e_absoluteActionDir, x, progress, case_width);
			progressivePaintY(e_absoluteActionDir, y, progress, case_height);
		}

		Image sprite = m_animation.getImage(progress, e_currAction, e_absoluteActionDir);

		g.drawImage(sprite, x, y, width, height, null);
	}

}
