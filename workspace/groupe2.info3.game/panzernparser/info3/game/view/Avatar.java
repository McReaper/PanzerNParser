package info3.game.view;

import java.awt.Graphics;
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
	LsAction m_currentAction; // TODO

	public Avatar(Entity entity, Animation animation) {
		m_entity = entity;
		m_animation = animation;
	}

	public abstract void paint(Graphics g);

	public abstract void nextImage();

	public abstract void updateAction();

}
