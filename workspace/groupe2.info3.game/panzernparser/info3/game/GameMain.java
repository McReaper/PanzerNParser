package info3.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.HashMap;

import javax.swing.JFrame;

import info3.game.controller.Controller;
import info3.game.model.Model;
import info3.game.view.HUD;
import info3.game.view.View;
import info3.game.view.ViewPort;

public class GameMain {

	static final String GAME_TITLE = "Panzer n' Parser - preAlpha Version";
	private static final int FRAME_WIDTH = 1024;
	private static final int FRAME_HEIGHT = 768;

	private Controller m_controller;
	private Model m_model;
	private View m_view;
	private JFrame m_frame;
	private boolean m_fullscreen;
	private HashMap<String, File> m_soundFiles;
	Menu m_menu;

	private static GameMain game;

	public static void main(String[] args) {
		System.out.println("Starting game");
		getGame();
		System.out.println("Game started");
	}

	private GameMain() {
		m_soundFiles = new HashMap<String, File>();
		GameConfiguration.getConfig();
		m_menu = new Menu(this,GameConfiguration.getConfig());
		// On force le parsing le configuration du jeu avant de créer quoi que ce soit

		// On charge les fichiers de sons en mémoire
		initSounds(new File(GameConfiguration.SOUND_PATH));
		
		// On créer l'univers du jeu
		m_model = Model.getModel();

		// On créer le contrôleur qui va intéragir avec cet univers
		m_controller = new Controller(m_model);
		
		
		// On créer une vue de cette univers
		m_view = new View(m_controller, m_model);

		// On attribut cette vue au controleur, qui écoute
		m_controller.setView(m_view);	
		
		m_fullscreen = false;
		m_frame = null;
		System.out.println("Setting up the frame ...");
		setupFrame();
		System.out.println("Frame set !");
	}
	
	public void launch() {
		m_frame.remove(m_menu.getMenu());
		m_frame.add(m_view, BorderLayout.CENTER);
		m_model.launch();
		m_view.launch();
		m_frame.invalidate();
		m_frame.validate();
		m_frame.repaint();
		getGame().m_controller.loadMusic("introkat-20db");
	}
	

	public static GameMain getGame() {
		if (game == null)
			game = new GameMain();
		return game;
	}

	private void setupFrame() {

		Dimension d = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
		m_frame = m_view.m_canvas.createFrame(d);

		m_frame.setTitle(GAME_TITLE);
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_menu.getMenu(), BorderLayout.CENTER);

		// Centre la fenêtre à l'écran :
		m_frame.setLocationRelativeTo(null);

		int min_width_hud = HUD.MINIMAL_WIDTH;
		int min_height_hud = HUD.MINIMAL_HEIGHT;
		int min_width_vp = ViewPort.MINIMAL_WIDTH;
		int min_height_vp = ViewPort.MINIMAL_HEIGHT;

		//TODO : a revoir, des bandes noires persistes
		m_frame.setMinimumSize(new Dimension(min_width_hud + Math.max(min_width_vp, min_height_hud) , Math.max(min_height_hud, min_height_vp)));

		if (m_fullscreen) {
			m_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			m_frame.setUndecorated(true);
		} else {
			m_frame.setExtendedState(JFrame.NORMAL);
			m_frame.setUndecorated(false);
		}

		// Rend la fenetre visible
		m_frame.setVisible(true);
	}

	public void goFullscreen() {
		m_frame.setVisible(false);
		m_fullscreen = !m_fullscreen;
		setupFrame();
	}

	public void initSounds(File folder) {
		for (final File fileEntry : folder.listFiles()) {
			String name = fileEntry.getName();
			name = name.substring(0, name.length() - 4);
			m_soundFiles.put(name, fileEntry);
		}
	}
	
	public HashMap<String, File> getSounds() {
		return m_soundFiles;
	}
	
	public void restart() {
		Model.restart();
		m_model = Model.getModel();
		m_model.launch();
		m_controller.setModel(m_model);
		m_view.setModel(m_model);
		getGame().m_controller.loadMusic("introkat-20db");
	}

	public void refresh() {
		m_frame.invalidate();
		m_frame.validate();
		m_frame.repaint();
	}

}
