package automate;

public abstract class Entity {

	final static int OK = 0;
	final static int MOVE = 1;

	int m_state;
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
		m_state = OK;
	}

	public void hit() {
		if (m_exist) {
			hit = true;
		}
	}

	public void move(int dx, int dy) {
		switch (m_state) {
			case (OK):
				System.out.println("L'objet est immobile à" + m_x + ' ' + m_y);
				m_state = MOVE;
			case (MOVE):
				m_x += dx;
				m_y += dy;
				System.out.println("L'objet move à" + m_x + ' ' + m_y);
				break;
			default:
				throw new IllegalStateException();
		}
	}
}
