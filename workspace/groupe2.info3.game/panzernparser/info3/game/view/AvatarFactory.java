package info3.game.view;

import info3.game.GameConfiguration;
import info3.game.model.entities.*;
import info3.game.model.entities.EntityFactory.MyEntities;

/**
 * Classe qui associe à une entité un Avatar correspondant et son animation en
 * fonction de la configuration du jeu
 */
public class AvatarFactory {

	public static Avatar newAvatar(Entity entity) {
		GameConfiguration config = GameConfiguration.getConfig();
		if (entity instanceof Droppable) {
			return new Avatar(entity, config.getAnimation(MyEntities.Droppable));
		} else if (entity instanceof Drone) {
			return new Avatar(entity, config.getAnimation(MyEntities.Drone));
		} else if (entity instanceof Enemy) {
			return new TankBodyAvatar(entity, config.getAnimation(MyEntities.Enemy));
		} else if (entity instanceof Vein) {
			return new Avatar(entity, config.getAnimation(MyEntities.Vein));
		} else if (entity instanceof Ground) {
			return new Avatar(entity, config.getAnimation(MyEntities.Ground));
		} else if (entity instanceof Marker) {
			return new MarkerAvatar(entity, config.getAnimation(MyEntities.Marker));
		} else if (entity instanceof Shot) {
			return new Avatar(entity, config.getAnimation(MyEntities.Shot));
		} else if (entity instanceof TankBody) {
			return new TankBodyAvatar(entity, config.getAnimation(MyEntities.TankBody));
		} else if (entity instanceof Turret) {
			return new TurretAvatar(entity, config.getAnimation(MyEntities.Turret));
		}

		throw new IllegalArgumentException("This instance of Entity is not recognized by the view.");
	}

}
