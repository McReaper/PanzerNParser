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
										// nombre de type d'entités, au lieu de 4000 objets (avatar + entités).

	public Avatar(Entity entity, Animation animation) {
		m_entity = entity;
		m_animation = animation;
	}

	public void paint(Graphics g, int case_width, int case_height) {
		MyDirection e_dir = m_entity.getLookAtDir();
		MyDirection e_actionDir = m_entity.getCurrentActionDir();
		LsAction e_ac = m_entity.getCurrentAction();
		MyDirection absoluteActionDir = MyDirection.toAbsolute(e_dir,e_actionDir);
		
		double progress = m_entity.getActionProgress();
		
		int width = m_entity.getWidth() * case_width;
		int height = m_entity.getHeight() * case_height;
		int x = m_entity.getX() * case_width;
		int y = m_entity.getY() * case_height;
		if (e_ac == LsAction.Move) {
			switch (absoluteActionDir) {
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
			}
			switch (absoluteActionDir) {
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
			}
		}
		
		Image sprite = m_animation.getImage(progress,e_ac,absoluteActionDir);
		
		g.drawImage(sprite, x, y, width, height, null);
	}


}
