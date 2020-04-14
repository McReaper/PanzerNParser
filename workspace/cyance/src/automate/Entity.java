package automate;

public abstract class Entity {
	int m_x;
	int m_y;
	int m_width;
	int m_height;
	boolean m_exist;
	boolean hit;
	
	public Entity() {
		m_x = 0;
		m_y = 0;
		m_width = 0;
		m_height = 0;
		m_exist = false;
	}

	public void hit() {
		if (m_exist) {
			hit = true;
		}
	}
}
