package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;

public class DroneAvatar extends Avatar {

	public DroneAvatar(Animation animation) {
		super(animation);
	}

	@Override
	public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		MyDirection dir = entity.getLookAtDir();
		LsAction e_currAction = entity.getCurrentAction();
		double acProgress = entity.getActionProgress();
		long time = Model.getModel().getTime();
		time = time%200;
		int progress;
		if(time > 100) {
			progress = 1;
		} else {
			progress = 0;
		}
		int x = xcase;
		int y = ycase;

		// Pour réaliser un affichage progressif dans le cas d'un move.
		if (e_currAction == LsAction.Move) {
			x = progressivePaintX(dir, x, acProgress, case_width);
			y = progressivePaintY(dir, y, acProgress, case_height);
		}

		Image img = m_animation.getImage(progress, LsAction.Move, dir, vision);
		g.drawImage(img, x, y, case_width*entity.getWidth(), case_height*entity.getHeight(), null);
	}
	
}
