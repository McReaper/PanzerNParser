package automate;

public class Move extends IAction{
	
	public Move() {
	}
	
	
	@Override
	void exec(Entity e) {
		e.move();
	}

}
