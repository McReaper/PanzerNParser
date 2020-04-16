package automate;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	IState source;
	List<ITransition> transitions;

	IBehaviour(IState source, List<ITransition> transitions) {
		this.source = source;
		this.transitions = transitions;
	}

	IState step(Entity e) {
	 Iterator<ITransition> it = transitions.iterator();
	 ITransition curr;
	 while(it.hasNext()) {
		 curr = it.next();
		 if(curr.feasible(e)) {
			 return curr.exec(e);
		 }
	 }
	 return source;
	}
}
