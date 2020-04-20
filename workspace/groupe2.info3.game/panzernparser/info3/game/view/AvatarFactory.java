package info3.game.view;

import info3.game.model.entities.Droppable;
import info3.game.model.entities.Entity;

public class AvatarFactory {

	public static Avatar newAvatar(Entity entity) {
		if (entity instanceof Droppable) {
			return new DroppableAvatar(entity);
		}
		return new Avatar(entity);
	}

}
