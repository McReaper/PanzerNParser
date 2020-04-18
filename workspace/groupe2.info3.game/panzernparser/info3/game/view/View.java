package info3.game.view;

import java.util.LinkedList;

import javax.swing.JFrame;

import info3.game.controller.Controller;
import info3.game.model.Model;

public class View extends JFrame {

	Model m_model;
	Controller m_controller;
	
	/**
	 * Victor : reparler de ça :
	 */
	private static final long serialVersionUID = 1L;

	private GameCanvas m_canvas;
	private CanvasListener m_canvasListener;
	LinkedList<Avatar> m_avatars;

	public View(String title) {
		super(title);
		// créer la fenetre de jeu avec les bandeaux d'updrage et le canvas.
	}

	public void refreshHUD() {
		// TODO met à jour l'ATH de l'interface de jeu en fonction du modèle.
	}

	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		m_controller.tick(elapsed);
	}
	
	

}
