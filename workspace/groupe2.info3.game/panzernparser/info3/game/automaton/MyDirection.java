package info3.game.automaton;

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

		}

	}
}
