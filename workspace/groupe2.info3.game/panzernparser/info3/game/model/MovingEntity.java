package info3.game.model;

public abstract class MovingEntity extends Entity {
	int m_health;
	// ORIENTATION m_orientation;
	double m_speed;
	// m_viewPort;

	abstract void move();
}
