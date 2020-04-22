package info3.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import info3.game.automaton.Automaton;
import info3.game.automaton.BotBuilder;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.view.Animation;
import info3.game.view.Sprite;

public class GameConfiguration {

	///////////// DOMAIN PUBLIC ///////////////

	public static GameConfiguration getConfig() {
		createSingleton();
		return self;
	}

	public Animation getAnimation(MyEntities ent) {
		return m_animations.get(ent);
	}
	
	public Automaton getAutomaton(MyEntities ent) {
		return m_automatons.get(ent);
	}
	
	public static class ActionDirection {
		public LsAction m_action;
		public MyDirection m_direction; // peut être null

		public ActionDirection(LsAction act, MyDirection dir) {
			m_action = act;
			m_direction = dir;
		}
		
		@Override
		public int hashCode() {
			int actH = m_action.hashCode();
			int dirH = 0;
			if(m_direction != null) {
				dirH = m_direction.hashCode();
			}
			return actH + dirH;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof ActionDirection) {
				ActionDirection ad = (ActionDirection)obj;
				return (ad.m_action.equals(this.m_action) && ad.m_direction.equals(this.m_direction));
			} else {
				return false;
			}
		}
	}

	//////////// DOMAINE PRIVÉ //////////////

	private static final String CONFIGFILE_NAME = "panzernparser.cfg";
	private static GameConfiguration self = null;

	private static File m_configfile;
	private static HashMap<MyEntities, Animation> m_animations; // Association des .ani aux entités
	private static HashMap<MyEntities, Automaton> m_automatons; // Association des .gal aux entités

	private GameConfiguration() throws FileNotFoundException {
		m_configfile = new File(CONFIGFILE_NAME);
		m_animations = new HashMap<MyEntities, Animation>();
		m_automatons = new HashMap<MyEntities, Automaton>();
		parseConfigFile();
	}

	private static void createSingleton() {
		if (self == null)
			try {
				System.out.println("Parsing de la configuration ...");
				self = new GameConfiguration();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(-1);
			}
	}

	private static void parseConfigFile() throws FileNotFoundException {
		Scanner sc_cfg;
		try {
			// On essaye d'ourvrir le fichier de config en lecture
			sc_cfg = new Scanner(m_configfile);
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("Impossible de charger le fichier " + CONFIGFILE_NAME);
		}

		BotBuilder builder = new BotBuilder(); // Le bot builder qui va créer les classes de l'automate.

		// Pour chaque ligne du fichier de config :
		while (sc_cfg.hasNextLine()) {
			String line = sc_cfg.nextLine();
			// line est de la forme : "Nom | Nom.gal | Nom.ani"
			String[] fields = line.split(" | ");
			// Comportement spécial ici : les "|" sont comptés dans le split de line.

			// Parsing des fichiers .gal !
			AST myAST;
			try {
				myAST = AutomataParser.from_file("gal/" + fields[2]);
			} catch (Exception e1) {
				throw new FileNotFoundException("le fichier gal/" + fields[2] + " est introuvable.");
			}
			List<Automaton> lsAuto = (List<Automaton>) myAST.accept(builder);
			for (Automaton aut : lsAuto) {
				if (aut.getName().equals(fields[0])) {
					m_automatons.put(MyEntities.valueOf(fields[0]), aut);
					break;
				}
			}

			// Parsing des fichiers .ani !
			File ani_file = new File("animations/" + fields[4]);
//			
//			 Les fichiers .ani ont la forme : 
//			 
//			 sprite_sheet = chemin 
//			 NomAction = 1,2,4,5,...
//			 NomAction_DIR = 2,8,11,...
//			  ...
//			 
			Scanner sc_ani = new Scanner(ani_file);

			line = sc_ani.nextLine(); // En-tête avec le chemin du sprite_sheet.
			String[] fields_ani = line.split(" = ");

			Sprite sprite = null;
			try {
				// On essaye de récupérer le sprite associé a cette animation.
				sprite = new Sprite(fields_ani[1]);
			} catch (IOException e) {
				sc_ani.close();
				throw new FileNotFoundException("Fichier " + fields_ani[1] + " Introuvable !");
			}
			// HashMap qui pour une Action et sa direction associe la séquence de sprite.
			HashMap<ActionDirection, int[]> animSeq = new HashMap<ActionDirection, int[]>();

			// Pour chaque ligne du fichier .ani :
			while (sc_ani.hasNextLine()) {
				line = sc_ani.nextLine(); // On récupère la ligne courante
				fields_ani = line.split(" = "); // On la sépare en deux au niveau du "="
				// On récupère une action (avec direction particulière si c'est le cas)
				String actionName = fields_ani[0];
				String directionName = null;
				if (fields_ani[0].contains("_")) {
					String[] actionAndDirection = fields_ani[0].split("_", 2);
					actionName = actionAndDirection[0];
					directionName = actionAndDirection[1];
				}
				// On récupère la séquence d'entier de l'animation pour le sprite_sheet donnée.
				fields_ani = fields_ani[1].split(",");
				int[] seqNumbers = new int[fields_ani.length];
				for (int i = 0; i < seqNumbers.length; i++) {
					seqNumbers[i] = Integer.parseInt(fields_ani[i]);
				}
				LsAction action = LsAction.valueOf(actionName);
				MyDirection direction = (directionName == null) ? null : MyDirection.valueOf(directionName);
				ActionDirection actDir = new ActionDirection(action, direction);
				animSeq.put(actDir , seqNumbers);
			}

			//Création de l'animation :
			Animation animation = new Animation(sprite, animSeq);
			m_animations.put(MyEntities.valueOf(fields[0]), animation);
			
			//Fermeture du fichier .ani
			sc_ani.close();
		}
		//Fermeture du fichier .cfg
		sc_cfg.close();
	}

}
