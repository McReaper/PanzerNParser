package info3.game.myAutotmaton;

import java.util.LinkedList;
import java.util.List;
import info3.game.model.Enemy;
import info3.game.model.Entity;


public class AutomatonMain {
	
	public static void main(String[] args) {
		Entity e = new Enemy();
		List<Mode> modes = new LinkedList<Mode>();
		List<Transition> transitions = new LinkedList<Transition>();
		List<FunCall> funCalls = new LinkedList<FunCall>();
		State state = new State("init");
		
		initFunCalls(funCalls);
		initTransition(transitions, state, funCalls);
		
		Behaviour behaviour = new Behaviour(transitions);
		
		initMode(modes, behaviour, state);
		
		Automaton aut = new Automaton("auto", (LinkedList<Mode>) modes, state);
		while(true) {
			aut.step(e);
		}
	}
	
	private static void initFunCalls(List<FunCall> funCalls) {
		FunCall fc = new Egg(100);
		funCalls.add(fc);
	}
	
	private static void initTransition(List<Transition> transitions, State init, List<FunCall> funCalls) {
		Condition c1 = new True();
		Action action = new Action(funCalls);
		Transition t1 = new Transition(c1, action, init);
		transitions.add(t1);
	}
	
	private static void initMode(List<Mode> modes, Behaviour behaviour, State init) {
		Mode mode1 = new Mode(behaviour, init);
		modes.add(mode1);
	}
}
