package info3.game.view;

import java.io.File;
import java.util.HashMap;

import info3.game.model.entities.Droppable;
import info3.game.model.entities.Enemy;
import info3.game.model.entities.Entity;

public class AvatarFactory {

	static final int NB_ENTITIES = 9; // Doit être changé si on rajoute des entités dans le modele.
	
	HashMap<String, Animation> m_animations;
	
	public AvatarFactory(File config_file) {
		m_animations = new HashMap<String, Animation>();
		//TODO : parser le fichier de config pour créer les animations et les ajouter dans la HashMap
	}
	
	public Avatar newAvatar(Entity entity) {
		if (entity instanceof Droppable) {
			return new DroppableAvatar(entity, m_animations.get("Droppable"));
		} else if (entity instanceof Enemy) {
			return new EnemyAvatar(entity);
		}
		//TODO : compléter la séquence avec d'autre type d'Avatars.
		
		
		throw new IllegalArgumentException("This instance of Entity is not recognized by the view.");
	}

}
