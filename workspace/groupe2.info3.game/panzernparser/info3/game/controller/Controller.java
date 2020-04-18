package info3.game.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.model.Model;
import info3.game.view.GameCanvasListener;
import info3.game.view.View;

public class Controller implements GameCanvasListener {

	Model m_model;
	View m_view;

	public Controller(Model model) {
		m_model = model;
		// Créer les timers pour la vue et le modele
	}

	public void setView(View view) {
		m_view = view;
	}

	@Override
	public void tick(long elapsed) {
		// a chaque pas de simulation on met à jour l'ATH de la vue (pas le Canvas) et
		// le modèle.
		m_model.step(elapsed);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		m_view.refreshHUD();
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endOfPlay(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void expired() {
		// TODO Auto-generated method stub

	}

}
