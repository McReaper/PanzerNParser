package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;

/**
 * Aspect graphique de l'entité dans le jeu (sprite 2D + Animation pour chaque
 * actions)
 */
public abstract class Avatar {
	Animation m_animation;
	Entity m_entity; // TODO : revoir ici pour potentiellement stocker toutes les entités associées
										// au type d'avatar concerné : on aurait pour 2000 entités, seulent 2000 + le
										// nombre de type d'entités, au lieu de 4000 objets (avatar + entités).

	public Avatar(Entity entity, Animation animation) {
		m_entity = entity;
		m_animation = animation;
	}

	public void paint(Graphics g) {
//	int width = m_entity.getWidth();
//	int height = m_entity.getHeight();
		Image sprite = m_animation.getImage(m_entity.getActionProgress(), m_entity.getCurrentAction());
		g.drawImage(sprite, m_entity.getX() * 100, m_entity.getY() * 100, 100, 100, null);
	}


}
