package info3.game.view;

import java.util.LinkedList;

import javax.swing.JFrame;

import info3.game.controller.Controller;
import info3.game.model.Model;

public class View extends JFrame {

	
	/**
	 * Victor : reparler de ça :
	 */
	private static final long serialVersionUID = 1L;

	public GameCanvas m_canvas;
	Controller m_controller;
	Model m_model;
	LinkedList<Avatar> m_avatars;

	public View(Controller controller) {
		super();
		// créer la fenetre de jeu avec les bandeaux d'updrage et le canvas.
		m_controller = controller;
		m_canvas = new GameCanvas(m_controller);
	}

	public void refreshHUD() {
		// TODO met à jour l'ATH de l'interface de jeu en fonction du modèle.
	}

	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		m_controller.tick(elapsed);
	}
	
	

}
