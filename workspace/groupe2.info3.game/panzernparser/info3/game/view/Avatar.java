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
	Sprite m_sprite;
	Entity m_entity;
	int m_idImageSprite;
	LsAction m_currentAction;

	public Avatar(Entity entity) {
		m_entity = entity;
		m_idImageSprite = 1;
		m_currentAction = LsAction.Nothing;
	}
	
	public abstract void paint(Graphics g);
	
	public abstract boolean nextImage();
	
	public abstract void updateAction();

}
