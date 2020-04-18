package info3.game.view;

import java.util.LinkedList;

import javax.swing.JFrame;

import info3.game.model.Model;

public class View extends JFrame {

	Model m_model;
	
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

	public void tick(long elapsed) {
		// TODO met a jour le Canvas et l'ATH en fonction du modèle.
		m_model.tick(elapsed);
	}
	
	

}
