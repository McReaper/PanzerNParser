package automate;

public class False extends ICondition{
	
	@Override
	boolean eval(Entity e) {
		return false;
	}

}
