package info3.game.automaton;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public enum MyDirection {
	NORTH, EAST, WEST, SOUTH, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST, LEFT, RIGHT, FRONT, BACK, HERE;

	public MyDirection toAbsolute(MyDirection currentDir, MyDirection actionDir) {
		switch (actionDir) {
			case FRONT:
				return currentDir;
			case LEFT:
				switch (currentDir) {
					case NORTH:
						return NORTHWEST;
					case EAST:
						return NORTHEAST;
					case SOUTH:
						return SOUTHEAST;
					case WEST:
						return SOUTHWEST;
					case NORTHEAST:
						return NORTH;
					case SOUTHEAST:
						return EAST;
					case SOUTHWEST:
						return SOUTH;
					case NORTHWEST:
						return WEST;
					default:
						break;
				}
				break;
			case LEFT:
				switch (m_dir) {
					case NORTH:
						m_x -= DEFAULT_MOVING_DISTANCE;
						break;
					case EAST:
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTH:
						m_x += DEFAULT_MOVING_DISTANCE;
						break;
					case WEST:
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case NORTHEAST:
						m_x -= DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTHEAST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					case SOUTHWEST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y += DEFAULT_MOVING_DISTANCE;
						break;
					case NORTHWEST:
						m_x += DEFAULT_MOVING_DISTANCE;
						m_y -= DEFAULT_MOVING_DISTANCE;
						break;
					default:
						break;
				}
				break;
			case RIGHT:
				switch (currentDir) {
					case NORTH:
						return NORTHEAST;
					case EAST:
						return SOUTHEAST;
					case SOUTH:
						return SOUTHWEST;
					case WEST:
						return NORTHWEST;
					case NORTHEAST:
						return EAST;
					case SOUTHEAST:
						return SOUTH;
					case SOUTHWEST:
						return WEST;
					case NORTHWEST:
						return NORTH;
				}
				break;
			case BACK:
				switch (currentDir) {
					case NORTH:
						return SOUTH;
					case EAST:
						return WEST;
					case SOUTH:
						return NORTH;
					case WEST:
						return EAST;
					case NORTHEAST:
						return SOUTHWEST;
					case SOUTHEAST:
						return NORTHWEST;
					case SOUTHWEST:
						return NORTHEAST;
					case NORTHWEST:
						return SOUTHEAST;
				}
				default:
					return actionDir;
		
	}

		}

	}
}
