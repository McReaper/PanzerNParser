package info3.game.myAutomaton.action;

import info3.game.model.Entity;
import info3.game.myAutotmaton.Direction;
import info3.game.myAutotmaton.FunCall;

public class Wizz extends FunCall{

	Direction m_direction;
	int m_angle;
	public Wizz(int percent, Direction dir) {
		super(percent);
		m_direction = dir;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execut(Entity e) {
		// TODO Auto-generated method stub
		
	}

}