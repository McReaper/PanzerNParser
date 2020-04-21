package info3.game.view;

import java.awt.Graphics;
import java.io.IOException;

import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;

public class EnemyAvatar extends Avatar {
	public EnemyAvatar(Entity entity) {
		super(entity);
		try {
			m_sprite = new Sprite("sprites/Trou.png");
		} catch (IOException e) {
			System.out.println("Impossible de trouver l'image Minerai!");
		}
	}

	public boolean nextImage() {
		if (m_idImageSprite < m_sprite.nbSprites()) {
			m_idImageSprite++;
			return true;
		} else {
			m_idImageSprite = 1;
			return true;
		}
	}

	public void updateIdImage() {
		float purcentAction = m_entity.getPurcentAction();
		float idImageFloat = purcentAction * m_sprite.m_length;
		m_idImageSprite = (int) idImageFloat;
		if (m_idImageSprite < m_sprite.m_length) {
			m_idImageSprite++;// car les images des sprites commencent à 1 et pas à 0
		}
	}

	@Override
	public void updateAction() {
		m_currentAction = m_entity.getCurrentAction();
	}

	@Override
	public void paint(Graphics g) {
//		int width = m_entity.getWidth();
//		int height = m_entity.getHeight();
		updateAction();
		if (m_currentAction != LsAction.Nothing) {
			updateIdImage();
		}
		g.drawImage(m_sprite.getSprite(m_idImageSprite), 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
	}
}
