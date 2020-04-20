package info3.game.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.automaton.LsKey;
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
		// a chaque tick on fait un pas de simulation, et donc met à jour le modèle.
		m_model.step(elapsed);
	}

	/**
	 * Méthode appelée par le GameCanvasListener, appelé par le GameCanvas.
	 */
	public void paint(Graphics g) {
		m_view.refreshHUD();
		m_view.paintCanvas(g);
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
		LsKey temp = toLsKey(e);
		m_model.addKeyPressed(temp);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		LsKey temp = toLsKey(e);
		m_model.removeKeyPressed(temp);
	}

	@Override
	public void windowOpened() {
		// TODO Auto-generated method stub

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
	
	public LsKey toLsKey(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_A:
				return LsKey.A;
			case KeyEvent.VK_B:
				return LsKey.B;
			case KeyEvent.VK_C:
				return LsKey.C;
			case KeyEvent.VK_D:
				return LsKey.D;
			case KeyEvent.VK_E:
				return LsKey.E;
			case KeyEvent.VK_F:
				return LsKey.F;
			case KeyEvent.VK_G:
				return LsKey.G;
			case KeyEvent.VK_H:
				return LsKey.H;
			case KeyEvent.VK_I:
				return LsKey.I;
			case KeyEvent.VK_J:
				return LsKey.J;
			case KeyEvent.VK_K:
				return LsKey.K;
			case KeyEvent.VK_L:
				return LsKey.L;
			case KeyEvent.VK_M:
				return LsKey.M;
			case KeyEvent.VK_N:
				return LsKey.N;
			case KeyEvent.VK_O:
				return LsKey.O;
			case KeyEvent.VK_P:
				return LsKey.P;
			case KeyEvent.VK_Q:
				return LsKey.Q;
			case KeyEvent.VK_R:
				return LsKey.R;
			case KeyEvent.VK_S:
				return LsKey.S;
			case KeyEvent.VK_T:
				return LsKey.T;
			case KeyEvent.VK_U:
				return LsKey.U;
			case KeyEvent.VK_V:
				return LsKey.V;
			case KeyEvent.VK_W:
				return LsKey.W;
			case KeyEvent.VK_X:
				return LsKey.X;
			case KeyEvent.VK_Y:
				return LsKey.Y;
			case KeyEvent.VK_Z:
				return LsKey.Z;
			case KeyEvent.VK_0:
				return LsKey.ZERO;
			case KeyEvent.VK_1:
				return LsKey.ONE;
			case KeyEvent.VK_2:
				return LsKey.TWO;
			case KeyEvent.VK_3:
				return LsKey.THREE;
			case KeyEvent.VK_4:
				return LsKey.FOUR;
			case KeyEvent.VK_5:
				return LsKey.FIVE;
			case KeyEvent.VK_6:
				return LsKey.SIX;
			case KeyEvent.VK_7:
				return LsKey.SEVEN;
			case KeyEvent.VK_8:
				return LsKey.EIGHT;
			case KeyEvent.VK_9:
				return LsKey.NINE;
			case KeyEvent.VK_UP:
				return LsKey.AU;
			case KeyEvent.VK_DOWN:
				return LsKey.AD;
			case KeyEvent.VK_LEFT:
				return LsKey.AL;
			case KeyEvent.VK_RIGHT:
				return LsKey.AR;
		}
		return null;
	}

}
