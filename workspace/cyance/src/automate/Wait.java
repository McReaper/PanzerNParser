package automate;

public class Wait extends IAction{
	
	public Wait() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	void exec(Entity e){
		e.waite();
	}

}
