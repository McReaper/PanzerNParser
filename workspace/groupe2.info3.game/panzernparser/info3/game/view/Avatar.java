package info3.game.view;

import java.awt.Graphics;

import info3.game.model.entities.Entity;

/**
 * Aspect graphique de l'entité dans le jeu (sprite 2D + Animation pour chaque
 * actions)
 */
public class Avatar {
	Sprite m_sprite;
	Entity m_entity;

	public Avatar(Entity entity) {
		m_entity = entity;
	}
	
	public void paint(Graphics g) {
		
	}

}
