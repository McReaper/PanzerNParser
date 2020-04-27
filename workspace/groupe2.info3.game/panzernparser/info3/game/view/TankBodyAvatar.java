package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.Entity;

public class TankBodyAvatar extends Avatar {

	public TankBodyAvatar(Entity entity, Animation animation) {
		super(entity, animation);
	}
	
	@Override
	public void paint(Graphics g,int xcase, int ycase, int case_width, int case_height) {
		MyDirection e_lookAtDir = m_entity.getLookAtDir();
		MyDirection e_actionDir = m_entity.getCurrentActionDir();
		LsAction e_currAction = m_entity.getCurrentAction();
		MyDirection e_absoluteActionDir = MyDirection.toAbsolute(e_lookAtDir, e_actionDir);
		double progress = m_entity.getActionProgress();

		int width = m_entity.getWidth() * case_width;
		int height = m_entity.getHeight() * case_height;
		int x = xcase;
		int y = ycase;

		// Pour réaliser un affichage progressif dans le cas d'un move.
		if (e_currAction == LsAction.Move) {
			x = progressivePaintX(e_absoluteActionDir, x, progress, case_width);
			y = progressivePaintY(e_absoluteActionDir, y, progress, case_height);
		}

		Image sprite = null;
		if(e_currAction != LsAction.Move) {
			sprite = m_animation.getImage(0, LsAction.Move,e_lookAtDir);
		} else {
			sprite = m_animation.getImage(progress, e_currAction, e_absoluteActionDir);			
		}
		g.drawImage(sprite, x, y, width, height, null);
		if(e_currAction == LsAction.Pop) {
			sprite = m_animation.getImage(progress, e_currAction, e_lookAtDir);
			switch (e_lookAtDir) {
				case NORTH:
					y -= height - (height*3/Sprite.SPRITE_HEIGHT);
					break;
				case NORTHEAST:
					y -= height - (height*11/Sprite.SPRITE_HEIGHT);
					x += width - (width*11/Sprite.SPRITE_WIDTH);
					break;
				case EAST:
					x += width - (width*3/Sprite.SPRITE_WIDTH);
					break;
				case SOUTHEAST:
					y += height - (height*11/Sprite.SPRITE_HEIGHT);
					x += width - (width*11/Sprite.SPRITE_WIDTH);
					break;
				case SOUTH:
					y += height - (height*3/Sprite.SPRITE_HEIGHT);
					break;
				case SOUTHWEST:
					y += height - (height*11/Sprite.SPRITE_HEIGHT);
					x -= width - (width*11/Sprite.SPRITE_WIDTH);
					break;
				case WEST:
					x -= width - (width*3/Sprite.SPRITE_WIDTH);
					break;
				case NORTHWEST:
					y -= height - (height*11/Sprite.SPRITE_HEIGHT);
					x -= width - (width*11/Sprite.SPRITE_WIDTH);
					break;
				default:
					break;
			}
			g.drawImage(sprite, x, y, width, height,null);

		}
	}

}
