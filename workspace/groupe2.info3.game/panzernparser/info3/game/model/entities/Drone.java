package info3.game.model.entities;

public class Drone extends MovingEntity {

	public Drone(int x, int y, int width, int height, int health, double timetotravel) {
		super(x, y, width, height, health, timetotravel);
		// TODO Auto-generated constructor stub
	}

	private enum VisionType {
		RESSOURCES,
		ENEMIES;
	}
	
	VisionType m_visionType;
	
	@Override
	public void step(long elapsed) {
		// TODO Auto-generated method stub
		
	}
	
}
