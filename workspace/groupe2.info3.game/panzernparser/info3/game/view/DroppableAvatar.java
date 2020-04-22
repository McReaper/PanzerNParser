package info3.game.view;

import java.awt.Graphics;
import java.io.IOException;

import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;

public class DroppableAvatar extends Avatar {

	public DroppableAvatar(Entity entity, Animation animation) {
		super(entity, animation);
//		try {
//			m_sprite = new Sprite("sprites/Trou.png");
//		} catch (IOException e) {
//			System.out.println("Impossible de trouver l'image Minerai!");
//		}
	}
//	
//	public void nextImage() {
//		if (m_idImageSprite < m_sprite.nbSprites()) {
//			m_idImageSprite ++;
//		}else {
//			m_idImageSprite = 1;
//		}
//	}
//	
//	public void updateIdImage() {
//		m_idImageSprite = (int)(m_entity.getActionProgress() * m_sprite.m_length);
//		if (m_idImageSprite < m_sprite.m_length) {
//			m_idImageSprite++; // car les images des sprites commencent à 1 et pas à 0
//		}
//	}
//	
//	@Override
//	public void updateAction() {
//		m_currentAction = m_entity.getCurrentAction();
//	}
//	
//	public void paint(Graphics g) {
////		int width = m_entity.getWidth();
////		int height = m_entity.getHeight();
//		//updateAction();
//		if (m_currentAction == LsAction.Nothing) {
//			paintStaticHole(g);
//		}else if (m_currentAction == LsAction.Pop){
//			paintDynamicHole(g);
//		}
//	}
//
//	public void paintStaticHole(Graphics g) {
//		g.drawImage(m_sprite.getSprite(1), 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
//	}
//	
//	public void paintDynamicHole(Graphics g) {
//		updateIdImage();
//		g.drawImage(m_sprite.getSprite(m_idImageSprite), 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
//		
//	}

}
