package info3.game.automaton;

public enum MyCategory {
	A/* Adversaire */, C/* Clue */, D/* Danger */, G/* Gate */, J/* Jumpable */, M/* Missile */, O/* Obstacle */,
	P/* Pickable */, T/* Team */, V/* Void */, AT/* Le joueur */, US/* N'importe quelle entité */;
	
	public static int toInt(MyCategory cat) {
		switch(cat) {
			case A:
				return 0;
			case AT:
				return 1;
			case C:
				return 2;
			case D:
				return 3;
			case G:
				return 4;
			case J:
				return 5;
			case M:
				return 6;
			case O:
				return 7;
			case P:
				return 8;
			case T:
				return 9;
			case US:
				return 10;
			case V:
				return 11;
			default:
				return -1;
			
		}
	}

}
