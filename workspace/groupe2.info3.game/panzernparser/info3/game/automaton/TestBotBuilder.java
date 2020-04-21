package info3.game.automaton;

import java.util.List;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import info3.game.model.entities.Enemy;
import info3.game.model.entities.Entity;

public class TestBotBuilder {

	public static void main(String[] args) throws Exception {

			AST myAST = AutomataParser.from_file("testBotBuilder.gal");
					List<Automaton> lsAuto = (List<Automaton>) myAST.accept(new BotBuilder());
			System.out.println("c'est passé");
			Entity e = new Enemy(0, 0, 0, 0);
			e.setState(lsAuto.get(0).m_state);
			while(true) {
				State newStateAuto1 = lsAuto.get(0).step(e);
				if (newStateAuto1 != null)
					e.setState(newStateAuto1);
			}
	
	}

}
