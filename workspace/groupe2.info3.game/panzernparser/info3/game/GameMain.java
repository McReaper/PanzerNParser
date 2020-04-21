package info3.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import info3.game.controller.Controller;
import info3.game.model.Model;
import info3.game.view.View;

public class GameMain {

	static final String GAME_TITLE = "Panzer n' Parser - Ultra preAlpha Version";

	Controller m_controller;
	Model m_model;
	View m_view;
	JFrame m_frame;

	static GameMain game;

	public static void main(String[] args){
		System.out.println("Starting game");
		game = new GameMain();
		System.out.println("Game started");
	}

	GameMain(){
		// On ouvre le fichier de config
		File config_file = new File("panzernparser.cfg");

		// On créer l'univers du jeu
		m_model = new Model();

		// On créer le contrôleur qui va intéragir avec cet univers
		m_controller = new Controller(m_model);

		// On créer une vue de cette univers
		m_view = new View(m_controller, m_model, config_file);

		// On attribut cette vue au controleur, qui écoute
		m_controller.setView(m_view);

		System.out.println("Creating frame");
		Dimension d = new Dimension(1024, 768);
		m_frame = m_view.m_canvas.createFrame(d);
		System.out.println("Frame created");
		setupFrame();
	}

	private void setupFrame() {
		m_frame.setTitle(GAME_TITLE);
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_view, BorderLayout.CENTER);

		// Centre la fenêtre à l'écran :
		m_frame.setLocationRelativeTo(null);

		// Rend la fenetre visible
		m_frame.setVisible(true);
	}
}
