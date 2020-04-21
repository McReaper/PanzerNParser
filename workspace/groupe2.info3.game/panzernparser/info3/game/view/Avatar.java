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
	Sprite m_sprite; // TODO
	Animation m_animation;
	Entity m_entity;
	int m_idImageSprite; //TODO : déplacer ce champs dans la classe animation.
	LsAction m_currentAction; // TODO

	public Avatar(Entity entity) {
		m_entity = entity;
		m_idImageSprite = 1;
		m_currentAction = LsAction.Nothing;
	}
	
	public abstract void paint(Graphics g);
	
	public abstract void nextImage();
	
	public abstract void updateAction();

}
