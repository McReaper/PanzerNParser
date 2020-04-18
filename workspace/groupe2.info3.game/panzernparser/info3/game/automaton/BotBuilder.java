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
import info3.game.automaton.action.Egg;
import info3.game.automaton.condition.Cell;
//alias mAut = info3.game.myAutotmaton;

public class BotBuilder implements IVisitor {

	@Override
	public Object visit(Category cat) {
		MyCategory myCat;
		switch (cat.terminal.content) {
			case "A":
				myCat = MyCategory.A;
				break;
			case "C":
				myCat = MyCategory.C;
				break;
			case "D":
				myCat = MyCategory.D;
				break;
			case "G":
				myCat = MyCategory.G;
				break;
			case "J":
				myCat = MyCategory.J;
				break;
			case "M":
				myCat = MyCategory.M;
				break;
			case "O":
				myCat = MyCategory.O;
				break;
			case "P":
				myCat = MyCategory.P;
				break;
			case "T":
				myCat = MyCategory.T;
				break;
			case "V":
				myCat = MyCategory.V;
				break;
			case "@":
				myCat = MyCategory.AT;
				break;
			case "_":
				myCat = MyCategory.US;
				break;
			default:
				myCat = MyCategory.V;
		}
		return myCat;
	}

	@Override
	public Object visit(Direction dir) {
		MyDirection myDir;
		switch (dir.terminal.content) {
			case "F":
				myDir = MyDirection.FRONT;
				break;
			//////////////////////////////////// TODO
			default:
				myDir = MyDirection.HERE;
		}
		return myDir;
	}

	@Override
	public Object visit(Key key) {
		LsKey myKey;
		switch (key.terminal.content) {
			case "A":
				myKey = LsKey.A;
				break;
			//////////////////////////////////// TODO
			case "0":
				myKey = LsKey.ZERO;
				break;
			//////////////////////////////////// TODO
			case "FU":
				myKey = LsKey.FU;
				break;
			//////////////////////////////////// TODO
			case "ENTER":
				myKey = LsKey.ENTER;
				break;
			case "SPACE":
				myKey = LsKey.SPACE;
				break;
			default:
				myKey = LsKey.ENTER;
		}
		return myKey;
	}

	@Override
	public Object visit(Value v) {
		Integer val = new Integer(v.value);
		return val;
	}

	@Override
	public Object visit(Underscore u) {
		MyCategory myCat = MyCategory.US;
		return myCat;
	}

	@Override
	public void enter(FunCall funcall) {
	}

	@Override
	public Object exit(FunCall funcall, List<Object> parameters) {
		Object function;
		int i = 0;
		Object param1;
		Object param2;
		Object param3;
		int paramSize = parameters.size();
		int percent = 100;
		switch (funcall.name) {
			// pour les conditions
			case "Cell":
				// paramètre par défaut
				 param1 = MyDirection.FRONT;
				 param2 = MyCategory.V;
				 param3 = 1;
				// paramètre entré
				if (i < paramSize && parameters.get(i) instanceof MyDirection) {
					param1 = (MyDirection) parameters.get(i);
					i++;
				}
				if (i < paramSize && parameters.get(i) instanceof MyCategory) {
					param2 = (MyCategory) parameters.get(i);
					i++;
				}
				if (i < paramSize && parameters.get(i) instanceof Integer) {
					param3 = (Integer) parameters.get(i);
					i++;
				}
				function = new Cell((MyDirection)param1, (MyCategory)param2, (int)param3);
				break;
				
				
			// pour les actions
			case "Egg":
			// paramètre par défaut
				param1 = MyDirection.FRONT;
				// paramètre entré
				if (i < paramSize && parameters.get(i) instanceof MyDirection) {
					param1 = (MyDirection) parameters.get(i);
					i++;
				}
				if (funcall.percent != -1) {
					percent = funcall.percent;
				}
				function = new Egg(percent, (MyDirection) param1);
				break;
		}
		return null;
	}

	@Override
	public Object visit(BinaryOp operator, Object left, Object right) {
		info3.game.automaton.Condition biOp = null;
		switch (operator.operator) {
			case "&":
				biOp = new info3.game.automaton.And((info3.game.automaton.Condition) left,
						(info3.game.automaton.Condition) right);
				break;
			case "/":
				biOp = new info3.game.automaton.Or((info3.game.automaton.Condition) left,
						(info3.game.automaton.Condition) right);
				break;
		}
		return biOp;
	}

	@Override
	public Object visit(UnaryOp operator, Object expression) {
		info3.game.automaton.Condition unOp = null;
		switch (operator.operator) {
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
	public void enter(Mode mode) {
	}

	@Override
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		info3.game.automaton.Mode myMode = new info3.game.automaton.Mode((info3.game.automaton.Behaviour) behaviour,
				(info3.game.automaton.State) source_state);
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
	public void enter(Condition condition) {
	}

	@Override
	public Object exit(Condition condition, Object expression) {
		return expression;
	}

	@Override
	public void enter(Action acton) {
	}

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
		info3.game.automaton.Transition tr = new info3.game.automaton.Transition((info3.game.automaton.Condition) condition,
				(info3.game.automaton.Action) action, (info3.game.automaton.State) target_state);
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
		info3.game.automaton.Automaton auto = new info3.game.automaton.Automaton(name, lsModes,
				(info3.game.automaton.State) initial_state);
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
