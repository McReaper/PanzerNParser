package info3.game.model.entities;

import info3.game.model.Material.MaterialType;
import info3.game.model.Model;

public class EntityFactory {
	public enum MyEntities {
		WALL, GROUND, ENEMY, DROPPABLE, SHOT, VEIN; // TODO
	}

	public static Entity newTank() {
		return null;
	}

	public static Entity newDrone() {
		return null;
	}

	public static Entity newMarker() {
		return null;
	}

	public static Entity newShot(int x, int y) {
		Model model = Model.getModel();
		return new Shot(x, y, Shot.SHOT_WIDTH, Shot.SHOT_HEIGHT, 10, 10,model);
		/* par defaut health et time_to_travel = 10 mais a voir où gerer ça */
	}

	public static Entity newEnemy(int x, int y) {
		Model model = Model.getModel();
		return new Enemy(x, y, Enemy.ENEMY_WIDTH, Enemy.ENEMY_HEIGHT,model);
	}

	public static Entity newDroppable(int x, int y) {
		Model model = Model.getModel();
		return new Droppable(x, y, Droppable.DROPPABLE_WIDTH, Droppable.DROPPABLE_HEIGHT, 1, MaterialType.ELECTRONIC,model);
		// 1 en quantité mais à discuter
	}

	public static Entity newVein(int x, int y) {
		Model model = Model.getModel();
		return new Vein(x, y, Ground.GROUND_WIDTH, Ground.GROUND_HEIGHT,model);
	}

	public static Entity newGround(int x, int y) {
		Model model = Model.getModel();
		return new Ground(x, y, Ground.GROUND_WIDTH, Ground.GROUND_HEIGHT,model);
	}

	public static Entity newEntity(MyEntities entity, int x, int y) {
		Entity res;
		switch (entity) {
			case WALL:
				res = newGround(x, y);
				/* voir comment transmettre le crossable */
				break;
			case GROUND:
				res = newGround(x, y);
				break;
			case ENEMY:
				res = newEnemy(x, y);
				break;
			case DROPPABLE:
				res = newDroppable(x, y);
				/* a voir où definir les quantités et le type de ressource */
				break;
			case VEIN:
				res = newVein(x, y);
				break;

			case SHOT:
				res = newShot(x, y);
				break;

			default:
				System.out.println("tentative de création d'une entité relatif au joueur détectée");
				throw new IllegalStateException();
		}
		return res;
	}
	
	public static String name(Entity entity) {
		System.out.println("test de entityFactory");
		System.out.println(entity.getClass().getName());
		return  entity.getClass().getName();
	}
	
}
