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

	///////////// DOMAINE PUBLIC ///////////////

	public static final String GAL_PATH = "gal/";
	public static final String SPRITE_PATH = "sprites/";
	public static final String ANIMATION_PATH = "animations/";
	public static final String PATTERN_PATH = "patterns_test/";
	public static final String SOUND_PATH = "sounds/";

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

	public HashMap<String, Animation> getAnimationsAvailable() {
		return m_animationsAvailable;
	}

	public HashMap<String, Automaton> getAutomatonsAvailable() {
		return m_automatonsAvailable;
	}
	
	public HashMap<MyEntities, Animation> getAnimationsConfig() {
		return m_animations;
	}

	public HashMap<MyEntities, Automaton> getAutomatonsConfig() {
		return m_automatons;
	}

	public static class ActionDirection {
		public LsAction m_action;
		public MyDirection m_direction;

		public ActionDirection(LsAction act, MyDirection dir) {
			m_action = act;
			m_direction = dir;
		}

		@Override
		public int hashCode() {
			int actH = m_action.hashCode();
			int dirH = 0;
			if (m_direction != null) {
				dirH = m_direction.hashCode();
			}
			return actH + dirH;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof ActionDirection) {
				ActionDirection ad = (ActionDirection) obj;
				return (ad.m_action == this.m_action && ad.m_direction == this.m_direction);
			} else {
				return false;
			}
		}
	}

	//////////// DOMAINE PRIVÉ //////////////

	private static final String CONFIGFILE_NAME = "panzernparser.cfg";
	private static GameConfiguration self = null;

	private static File m_configfile;
	private static HashMap<String, Animation> m_animationsAvailable; // Les animations dans les fichiers.
	private static HashMap<String, Automaton> m_automatonsAvailable; // Les animations dans les fichiers.
	private static HashMap<MyEntities, Animation> m_animations; // Association des .ani aux entités
	private static HashMap<MyEntities, Automaton> m_automatons; // Association des .gal aux entités

	private GameConfiguration() throws FileNotFoundException {
		m_configfile = new File(CONFIGFILE_NAME);
		m_animationsAvailable = new HashMap<String, Animation>();
		m_automatonsAvailable = new HashMap<String, Automaton>();
		m_animations = new HashMap<MyEntities, Animation>();
		m_automatons = new HashMap<MyEntities, Automaton>();
		parseConfigFile();
	}

	private static void createSingleton() {
		if (self == null)
			try {
				System.out.println("Parsing de la configuration ...");
				self = new GameConfiguration();
				System.out.println("Configuration chargée !");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(1);
			}
	}

	private static void parseConfigFile() throws FileNotFoundException {
		BotBuilder builder = new BotBuilder();
		File autRepository = new File(GAL_PATH);
		String[] autList = autRepository.list();
		for (int i = 0; i < autList.length; i++) {
			if (autList[i].substring(autList[i].length() - 4, autList[i].length()).equals(".gal")) {
				// Parsing d'un .gal
				AST myAST;
				try {
					myAST = AutomataParser.from_file(GAL_PATH + autList[i]);
				} catch (Exception e1) {
					throw new FileNotFoundException("le fichier " + GAL_PATH + autList[i] + " est introuvable ou erroné");
				}
				@SuppressWarnings("unchecked")
				List<Automaton> lsAuto = (List<Automaton>) myAST.accept(builder);
				m_automatonsAvailable.put(autList[i], lsAuto.get(0));

			}
		}
		File aniRepository = new File(ANIMATION_PATH);
		String[] aniList = aniRepository.list();
		for (int i = 0; i < aniList.length; i++) {
			if (aniList[i].substring(aniList[i].length() - 4, aniList[i].length()).equals(".ani")) {
				File ani_file = new File(ANIMATION_PATH + aniList[i]);
//			
//			 Les fichiers .ani ont la forme : 
//			 
//			 sprite_sheet1 = chemin 
//			 sprite_sheet2 = chemin <- vision enemie
//			 sprite_sheet3 = chemin <- vision ressources
//			 NomAction = 1,2,4,5,...
//			 NomAction_DIR = 2,8,11,...
//			  ...
//			 
				// Parsing d'un .ani
				Scanner sc_ani = new Scanner(ani_file);
				Sprite[] sprites = new Sprite[3];
				String[] fields_ani;
				String line;

				for (int j = 0; j < 3; j++) {
					line = sc_ani.nextLine(); // En-tête avec le chemin du sprite_sheet.
					fields_ani = line.split(" = ");

					Sprite sprite = null;
					try {
						// On essaye de récupérer le sprite associé a cette animation.
						sprite = new Sprite(SPRITE_PATH + fields_ani[1]);
						sprites[j] = sprite;
					} catch (IOException e) {
						sc_ani.close();
						throw new FileNotFoundException("Fichier " + SPRITE_PATH + fields_ani[1] + " Introuvable !");
					}
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
					for (int j = 0; j < seqNumbers.length; j++) {
						seqNumbers[j] = Integer.parseInt(fields_ani[j]);
					}
					LsAction action = LsAction.valueOf(actionName);
					MyDirection direction = (directionName == null) ? null : MyDirection.valueOf(directionName);
					ActionDirection actDir = new ActionDirection(action, direction);
					animSeq.put(actDir, seqNumbers);
				}

				// Création de l'animation :
				Animation animation = new Animation(sprites, animSeq, aniList[i]);
				m_animationsAvailable.put(aniList[i], animation);

				sc_ani.close();
			}
		}

		Scanner sc_cfg;
		try {
			// On essaye d'ourvrir le fichier de config en lecture
			sc_cfg = new Scanner(m_configfile);
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("Impossible de charger le fichier " + CONFIGFILE_NAME);
		}

		// BotBuilder builder = new BotBuilder(); // Le bot builder qui va créer les
		// classes de l'automate.

		// Pour chaque ligne du fichier de config :
		while (sc_cfg.hasNextLine()) {
			String line = sc_cfg.nextLine();
			// line est de la forme : "Nom | Nom.gal | Nom.ani"
			String[] fields = line.split(" | ");
			// Comportement spécial ici : les "|" sont comptés dans le split de line.

			// config gal
			m_automatons.put(MyEntities.valueOf(fields[0]), m_automatonsAvailable.get(fields[2]));

			// config ani
			m_animations.put(MyEntities.valueOf(fields[0]), m_animationsAvailable.get(fields[4]));

			// Fermeture du fichier .ani
		}
		// Fermeture du fichier .cfg
		sc_cfg.close();
	}

}
