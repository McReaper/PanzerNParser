package info3.game.view.avatar;

import java.awt.Graphics;
import java.awt.Image;

import info3.game.automaton.action.LsAction;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Entity;
import info3.game.view.Animation;

public class HoleAvatar extends Avatar {

		public HoleAvatar(Animation animation) {
			super(animation);
		}
		
		@Override
		public void paint(Graphics g, Entity entity, int xcase, int ycase, int case_width, int case_height) {
			VisionType vision = Model.getModel().getVisionType();
			LsAction ac = entity.getCurrentAction();
			Image img;
			if(ac == LsAction.Pop) {
				double progress = entity.getActionProgress();
				img = m_animation.getImage(progress, LsAction.Pop, null, vision);		
			} else {
				img = m_animation.getImage(4, LsAction.Wait, null, vision);			
			}
			int w = case_width*entity.getWidth();
			int h = case_height*entity.getHeight();
			g.drawImage(img, xcase, ycase, w, h, null);
		}

}
