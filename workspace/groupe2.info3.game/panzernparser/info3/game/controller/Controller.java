package info3.game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import info3.game.model.Model;
import info3.game.view.View;

public class Controller implements MouseListener, MouseMotionListener, KeyListener {

	Model m_model;
	View m_view;

	public Controller(View view, Model model) {
		m_view = view;
		m_model = model;
		// Créer les timers pour la vue et le modele
	}

	public void step() {
		// a chaque pas de simulation on met à jour l'ATH de la vue (pas le Canvas) et
		// le modèle.
		m_model.step();
		m_view.refreshHUD();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
