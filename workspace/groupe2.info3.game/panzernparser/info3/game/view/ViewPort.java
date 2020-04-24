package info3.game.view;

import info3.game.model.Model;
import info3.game.model.entities.Entity;

public class ViewPort {
	
	static final int DRONE_VIEW = 10;
	static final int TANK_VIEW = 5;

	Model m_model;
	Entity m_player;

	public ViewPort(Model model, Entity player) {
		m_model = model;
		m_player = player;
	}
	
	public void setPlayer(Entity player) { ///pour quand on passe de done à tank et inversement
		m_player = player;
	}
	
	
	
	

}
