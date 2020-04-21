package info3.game.view;

import info3.game.model.entities.Droppable;
import info3.game.model.entities.Enemy;
import info3.game.model.entities.Entity;
import info3.game.model.entities.Vein;

public class AvatarFactory {

	public static Avatar newAvatar(Entity entity) {
		if (entity instanceof Droppable) {
			return new EnemyAvatar(entity);
		} else if (entity instanceof Enemy) {
			return new EnemyAvatar(entity);
		} else if (entity instanceof Vein) {
			return new EnemyAvatar(entity);
		} // TODO : compléter la séquence avec d'autre type d'Avatars.
		throw new IllegalArgumentException("This instance of Entity is not recognized by the view.");
	}

}
