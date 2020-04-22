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
import info3.game.automaton.action.Explode;
import info3.game.automaton.action.Get;
import info3.game.automaton.action.Hit;
import info3.game.automaton.action.Jump;
import info3.game.automaton.action.Move;
import info3.game.automaton.action.Pick;
import info3.game.automaton.action.Pop;
import info3.game.automaton.action.Power;
import info3.game.automaton.action.Protect;
import info3.game.automaton.action.Store;
import info3.game.automaton.action.Throw;
import info3.game.automaton.action.Turn;
import info3.game.automaton.action.Wait;
import info3.game.automaton.action.Wizz;
//alias mAut = info3.game.myAutotmaton;
import info3.game.automaton.condition.Cell;
import info3.game.automaton.condition.Closest;
import info3.game.automaton.condition.GotPower;
import info3.game.automaton.condition.GotStuff;
import info3.game.automaton.condition.MyDir;
import info3.game.automaton.condition.True;

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
			case "B":
				myDir = MyDirection.BACK;
				break;
			case "L":
				myDir = MyDirection.LEFT;
				break;
			case "R":
				myDir = MyDirection.RIGHT;
				break;
			case "N":
				myDir = MyDirection.NORTH;
				break;
			case "NE":
				myDir = MyDirection.NORTHEAST;
				break;
			case "E":
				myDir = MyDirection.EAST;
				break;
			case "SE":
				myDir = MyDirection.SOUTHEAST;
				break;
			case "S":
				myDir = MyDirection.SOUTH;
				break;
			case "SW":
				myDir = MyDirection.SOUTHWEST;
				break;
			case "W":
				myDir = MyDirection.WEST;
				break;
			case "NW":
				myDir = MyDirection.NORTHWEST;
				break;
			default:
				myDir = null;
		}
		return myDir;
	}

	@Override
	public Object visit(Key key) {
		LsKey myKey;
		switch (key.terminal.content) {
			// lettres
			case "a":
				myKey = LsKey.A;
				break;
			case "z":
				myKey = LsKey.Z;
				break;
			case "e":
				myKey = LsKey.E;
				break;
			case "r":
				myKey = LsKey.R;
				break;
			case "t":
				myKey = LsKey.T;
				break;
			case "y":
				myKey = LsKey.Y;
				break;
			case "u":
				myKey = LsKey.U;
				break;
			case "i":
				myKey = LsKey.I;
				break;
			case "o":
				myKey = LsKey.O;
				break;
			case "p":
				myKey = LsKey.P;
				break;
			case "q":
				myKey = LsKey.Q;
				break;
			case "s":
				myKey = LsKey.S;
				break;
			case "d":
				myKey = LsKey.D;
				break;
			case "f":
				myKey = LsKey.F;
				break;
			case "g":
				myKey = LsKey.G;
				break;
			case "h":
				myKey = LsKey.H;
				break;
			case "j":
				myKey = LsKey.J;
				break;
			case "k":
				myKey = LsKey.K;
				break;
			case "l":
				myKey = LsKey.L;
				break;
			case "m":
				myKey = LsKey.M;
				break;
			case "w":
				myKey = LsKey.W;
				break;
			case "x":
				myKey = LsKey.X;
				break;
			case "c":
				myKey = LsKey.C;
				break;
			case "v":
				myKey = LsKey.V;
				break;
			case "b":
				myKey = LsKey.B;
				break;
			case "n":
				myKey = LsKey.N;
				break;
			// nombres
			case "0":
				myKey = LsKey.ZERO;
				break;
			case "1":
				myKey = LsKey.ONE;
				break;
			case "2":
				myKey = LsKey.TWO;
				break;
			case "3":
				myKey = LsKey.THREE;
				break;
			case "4":
				myKey = LsKey.FOUR;
				break;
			case "5":
				myKey = LsKey.FIVE;
				break;
			case "6":
				myKey = LsKey.SIX;
				break;
			case "7":
				myKey = LsKey.SEVEN;
				break;
			case "8":
				myKey = LsKey.EIGHT;
				break;
			case "9":
				myKey = LsKey.NINE;
				break;
			// special
			case "FU":
				myKey = LsKey.AU;
				break;
			case "FD":
				myKey = LsKey.AD;
				break;
			case "FR":
				myKey = LsKey.AR;
				break;
			case "FL":
				myKey = LsKey.AL;
				break;
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
		Object function = null;
		int i = 0;
		// paramètre par défaut
		MyDirection param1 = null;
		MyCategory param2 = MyCategory.V;
		int param3 = -1;
		LsKey paramKey = null;
		int paramSize = parameters.size();
		int percent = funcall.percent;
		// paramètre entré
		if (i < paramSize && parameters.get(i) instanceof LsKey) {
			paramKey = (LsKey) parameters.get(i);
		} else {
			if (i < paramSize && parameters.get(i) instanceof MyDirection) {
				param1 = (MyDirection) parameters.get(i);
				i++;
			}
			if (i < paramSize && parameters.get(i) instanceof MyCategory) {
				param2 = (MyCategory) parameters.get(i);
				i++;
			}
			if (i < paramSize && parameters.get(i) instanceof MyDirection) { // pour le closest où les param sont à l'envert
				param1 = (MyDirection) parameters.get(i);
				i++;
			}
			if (i < paramSize && parameters.get(i) instanceof Integer) {
				param3 = (Integer) parameters.get(i);
				i++;
			}
		}
		switch (funcall.name) {
			// poutr les condition
			case "Cell":
				function = new Cell(param1, param2, param3);
				break;
			case "Closest":
				function = new Closest(param2, param1);
				break;
			case "GotPower":
				function = new GotPower();
				break;
			case "GotStuff":
				function = new GotStuff();
				break;
			case "Key":
				function = new info3.game.automaton.condition.Key(paramKey);
				break;
			case "MyDir":
				function = new MyDir(param1);
				break;
			case "True":
				function = new True();
				break;
			// pour les action
			case "Egg":
				function = new Egg(percent, param1);
				break;
			case "Explode":
				function = new Explode(percent);
				break;
			case "Get":
				function = new Get(percent, param1);
				break;
			case "Hit":
				function = new Hit(percent, param1);
				break;
			case "Jump":
				function = new Jump(percent, param1);
				break;
			case "Move":
				function = new Move(percent, param1);
				break;
			case "Pick":
				function = new Pick(percent, param1);
				break;
			case "Pop":
				function = new Pop(percent, param1);
				break;
			case "Power":
				function = new Power(percent);
				break;
			case "Protect":
				function = new Protect(percent, param1);
				break;
			case "Store":
				function = new Store(percent, param1);
				break;
			case "Throw":
				function = new Throw(percent, param1);
				break;
			case "Turn":
				if (param3 == -1) {
					function = new Turn(percent, param1);
				} else {
					function = new Turn(percent, param3);
				}
				break;
			case "Wait":
				function = new Wait(percent);
				break;
			case "Wizz":
				function = new Wizz(percent, param1);
				break;
		}
		return function;
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
		@SuppressWarnings("rawtypes")
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
		@SuppressWarnings("rawtypes")
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
		@SuppressWarnings("rawtypes")
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
		@SuppressWarnings("rawtypes")
		Iterator it = automata.iterator();
		while (it.hasNext()) {
			lsAuto.add((info3.game.automaton.Automaton) it.next());
		}
		return lsAuto;
	}

}
