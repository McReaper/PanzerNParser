package info3.game.model.entities;

import info3.game.model.Material.MaterialType;

public class EntityFactory {
	public enum MyEntities {
		WALL, GROUND, ENEMY, DROPPABLE, SHOT, VEIN; // TODO
	}

	public Entity newTank() {
		return null;
	}

	public Entity newDrone() {
		return null;
	}

	public Entity newMarker() {
		return null;
	}

	public Entity newShot(int x, int y) {
		return new Shot(x, y, Shot.SHOT_WIDTH, Shot.SHOT_HEIGHT, 10, 10);
		/* par defaut health et time_to_travel = 10 mais a voir où gerer ça */
	}

	public Entity newEnemy(int x, int y) {
		return new Enemy(x, y, Enemy.ENEMY_WIDTH, Enemy.ENEMY_HEIGHT);
	}

	public Entity newDroppable(int x, int y) {
		return new Droppable(x, y, Droppable.DROPPABLE_WIDTH, Droppable.DROPPABLE_HEIGHT, 1, MaterialType.ELECTRONIC);
		// 1 en quantité mais à discuter
	}

	public Entity newVein(int x, int y) {
		return new Vein(x, y, Ground.GROUND_WIDTH, Ground.GROUND_HEIGHT);
	}

	public Entity newGround(int x, int y) {
		return new Ground(x, y, Ground.GROUND_WIDTH, Ground.GROUND_HEIGHT);
	}

	public Entity newEntity(MyEntities entity, int x, int y) {
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
	
	public String name(Entity entity) {
		System.out.println("test de entityFactory");
		System.out.println(entity.getClass().getName());
		return  entity.getClass().getName();
	}
	
}
