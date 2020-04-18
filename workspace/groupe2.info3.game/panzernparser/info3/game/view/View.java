package info3.game.view;

import javax.swing.JFrame;

import info3.game.model.Model;

public class View extends JFrame {

	/**
	 * Victor : reparler de ça :
	 */
	private static final long serialVersionUID = 1L;

	private GameCanvas m_canvas;
	private CanvasListener m_canvasListener;
	Model m_model;

	public View(String title) {
		super(title);
		// créer la fenetre de jeu avec les bandeaux d'updrage et le canvas.
	}

	public void tick(long elapsed) {
		// TODO met a jour le Canvas et l'ATH en fonction du modèle.
		m_model.tick(elapsed);
	}

}
