package info3.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

	public static final String GAL_PATH = "resources/gal/";
	public static final String SPRITE_PATH = "resources/sprites/";
	public static final String ANIMATION_PATH = "resources/animations/";
	public static final String PATTERN_PATH = "resources/patterns/";
	public static final String SOUND_PATH = "resources/sounds/";

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

	public static void fileNotFound(String path) {
		String message = "Can't find/parse " + path + ".\nPlease make sure your copy of the game is complete.";
		JOptionPane.showMessageDialog(new JFrame(), message, "Resource file missing or corrupted", JOptionPane.ERROR_MESSAGE);
		System.exit(1);
	}

	public static void checkResourcesFolder() throws MissingResourceException {
		File srcFolder = new File("resources/");
		File galFolder = new File(GAL_PATH);
		File aniFolder = new File(ANIMATION_PATH);
		File sprFolder = new File(SPRITE_PATH);
		File sndFolder = new File(SOUND_PATH);
		File patFolder = new File(PATTERN_PATH);
		File cfgFile = new File(CONFIGFILE_PATH);
		if (!srcFolder.isDirectory())
			fileNotFound("resources/");
		if (!galFolder.isDirectory())
			fileNotFound(GAL_PATH);
		if (!aniFolder.isDirectory())
			fileNotFound(ANIMATION_PATH);
		if (!sprFolder.isDirectory())
			fileNotFound(SPRITE_PATH);
		if (!sndFolder.isDirectory())
			fileNotFound(SOUND_PATH);
		if (!patFolder.isDirectory())
			fileNotFound(PATTERN_PATH);
		if (!cfgFile.exists())
			fileNotFound(CONFIGFILE_PATH);
	}

	//////////// DOMAINE PRIVÉ //////////////

	private static final String CONFIGFILE_PATH = "resources/panzernparser.cfg";
	private static GameConfiguration self = null;

	private static File m_configfile;
	private static HashMap<String, Animation> m_animationsAvailable; // Les animations dans les fichiers.
	private static HashMap<String, Automaton> m_automatonsAvailable; // Les automates dans les fichiers.
	private static HashMap<MyEntities, Animation> m_animations; // Association des .ani aux entités
	private static HashMap<MyEntities, Automaton> m_automatons; // Association des .gal aux entités

	private GameConfiguration() {
		m_configfile = new File(CONFIGFILE_PATH);
		m_animationsAvailable = new HashMap<String, Animation>();
		m_automatonsAvailable = new HashMap<String, Automaton>();
		m_animations = new HashMap<MyEntities, Animation>();
		m_automatons = new HashMap<MyEntities, Automaton>();
		parseConfigFile();
	}

	private static void createSingleton() {
		if (self == null) {
			System.out.println("Parsing the configuration of the game ...");
			self = new GameConfiguration();
			System.out.println("Configuration parsed !");
		}
	}

	private static void parseConfigFile() {
		BotBuilder builder = new BotBuilder();
		File autRepository = new File(GAL_PATH);
		File aniRepository = new File(ANIMATION_PATH);

		// Parsing de tout les fichier .gal existants.
		String[] autList = autRepository.list();
		for (int i = 0; i < autList.length; i++) {
			if (autList[i].substring(autList[i].length() - 4, autList[i].length()).equals(".gal")) {
				// Parsing d'un .gal
				AST myAST = null;
				try {
					myAST = AutomataParser.from_file(GAL_PATH + autList[i]);
				} catch (Exception e1) {
					System.err.println(autList[i] + " may be corrupted.");
					fileNotFound(GAL_PATH + autList[i]);
				}
				@SuppressWarnings("unchecked")
				List<Automaton> lsAuto = (List<Automaton>) myAST.accept(builder);
				m_automatonsAvailable.put(autList[i], lsAuto.get(0));
			} else {
				System.err.println("Ignoring " + autList[i] + " because file format is not recognized here.");
			}
		}

		// Parsing de tout les ficheirs .ani existants.
		String[] aniList = aniRepository.list();
		for (int i = 0; i < aniList.length; i++) {
			if (aniList[i].substring(aniList[i].length() - 4, aniList[i].length()).equals(".ani")) {
				File ani_file = new File(ANIMATION_PATH + aniList[i]);
//		
//		 Les fichiers .ani ont la forme : 
//		 
//		 sprite_sheet1 = chemin 
//		 sprite_sheet2 = chemin <- vision enemie
//		 sprite_sheet3 = chemin <- vision ressources
//		 NomAction = 1,2,4,5,...
//		 NomAction_DIR = 2,8,11,...
//		  ...
//		 
				// Parsing d'un .ani
				Scanner sc_ani = null;
				try {
					sc_ani = new Scanner(ani_file);
				} catch (FileNotFoundException e1) {
					fileNotFound(ani_file.getPath());
				}
				Sprite[] sprites = new Sprite[3];
				String[] fields_ani;
				String line;
				Sprite sprite = null;

				for (int j = 0; j < 3; j++) {
					line = sc_ani.nextLine(); // En-tête avec le chemin du sprite_sheet.
					fields_ani = line.split(" = ");
					try {
						// On essaye de récupérer le sprite associé a cette animation.
						sprite = new Sprite(SPRITE_PATH + fields_ani[1]);
						sprites[j] = sprite;
					} catch (IOException e) {
						sc_ani.close();
						fileNotFound(SPRITE_PATH + fields_ani[1]);
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
			} else {
				System.err.println("Ignoring " + aniList[i] + " because file format is not recognized here.");
			}
		}

		// Parsing de la configuration du jeu apr défaut (fichier .cfg)
		Scanner sc_cfg = null;
		try {
			// On essaye d'ourvrir le fichier de config en lecture
			sc_cfg = new Scanner(m_configfile);
		} catch (FileNotFoundException fnfe) {
			fileNotFound(CONFIGFILE_PATH);
		}

		// Pour chaque ligne du fichier de config :
		while (sc_cfg.hasNextLine()) {
			String line = sc_cfg.nextLine();
			if (line.length() == 0)
				continue;
			// line est de la forme : "Nom | Nom.gal | Nom.ani"
			String[] fields = line.split(" \\| ");

			if (fields.length != 3) {
				System.err.println("It seems that the configuration file " + CONFIGFILE_PATH + " is broken.");
				System.err
						.println("Please make sure each line is in format : EntityName | AutomataFile.gal | AnimationFile.ani");
				System.exit(1);
			}

			try {
				// config gal
				MyEntities entityType = MyEntities.valueOf(fields[0]);
				Automaton automata = m_automatonsAvailable.get(fields[1]);
				Animation animation = m_animationsAvailable.get(fields[2]);
				if (automata == null) {
					System.err.println("ERROR in " + CONFIGFILE_PATH + ", no Automata of name " + fields[1]);
					fileNotFound(fields[1] + " (" + CONFIGFILE_PATH + ")");
				}
				if (animation == null) {
					System.err.println("ERROR in " + CONFIGFILE_PATH + ", no Animation of name " + fields[2]);
					fileNotFound(fields[2] + " (" + CONFIGFILE_PATH + ")");
				}

				m_automatons.put(entityType, automata);
				m_animations.put(entityType, animation);
			} catch (IllegalArgumentException e) {
				System.err.println("ERROR in " + CONFIGFILE_PATH + ", no Entity of name " + fields[0]);
				System.exit(1);
			}
		}
		// Fermeture du fichier .cfg
		sc_cfg.close();
	}

}
