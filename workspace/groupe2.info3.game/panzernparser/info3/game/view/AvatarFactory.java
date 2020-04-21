package info3.game.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import info3.game.automaton.action.LsAction;
import info3.game.model.entities.*;

public class AvatarFactory {

	HashMap<String, Animation> m_animations;

	public AvatarFactory(File config_file) throws FileNotFoundException {
		m_animations = new HashMap<String, Animation>();

		Scanner sc = new Scanner(config_file);
		String line;

		// Pour chaque ligne du fichier de config :
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			// line est de la forme : "Nom | Nom.gal | Nom.ani"
			String[] fields = line.split(" | ");

			/*
			 * Les fichiers .ani ont la forme :
			 * sprite_sheet = chemin 
			 * NomAction = 1,2,4,5,...
			 * ...
			 */
			File ani_file = new File("animations/" + fields[4]);
			Scanner sc_ani = new Scanner(ani_file);

			line = sc_ani.nextLine(); // En-tête avec le chemin du sprite.
			String[] fields_ani = line.split(" = ");

			Sprite sprite = null;
			try {
				sprite = new Sprite(fields_ani[1]);
			} catch (IOException e) {
				sc_ani.close();
				throw new FileNotFoundException("Fichier " + fields_ani[1] + " Introuvable !");
			}
			HashMap<LsAction, int[]> animSeq = new HashMap<LsAction, int[]>();
			// Pour chaque ligne du fichier .ani :
			while (sc_ani.hasNextLine()) {
				line = sc_ani.nextLine();
				fields_ani = line.split(" = ");
				String actionName = fields_ani[0];
				fields_ani = fields_ani[1].split(",");
				int[] seqNumbers = new int[fields_ani.length];
				for (int i = 0; i < seqNumbers.length; i++) {
					seqNumbers[i] = Integer.parseInt(fields_ani[i]);
				}
				animSeq.put(LsAction.valueOf(actionName), seqNumbers);
			}
			Animation animation = new Animation(sprite, animSeq);
			m_animations.put(fields[0], animation);
			sc_ani.close();
		}
		sc.close();
	}

	public Avatar newAvatar(Entity entity) {
		if (entity instanceof Droppable) {
			return new Avatar(entity, m_animations.get("Droppable"));
		} else if (entity instanceof Drone) {
			return new Avatar(entity, m_animations.get("Drone"));
		} else if (entity instanceof Enemy) {
			return new Avatar(entity, m_animations.get("Enemy"));
		} else if (entity instanceof Ground) {
			return new Avatar(entity, m_animations.get("Ground"));
		} else if (entity instanceof Marker) {
			return new Avatar(entity, m_animations.get("Marker"));
		} else if (entity instanceof Shot) {
			return new Avatar(entity, m_animations.get("Shot"));
		} else if (entity instanceof TankBody) {
			return new Avatar(entity, m_animations.get("TankBody"));
		} else if (entity instanceof Turret) {
			return new Avatar(entity, m_animations.get("Turret"));
		} else if (entity instanceof Vein) {
			return new Avatar(entity, m_animations.get("Vein"));
		}
		
		throw new IllegalArgumentException("This instance of Entity is not recognized by the view.");
	}

}
