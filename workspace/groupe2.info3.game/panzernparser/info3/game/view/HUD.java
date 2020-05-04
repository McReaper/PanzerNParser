package info3.game.view;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import javax.swing.ToolTipManager;

import info3.game.model.*;
import info3.game.model.entities.*;
import info3.game.model.entities.EntityFactory.MyEntities;

public class HUD {

	public static final int MINIMAL_WIDTH = 2 * 120;
	public static final int MINIMAL_HEIGHT = 600;

	View m_view;

	JPanel m_West;
	JPanel m_East;

	JPanel m_ammoPanel;
	JPanel m_viewModePanel;
	JLabel m_viewModeLabel;

	JLabel m_mineralsLabel;
	JLabel m_toolsLabel;
	JLabel m_weaponImage;
	ImageIcon[] m_weaponArray;
	JProgressBar m_health;
	JProgressBar m_drone;
	TitledBorder m_ammoTitledBorder;
	TitledBorder m_weaponTitledBorder;

	JLabel m_score;
	JLabel m_level;
	JLabel m_time;
	JLabel m_ammo;
	CompassWidget m_compass;
	JPanel m_upgrade;
	Hashtable<Upgrade, JButton> m_availableUpgrades;

	public HUD(View view) {
		ToolTipManager.sharedInstance().setInitialDelay(0);
		UIManager.put("ToolTip.background", new Color(45, 105, 45));
		LineBorder tooltipBorder = (LineBorder) BorderFactory.createLineBorder(Color.BLACK);
		UIManager.put("ToolTip.border", tooltipBorder);

		m_view = view;

		// La font des futurs labels
		Font fontWest = new Font(null, 0, 15);

		m_West = new JPanel();
		m_West.setBackground(Color.DARK_GRAY);
		m_West.setPreferredSize(new Dimension(120, 150));
		GridLayout GLWest = new GridLayout(2, 0);
		m_West.setLayout(GLWest);

		JPanel MinToolsWeapon = new JPanel();
		BoxLayout BLMintoolsweapon = new BoxLayout(MinToolsWeapon, BoxLayout.Y_AXIS);
		MinToolsWeapon.setLayout(BLMintoolsweapon);
		MinToolsWeapon.setBackground(Color.DARK_GRAY);

		m_weaponArray = new ImageIcon[6];

		m_weaponArray[0] = new ImageIcon(
				new ImageIcon("sprites/Weapon0.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_weaponArray[1] = new ImageIcon(
				new ImageIcon("sprites/Weapon1.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_weaponArray[2] = new ImageIcon(
				new ImageIcon("sprites/Weapon2.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_weaponArray[3] = new ImageIcon(
				new ImageIcon("sprites/VueRessourceIcon.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_weaponArray[4] = new ImageIcon(
				new ImageIcon("sprites/VueEnemyIcon.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_weaponArray[5] = new ImageIcon(
				new ImageIcon("sprites/MarkerSingle.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));

		ImageIcon mineralsIcone = new ImageIcon(
				new ImageIcon("sprites/Minerals.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		ImageIcon toolsIcone = new ImageIcon(
				new ImageIcon("sprites/Electronics.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		JLabel mineralsImage = new JLabel(mineralsIcone);
		mineralsImage.setMaximumSize(new Dimension(100, 0));
		JLabel toolsImage = new JLabel(toolsIcone);
		toolsImage.setMaximumSize(new Dimension(100, 0));
		m_weaponImage = new JLabel(m_weaponArray[0]);

		JPanel parentMineralsPanel = new JPanel();
		JPanel parentToolsPanel = new JPanel();
		parentMineralsPanel.setOpaque(false);
		parentToolsPanel.setOpaque(false);
		BoxLayout parentMineralsLayout = new BoxLayout(parentMineralsPanel, BoxLayout.Y_AXIS);
		BoxLayout parentToolsLayout = new BoxLayout(parentToolsPanel, BoxLayout.Y_AXIS);

		m_mineralsLabel = new JLabel("0");
		m_mineralsLabel.setForeground(Color.RED);
		m_mineralsLabel.setFont(fontWest);
		m_toolsLabel = new JLabel("0");
		m_toolsLabel.setForeground(Color.RED);
		m_toolsLabel.setFont(fontWest);

		m_mineralsLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		m_toolsLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		mineralsImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		toolsImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		m_weaponImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		JPanel mineralsPanel = new JPanel();
		mineralsPanel.setBackground(Color.BLACK);
		Border mineralsPanelBorder = BorderFactory.createLineBorder(Color.GRAY);
		mineralsPanel.setBorder(mineralsPanelBorder);
		mineralsPanel.setMaximumSize(new Dimension(90, 0));
		mineralsPanel.add(m_mineralsLabel);
		JPanel toolsPanel = new JPanel();
		toolsPanel.setBackground(Color.BLACK);
		Border toolsPanelBorder = BorderFactory.createLineBorder(Color.GRAY);
		toolsPanel.setBorder(toolsPanelBorder);
		toolsPanel.setMaximumSize(new Dimension(90, 0));
		toolsPanel.add(m_toolsLabel);

		Border mineralsBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder mineralsTitledBorder = BorderFactory.createTitledBorder(mineralsBorder, "Minerals");
		mineralsTitledBorder.setTitleColor(Color.BLACK);
		mineralsTitledBorder.setTitleJustification(TitledBorder.CENTER);
		parentMineralsPanel.setBorder(mineralsTitledBorder);
		Border electronicsBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder electronicsTitledBorder = BorderFactory.createTitledBorder(electronicsBorder, "Electronics");
		electronicsTitledBorder.setTitleColor(Color.BLACK);
		electronicsTitledBorder.setTitleJustification(TitledBorder.CENTER);
		parentToolsPanel.setBorder(electronicsTitledBorder);
		Border weaponBorder = BorderFactory.createLineBorder(Color.BLACK);
		m_weaponTitledBorder = BorderFactory.createTitledBorder(weaponBorder, "Weapon :");
		m_weaponTitledBorder.setTitleColor(Color.BLACK);
		m_weaponTitledBorder.setTitleJustification(TitledBorder.CENTER);
		m_weaponImage.setMaximumSize(new Dimension(100, 0));
		m_weaponImage.setBorder(m_weaponTitledBorder);

		parentMineralsPanel.setLayout(parentMineralsLayout);
		parentToolsPanel.setLayout(parentToolsLayout);

		parentMineralsPanel.add(Box.createVerticalGlue());
		parentMineralsPanel.add(mineralsImage);
		parentMineralsPanel.add(Box.createVerticalGlue());
		parentMineralsPanel.add(mineralsPanel);
		parentMineralsPanel.add(Box.createVerticalGlue());
		parentToolsPanel.add(Box.createVerticalGlue());
		parentToolsPanel.add(toolsImage);
		parentToolsPanel.add(Box.createVerticalGlue());
		parentToolsPanel.add(toolsPanel);
		parentToolsPanel.add(Box.createVerticalGlue());

		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(parentMineralsPanel);
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(parentToolsPanel);
		MinToolsWeapon.add(Box.createVerticalGlue());
		MinToolsWeapon.add(m_weaponImage);
		MinToolsWeapon.add(Box.createVerticalGlue());

//		Init de m_HPStamina et de son BL		
		JPanel HPStamina = new JPanel();
		BoxLayout BLWestSorth = new BoxLayout(HPStamina, BoxLayout.Y_AXIS);
		HPStamina.setLayout(BLWestSorth);
		HPStamina.setBackground(Color.DARK_GRAY);

//		m_HPStamina avec les deux barres d'HP et DP
//		m_HPStamina.setPreferredSize(new Dimension(100, 100));

		BasicProgressBarUI progressUIHealth = new BasicProgressBarUI();
		BasicProgressBarUI progressUIDrone = new BasicProgressBarUI();

		HPStamina.add(Box.createVerticalGlue());
		LineBorder progressBarBorder = (LineBorder) BorderFactory.createLineBorder(Color.BLACK, 3);
		m_health = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		m_health.setUI(progressUIHealth);
		m_health.setBorder(null);
		m_health.setBorder(progressBarBorder);
		m_health.setForeground(Color.RED);
		m_health.setBackground(Color.GRAY);
		m_health.setMaximumSize(new Dimension(50, 200));
		m_health.setValue(100);
		HPStamina.add(m_health);
		HPStamina.add(Box.createVerticalGlue());
		m_drone = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		m_drone.setUI(progressUIDrone);
		m_drone.setBorder(progressBarBorder);
		m_drone.setForeground(Color.YELLOW);
		m_drone.setBackground(Color.GRAY);
		m_drone.setMaximumSize(new Dimension(50, 200));
		m_drone.setValue(50);
		HPStamina.add(m_drone);
		HPStamina.add(Box.createVerticalGlue());
		m_West.add(HPStamina);
		m_West.add(MinToolsWeapon);

		// ATH droit

		// La font des futurs labels
		Font font = new Font(null, 0, 20);

		m_East = new JPanel();
		m_East.setBackground(Color.DARK_GRAY);
		m_East.setPreferredSize(new Dimension(120, 150));
		BoxLayout BLEast = new BoxLayout(m_East, BoxLayout.Y_AXIS);
		m_East.setLayout(BLEast);

		// La boussole
		m_compass = new CompassWidget();
		m_compass.setBackground(new Color(18, 16, 38));
		m_compass.setForeground(Color.BLACK);
		Dimension CompassSize = new Dimension(120, 120);
		m_compass.setMinimumSize(CompassSize);
		m_compass.setMaximumSize(CompassSize);
		m_compass.setPreferredSize(CompassSize);

		m_East.add(m_compass);

		// Zone des stats
		JPanel Stats = new JPanel();
		Stats.setBackground(Color.DARK_GRAY);
		Dimension statsDimension = new Dimension(120, 130);
		Stats.setMaximumSize(statsDimension);
		Stats.setPreferredSize(new Dimension(120, 90));

		// Border pour les stats
		Border blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder statsBorder = BorderFactory.createTitledBorder(blackLineBorder, "Stats");
		statsBorder.setTitleColor(Color.BLACK);
		statsBorder.setTitleJustification(TitledBorder.CENTER);
		Stats.setBorder(statsBorder);

		// Label du niveau
		m_level = new JLabel("Level : 45");
		m_level.setForeground(Color.BLACK);
		m_level.setFont(font);
		m_level.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		Stats.add(m_level);

		// Label des points
		m_score = new JLabel("450 pts");
		m_score.setForeground(Color.BLACK);
		m_score.setFont(font);
		m_score.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		Stats.add(m_score);

		// Cadrant de l'horloge
		JPanel timePanel = new JPanel();
		timePanel.setBackground(Color.BLACK);

		// Border de l'horloge
		Border timePanelBorder = BorderFactory.createLineBorder(Color.GRAY);
		timePanel.setBorder(timePanelBorder);
		timePanel.setMaximumSize(new Dimension(90, 20));

		// Label Temps
		m_time = new JLabel("0 : 00");
		m_time.setForeground(Color.RED);
		m_time.setFont(new Font("monospaced", 0, 20));
		m_time.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		timePanel.add(m_time);
		m_East.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 5), new Dimension(0, 10)));
		m_East.add(timePanel);
		m_East.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 5), new Dimension(0, 10)));
		m_East.add(Stats);

		// Espace munition
		m_ammoPanel = new JPanel();
		m_ammoPanel.setBackground(Color.DARK_GRAY);

		// Ajout d'une border à ammo
		Border ammoBorder = BorderFactory.createLineBorder(Color.BLACK);
		m_ammoTitledBorder = BorderFactory.createTitledBorder(ammoBorder, "Ammo");
		m_ammoTitledBorder.setTitleColor(Color.BLACK);
		m_ammoTitledBorder.setTitleJustification(TitledBorder.CENTER);
		m_ammoPanel.setBorder(m_ammoTitledBorder);

		// Label des munitions
		m_ammo = new JLabel("10/10");
		m_ammo.setFont(font);
		m_ammo.setForeground(Color.BLACK);

		m_ammoPanel.add(m_ammo);
		m_ammoPanel.setMaximumSize(new Dimension(120, 30));
		m_East.add(m_ammoPanel);

		// Zone des boutons d'upgrade
		m_upgrade = new JPanel();
		m_upgrade.setBackground(Color.DARK_GRAY);

		// Ajout d'une border à upgrade
		Border upgradeBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder upgradeTitledBorder = BorderFactory.createTitledBorder(upgradeBorder, "Upgrades");
		upgradeTitledBorder.setTitleColor(Color.BLACK);
		upgradeTitledBorder.setTitleJustification(TitledBorder.CENTER);
		m_upgrade.setBorder(upgradeTitledBorder);

		// Ajout d'une border au bouton
		Border inset = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		LineBorder buttonLineBorder = (LineBorder) BorderFactory.createLineBorder(new Color(52, 109, 46));
		Border buttonBorder = BorderFactory.createCompoundBorder(buttonLineBorder, inset);

		// TODO implémenter un affichage dynamique pour les upgrade après avoir
		// implémenté les upgrades
		LinkedList<Upgrade> statUpgrades = Model.getModel().getStatUpgrade();
		LinkedList<JButton> buttons = new LinkedList<JButton>();
		for (Upgrade upg : statUpgrades) {
			int eleCost = upg.getCostElec();
			int minCost = upg.getCostMine();
			upgradeButton upgradeButton = new upgradeButton("Tourelle De", "BONHJOUR",toolsIcone, mineralsIcone, eleCost, minCost);
			upgradeButton.setBorder(buttonBorder);
			upgradeButton.setEnabled(false);
			buttons.add(upgradeButton);
		}

		// Espace bouton
		JPanel statUpgrade = new JPanel();
		statUpgrade.setPreferredSize(new Dimension(110, 500));
		statUpgrade.setBackground(Color.DARK_GRAY);

		for (JButton jButton : buttons) {
			statUpgrade.add(jButton);
		}

		// Le scrollPane
		JScrollPane scrollbutton = new JScrollPane(statUpgrade);
		scrollbutton.setPreferredSize(new Dimension(110, 100));
		scrollbutton.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollbutton.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollbutton.setBorder(null);

		// Une scrollbar
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setPreferredSize(new Dimension(5, 100));
		scrollBar.setBackground(Color.GREEN);
		scrollBar.setForeground(Color.RED);

		// UI Du scrollbar
		BasicScrollBarUI scrollUI = new BasicScrollBarUI() {
			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = new JButton();
				Dimension dim = new Dimension(0, 0);
				button.setMaximumSize(dim);
				button.setMinimumSize(dim);
				button.setPreferredSize(dim);
				return button;
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = new JButton();
				Dimension dim = new Dimension(0, 0);
				button.setMaximumSize(dim);
				button.setMinimumSize(dim);
				button.setPreferredSize(dim);
				return button;
			}

			@Override
			protected void configureScrollBarColors() {
				super.configureScrollBarColors();
				thumbColor = new Color(52, 109, 46);
				trackColor = Color.BLACK;
			}

			@Override
			protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
				if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
					return;
				}

				int w = thumbBounds.width;
				int h = thumbBounds.height;

				g.translate(thumbBounds.x, thumbBounds.y);
				g.setColor(thumbColor);
				g.fillRect(0, 0, w, h);
			}
		};
		scrollBar.setUI(scrollUI);
		scrollBar.setUnitIncrement(30);
		scrollbutton.setVerticalScrollBar(scrollBar);

		m_upgrade.add(scrollbutton);
		m_East.add(m_upgrade);

		m_viewModePanel = new JPanel();
		Border viewModeBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder viewModeTitledBorder = BorderFactory.createTitledBorder(viewModeBorder, "View Mode :");
		viewModeTitledBorder.setTitleColor(Color.BLACK);
		viewModeTitledBorder.setTitleJustification(TitledBorder.CENTER);
		m_viewModePanel.setBorder(viewModeTitledBorder);
		m_viewModePanel.setBackground(Color.DARK_GRAY);
		m_viewModeLabel = new JLabel(m_weaponArray[3]);
		m_viewModePanel.add(m_viewModeLabel);

	}

//TODO
	public void refreshHUD() {
		Model model = Model.getModel();
		Tank tank = model.getTank();
		Drone drone = model.getDrone();
		TankBody tankBody = tank.getBody();
		Turret tankTurret = tank.getTurret();
		Entity played = model.getPlayed();

		// Timer
		long time = model.getTime();
		long seconde = time / 1000;
		long minutes = seconde / 60;
		seconde = seconde % 60;
		String timeString = "";
		if (minutes <= 9) {
			timeString += "0" + minutes;
		} else {
			timeString += minutes;
		}
		if (time % 1000 < 500)
			timeString += ":";
		else
			timeString += " ";
		if (seconde <= 9) {
			timeString += "0" + seconde;
		} else {
			timeString += seconde;
		}

		// Boussole
		m_time.setText(timeString);
		float x = played.getX() + played.getWidth() / 2;
		float y = played.getY() + played.getHeight() / 2;

		m_compass.resetArrow();
		LinkedList<Entity> markers = model.getEntities(MyEntities.Marker);

		for (Entity mark : markers) {
			float xMark = mark.getX();
			float yMark = mark.getY();
			int throughToreW = throughToreW((int) x, (int) xMark);
			int throughToreH = throughToreH((int) y, (int) yMark);
			xMark += model.getGrid().getNbCellsX() * throughToreW;
			yMark += model.getGrid().getNbCellsY() * throughToreH;

			// TODO Si les drapeaux sont plus grand, prendre en compte leur taille
			double angle = (double) Math.atan(((double) (yMark - y)) / ((double) (xMark - x)));
			angle = Math.toDegrees(angle);
			if (xMark < x) {
				angle += 180;
			}
			m_compass.addArrow(null, (int) angle);
		}

		Model.getModel().getDrone();
		m_level.setText("Level : ".concat(Integer.toString(tankBody.getLevel())));

		// Barres HP et Drone
		m_health.setMaximum(tankBody.getMaxHealth());
		m_health.setValue(tankBody.getHealth());
		m_drone.setMaximum(drone.getMaxHealth());
		m_drone.setValue(drone.getHealth());

		// Minerals, Electronics, Weapons et Markers
		m_toolsLabel.setText(Integer.toString(tank.getInventory().getQuantity(MaterialType.ELECTRONIC)));
		m_mineralsLabel.setText(Integer.toString(tank.getInventory().getQuantity(MaterialType.MINERAL)));
		switch (model.getVisionType()) {
			case TANK:
				m_weaponTitledBorder.setTitle("Weapon");
				/*TODO : adapter HUD en fction de Weapon*/
				m_weaponImage.setIcon(m_weaponArray[tankTurret.getIndexWeapon()]);
				m_ammoTitledBorder.setTitle("Ammo");
				m_ammo.setText("10 / 10");
				m_ammoPanel.setBorder(m_ammoTitledBorder);
				m_ammoPanel.repaint();
				m_East.remove(m_viewModePanel);
				m_East.add(m_upgrade);
				m_East.repaint();

				/*
				 * for each m_availableUpgrade as upgrade if upgrade.available() == true
				 * m_upgrade.add(m_availableUpgrade.get(upgrade));
				 */

				break;
			case RESSOURCES:
				m_weaponTitledBorder.setTitle("Marker");
				m_weaponImage.setIcon(m_weaponArray[5]);
				m_ammoTitledBorder.setTitle("Marker");
				m_ammo.setText(markers.size() + " / " + drone.getMaxMarkers());
				m_ammoPanel.setBorder(m_ammoTitledBorder);
				m_ammoPanel.repaint();

				m_East.remove(m_upgrade);
				m_viewModeLabel.setIcon(m_weaponArray[3]);
				m_East.add(m_viewModePanel);
				m_East.repaint();
				break;
			case ENEMIES:
				m_weaponTitledBorder.setTitle("Marker");
				m_weaponImage.setIcon(m_weaponArray[5]);
				m_ammoTitledBorder.setTitle("Marker");
				m_ammo.setText(markers.size() + " / " + drone.getMaxMarkers());
				m_ammoPanel.setBorder(m_ammoTitledBorder);
				m_ammoPanel.repaint();

				m_East.remove(m_upgrade);
				m_viewModeLabel.setIcon(m_weaponArray[4]);
				m_East.add(m_viewModePanel);
				m_East.repaint();
				break;
		}

	}

	public static final int DIRECT = 0;
	public static final int THROUGH_POSITIVE = 1;
	public static final int THROUGH_NEGATIVE = -1;

	int throughToreW(int x, int xMark) {
		int dstXdir = Math.abs(x - xMark);
		int dstXtore = Math.min(x, xMark) + Model.getModel().getGrid().getNbCellsX() - Math.max(x, xMark);
		if (dstXdir <= dstXtore) {
			return DIRECT;
		} else if (xMark < x) {
			return THROUGH_POSITIVE;
		} else {
			return THROUGH_NEGATIVE;
		}
	}

	int throughToreH(int y, int yMark) {
		int dstYdir = Math.abs(y - yMark);
		int dstYtore = Math.min(y, yMark) + Model.getModel().getGrid().getNbCellsY() - Math.max(y, yMark);
		if (dstYdir <= dstYtore) {
			return DIRECT;
		} else if (yMark < y) {
			return THROUGH_POSITIVE;
		} else {
			return THROUGH_NEGATIVE;
		}
	}

	private class upgradeButton extends JButton {

		private boolean m_isLocked;
		private String m_elecCost;
		private String m_mineCost;
		private Image m_electronics;
		private Image m_minerals;
		private String m_text;
		private String m_descript;

		public upgradeButton(String string, String descript, ImageIcon elec, ImageIcon mine, int eCost, int mCost) {
			super();
			m_elecCost = Integer.toString(eCost);
			m_mineCost = Integer.toString(mCost);
			m_text = string;
			m_descript = descript;
			m_electronics = elec.getImage();
			m_minerals = mine.getImage();
			this.setForeground(Color.BLACK);
			this.setBackground(new Color(29, 73, 25));
			this.setPreferredSize(new Dimension(95, 60));
			BasicButtonUI buttonUI = new BasicButtonUI() {
				@Override
				protected void paintButtonPressed(Graphics g, AbstractButton b) {
					Color color = new Color(0F, 0F, 0F, 0.3F);
					g.setColor(color);
					g.fillRect(0, 0, b.getWidth(), b.getHeight());
				}
			};
			this.setUI(buttonUI);
			this.setToolTipText(m_descript);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			int space = getHeight()-20;
			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
			g.drawLine(0, space, getWidth() - 1, space);
			g.drawLine(getWidth() / 2, space, getWidth() / 2, getHeight() - 1);
			g.drawImage(m_minerals, 3, space + 3, 15, 15, null);
			g.drawImage(m_electronics, 3 + getWidth() / 2, space+ 3, 15, 15, null);
			FontRenderContext frc = new FontRenderContext(null, true, false);
			
//			Rectangle2D rect = g.getFont().getStringBounds(m_text, 0, m_text.length(), frc);
//			g.drawString(m_text, (int) ((getWidth() - rect.getWidth()) / 2), (int) ((30 + rect.getHeight()) / 2));
			Rectangle2D rectEle = g.getFont().getStringBounds(m_mineCost, 0, m_mineCost.length(), frc);
			Rectangle2D rectMin = g.getFont().getStringBounds(m_elecCost, 0, m_elecCost.length(), frc);
			g.drawString(m_mineCost, 6 + 15, getHeight() - 5);
			g.drawString(m_elecCost, 6 + 15 + getWidth() / 2, getHeight() - 5);
			paintTitle(g,space);
			if (!isEnabled()) {
				Color color = new Color(0F, 0F, 0F, 0.60F);
				g.setColor(color);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		}

		private void paintTitle(Graphics g,int space) {
			FontRenderContext frc = new FontRenderContext(null, true, false);
			String[] mots = m_text.split(" ");
			Font font = g.getFont();
			Rectangle2D mot1,mot2,mot3;
			int x,y;
			switch(mots.length) {
				case 1:
					font = font.deriveFont(15F);
					mot1 = font.getStringBounds(mots[0], 0, mots[0].length(), frc);
					g.setFont(font);
					y = (int) (space - ((double)(space-mot1.getHeight())/2));
					System.out.println("y");
					x = (int) ((double)(getWidth()-mot1.getWidth())/2);
					g.drawString(mots[0], x, y);
					break;
				case 2:
					font = font.deriveFont(14F);
					mot1 = font.getStringBounds(mots[0], 0, mots[0].length(), frc);
					mot2 = font.getStringBounds(mots[1], 0, mots[1].length(), frc);
					g.setFont(font);
					double space2 = (double)space/2;
					y = (int) (space2 - ((double)(space2 - mot1.getHeight())/2));
					x = (int) ((double)(getWidth()-mot1.getWidth())/2);
					g.drawString(mots[0], x, y-1);
					y = (int) (space - ((space2 - mot2.getHeight())/2));
					x = (int) ((double)(getWidth()-mot2.getWidth())/2);
					g.drawString(mots[1], x, y-1);
					break;
				case 3:
					font = font.deriveFont(12F);
					mot1 = font.getStringBounds(mots[0], 0, mots[0].length(), frc);
					mot2 = font.getStringBounds(mots[1], 0, mots[1].length(), frc);
					mot3 = font.getStringBounds(mots[2], 0, mots[2].length(), frc);
					g.setFont(font);
					double space3 = (double)space/3;
					y = (int) (space3 - ((double)(space3 - mot1.getHeight())/2));
					x = (int) ((double)(getWidth()-mot1.getWidth())/2);
					g.drawString(mots[0], x, y-1);
					y = (int) (space3*2 - ((space3 - mot2.getHeight())/2));
					x = (int) ((double)(getWidth()-mot2.getWidth())/2);
					g.drawString(mots[1], x, y-1);
					y = (int) (space - ((space3 - mot3.getHeight())/2));
					x = (int) ((double)(getWidth()-mot3.getWidth())/2);
					g.drawString(mots[2], x, y-1);
					break;
			}
			
			
		}
	}
}
