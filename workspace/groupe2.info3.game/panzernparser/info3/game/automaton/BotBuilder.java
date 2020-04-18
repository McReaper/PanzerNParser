package info3.game.automaton;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import info3.game.automata.ast.AST;
import info3.game.automata.ast.Action;
import info3.game.automata.ast.Automaton;
import info3.game.automata.ast.Behaviour;
import info3.game.automata.ast.BinaryOp;
import info3.game.automata.ast.Category;
import info3.game.automata.ast.Condition;
import info3.game.automata.ast.Direction;
import info3.game.automata.ast.FunCall;
import info3.game.automata.ast.IVisitor;
import info3.game.automata.ast.Key;
import info3.game.automata.ast.Mode;
import info3.game.automata.ast.State;
import info3.game.automata.ast.Transition;
import info3.game.automata.ast.UnaryOp;
import info3.game.automata.ast.Underscore;
import info3.game.automata.ast.Value;
//alias mAut = info3.game.myAutotmaton;

public class BotBuilder implements IVisitor {

	@Override
	public Object visit(Category cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Direction dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Value v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Underscore u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(FunCall funcall) {}

	@Override
	public Object exit(FunCall funcall, List<Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(BinaryOp operator, Object left, Object right) {
		info3.game.automaton.Condition biOp;
		switch (operator.operator){
			case "&":
				biOp = new info3.game.automaton.And((info3.game.automaton.Condition) left, (info3.game.automaton.Condition) right);
				break;
			case "/":
				biOp = new info3.game.automaton.Or((info3.game.automaton.Condition) left, (info3.game.automaton.Condition) right);
				break;
		}
		return biOp;
	}

	@Override
	public Object visit(UnaryOp operator, Object expression) {
		info3.game.automaton.Condition unOp;
		switch (operator.operator){
			case "!":
				unOp = new info3.game.automaton.Not((info3.game.automaton.Condition) expression);
				break;
		}
		return unOp;
	}

	@Override
	public Object visit(State state) {
		String name = state.name;
		info3.game.automaton.State mySt = new info3.game.automaton.State(name);
		return mySt;
	}

	@Override
	public void enter(Mode mode) {}

	@Override
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		info3.game.automaton.Mode myMode = new info3.game.automaton.Mode((info3.game.automaton.Behaviour)behaviour, (info3.game.automaton.State)source_state);
		return myMode;
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		List<info3.game.automaton.Transition> lsTr = new LinkedList<info3.game.automaton.Transition>();
		Iterator it = transitions.iterator();
		while (it.hasNext()) {
			lsTr.add((info3.game.automaton.Transition) it.next());
		}
		info3.game.automaton.Behaviour myBehaviour = new info3.game.automaton.Behaviour(lsTr);
		return myBehaviour;
	}

	@Override
	public void enter(Condition condition) {}

	@Override
	public Object exit(Condition condition, Object expression) {
		return expression;
	}

	@Override
	public void enter(Action acton) {}

	@Override
	public Object exit(Action action, List<Object> funcalls) {
		List<info3.game.automaton.FunCall> lsFC = new LinkedList<info3.game.automaton.FunCall>();
		Iterator it = funcalls.iterator();
		while (it.hasNext()) {
			lsFC.add((info3.game.automaton.FunCall) it.next());
		}
		info3.game.automaton.Action act = new info3.game.automaton.Action(lsFC);
		return act;
	}

	@Override
	public Object visit(Transition transition, Object condition, Object action, Object target_state) {
		info3.game.automaton.Transition tr = new info3.game.automaton.Transition((info3.game.automaton.Condition)condition, (info3.game.automaton.Action)action, (info3.game.automaton.State)target_state);
		return tr;
	}

	@Override
	public void enter(Automaton automaton) {
	}

	@Override
	public Object exit(Automaton automaton, Object initial_state, List<Object> modes) {
		List<info3.game.automaton.Mode> lsModes = new LinkedList<info3.game.automaton.Mode>();
		Iterator it = modes.iterator();
		while (it.hasNext()) {
			lsModes.add((info3.game.automaton.Mode) it.next());
		}
		String name = automaton.name;
		info3.game.automaton.Automaton auto = new info3.game.automaton.Automaton(name, lsModes,	(info3.game.automaton.State) initial_state);
		return auto;
	}

	@Override
	public Object visit(AST bot, List<Object> automata) {
		List<info3.game.automaton.Automaton> lsAuto = new LinkedList<info3.game.automaton.Automaton>();
		Iterator it = automata.iterator();
		while (it.hasNext()) {
			lsAuto.add((info3.game.automaton.Automaton) it.next());
		}
		return lsAuto;
	}

}
