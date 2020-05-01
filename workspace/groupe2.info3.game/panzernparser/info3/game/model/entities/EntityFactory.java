package info3.game.model.entities;

import info3.game.GameConfiguration;
import info3.game.automaton.Automaton;
import info3.game.automaton.MyCategory;
import info3.game.model.MaterialType;
import info3.game.model.Model;

public class EntityFactory {
	public enum MyEntities {
		Wall, Ground, Enemy, Droppable, Shot, Vein, Drone, Marker, TankBody, Turret;
	}

	public static Entity newTankBody(int x, int y, Automaton aut) {
		return new TankBody(x, y,aut);
	}

	public static Entity newTankTurret(int x, int y, Automaton aut) {
		return new Turret(x, y, aut);
	}

	public static Entity newEnemy(int x, int y, Automaton aut) {
		Entity enemy = new Enemy(x, y, aut);
		return enemy;
	}

	public static Entity newDroppable(int x, int y, Automaton aut) {
		Entity drop = new Droppable(x, y, MaterialType.ELECTRONIC, aut);
		return drop;
	}

	public static Entity newVein(int x, int y, Automaton aut) {
		Entity vein = new Vein(x, y, aut);
		return vein;
	}

	public static Entity newGround(int x, int y, Automaton aut) {
		Entity ground = new Ground(x, y, aut);
		return ground;
	}
	
	public static Entity newWall(int x, int y, Automaton aut) {
		Entity wall = newGround(x, y, aut);
		wall.setCategory(MyCategory.O);
		return wall;
	}

	private static Entity newDrone(int x, int y, Automaton aut) {
		Entity drone = new Drone(x, y, aut);
		return drone;
	}

	public static Entity newMarker(int x, int y, Automaton aut) {
		Entity marker = new Marker(x, y, aut);
		return marker;
	}
	//////////////////Creation des différents type de shot///////////////
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
	//////////////////////////////////////////////////////////////////////

	public static Entity newEntity(MyEntities entity, int x, int y) {
		Entity res;
		GameConfiguration config = GameConfiguration.getConfig();
		switch (entity) {
			case Wall:
				res = newWall(x, y, config.getAutomaton(MyEntities.Wall));
				break;
			case Ground:
				res = newGround(x, y, config.getAutomaton(MyEntities.Ground));
				break;
			case Enemy:
				res = newEnemy(x, y, config.getAutomaton(MyEntities.Enemy));
				break;
			case Droppable:
				res = newDroppable(x, y, config.getAutomaton(MyEntities.Droppable));
				/* TODO a voir où definir les quantités et le type de ressource */
				break;
			case Vein:
				res = newVein(x, y, config.getAutomaton(MyEntities.Vein));
				break;

			case Shot://par défaut on prend les balles lentes
				res = newShotSlow(x, y, config.getAutomaton(MyEntities.Shot));
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
			default:
				throw new IllegalStateException("Entité non reconnue !");
		}
		Model.getModel().getGrid().addEntity(res);
		Model.getModel().getEntities(entity).add(res);
		return res;
	}
	
	public static Entity newEntityShot(MyEntities entity, int x, int y, int typeShot) {
		Entity res;
		GameConfiguration config = GameConfiguration.getConfig();
		switch (typeShot) {
			case Turret.GUN_BULLET_SLOW:
				res = newShotSlow(x, y, config.getAutomaton(MyEntities.Shot));
				break;
			case Turret.GUN_BULLET_FAST:
				res = newShotFast(x, y, config.getAutomaton(MyEntities.Shot));
				break;
			case Turret.GUN_BIG_BULLET:
				res = newShotBig(x, y, config.getAutomaton(MyEntities.Shot));
				break;
			default:
				throw new IllegalStateException("Entité non reconnue !");
		}
		Model.getModel().getEntities(entity).add(res);
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
		} else if (e instanceof Enemy) {
			return MyEntities.Enemy;
		} else if (e instanceof Vein) {
			return MyEntities.Vein;
		} else if (e instanceof Ground) {
			if(e.getCategory() == MyCategory.O)
				return MyEntities.Wall;
			return MyEntities.Ground;
		} else if (e instanceof Marker) {
			return MyEntities.Marker;
		} else if (e instanceof Shot) {
			return MyEntities.Shot;
		} else if (e instanceof TankBody) {
			return MyEntities.TankBody;
		} else if (e instanceof Turret) {
			return MyEntities.Turret;
		} else {
			throw new IllegalArgumentException("Entité non reconnue !");
		}
	}

}
