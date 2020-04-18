package info3.game.myAutotmaton;

import java.util.List;
import java.util.ListIterator;

import info3.game.model.Entity;

public class Action {
	List<FunCall> m_funCalls;
	
	Action(List<FunCall> funCalls){
		m_funCalls = funCalls;
	}
	
	boolean execute(Entity e) {
		ListIterator<FunCall> iter = m_funCalls.listIterator();
		FunCall current;
		while (iter.hasNext()) {
			current = (FunCall) iter.next();
			current.execut(e);
			return true;
		}
		return false;
	}
}
