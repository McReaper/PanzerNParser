package info3.game.view.avatars;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;
import info3.game.view.Animation;

public class WreckTankAvatar extends Avatar {

	public WreckTankAvatar(Animation animation) {
		super(animation);
	}

	@Override
	public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		double progress = entity.getActionProgress();
		MyDirection dir = entity.getLookAtDir();
		Image img;
		if (ExplosionAvatar.printEntity(entity)) {
	 		img = m_animation.getImage(progress, LsAction.Wait, dir, vision);
			g.drawImage(img, xcase, ycase, case_width*entity.getWidth(), case_height*entity.getHeight(), null);
		}else {
			dir = MyDirection.toAbsolute(dir, MyDirection.BACK);
			img = m_animation.getImage(progress, LsAction.Wait, dir, vision);
			g.drawImage(img, xcase, ycase, case_width*entity.getWidth(), case_height*entity.getHeight(), null);
			ExplosionAvatar.exploding(g, entity, xcase, ycase, case_width*entity.getWidth(), case_height*entity.getHeight());
		}
	}
	
	
}
