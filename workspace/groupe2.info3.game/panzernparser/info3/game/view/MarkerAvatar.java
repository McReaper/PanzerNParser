package info3.game.view;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;

public class MarkerAvatar extends Avatar {

	public MarkerAvatar(Animation animation) {
		super(animation);
	}

	@Override
	public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
		VisionType vision = Model.getModel().getVisionType();
		MyDirection e_actionDir = null;
		LsAction e_currAction = LsAction.Wait;
		double progress = entity.getActionProgress();

		int width = entity.getWidth() * case_width;
		int height = entity.getHeight() * case_height;
		int x = xcase; // deux seul changement
		int y = ycase;

		// Pour réaliser un affichage progressif dans le cas d'un move.

		Image sprite = m_animation.getImage(progress, e_currAction, e_actionDir, vision);

		g.drawImage(sprite, x, y, width, height, null);
	}
}
