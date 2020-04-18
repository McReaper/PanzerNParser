package info3.game.automaton;

import java.util.LinkedList;
import java.util.List;

import info3.game.automaton.action.*;
import info3.game.automaton.condition.*;
import info3.game.model.entities.*;


public class AutomatonMain {
	
	public static void main(String[] args) {
		Entity e = new Enemy();
		List<Mode> modesAuto1 = new LinkedList<Mode>();
		List<Mode> modesAuto2 = new LinkedList<Mode>();
		List<Transition> transitionsAuto1 = new LinkedList<Transition>();
		List<Transition> transitionsAuto2 = new LinkedList<Transition>();
		List<FunCall> funCallsAuto1 = new LinkedList<FunCall>();
		List<FunCall> funCallsAuto2 = new LinkedList<FunCall>();
		State state = new State("init");
		
		initFunCallsAuto1(funCallsAuto1);
		initFunCallsAuto2(funCallsAuto2);
		initTransition(transitionsAuto1, state, funCallsAuto1);
		initTransition(transitionsAuto2, state, funCallsAuto2);
		
		Behaviour behaviourAuto1 = new Behaviour(transitionsAuto1);
		Behaviour behaviourAuto2 = new Behaviour(transitionsAuto2);
		
		initMode(modesAuto1, behaviourAuto1, state);
		initMode(modesAuto2, behaviourAuto2, state);
		
		Automaton auto1 = new Automaton("auto_1", (LinkedList<Mode>) modesAuto1, state);
		Automaton auto2 = new Automaton("auto_2", (LinkedList<Mode>) modesAuto2, state);
		
		while(true) {
			auto1.step(e);
			auto2.step(e);
		}
	}
	
	private static void initFunCallsAuto1(List<FunCall> funCalls) {
		FunCall fc = new Egg(100, null);
		funCalls.add(fc);
	}
	
	private static void initFunCallsAuto2(List<FunCall> funCalls) {
		FunCall fc = new Explode(100);
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
