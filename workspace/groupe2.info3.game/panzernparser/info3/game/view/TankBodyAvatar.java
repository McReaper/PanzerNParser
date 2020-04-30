package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;

public class TankBodyAvatar extends Avatar {

	public TankBodyAvatar(Animation animation) {
		super(animation);
	}

	@Override
	public void paint(Graphics g,Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		MyDirection e_lookAtDir = entity.getLookAtDir();
		MyDirection e_actionDir = entity.getCurrentActionDir();
		LsAction e_currAction = entity.getCurrentAction();
		MyDirection e_absoluteActionDir = MyDirection.toAbsolute(e_lookAtDir, e_actionDir);
		double progress = entity.getActionProgress();

		int width = entity.getWidth() * case_width;
		int height = entity.getHeight() * case_height;
		int x = xcase;
		int y = ycase;

		// Pour réaliser un affichage progressif dans le cas d'un move.
		if (e_currAction == LsAction.Move) {
			x = progressivePaintX(e_absoluteActionDir, x, progress, case_width);
			y = progressivePaintY(e_absoluteActionDir, y, progress, case_height);
		}

		Image sprite = null;
		if (e_currAction != LsAction.Move) {
			sprite = m_animation.getImage(0, LsAction.Move, e_lookAtDir, vision);
		} else {
			sprite = m_animation.getImage(progress, e_currAction, e_absoluteActionDir, vision);
		}
		g.drawImage(sprite, x, y, width, height, null);
		if (e_currAction == LsAction.Pop) {
			sprite = m_animation.getImage(progress, e_currAction, e_lookAtDir, vision);
			switch (e_lookAtDir) {
				case NORTH:
					y -= height - (height * 3 / Sprite.SPRITE_HEIGHT);
					break;
				case NORTHEAST:
					y -= height - (height * 11 / Sprite.SPRITE_HEIGHT);
					x += width - (width * 11 / Sprite.SPRITE_WIDTH);
					break;
				case EAST:
					x += width - (width * 3 / Sprite.SPRITE_WIDTH);
					break;
				case SOUTHEAST:
					y += height - (height * 11 / Sprite.SPRITE_HEIGHT);
					x += width - (width * 11 / Sprite.SPRITE_WIDTH);
					break;
				case SOUTH:
					y += height - (height * 3 / Sprite.SPRITE_HEIGHT);
					break;
				case SOUTHWEST:
					y += height - (height * 11 / Sprite.SPRITE_HEIGHT);
					x -= width - (width * 11 / Sprite.SPRITE_WIDTH);
					break;
				case WEST:
					x -= width - (width * 3 / Sprite.SPRITE_WIDTH);
					break;
				case NORTHWEST:
					y -= height - (height * 11 / Sprite.SPRITE_HEIGHT);
					x -= width - (width * 11 / Sprite.SPRITE_WIDTH);
					break;
				default:
					break;
			}
			g.drawImage(sprite, x, y, width, height, null);

		}
	}

}
