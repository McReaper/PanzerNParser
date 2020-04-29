package info3.game;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import info3.game.controller.Controller;
import info3.game.model.Model;
import info3.game.view.View;
import info3.game.view.ViewPort;

public class GameMain {

	static final String GAME_TITLE = "Panzer n' Parser - preAlpha Version";

	Controller m_controller;
	Model m_model;
	View m_view;
	JFrame m_frame;
	boolean m_fullscreen;

	static GameMain game;

	public static void main(String[] args) {
		System.out.println("Starting game");
		game = new GameMain();
		System.out.println("Game started");
	}

	GameMain() {
		// On force le parsing le configuration du jeu avant de créer quoi que ce soit
		GameConfiguration.getConfig();

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
		System.out.println("Creating frame");
		setupFrame();
		System.out.println("Frame created");
	}
	
	public static GameMain getGame() {
		return game;
	}

	private void setupFrame() {
		
		Dimension d = new Dimension(1024, 768);
		m_frame = m_view.m_canvas.createFrame(d);
		
		m_frame.setTitle(GAME_TITLE);
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_view, BorderLayout.CENTER);

		// Centre la fenêtre à l'écran :
		m_frame.setLocationRelativeTo(null);

		int min_width_hud = 100 * 2;
		int min_height_hud = 100 * 2;
		int min_width_vp = ViewPort.MINIMAL_WIDTH;
		int min_height_vp = ViewPort.MINIMAL_HEIGHT;

		m_frame.setMinimumSize(new Dimension(min_width_hud + min_width_vp, min_height_hud + min_height_vp));

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
}
