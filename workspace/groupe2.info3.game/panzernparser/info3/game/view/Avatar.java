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
	int m_idImageSprite;

	public Avatar(Entity entity) {
		m_entity = entity;
		m_idImageSprite = 1;
		m_sprite = new  Sprite("../sprites/Minerai.png");
	}
	
	public boolean nextImage() {
		if (m_idImageSprite<= m_sprite.nbSprites()) {
			m_idImageSprite ++;
			return true;
		}else {
			m_idImageSprite = 1;
			return true;
		}
	}
	
	public void paint(Graphics g) {
		int x = m_entity.getX();
		int y = m_entity.getY();
		int scale = 1;
		g.drawImage(m_sprite.getSprite(m_idImageSprite), x, y, scale, scale);
		nextImage();
	}

}
