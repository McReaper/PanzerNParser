package info3.game.myAutotmaton;

import info3.game.model.Entity;

public class Action {
	List<FunCall> m_funCalls;
	
	Action(List<FunCall> funCalls){
		m_funCalls = funCalls;
	}
	
	void execute(Entity e) {
		
	}
}
