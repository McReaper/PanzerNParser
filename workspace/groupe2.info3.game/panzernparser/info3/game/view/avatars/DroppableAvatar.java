package info3.game.view.avatars;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.MaterialType;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;
import info3.game.view.Animation;
import info3.game.model.entities.Droppable;

public class DroppableAvatar extends Avatar {

	public DroppableAvatar(Animation animation) {
		super(animation);
	}

	@Override
	public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		double progress = entity.getActionProgress();
		MaterialType mat = ((Droppable) entity).getMType();
		Image img;
		if (mat == MaterialType.ELECTRONIC) {
			img = m_animation.getImage(progress, LsAction.Wait, MyDirection.NORTH, vision);
		} else {
			img = m_animation.getImage(progress, LsAction.Wait, null, vision);
		}
		g.drawImage(img, xcase, ycase, case_width * entity.getWidth(), case_height * entity.getHeight(), null);

	}

}
