package automate;


import java.util.ArrayList;
import java.util.List;


public class AutomateMain {
	public static void main(String[] args) {
		IState etat1 = new IState("st1");
		IState etat2 = new IState("st2");
		IAction move = new Move();
		IAction wait = new Wait();
		ICondition cond1 = new True();
		ICondition cond2 = new False();
		ITransition tr1 = new ITransition(cond2, wait, etat1); //// | False ? Wait : (st1)
		ITransition tr2 = new ITransition(cond1, move, etat2); //// | True ? Move : (st2)
		List<ITransition> liste1 = new ArrayList<ITransition>();
		liste1.add(tr1);
		liste1.add(tr2);
		ITransition tr3 = new ITransition(cond1, wait, etat1); //// | True ? Wait : (st1)
		List<ITransition> liste2 = new ArrayList<ITransition>();
		liste2.add(tr3);
		IBehaviour e1 = new IBehaviour(etat1, liste1);
		IBehaviour e2 = new IBehaviour(etat2, liste2);
		List<IBehaviour> listeB = new ArrayList<IBehaviour>();
		listeB.add(e1);
		listeB.add(e2);
		IAutomaton auto = new IAutomaton(etat1, listeB);
		Entity stuff = new Coucou();
		while(true) {
			auto.step(stuff);
		}
	}
	
}
