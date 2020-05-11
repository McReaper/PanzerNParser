package info3.game.view.avatars;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import info3.game.GameConfiguration;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;
import info3.game.view.Animation;
import info3.game.view.Sprite;
import info3.game.view.View;
import info3.game.view.ViewPort;

public class DroneAvatar extends Avatar {

	private View m_view;
	private Sprite angle;

	public DroneAvatar(Animation animation, View view) {
		super(animation);
		m_view = view;
		try {
			angle = new Sprite(GameConfiguration.SPRITE_PATH + "angle.png");
		} catch (IOException e) {
			GameConfiguration.fileNotFound(GameConfiguration.SPRITE_PATH + "angle.png");
		}
	}

	@Override
	public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		MyDirection dir = entity.getLookAtDir();
		LsAction ac = entity.getCurrentAction();
		double act_progress = entity.getActionProgress();
		long time = Model.getModel().getTime();
		time = time % 200;
		int progress;
		if (time > 100) {
			progress = 1;
		} else {
			progress = 0;
		}
		Image img = null;
		if(ac != LsAction.Wizz) {
			img = m_animation.getImage(progress, LsAction.Move, dir, vision);			
		} else {
			img = m_animation.getImage(progress, LsAction.Move, MyDirection.WEST, vision);			
		}

		ViewPort vp = m_view.getViewPort();
		int x = vp.getOffsetWindowX();
		int y = vp.getOffsetWindowY();
		int w = vp.getPaintSize();
		int h = vp.getPaintSize();
		int angleSize = vp.getPaintSize() / 6;
		g.drawImage(angle.getSprite(1), x, y, angleSize, angleSize, null);
		g.drawImage(angle.getSprite(2), x + w - angleSize, y, angleSize, angleSize, null);
		g.drawImage(angle.getSprite(3), x, y + h - angleSize, angleSize, angleSize, null);
		g.drawImage(angle.getSprite(4), x + w - angleSize, y + h - angleSize, angleSize, angleSize, null);
		int reduction = (int) (vp.getPaintSize() / 2.5);
		x += reduction;
		y += reduction;
		w -= 2 * reduction;
		h -= 2 * reduction;
		if(ac == LsAction.Wizz) {
			x -= Math.abs(x-vp.getOffsetWindowX()+w)*act_progress*Math.exp(act_progress)/Math.exp(1);
			w -= 60*act_progress;
			h -= 60*act_progress;
			y += 30*act_progress;
		}
		g.drawImage(img, x, y, w, h, null);
	}

}
