package info3.game.automaton;

public enum MyDirection {
	NORTH, EAST, WEST, SOUTH, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST, LEFT, RIGHT, FRONT, BACK, HERE;

	public static MyDirection toAbsolute(MyDirection currentDir, MyDirection actionDir) {
		if (actionDir == null)
			return null;
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
					default:
						break;
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
					default:
						break;
				}
			default:
				break;

		}
		return actionDir;

	}

	public static int toInt(MyDirection dir) {
		switch (dir) {
			case NORTH:
				return 0;
			case SOUTH:
				return 1;
			case EAST:
				return 2;
			case WEST:
				return 3;
			case SOUTHEAST:
				return 4;
			case SOUTHWEST:
				return 5;
			case NORTHEAST:
				return 6;
			case NORTHWEST:
				return 7;
			default:
				return -1;
		}
	}

}
