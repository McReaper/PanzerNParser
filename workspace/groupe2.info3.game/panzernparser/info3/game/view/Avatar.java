package info3.game.view;

import java.awt.Graphics;
import java.io.IOException;

import info3.game.model.entities.Entity;

/**
 * Aspect graphique de l'entité dans le jeu (sprite 2D + Animation pour chaque
 * actions)
 */
public class Avatar {
	Sprite m_sprite;
	Entity m_entity;
	int m_idImageSprite;

	public Avatar(Entity entity) {
		m_entity = entity;
		m_idImageSprite = 1;
		try {
			m_sprite = new Sprite("sprites/Minerai.png");
		} catch (IOException e) {
			System.out.println("Impossible de trouver l'image !");
		}
	}
	
	public boolean nextImage() {
		if (m_idImageSprite < m_sprite.nbSprites()) {
			m_idImageSprite ++;
			return true;
		}else {
			m_idImageSprite = 1;
			return true;
		}
	}
	
	public void paint(Graphics g) {
//		int width = m_entity.getWidth();
//		int height = m_entity.getHeight();
		g.drawImage(m_sprite.getSprite(m_idImageSprite), 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
		nextImage();
	}

}
