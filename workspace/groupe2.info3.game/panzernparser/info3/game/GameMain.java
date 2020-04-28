package info3.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.JFrame;

import info3.game.controller.Controller;
import info3.game.model.Model;
import info3.game.view.View;

public class GameMain {

	static final String GAME_TITLE = "Panzer n' Parser - preAlpha Version";

	Controller m_controller;
	Model m_model;
	View m_view;
	JFrame m_frame;
	public HashMap<String,File> m_file;

	static GameMain game;

	public static void main(String[] args){
		System.out.println("Starting game");
		game = new GameMain();
		System.out.println("Game started");
	}

	GameMain(){
		m_file = new HashMap<String, File>();
		//On force le parsing le configuration du jeu avant de créer quoi que ce soit
		GameConfiguration.getConfig();

		// On créer l'univers du jeu
		m_model = Model.getModel();

		initSounds(new File("sounds/"));
		
		// On créer le contrôleur qui va intéragir avec cet univers
		m_controller = new Controller(m_model);

		// On créer une vue de cette univers
		m_view = new View(m_controller, m_model);

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
	
	public void initSounds(File folder) {
		for (final File fileEntry : folder.listFiles()) {
			String name = fileEntry.getName();
			name = name.substring(0,name.length()-4);
			m_file.put(name, fileEntry);
		}
	}
	
	public static GameMain getGame() {
		return game;
	}
		
}
