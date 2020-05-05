package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;

public class DroneAvatar extends Avatar {

	private View m_view;
	private Sprite angle;

	public DroneAvatar(Animation animation, View view) {
		super(animation);
		m_view = view;
		try {
			angle = new Sprite("sprites/angle.png");
		} catch (IOException e) {
			System.out.println("pas d'angle dans la vu du drone");
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		MyDirection dir = entity.getLookAtDir();
		long time = Model.getModel().getTime();
		time = time % 200;
		int progress;
		if (time > 100) {
			progress = 1;
		} else {
			progress = 0;
		}
		Image img = m_animation.getImage(progress, LsAction.Move, dir, vision);

		ViewPort vp = m_view.m_viewPort;
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
		if (ExplosionAvatar.printEntity(entity)) {
			g.drawImage(img, x, y, w, h, null);
		}
		ExplosionAvatar.exploding(g, entity, x, y, w, h);
	}

}
