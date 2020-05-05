package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;

public class VeinAvatar extends Avatar {

	public VeinAvatar(Animation animation) {
		super(animation);
	}
	
	@Override
	public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		Image img = m_animation.getImage(0, LsAction.Wait, null, vision);
		if (ExplosionAvatar.printEntity(entity)) {
			g.drawImage(img, xcase, ycase, case_width*entity.getWidth(), case_height*entity.getHeight(), null);
		}
		ExplosionAvatar.exploding(g, entity, xcase, ycase, case_width*entity.getWidth(), case_height*entity.getHeight());
	}

}
