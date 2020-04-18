package info3.game.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.LinkedList;

import info3.game.controller.Controller;
import info3.game.model.Model;

public class View extends Container {

	
	/**
	 * Victor : reparler de ça :
	 */
	private static final long serialVersionUID = 1L;

	public GameCanvas m_canvas;
	Controller m_controller;
	Model m_model;
	LinkedList<Avatar> m_avatars;

	public View(Controller controller) {
		// créer la fenetre de jeu avec les bandeaux d'updrage et le canvas.
		m_controller = controller;
		m_canvas = new GameCanvas(m_controller);
		this.setLayout(new BorderLayout());
		this.add(m_canvas, BorderLayout.CENTER);
	}

	public void refreshHUD() {
		// TODO met à jour l'ATH de l'interface de jeu en fonction du modèle.
	}	
	

}
