package info3.game.model.entities;

import info3.game.GameConfiguration;
import info3.game.automaton.Automaton;
import info3.game.model.MaterialType;

public class EntityFactory {
	public enum MyEntities {
		Wall, Ground, Enemy, Droppable, Shot, Vein, Drone, Marker, TankBody, Turret;
	}

	public static Entity newTankBody(int x, int y, Automaton aut) {
		Entity tankBody = new TankBody(x, y, TankBody.TANKBODY_WIDTH, TankBody.TANKBODY_HEIGHT, aut);
		return tankBody;
	}

	public static Entity newTankTurret(int x, int y, Automaton aut) {
		Entity turret = new Turret(x, y, Turret.TURRET_WIDTH, Turret.TURRET_HEIGHT, Turret.TURRET_HEALTH,
				Turret.TURRET_SPEED, aut);
		return turret;
	}

	public static Entity newDrone() {
		return null;
	}

	public static Entity newMarker() {
		return null;
	}

	public static Entity newShot(int x, int y, Automaton aut) {
		Entity shot = new Shot(x, y, Shot.SHOT_WIDTH, Shot.SHOT_HEIGHT, 10, 10, aut);
		return shot;
		/* par defaut health et time_to_travel = 10 mais a voir où gerer ça */
	}

	public static Entity newEnemy(int x, int y, Automaton aut) {
		Entity enemy = new Enemy(x, y, Enemy.ENEMY_WIDTH, Enemy.ENEMY_HEIGHT, aut);
		return enemy;
	}

	public static Entity newDroppable(int x, int y, Automaton aut) {
		Entity drop = new Droppable(x, y, Droppable.DROPPABLE_WIDTH, Droppable.DROPPABLE_HEIGHT, 1, MaterialType.ELECTRONIC,
				aut);
		return drop;
		// 1 en quantité mais à discuter
	}

	public static Entity newVein(int x, int y, Automaton aut) {
		Entity vein = new Vein(x, y, Vein.VEIN_WIDTH, Vein.VEIN_HEIGHT, aut);
		return vein;
	}

	public static Entity newGround(int x, int y, Automaton aut) {
		Entity ground = new Ground(x, y, Ground.GROUND_WIDTH, Ground.GROUND_HEIGHT, aut);
		return ground;
	}

	private static Entity newDrone(int x, int y, Automaton automaton) {
		Entity drone = new Drone(x, y, Drone.DRONE_WIDTH, Drone.DRONE_HEIGHT, Drone.DRONE_HEALTH, Drone.DRONE_SPEED,
				automaton);
		return drone;
	}

	public static Entity newEntity(MyEntities entity, int x, int y) {
		Entity res;
		GameConfiguration config = GameConfiguration.getConfig();
		switch (entity) {
			case Wall:
				res = newGround(x, y, config.getAutomaton(MyEntities.Ground));
				/* voir comment transmettre le crossable */
				break;
			case Ground:
				res = newGround(x, y, config.getAutomaton(MyEntities.Ground));
				break;
			case Enemy:
				res = newEnemy(x, y, config.getAutomaton(MyEntities.Enemy));
				break;
			case Droppable:
				res = newDroppable(x, y, config.getAutomaton(MyEntities.Droppable));
				/* a voir où definir les quantités et le type de ressource */
				break;
			case Vein:
				res = newVein(x, y, config.getAutomaton(MyEntities.Vein));
				break;

			case Shot:
				res = newShot(x, y, config.getAutomaton(MyEntities.Shot));
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

			default:
				System.out.println("tentative de création d'une entité relatif au joueur détectée");
				throw new IllegalStateException();
		}
		return res;
	}

	public static String name(Entity entity) {
		// System.out.println("test de entityFactory");
		// System.out.println(entity.getClass().getName());
		return entity.getClass().getName();
	}

}
