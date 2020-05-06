package info3.game.model.entities;

import info3.game.GameConfiguration;
import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.model.MaterialType;
import info3.game.model.Model;

public class EntityFactory {
	public enum MyEntities {
		Wall, Rock, EnemyBasic, EnemyLevel2, Droppable, ShotSlow, ShotFast, ShotBig, Vein, Drone, Marker, TankBody, Turret,
		Hole, WreckTank, Mud;
	}

	public static Entity newTankBody(int x, int y, Automaton aut) {
		return new TankBody(x, y, aut);
	}

	public static Entity newTankTurret(int x, int y, Automaton aut) {
		return new Turret(x, y, aut);
	}

	public static Entity newDroppable(int x, int y, Automaton aut) {
		Entity drop = new Droppable(x, y, MaterialType.ELECTRONIC, aut);
		return drop;
	}

	public static Entity newVein(int x, int y, Automaton aut) {
		Entity vein = new Vein(x, y, aut);
		return vein;
	}

	public static Entity newMud(int x, int y, Automaton aut) {
		Entity mud = new Mud(x, y, aut);
		return mud;
	}

	public static Entity newWall(int x, int y, Automaton aut) {
		Entity wall = new Wall(x, y, aut);
		return wall;
	}

	public static Entity newRock(int x, int y, Automaton aut) {
		Entity rock = new Rock(x, y, aut);
		return rock;
	}

	public static Entity newWreckTank(int x, int y, Automaton aut) {
		Entity wreck = new WreckTank(x, y, aut);
		return wreck;
	}

	public static Entity newHole(int x, int y, Automaton aut) {
		Entity hole = new Hole(x, y, aut);
		return hole;
	}

	private static Entity newDrone(int x, int y, Automaton aut) {
		Entity drone = new Drone(x, y, aut);
		return drone;
	}

	public static Entity newMarker(int x, int y, Automaton aut) {
		Entity marker = new Marker(x, y, aut);
		return marker;
	}

	////////////////// Creation des différents type de shot///////////////
	public static Entity newShotSlow(int x, int y, Automaton aut) {
		Entity shot = new ShotSlow(x, y, aut);
		return shot;
	}

	public static Entity newShotFast(int x, int y, Automaton aut) {
		Entity shot = new ShotFast(x, y, aut);
		return shot;
	}

	public static Entity newShotBig(int x, int y, Automaton aut) {
		Entity shot = new ShotBig(x, y, aut);
		return shot;
	}

//////////////////Creation des différents type d'Enemy///////////////
	public static Entity newEnemyBasic(int x, int y, Automaton aut) {
		Entity enemyB = new EnemyBasic(x, y, aut);
		return enemyB;
	}

	public static Entity newEnemyLevel2(int x, int y, Automaton aut) {
		Entity enemyL2 = new EnemyLevel2(x, y, aut);
		return enemyL2;
	}
	//////////////////////////////////////////////////////////////////////

	public static Entity newEntity(MyEntities entity, int x, int y) {
		Entity res;
		GameConfiguration config = GameConfiguration.getConfig();
		switch (entity) {
			case Wall:
				res = newWall(x, y, config.getAutomaton(MyEntities.Wall));
				break;
			case Rock:
				res = newRock(x, y, config.getAutomaton(MyEntities.Rock));
				break;
			case EnemyBasic:
				res = newEnemyBasic(x, y, config.getAutomaton(MyEntities.EnemyBasic));
				break;
			case EnemyLevel2:
				res = newEnemyLevel2(x, y, config.getAutomaton(MyEntities.EnemyLevel2));
				break;
			case Droppable:
				res = newDroppable(x, y, config.getAutomaton(MyEntities.Droppable));
				break;
			case Vein:
				res = newVein(x, y, config.getAutomaton(MyEntities.Vein));
				break;
			case WreckTank:
				res = newWreckTank(x, y, config.getAutomaton(MyEntities.WreckTank));
				break;
			case ShotSlow:
				res = newShotSlow(x, y, config.getAutomaton(MyEntities.ShotSlow));
				break;
			case ShotFast:
				res = newShotFast(x, y, config.getAutomaton(MyEntities.ShotFast));
				break;
			case ShotBig:
				res = newShotBig(x, y, config.getAutomaton(MyEntities.ShotBig));
				break;
			case TankBody:
				res = newTankBody(x, y, config.getAutomaton(MyEntities.TankBody));
				break;
			case Turret:
				res = newTankTurret(x, y, config.getAutomaton(MyEntities.Turret));
				break;
			case Drone:
				res = newDrone(x, y, config.getAutomaton(MyEntities.Drone));
				break;
			case Marker:
				res = newMarker(x, y, config.getAutomaton(MyEntities.Droppable));
				break;
			case Hole:
				res = newHole(x, y, config.getAutomaton(MyEntities.Hole));
				break;
			case Mud:
				res = newMud(x, y, config.getAutomaton(MyEntities.Mud));
				break;
			default:
				throw new IllegalStateException("Entité non reconnue !");
		}
		Model.getModel().addEntity(res);
		return res;
	}

	public static String name(Entity entity) {
		return entity.getClass().getName();
	}

	public static MyEntities getMyEntities(Entity e) {
		if (e instanceof Droppable) {
			return MyEntities.Droppable;
		} else if (e instanceof Drone) {
			return MyEntities.Drone;
		} else if (e instanceof EnemyBasic) {
			return MyEntities.EnemyBasic;
		} else if (e instanceof EnemyLevel2) {
			return MyEntities.EnemyLevel2;
		} else if (e instanceof Vein) {
			return MyEntities.Vein;
		} else if (e instanceof Wall) {
			return MyEntities.Wall;
		} else if (e instanceof Rock) {
			return MyEntities.Rock;
		} else if (e instanceof Mud) {
			return MyEntities.Mud;
		} else if (e instanceof Hole) {
			return MyEntities.Hole;
		} else if (e instanceof Marker) {
			return MyEntities.Marker;
		} else if (e instanceof ShotSlow) {
			return MyEntities.ShotSlow;
		} else if (e instanceof ShotFast) {
			return MyEntities.ShotFast;
		} else if (e instanceof ShotBig) {
			return MyEntities.ShotBig;
		} else if (e instanceof TankBody) {
			return MyEntities.TankBody;
		} else if (e instanceof Turret) {
			return MyEntities.Turret;
		} else if (e instanceof WreckTank) {
			return MyEntities.WreckTank;
		} else {
			throw new IllegalArgumentException("Entité non reconnue !");
		}
	}

}
