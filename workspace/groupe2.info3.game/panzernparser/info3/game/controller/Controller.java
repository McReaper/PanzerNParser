package info3.game.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import info3.game.GameMain;
import info3.game.automaton.LsKey;
import info3.game.model.Grid.Coords;
import info3.game.model.Model;
import info3.game.model.upgrades.Upgrade;
import info3.game.model.upgrades.UpgradeShot;
import info3.game.view.GameCanvasListener;
import info3.game.view.View;

public class Controller implements GameCanvasListener {

	Model m_model;
	View m_view;

	public Controller(Model model) {
		m_model = model;
	}

	public void setView(View view) {
		m_view = view;
	}

	@Override
	public void tick(long elapsed) {
		// a chaque tick on fait un pas de simulation, et donc met à jour le modèle.
		boolean isEnd = m_model.getGameOver();
		m_model.step(elapsed);
		if (!m_model.getTank().gotPower()) {
			m_view.m_canvas.stop("KatyushaIntro");
			m_view.m_canvas.stop("KatyushaBoucle");
		}
		if (!m_model.getSounds().isEmpty()) {
			Iterator<String> iter = m_model.getSounds().iterator();
			while (iter.hasNext()) {
				String name = (String) iter.next();
				if(!isEnd || name.equals("deg") || name.contentEquals("Game_Over"))
					loadMusic(name);
			}
			m_model.getSounds().removeAll(m_model.getSounds());
		}
	}

	public void loadMusic(String name) {
		GameMain game = GameMain.getGame();
		File file = game.getSounds().get(name);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			// TODO : IOOBE , demandez aux autres groupes.
			m_view.m_canvas.play(name, fis, -1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode appelée par le GameCanvasListener, appelé par le GameCanvas.
	 */
	public void paint(Graphics g) {
		m_view.m_HUD.refreshHUD();
		m_view.paintCanvas(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		LsKey temp = toLsKey(e);
		m_model.addKeyPressed(temp);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F11) {
			// GameMain.getGame().goFullscreen(); // TODO : tobefixed
		}
		LsKey temp = toLsKey(e);
		m_model.removeKeyPressed(temp);
		if (m_model.getGameOver() && e.getKeyCode() == KeyEvent.VK_R) {
			GameMain.getGame().restart();
			m_view.m_HUD.refreshUpgrade();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!m_model.isPlayingTank()) {
			Coords c = new Coords(e.getX(), e.getY());
			try {
				c = m_view.toGridCoord(c);
				m_model.addClue(c);
			} catch (IllegalArgumentException ex) {
				return; // On ne pose pas de marqueurs.
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

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
	public void windowOpened() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endOfPlay(String name) {
		if ((name.equals("KatyushaIntro") || name.equals("KatyushaBoucle")) && Model.getModel().getTank().gotPower()) {
			loadMusic("KatyushaBoucle");
		}
	}

	@Override
	public void expired() {
		// TODO Auto-generated method stub

	}

	public LsKey toLsKey(KeyEvent e) {
		switch (e.getKeyCode()) {
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
			case KeyEvent.VK_SPACE:
				return LsKey.SPACE;
			case KeyEvent.VK_ENTER:
				return LsKey.ENTER;
		}
		return null;
	}

	public void upgradeClicked(Upgrade upgrade) {
		m_model.performUpgrade(upgrade);
	}

	public void setModel(Model model) {
		m_model = model;
	}

}
