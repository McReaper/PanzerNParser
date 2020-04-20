package info3.game.automaton;

import java.util.List;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;

public class TestBotBuilder {

	public static void main(String[] args) throws Exception {

			AST myAST = AutomataParser.from_file("testBotBuilder.gal");
					List<Automaton> lsAuto = (List<Automaton>) myAST.accept(new BotBuilder());
			System.out.println("c'est passé");
	
	}

}
