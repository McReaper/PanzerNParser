package info3.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.util.LinkedList;
import info3.game.GameConfiguration;
import info3.game.controller.Controller;
import info3.game.model.Grid;
import info3.game.model.Grid.Coords;
import info3.game.model.MaterialType;
import info3.game.model.Model;
import info3.game.model.entities.EntityFactory.MyEntities;

public class View extends Container {

	private static final long serialVersionUID = 1L;
	public GameCanvas m_canvas;
	public Controller m_controller;
	Model m_model;
	LinkedList<Avatar> m_avatars;// type d'avatar
	ViewPort m_viewPort;
	public HUD m_HUD;
	public LinkedList<MyEntities> orderEntities;

	public View(Controller controller, Model model) {
		// créer la fenetre de jeu avec les bandeaux d'updrage et le canvas.
		m_controller = controller;
		m_model = model;
		m_canvas = new GameCanvas(m_controller);
		orderEntities = new LinkedList<MyEntities>();
		m_HUD = new HUD(this);
		BorderLayout BL = initiateHUD();

		this.setLayout(BL);
		m_avatars = new LinkedList<Avatar>();
		initAvatars();
		m_viewPort = new ViewPort(m_model.getPlayed(), this);
	}

	/*
	 * liste d'avatar devient une liste d'avatar representant une categorie d'entité
	 * au lieu du nombre d'entité
	 */
	private void initAvatars() {
		GameConfiguration config = GameConfiguration.getConfig();
		m_avatars.add(new Avatar(config.getAnimation(MyEntities.Ground)));
		orderEntities.add(MyEntities.Ground);
		m_avatars.add(new VeinAvatar(config.getAnimation(MyEntities.Vein)));
		orderEntities.add(MyEntities.Vein);
		m_avatars.add(new Avatar(config.getAnimation(MyEntities.Hole)));
		orderEntities.add(MyEntities.Hole);
		m_avatars.add(new Avatar(config.getAnimation(MyEntities.Mud)));
		orderEntities.add(MyEntities.Mud);
		m_avatars.add(new DroppableAvatar(config.getAnimation(MyEntities.Droppable)));
		orderEntities.add(MyEntities.Droppable);
		m_avatars.add(new Avatar(config.getAnimation(MyEntities.Wall)));
		orderEntities.add(MyEntities.Wall);
		m_avatars.add(new Avatar(config.getAnimation(MyEntities.ShotSlow)));
		orderEntities.add(MyEntities.ShotSlow);
		m_avatars.add(new Avatar(config.getAnimation(MyEntities.ShotFast)));
		orderEntities.add(MyEntities.ShotFast);
		m_avatars.add(new Avatar(config.getAnimation(MyEntities.ShotBig)));
		orderEntities.add(MyEntities.ShotBig);
		m_avatars.add(new EnemyAvatar(config.getAnimation(MyEntities.EnemyBasic)));
		orderEntities.add(MyEntities.EnemyBasic);
		m_avatars.add(new EnemyAvatar(config.getAnimation(MyEntities.EnemyLevel2)));
		orderEntities.add(MyEntities.EnemyLevel2);
		m_avatars.add(new MarkerAvatar(config.getAnimation(MyEntities.Marker)));
		orderEntities.add(MyEntities.Marker);
		m_avatars.add(new TankBodyAvatar(config.getAnimation(MyEntities.TankBody)));
		orderEntities.add(MyEntities.TankBody);
		m_avatars.add(new TurretAvatar(config.getAnimation(MyEntities.Turret)));
		orderEntities.add(MyEntities.Turret);
		m_avatars.add(new DroneAvatar(config.getAnimation(MyEntities.Drone), this));
		orderEntities.add(MyEntities.Drone);
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

	public Coords toGridCoord(Coords c) throws IllegalArgumentException {
		Grid g = Model.getModel().getGrid();
		if (!m_viewPort.isInViewport((int)c.X, (int)c.Y))
			throw new IllegalArgumentException("Clic dans la zone noire !");
		int Rx, Ry;
		double offX = c.X + m_viewPort.getOffsetX() - m_viewPort.getOffsetWindowX();
		double offY = c.Y + m_viewPort.getOffsetY() - m_viewPort.getOffsetWindowY();
		offX = offX / m_viewPort.getCaseWidth();
		offY = offY / m_viewPort.getCaseHeight();
		Rx = m_viewPort.getX() + 2 + (int) offX;
		Ry = m_viewPort.getY() + 2 + (int) offY;
		return new Coords(g.realX(Rx), g.realY(Ry));
	}

	/**
	 * Méthode qui dessine la grille et les entités sur celle-ci.
	 */
	public void paintCanvas(Graphics g) {
		int width = m_canvas.getWidth();
		int height = m_canvas.getHeight();

		g.setColor(new Color(18 *3,16*3,38*3));
		g.fillRect(0, 0, width, height);

		m_viewPort.paint(g, m_avatars);
	}

}
