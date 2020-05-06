package info3.game.view.avatars;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;
import info3.game.view.Animation;

public class RockAvatar extends Avatar {

	public RockAvatar(Animation animation) {
		super(animation);
	}

	@Override
	public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		Image img = m_animation.getImage(entity.getActionProgress(), LsAction.Wait, null, vision);
		g.drawImage(img, xcase, ycase, case_width*entity.getWidth(), case_height*entity.getHeight(), null);
	}
	
}
