package info3.game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import info3.game.GameConfiguration;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Grid;
import info3.game.model.Model;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Drone;
import info3.game.model.entities.Entity;
import info3.game.model.entities.TankBody;
import info3.game.view.avatars.Avatar;

public class ViewPort {

	public static final int MINIMAL_WIDTH = 562;
	public static final int MINIMAL_HEIGHT = 562;
	private static final Color ENEMI_COLOR = new Color(17, 0, 123, 200);

	private Entity m_player;
	private View m_view;
	private Grid m_grid;
	private int m_width; // Taille du canvas en pixel
	private int m_height;
	private int m_nbCells; // Nombre de cellule du view port en une dimention
	private double m_caseSize; // Taille d'une case en pixel
	private int m_x; // Position du viewPort dans la grid /!\ Marge de 2 autour de ce que l'on voit
	private int m_y;
	private int m_offsetX; // Décalage propre au animations du move du player
	private int m_offsetY;
	private int m_field_of_view;
	private int m_paintSize;
	private int m_offsetWindowX;
	private int m_offsetWindowY;
	private double m_zoom;
	private BufferedImage m_map;
	private BufferedImage m_neutralMap;
	private BufferedImage m_mapRessource;

	public ViewPort(View view) {
		m_view = view;
		m_grid = Model.getModel().getGrid();
		try {
			m_neutralMap = ImageIO.read(new File(GameConfiguration.SPRITE_PATH + "Map1.png"));
			m_mapRessource = ImageIO.read(new File(GameConfiguration.SPRITE_PATH + "Map1.png"));
			ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
			op.filter(m_mapRessource, m_mapRessource);
		} catch (IOException e) {
			GameConfiguration.fileNotFound(GameConfiguration.SPRITE_PATH + "Map1.png");
		}
	}

	public void setPlayer(Entity player) { /// pour quand on passe de done à tank et inversement
		m_player = player;
		m_field_of_view = m_player.getFieldOfView();
		m_nbCells = m_field_of_view * 2; // pour les deux coté du tank
		m_nbCells += m_player.getWidth(); // pour le tank
		m_nbCells += 4; // pour la marge
		int maxCells = Math.min(m_grid.getNbCellsX(), m_grid.getNbCellsY());
		while (m_nbCells >= maxCells) {
			m_nbCells -= 2;
			m_field_of_view--;
		}
	}

	private void calcul() {
		m_width = m_view.m_canvas.getWidth();
		m_height = m_view.m_canvas.getHeight();
		m_paintSize = Math.min(m_height, m_width);
		m_caseSize = ((double) m_paintSize / (m_nbCells + m_zoom - 4));
		// Décalage du aux dimensions de la fenêtre (centrage en y)
		m_offsetWindowX = (m_width - m_paintSize) / 2;
		m_offsetWindowY = (m_height - m_paintSize) / 2;
	}

	private void positionViewPort() {
		m_x = m_player.getX();
		m_x -= m_field_of_view;
		m_x -= 2; // pour la marge
		m_x = m_grid.realX(m_x);
		m_y = m_player.getY();
		m_y -= m_field_of_view;
		m_y -= 2;
		m_y = m_grid.realY(m_y);
	}

	private void doTransitionDarken(Graphics g) {
		int state = Model.getModel().getReloadingState();
		if (state == Model.IN_PLAY) {
			return;
		}
		Color filter;
		double progress = Model.getModel().getReloadProgress();
		if (progress < 0.33) {
			filter = new Color(0F, 0F, 0F, (float) progress * 3);
		} else if (progress >= 0.33 && progress <= 0.67) {
			filter = Color.BLACK;

		} else {
			filter = new Color(0F, 0F, 0F, (float) Math.abs(progress - 1) * 3);
		}
		g.setColor(filter);
		g.fillRect(0, 0, m_view.m_canvas.getWidth(), m_view.m_canvas.getHeight());
		Font font = new Font("monospaced", Font.BOLD, 45);
		g.setFont(font);
		g.setColor(Color.WHITE);
		String message = "Level up !";
		FontRenderContext frc = new FontRenderContext(null, true, false);
		Rectangle2D rect = font.getStringBounds(message, 0, message.length(), frc);
		int x = (int) ((m_view.m_canvas.getWidth() - rect.getWidth()) / 2);
		int y = (int) ((m_view.m_canvas.getHeight() - rect.getHeight()) / 2);
		g.drawString(message, x, y);
	}

	private void offset() {
		m_offsetX = 0;
		m_offsetY = 0;
		if (m_player.getCurrentAction() == LsAction.Move) {
			double progress = m_player.getActionProgress();
			MyDirection actDir = MyDirection.toAbsolute(m_player.getLookAtDir(), m_player.getCurrentActionDir());
			switch (actDir) {
				case NORTH:
				case NORTHEAST:
				case NORTHWEST:
					m_offsetY -= (m_caseSize * progress) - m_caseSize;
					break;
				case SOUTH:
				case SOUTHEAST:
				case SOUTHWEST:
					m_offsetY += (m_caseSize * progress) - m_caseSize;
					break;
				default:
					break;
			}
			switch (actDir) {
				case EAST:
				case NORTHEAST:
				case SOUTHEAST:
					m_offsetX += (m_caseSize * progress) - m_caseSize;
					break;
				case WEST:
				case NORTHWEST:
				case SOUTHWEST:
					m_offsetX -= (m_caseSize * progress) - m_caseSize;
					break;
				default:
					break;
			}
		}
	}

	private void zoom() {
		if (m_player.getCurrentAction() == LsAction.Jump) {
			double progress = m_player.getActionProgress();
			MyDirection actDir = m_player.getCurrentActionDir();
			switch (actDir) {
				case FRONT:
					m_zoom = progress * 2;
					break;
				case BACK:
					m_zoom = -progress * 2;
				default:
					break;
			}
		}

	}

	private void animationTankDrone() {
		Model model = Model.getModel();
		TankBody tank = model.getTank().getBody();
		Drone drone = model.getDrone();
		if (tank.getCurrentAction() == LsAction.Wizz) {
			int decalage = drone.getFieldOfView() - tank.getFieldOfView();
			double progress = tank.getActionProgress();
			m_field_of_view = (int) (progress * decalage + tank.getFieldOfView());
			m_zoom = (progress * decalage % 1) * 2;
			m_nbCells = m_field_of_view * 2; // pour les deux coté du tank
			m_nbCells += m_player.getWidth(); // pour le tank
			m_nbCells += 4; // pour la marge
		} else {
			m_zoom = 0;
		}
	}

	public double getCaseWidth() {
		return m_caseSize;
	}

	public double getCaseHeight() {
		return m_caseSize;
	}

	public int getOffsetWindowX() {
		return m_offsetWindowX;
	}

	public int getOffsetWindowY() {
		return m_offsetWindowY;
	}

	public int getOffsetX() {
		return m_offsetX;
	}

	public int getOffsetY() {
		return m_offsetY;
	}

	public int getX() {
		return m_x;
	}

	public int getY() {
		return m_y;
	}

	public int getPaintSize() {
		return m_paintSize;
	}

	public void paint(Graphics g, List<Avatar> lsAvatars) {
		Entity play = Model.getModel().getPlayed();
		if (m_player != play || m_field_of_view != m_player.getFieldOfView()) {
			setPlayer(play);
		}
		animationTankDrone();
		zoom();
		calcul();
		positionViewPort();
		offset(); // décalage due au mouve du tank
		int x, y, w, h;

//		g.setColor(Color.BLACK);
//		for (double i = (m_offsetWindowX - m_offsetX + m_zoom * m_caseSize / 2); i < m_paintSize
//				+ m_offsetWindowX; i += m_caseSize)
//			g.drawLine((int) i, m_offsetWindowY, (int) i, m_offsetWindowY + m_paintSize);
//		for (double j = (m_offsetWindowY - m_offsetY + m_zoom * m_caseSize / 2); (int) j < m_paintSize
//				+ m_offsetWindowY; j += m_caseSize)
//			g.drawLine(m_offsetWindowX, (int) j, m_offsetWindowX + m_paintSize, (int) j);

		if (Model.getModel().getVisionType() == VisionType.RESSOURCES) {
			m_map = m_mapRessource;
		} else {
			m_map = m_neutralMap;
		}

		drawMap(g);
		if (Model.getModel().getVisionType() == VisionType.ENEMIES) {
			g.setColor(ENEMI_COLOR);
			g.fillRect(0, 0, m_width, m_height);
		}

		LinkedList<Entity> entityList;
		int i = 0;
		for (Avatar avatar : m_view.m_avatars) {
			entityList = Model.getModel().getEntities(m_view.orderEntities.get(i));
			i++;
			for (Entity e : entityList) {
				if (e.isShown()) {
					x = e.getX();
					y = e.getY();
					w = e.getWidth();
					h = e.getHeight();
					int intView = this.inView(x, y, w, h, e);
					if (intView != DO_NOT_PAINT) {
						// position de la case dans le vp
						x -= m_x;
						y -= m_y;
						// pour le décalage
						double dx = x - 2 + m_zoom / 2;
						double dy = y - 2 + m_zoom / 2;
						// position en px de la case
						dx *= m_caseSize;
						dy *= m_caseSize;
						// position case en px avec décalage
						dx += m_offsetWindowX - m_offsetX;
						dy += m_offsetWindowY - m_offsetY;
						switch (intView) {
							// Téléportation pour rentrer dans le viewPort
							case PAINT_MOVE_X:
								dx += m_grid.getNbCellsX() * m_caseSize;
								break;
							case PAINT_MOVE_Y:
								dy += m_grid.getNbCellsY() * m_caseSize;
								break;
							case PAINT_MOVE_XY:
								dx += m_grid.getNbCellsX() * m_caseSize;
								dy += m_grid.getNbCellsY() * m_caseSize;
								break;
						}
						avatar.paint(g, e, (int) dx, (int) dy, (int) m_caseSize, (int) m_caseSize);

					}
				}

			}

		}
		// Pour empêcher de regarder plus loin grâce à la redimension de la fenêtre
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, m_width, m_offsetWindowY);
		g.fillRect(0, m_offsetWindowY + m_paintSize, m_width, m_offsetWindowY + 10);
		g.fillRect(0, 0, m_offsetWindowX, m_height);
		g.fillRect(m_offsetWindowX + m_paintSize, 0, m_offsetWindowX + 10, m_height);
		doTransitionDarken(g);
	}

	public void drawMap(Graphics g) {
		int imageCaseSize = 3200 / m_grid.getNbCellsX();
		int imageX = m_x * imageCaseSize;
		int imageY = m_y * imageCaseSize;
		int imageSize = m_nbCells * imageCaseSize;
		double x = m_offsetWindowX - m_offsetX + (m_zoom / 2 - 2) * m_caseSize;
		double y = m_offsetWindowY - m_offsetY + (m_zoom / 2 - 2) * m_caseSize;
		double size = m_nbCells * m_caseSize;
		if (m_x + m_nbCells <= m_grid.getNbCellsX() && m_y + m_nbCells <= m_grid.getNbCellsY()) {
			Image viewmap = m_map.getSubimage(imageX, imageY, imageSize, imageSize);
			g.drawImage(viewmap, (int) x, (int) y, (int) size, (int) size, null);
		}
		if (m_x + m_nbCells > m_grid.getNbCellsX() && m_y + m_nbCells <= m_grid.getNbCellsY()) {
			int intersect = m_grid.getNbCellsX() - m_x;

			Image img1 = m_map.getSubimage(imageX, imageY, intersect * imageCaseSize, imageSize);
			g.drawImage(img1, (int) x + 1, (int) y, (int) (intersect * m_caseSize), (int) size, null);

			Image img2 = m_map.getSubimage(0, imageY, (m_nbCells - intersect) * imageCaseSize, imageSize);
			g.drawImage(img2, (int) (m_offsetWindowX - m_offsetX + (intersect + m_zoom / 2 - 2) * m_caseSize), (int) y,
					(int) ((m_nbCells - intersect) * m_caseSize), (int) size, null);
		}
		if (m_x + m_nbCells <= m_grid.getNbCellsX() && m_y + m_nbCells > m_grid.getNbCellsY()) {
			int intersect = m_grid.getNbCellsY() - m_y;

			Image img1 = m_map.getSubimage(imageX, imageY, imageSize, intersect * imageCaseSize);
			g.drawImage(img1, (int) x, (int) y + 1, (int) size, (int) (intersect * m_caseSize), null);

			Image img2 = m_map.getSubimage(imageX, 0, imageSize, (m_nbCells - intersect) * imageCaseSize);
			g.drawImage(img2, (int) x, (int) (m_offsetWindowY - m_offsetY + (intersect + m_zoom / 2 - 2) * m_caseSize),
					(int) size, (int) ((m_nbCells - intersect) * m_caseSize), null);
		}
		if (m_x + m_nbCells > m_grid.getNbCellsX() && m_y + m_nbCells > m_grid.getNbCellsY()) {
			int intersectX = m_grid.getNbCellsX() - m_x;
			int intersectY = m_grid.getNbCellsY() - m_y;

			Image img1 = m_map.getSubimage(imageX, imageY, intersectX * imageCaseSize, intersectY * imageCaseSize);
			g.drawImage(img1, (int) x + 1, (int) y + 1, (int) (intersectX * m_caseSize), (int) (intersectY * m_caseSize),
					null);

			Image img2 = m_map.getSubimage(imageX, 0, intersectX * imageCaseSize, (m_nbCells - intersectY) * imageCaseSize);
			g.drawImage(img2, (int) x + 1, (int) (m_offsetWindowY - m_offsetY + (intersectY + m_zoom / 2 - 2) * m_caseSize),
					(int) (intersectX * m_caseSize), (int) ((m_nbCells - intersectY) * m_caseSize), null);

			Image img3 = m_map.getSubimage(0, imageY, (m_nbCells - intersectX) * imageCaseSize, intersectY * imageCaseSize);
			g.drawImage(img3, (int) (m_offsetWindowX - m_offsetX + (intersectX + m_zoom / 2 - 2) * m_caseSize), (int) y + 1,
					(int) ((m_nbCells - intersectX) * m_caseSize), (int) (intersectY * m_caseSize), null);

			Image img4 = m_map.getSubimage(0, 0, (m_nbCells - intersectX) * imageCaseSize,
					(m_nbCells - intersectY) * imageCaseSize);
			g.drawImage(img4, (int) (m_offsetWindowX - m_offsetX + (intersectX + m_zoom / 2 - 2) * m_caseSize),
					(int) (m_offsetWindowY - m_offsetY + (intersectY + m_zoom / 2 - 2) * m_caseSize),
					(int) ((m_nbCells - intersectX) * m_caseSize), (int) ((m_nbCells - intersectY) * m_caseSize), null);

		}

	}

	private static final int DO_NOT_PAINT = -1;
	private static final int PAINT_HERE = 0;
	private static final int PAINT_MOVE_X = 1;
	private static final int PAINT_MOVE_Y = 2;
	private static final int PAINT_MOVE_XY = 3;

	private int inView(int x, int y, int w, int h, Entity e) {
		int xL = m_x;
		int xR = m_grid.realX(m_x + m_nbCells);
		int yU = m_y;
		int yD = m_grid.realY(m_y + m_nbCells);
		boolean inX = false;
		boolean inY = false;
		int painting = PAINT_HERE;
		if ((x + w) > xL && x < xR) {
			inX = true;
		} else if (xL > xR && ((x + w) > xL || x < xR)) {
			inX = true;
			if (x + w < xL) {
				painting += PAINT_MOVE_X;
			}
		}
		if ((y + h) > yU && y < yD) {
			inY = true;
		} else if (yU > yD && ((y + h) > yU || y < yD)) {
			inY = true;
			if (y + h < yU) {
				painting += PAINT_MOVE_Y;
			}
		}

		if (inX && inY) {
			return painting;
		}
		return DO_NOT_PAINT;
	}

	public boolean isInViewport(int x, int y) {
		return (x >= m_offsetWindowX && x < m_offsetWindowX + m_paintSize && y >= m_offsetWindowY
				&& y < m_offsetWindowY + m_paintSize);
	}

}
