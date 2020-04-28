package info3.game.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.ImageProducer;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.border.Border;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		
		JPanel South = new JPanel();
		South.setBackground(new Color(0,150,20));
		GridLayout GLSouth = new GridLayout(0,3);
		South.setLayout(GLSouth);
		
		JPanel West = new JPanel();
		West.setBackground(Color.DARK_GRAY);
		West.setPreferredSize(new Dimension(150, 150));
		GridLayout GLWest = new GridLayout(2,0);
		West.setLayout(GLWest);
		
		JPanel MinToolsWeapon = new JPanel();
		BoxLayout BLMinToolsWeapon = new BoxLayout(MinToolsWeapon, BoxLayout.Y_AXIS);
		MinToolsWeapon.setLayout(BLMinToolsWeapon);
		MinToolsWeapon.setBackground(Color.DARK_GRAY);//ce
		
		JLabel Minerals = new JLabel(new ImageIcon("sprites/Trou.png"));
		JLabel Tools = new JLabel(new ImageIcon("sprites/Trou.png"));
		JLabel Weapon = new JLabel(new ImageIcon("sprites/Trou.png"));
		
		JLabel minerals = new JLabel("Minerals : ");
		JLabel tools = new JLabel("Tools : ");
		JLabel weapon = new JLabel("Current Weapon : ");
		
		Minerals.setAlignmentX(CENTER_ALIGNMENT);
		Tools.setAlignmentX(CENTER_ALIGNMENT);
		Weapon.setAlignmentX(CENTER_ALIGNMENT);
		minerals.setAlignmentX(CENTER_ALIGNMENT);
		tools.setAlignmentX(CENTER_ALIGNMENT);
		weapon.setAlignmentX(CENTER_ALIGNMENT);
		
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(minerals);
		MinToolsWeapon.add(Minerals);
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(tools);
		MinToolsWeapon.add(Tools);
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(weapon);
		MinToolsWeapon.add(Weapon);
		MinToolsWeapon.add(Box.createVerticalGlue());
				
//		Init de HPStamina et de son BL		
		JPanel HPStamina = new JPanel();
		BoxLayout BLWestSorth = new BoxLayout(HPStamina, BoxLayout.Y_AXIS);
		HPStamina.setLayout(BLWestSorth);
		HPStamina.setBackground(Color.DARK_GRAY);

//		HPStamina avec les deux barres d'HP et DP
//		HPStamina.setPreferredSize(new Dimension(100, 100));
		HPStamina.add(new Box.Filler(new Dimension(0,0), new Dimension(15,15), new Dimension(15,15)));
		JProgressBar health = new JProgressBar(JProgressBar.VERTICAL,0,100);
		health.setForeground(Color.RED);
		health.setMaximumSize(new Dimension(50,200));
		health.setValue(100);
		HPStamina.add(health);
		HPStamina.add(new Box.Filler(new Dimension(0,0), new Dimension(15,15), new Dimension(15,15)));
		JProgressBar drone = new JProgressBar(JProgressBar.VERTICAL,0,100);
		drone.setForeground(Color.YELLOW);
		drone.setMaximumSize(new Dimension(50,200));
		drone.setValue(50);
		HPStamina.add(drone);
		HPStamina.add(new Box.Filler(new Dimension(0,0), new Dimension(15,15), new Dimension(15,15)));
		West.add(HPStamina);
		West.add(MinToolsWeapon);
		
		BL.addLayoutComponent(West, BorderLayout.WEST);
		BL.addLayoutComponent(South, BorderLayout.SOUTH);
		
//		Init de SouthCenter et des bouton d'upgrade
		JPanel SouthCenter = new JPanel();
		
		this.add(West);
		
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
