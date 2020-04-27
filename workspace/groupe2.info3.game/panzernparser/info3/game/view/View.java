package info3.game.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.border.Border;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import info3.game.controller.Controller;
import info3.game.model.Model;
import info3.game.model.entities.Entity;

public class View extends Container {

	private static final long serialVersionUID = 1L;
	public GameCanvas m_canvas;
	Controller m_controller;
	Model m_model;
	LinkedList<Avatar> m_avatars;
	// AvatarFactory m_factory;

	public View(Controller controller, Model model) {
		// créer la fenetre de jeu avec les bandeaux d'updrage et le canvas.
		m_controller = controller;
		m_model = model;
		m_canvas = new GameCanvas(m_controller);

		BorderLayout BL = initiateHUD();

		this.setLayout(BL);
		m_avatars = new LinkedList<Avatar>();
		updateAvatars();
	}

	public BorderLayout initiateHUD() {
		BorderLayout BL = new BorderLayout();
		BL.addLayoutComponent(m_canvas, BorderLayout.CENTER);
		this.add(m_canvas);

		// SOUTH
		Container south = new Container();
		south.setPreferredSize(new Dimension(200, 200));
		GridLayout BLSouth = new GridLayout(0, 3);
		Container West = new Container();
//		West.setPreferredSize(new Dimension((int) (m_canvas.getWidth()),200));
//		Container Center = new Container();
		Container East = new Container();
		JProgressBar health = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		JProgressBar drone = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		health.setAlignmentX(CENTER_ALIGNMENT);
		drone.setAlignmentX(CENTER_ALIGNMENT);
		health.setPreferredSize(new Dimension(100,100));
		drone.setPreferredSize(new Dimension(100,100));
		health.setForeground(Color.RED);
		health.setValue(100);
		drone.setValue(50);
		drone.setSize(200, 50);
		
		JButton buttonCenter = new JButton("CENTER");
		buttonCenter.setAlignmentX(CENTER_ALIGNMENT);
		buttonCenter.setAlignmentY(CENTER_ALIGNMENT);
		JButton buttonEast = new JButton("EAST");
		buttonEast.setAlignmentX(CENTER_ALIGNMENT);
		West.add(new Box.Filler(new Dimension(0, 0), new Dimension(50, 50), new Dimension(200, 200)));
		West.add(health);
		West.add(new Box.Filler(new Dimension(0, 0), new Dimension(50, 50), new Dimension(200, 200)));
		West.add(drone);
		West.add(new Box.Filler(new Dimension(0, 0), new Dimension(50, 50), new Dimension(200, 200)));

//		Center.add(buttonCenter);
		East.add(buttonEast);
		BoxLayout BLWest = new BoxLayout(West, BoxLayout.X_AXIS);
		West.setLayout(BLWest);
//		BoxLayout BLCenter = new BoxLayout(Center, BoxLayout.Y_AXIS);
//		Center.setLayout(BLCenter);
		BoxLayout BLEast = new BoxLayout(East, BoxLayout.Y_AXIS);
		East.setLayout(BLEast);
		south.add(West);
		south.add(buttonCenter);
//		south.add(Center);
		south.add(East);
		BLSouth.addLayoutComponent("West", West);
		BLSouth.addLayoutComponent("center", buttonCenter);
		BLSouth.addLayoutComponent("east", East);
		south.setLayout(BLSouth);
		BL.addLayoutComponent(south, BorderLayout.SOUTH);

		this.add(south);
		return BL;
	}

	public void refreshHUD() {
	}

	private void updateAvatars() {
		boolean mustRebuild = false;
		if (m_model.getEntities().size() != m_avatars.size())
			mustRebuild = true;
		Iterator<Entity> iter_ent = m_model.getEntities().iterator();
		Iterator<Avatar> iter_avt = m_avatars.iterator();
		// TODO : optimisation -- revoir cette boucle pour la mise a jour des avatars.
		while (!mustRebuild && iter_ent.hasNext() && iter_avt.hasNext()) {
			Entity entity = iter_ent.next();
			Avatar avatar = iter_avt.next();
			if (entity != avatar.m_entity)
				mustRebuild = true;
		}
		if (mustRebuild) {
			m_avatars = new LinkedList<Avatar>();
			for (Entity entity : m_model.getEntities()) {
				m_avatars.add(AvatarFactory.newAvatar(entity));
			}
		}
	}

	/**
	 * Méthode qui dessine la grille et les entités sur celle-ci.
	 */
	public void paintCanvas(Graphics g) {
		int width = m_canvas.getWidth();
		int height = m_canvas.getHeight();

		// Dessin précaire de la grille :
		int nb_cells_X = m_model.getGrid().getNbCellsX();
		int nb_cells_Y = m_model.getGrid().getNbCellsY();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);

		int case_width = width / nb_cells_X;
		int case_height = height / nb_cells_Y;

		g.setColor(Color.LIGHT_GRAY);
		for (int x = 1; x < nb_cells_X; x++)
			g.drawLine(x * case_width, 0, x * case_width, height);
		for (int y = 1; y < nb_cells_Y; y++)
			g.drawLine(0, y * case_height, width, y * case_height);

		updateAvatars();
		for (Avatar avatar : m_avatars) {
			avatar.paint(g, case_width, case_height); // TODO : revoir la zone avec le viewport plus tard.
		}
		refreshHUD();
	}

}
