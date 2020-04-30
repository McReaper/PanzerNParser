package info3.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import info3.game.automaton.MyCategory;
import info3.game.controller.Controller;
import info3.game.model.Grid;
import info3.game.model.Grid.Coords;
import info3.game.model.MaterialType;
import info3.game.model.Model;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.Marker;
import info3.game.model.entities.TankBody;

public class View extends Container {

	private static final long serialVersionUID = 1L;
	public GameCanvas m_canvas;
	Controller m_controller;
	Model m_model;
	LinkedList<Avatar> m_avatars;
	ViewPort m_viewPort;
	public HUD m_HUD;
	// AvatarFactory m_factory;

	public View(Controller controller, Model model) {
		// créer la fenetre de jeu avec les bandeaux d'updrage et le canvas.
		m_controller = controller;
		m_model = model;
		m_canvas = new GameCanvas(m_controller);

		m_HUD = new HUD(this);
		BorderLayout BL = initiateHUD();

		this.setLayout(BL);
		m_avatars = new LinkedList<Avatar>();
		updateAvatars();
//		for (Entity e : model.getEntities()) {
//			if(e instanceof Tank)
//		if (model.m_player == model.PLAYER_TANK) {
//			m_viewPort = new ViewPort(model,model.m_tank.m_body, this);
//		}else {
		m_viewPort = new ViewPort(m_model.getPlayed(), this);
		// }//TODO passage de l'un a l'autre
//		}
	}

	public BorderLayout initiateHUD() {
		BorderLayout BL = new BorderLayout();
		BL.addLayoutComponent(m_canvas, BorderLayout.CENTER);
		this.add(m_canvas);

		BL.addLayoutComponent(m_HUD.m_West, BorderLayout.WEST);
		BL.addLayoutComponent(m_HUD.m_East, BorderLayout.EAST);

		this.add(m_HUD.m_East);
		this.add(m_HUD.m_West);

		return BL;
	}

	public Coords toGridCoord(Coords c) {
		Grid g = Model.getModel().getGrid();
		int Rx, Ry;
		double offX = c.X + m_viewPort.getOffsetX() - m_viewPort.getOffsetWindowX();
		double offY = c.Y + m_viewPort.getOffsetY() - m_viewPort.getOffsetWindowY();
		offX = offX / m_viewPort.getCaseWidth();
		offY = offY / m_viewPort.getCaseHeight();
		Rx = m_viewPort.getX() + 2 + (int) offX;
		Ry = m_viewPort.getY() + 2 + (int) offY;
		return new Coords(g.realX(Rx), g.realY(Ry));
	}

	private void updateAvatars() {
		boolean mustRebuild = false;
		if (m_model.getNbEntities() != m_avatars.size())
			mustRebuild = true;
		HashMap<EntityFactory.MyEntities, LinkedList<Entity>> entities = m_model.getHashEntities();
		for (EntityFactory.MyEntities entity : entities.keySet()) {

			LinkedList<Entity> listEntities = entities.get(entity);
			Iterator<Avatar> iter_avt = m_avatars.iterator();
			Iterator<Entity> iter_ent = listEntities.iterator();

			while (!mustRebuild && iter_avt.hasNext() && iter_ent.hasNext()) {
				Entity currentEntity = iter_ent.next();
				Avatar avatar = iter_avt.next();
				if (currentEntity != avatar.m_entity)
					mustRebuild = true;
			}
		}
		if (mustRebuild) {
			m_avatars = new LinkedList<Avatar>();
			for (EntityFactory.MyEntities entity : entities.keySet()) {
				LinkedList<Entity> listEntities = entities.get(entity);
				for (Entity currentEntity : listEntities) {
					m_avatars.add(AvatarFactory.newAvatar(currentEntity));
				}
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
		// int nb_cells_X = m_model.getGrid().getNbCellsX();
		// int nb_cells_Y = m_model.getGrid().getNbCellsY();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);
		/*
		 * int case_width = width / nb_cells_X; int case_height = height / nb_cells_Y;
		 * 
		 * g.setColor(Color.LIGHT_GRAY); for (int x = 1; x < nb_cells_X; x++)
		 * g.drawLine(x * case_width, 0, x * case_width, height); for (int y = 1; y <
		 * nb_cells_Y; y++) g.drawLine(0, y * case_height, width, y * case_height);
		 * 
		 * updateAvatars(); for (Avatar avatar : m_avatars) { avatar.paint(g,
		 * case_width, case_height); // TODO : revoir la zone avec le viewport plus
		 * tard. }
		 */
		updateAvatars();
		m_viewPort.paint(g, m_avatars);
	}

}
