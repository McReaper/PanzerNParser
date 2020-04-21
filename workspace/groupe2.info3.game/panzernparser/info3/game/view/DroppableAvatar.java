package info3.game.view;

import java.awt.Graphics;
import java.io.IOException;

import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;

public class DroppableAvatar extends Avatar {

	public DroppableAvatar(Entity entity) {
		super(entity);
		try {
			m_sprite = new Sprite("sprites/Trou.png");
		} catch (IOException e) {
			System.out.println("Impossible de trouver l'image Minerai!");
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
	
	@Override
	public void updateAction() {
		currentAction = m_entity.
	}
	
	public void paint(Graphics g) {
//		int width = m_entity.getWidth();
//		int height = m_entity.getHeight();
		g.drawImage(m_sprite.getSprite(m_idImageSprite), 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
		nextImage();
	}

	

}
