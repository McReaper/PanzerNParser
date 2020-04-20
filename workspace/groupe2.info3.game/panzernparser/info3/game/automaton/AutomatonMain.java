package info3.game.automaton;

import java.util.LinkedList;
import java.util.List;

import info3.game.automaton.action.*;
import info3.game.automaton.condition.*;
import info3.game.model.entities.*;


public class AutomatonMain {
	/*
	 * Emilie : j'ai mis en commentaire le deuxieme automate car mes tests portent sur un seul automate
	 */
	
	/*
	 * automate testé ici :
	 * *(init)
	 * 		|true ? Move() :(droite)
	 * *(droite)
	 * 		|true and False ? Protect() : (init)
	 * 		|true ? Egg() : (gauche)
	 * *(gauche)
	 * 		|true or false ? Pop() : (init)
	 */
	public static void main(String[] args) {
		/*
		 * correspond à l'entité qui va appeler cet automate
		 */
		Entity e = new Enemy();

		/*
		 * création de la liste de modes (de l'automate)
		 */
		List<Mode> automate = new LinkedList<Mode>();
		
		/*
		 * création de toutes les transitions (ici une pour chaque state)
		 */
		List<Transition> transitionsInit= new LinkedList<Transition>();
		List<Transition> transitionsDroite = new LinkedList<Transition>();
		List<Transition> transitionsGauche = new LinkedList<Transition>();
		
		/*
		 * Création de la liste de Funcalls pour chaque action 
		 * autrement dit pour chaque transition
		 */
		List<FunCall> funCallsMove = new LinkedList<FunCall>();
		List<FunCall> funCallsEgg = new LinkedList<FunCall>();
		List<FunCall> funCallsPop = new LinkedList<FunCall>();
		List<FunCall> funCallsProtect = new LinkedList<FunCall>();
		
		/*
		 * Créations de tous les states de l'automate (ici il y en a 3)
		 */
		State stateInit = new State("init");
		State stateDroite = new State ("droite");
		State stateGauche = new State ("gauche");
		
		/*
		 * création de toutes les conditions qui vont être utilisés dans l'automate
		 * pour le moment on utilise que True, False et (True || False)
		 */
		Condition condTrue = new True();
		Condition condFalse = new False();
		Condition condTrueOrFalse = new Or(condTrue, condFalse);
		Condition condTrueAndFalse = new And(condTrue, condFalse);
		/*
		 * création de toutes les funCalls qui vont être utilisés dans l'automate
		 * pour le moment on utilise que 4 funcalls : Move, Egg, Pop et Protect
		 * 
		 * et ajout de ces funcalls dans les listes dédiés
		 */
		FunCall fcMove = new Move(100, null);
		funCallsMove.add(fcMove);
		
		FunCall fcPop = new Pop(100, null);
		funCallsPop.add(fcPop);
		
		FunCall fcEgg = new Egg(100, null);
		funCallsEgg.add(fcEgg);
		
		FunCall fcProtect = new Protect(100, null);
		funCallsProtect.add(fcProtect);
		
		/*
		 * création de toutes les actions formées avec les listes de funcalls
		 * pour le moment on utilise que 4 funcalls : Move, Egg, Protect et Pop
		 */
		Action actionMove = new Action(funCallsMove);
		Action actionEgg = new Action(funCallsEgg);
		Action actionPop = new Action(funCallsPop);
		Action actionProtect = new Action(funCallsProtect);
		
		/*
		 * Création des transitions en fonction des condition, action et state qu'il doit recevoir
		 */
		Transition TransTrueMoveDroite = new Transition(condTrue, actionMove, stateDroite);
		Transition TransTrueAndFalseProtectInit = new Transition(condTrueAndFalse,actionProtect, stateInit);
		Transition TransTrueEggGauche = new Transition(condTrue, actionEgg, stateGauche);
		Transition TransTrueOrFalsePopInit = new Transition(condTrueOrFalse , actionPop, stateInit);
		
		/*
		 * implémentation des listes de trnasitions en fonction des transitions qu'ils sont censé recevoir
		 */
		transitionsInit.add(TransTrueMoveDroite);
		
		transitionsDroite.add(TransTrueAndFalseProtectInit);
		transitionsDroite.add(TransTrueEggGauche);
		
		transitionsGauche.add(TransTrueOrFalsePopInit);
		
		/*
		 * implémentation des behavours en fonctions des listes de transition
		 */
		Behaviour behaviourInit = new Behaviour(transitionsInit);
		Behaviour behaviourDroite = new Behaviour(transitionsDroite);
		Behaviour behaviourGauche = new Behaviour(transitionsGauche);
		
		/*
		 * création des 3 modes : init, droite et gauche
		 */
		Mode mInit = new Mode(behaviourInit, stateInit);
		Mode mDroite = new Mode(behaviourDroite, stateDroite);
		Mode mGauche = new Mode(behaviourGauche, stateGauche);
		
		
		/*
		 * ajout des 3 modes à l'automate
		 */
		automate.add(mInit);
		automate.add(mDroite);
		automate.add(mGauche);		
		
		Automaton auto1 = new Automaton("auto", automate, e.getState());
		
		/*
		 * pour le test on doit donner un state initial a Entity
		 * je le fais ici pour ce test mais plus tard il faudra gérer ca dans Entity directement
		 */
		e.setState(stateInit);
		while(true) {
			State newStateAuto1 = auto1.step(e);
			if (newStateAuto1 != null)
				e.setState(newStateAuto1);
		}
	}
	
}
