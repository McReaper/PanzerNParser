package info3.game.myAutotmaton;

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
		info3.game.myAutotmaton.Condition biOp;
		switch (operator.operator){
			case "&":
				biOp = new info3.game.myAutotmaton.And((info3.game.myAutotmaton.Condition) left, (info3.game.myAutotmaton.Condition) right);
				break;
			case "/":
				biOp = new info3.game.myAutotmaton.Or((info3.game.myAutotmaton.Condition) left, (info3.game.myAutotmaton.Condition) right);
				break;
		}
		return biOp;
	}

	@Override
	public Object visit(UnaryOp operator, Object expression) {
		info3.game.myAutotmaton.Condition unOp;
		switch (operator.operator){
			case "!":
				unOp = new info3.game.myAutotmaton.Not((info3.game.myAutotmaton.Condition) expression);
				break;
		}
		return unOp;
	}

	@Override
	public Object visit(State state) {
		String name = state.name;
		info3.game.myAutotmaton.State mySt = new info3.game.myAutotmaton.State(name);
		return mySt;
	}

	@Override
	public void enter(Mode mode) {}

	@Override
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		info3.game.myAutotmaton.Mode myMode = new info3.game.myAutotmaton.Mode((info3.game.myAutotmaton.Behaviour)behaviour, (info3.game.myAutotmaton.State)source_state);
		return myMode;
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		List<info3.game.myAutotmaton.Transition> lsTr = new LinkedList<info3.game.myAutotmaton.Transition>();
		Iterator it = transitions.iterator();
		while (it.hasNext()) {
			lsTr.add((info3.game.myAutotmaton.Transition) it.next());
		}
		info3.game.myAutotmaton.Behaviour myBehaviour = new info3.game.myAutotmaton.Behaviour(lsTr);
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
		List<info3.game.myAutotmaton.FunCall> lsFC = new LinkedList<info3.game.myAutotmaton.FunCall>();
		Iterator it = funcalls.iterator();
		while (it.hasNext()) {
			lsFC.add((info3.game.myAutotmaton.FunCall) it.next());
		}
		info3.game.myAutotmaton.Action act = new info3.game.myAutotmaton.Action(lsFC);
		return act;
	}

	@Override
	public Object visit(Transition transition, Object condition, Object action, Object target_state) {
		info3.game.myAutotmaton.Transition tr = new info3.game.myAutotmaton.Transition((info3.game.myAutotmaton.Condition)condition, (info3.game.myAutotmaton.Action)action, (info3.game.myAutotmaton.State)target_state);
		return tr;
	}

	@Override
	public void enter(Automaton automaton) {
	}

	@Override
	public Object exit(Automaton automaton, Object initial_state, List<Object> modes) {
		List<info3.game.myAutotmaton.Mode> lsModes = new LinkedList<info3.game.myAutotmaton.Mode>();
		Iterator it = modes.iterator();
		while (it.hasNext()) {
			lsModes.add((info3.game.myAutotmaton.Mode) it.next());
		}
		String name = automaton.name;
		info3.game.myAutotmaton.Automaton auto = new info3.game.myAutotmaton.Automaton(name, lsModes,	(info3.game.myAutotmaton.State) initial_state);
		return auto;
	}

	@Override
	public Object visit(AST bot, List<Object> automata) {
		List<info3.game.myAutotmaton.Automaton> lsAuto = new LinkedList<info3.game.myAutotmaton.Automaton>();
		Iterator it = automata.iterator();
		while (it.hasNext()) {
			lsAuto.add((info3.game.myAutotmaton.Automaton) it.next());
		}
		return lsAuto;
	}

}
