package info3.game.myAutotmaton;

import java.util.LinkedList;
import java.util.List;
import info3.game.model.Enemy;
import info3.game.model.Entity;
import info3.game.myAutomaton.action.*;
import info3.game.myAutomaton.condition.*;


public class AutomatonMain {
	public static void main(String[] args) {
		Entity e = new Enemy();
		List<Mode> modes = new LinkedList<Mode>();
		List<Transition> transitions = new LinkedList<Transition>();
		List<FunCall> funCalls = new LinkedList<FunCall>();
		Condition c1 = new True();
		FunCall fc = new Egg(100, null);
		funCalls.add(fc);
		Action action = new Action(funCalls);
		State state = new State("init");
		Transition t1 = new Transition(c1, action, state);
		transitions.add(t1);
		Behaviour behaviour = new Behaviour(transitions);
		Mode mode1 = new Mode(behaviour, state);
		modes.add(mode1);
		Automaton aut = new Automaton("auto", (LinkedList<Mode>) modes, state);
		while(true) {
			aut.step(e);
		}
	}
}
