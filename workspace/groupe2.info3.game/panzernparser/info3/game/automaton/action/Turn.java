package info3.game.automaton.action;

import info3.game.automaton.Direction;
import info3.game.automaton.FunCall;
import info3.game.model.entities.*;

public class Turn extends FunCall{

	Direction m_direction;
	int m_angle;
	public Turn(int percent, Direction dir) {
		super(percent);
		m_direction = dir;
		// TODO Auto-generated constructor stub
	}
	
	public Turn(int percent, int angle) {
		super(percent);
		m_angle = angle;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execut(Entity e) {
		// TODO Auto-generated method stub
		
	}

}