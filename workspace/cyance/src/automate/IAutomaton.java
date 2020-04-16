package automate;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAutomaton {
	IState current ;
	List<IBehaviour> behaviours ;
	
	IAutomaton(IState initial, List<IBehaviour> behaviours){
		this.current = initial ;
		this.behaviours = behaviours ;
	}
	
	boolean step(Entity e) {
		// - selectionne le comportement en fonction de l'état courant
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		Iterator<IBehaviour> it = behaviours.iterator();
		IBehaviour curr;
		while(it.hasNext()) {
			curr = it.next();
			if(curr.source == current) {
				current = curr.step(e);
				return true;
			}
		}
		return false ; // true si une transition effectuée, false si aucune transition possible (=?= mort de l'automate ?) 
	}
}
