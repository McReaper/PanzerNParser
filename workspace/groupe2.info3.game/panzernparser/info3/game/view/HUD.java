package info3.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

import info3.game.model.MaterialType;
import info3.game.model.Model;
import info3.game.model.Tank;
import info3.game.model.Weapon;
import info3.game.model.Model.VisionType;
import info3.game.model.entities.Drone;
import info3.game.model.entities.Entity;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.model.entities.TankBody;
import info3.game.model.entities.Turret;
import info3.game.model.upgrades.Upgrade;

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
	ImageIcon m_elecImage;
	ImageIcon m_mineImage;

	JLabel m_score;
	JLabel m_level;
	JLabel m_time;
	JLabel m_ammo;
	CompassWidget m_compass;
	JPanel m_upgrade;
	LinkedList<UpgradeButton> m_statButtons;
	LinkedList<UpgradeButton> m_uniqButtons;

	VisionType m_vision;

	public HUD(View view) {
		m_view = view;

		initiateToolTip();

		m_weaponArray = initiateWeaponArray();

		m_mineImage = new ImageIcon(
				new ImageIcon("sprites/Minerals.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		m_elecImage = new ImageIcon(
				new ImageIcon("sprites/Electronics.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));

		m_West = new JPanel();
		m_West.setBackground(Color.DARK_GRAY);
		m_West.setPreferredSize(new Dimension(120, 150));
		GridLayout GLWest = new GridLayout(2, 0);
		m_West.setLayout(GLWest);

		// Panel de l'inventaire
		JPanel MinToolsWeapon = initiateInventoryPanel();

		// Init de m_HPStamina et de son BL
		JPanel HPStamina = initiateLifeEnergyPanel();

		m_West.add(HPStamina);
		m_West.add(MinToolsWeapon);

		// La font des futurs labels
		Font font = new Font(null, 0, 20);

		m_East = new JPanel();
		m_East.setBackground(Color.DARK_GRAY);
		m_East.setPreferredSize(new Dimension(120, 150));
		BoxLayout BLEast = new BoxLayout(m_East, BoxLayout.Y_AXIS);
		m_East.setLayout(BLEast);

		// La boussole
		m_compass = initiateCompass();

		// Zone des stats
		JPanel Stats = initiateStatPanel(font);

		// Cadrant de l'horloge
		JPanel timePanel = initiateHorloge();

		// Espace munition
		m_ammoPanel = initiateAmmoPanel(font);

		// Zone des boutons d'upgrade
		m_upgrade = initiateUpgradePanel();

		m_East.add(m_compass);
		m_East.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 5), new Dimension(0, 10)));
		m_East.add(timePanel);
		m_East.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 5), new Dimension(0, 10)));
		m_East.add(Stats);
		m_East.add(m_ammoPanel);
		m_East.add(m_upgrade);

		m_viewModePanel = initiateViewModePanel();
		refreshHUD();
	}

	private JPanel initiateInventoryPanel() {
		Font font = new Font(null, 0, 15);

		JPanel inventoryPanel = new JPanel();

		BoxLayout BLMintoolsweapon = new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS);

		inventoryPanel.setLayout(BLMintoolsweapon);
		inventoryPanel.setBackground(Color.DARK_GRAY);

		m_weaponImage = new JLabel(m_weaponArray[0]);

		JPanel parentMineralsPanel = createInventoryUnit(m_mineImage, font, "Minerals");
		JPanel parentToolsPanel = createInventoryUnit(m_elecImage, font, "Electronics");

		m_weaponImage.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		m_weaponTitledBorder = createTitleBorder("Weapon");

		m_weaponImage.setMaximumSize(new Dimension(100, 0));
		m_weaponImage.setBorder(m_weaponTitledBorder);

		inventoryPanel.add(Box.createVerticalGlue());
		inventoryPanel.add(parentMineralsPanel);
		inventoryPanel.add(Box.createVerticalGlue());
		inventoryPanel.add(parentToolsPanel);
		inventoryPanel.add(Box.createVerticalGlue());
		inventoryPanel.add(m_weaponImage);
		inventoryPanel.add(Box.createVerticalGlue());
		return inventoryPanel;
	}

	private JPanel createInventoryUnit(ImageIcon image, Font font, String string) {
		JPanel inventoryUnit = new JPanel();
		inventoryUnit.setOpaque(false);

		BoxLayout parentMineralsLayout = new BoxLayout(inventoryUnit, BoxLayout.Y_AXIS);
		inventoryUnit.setLayout(parentMineralsLayout);

		Border titledBorder = createTitleBorder(string);
		inventoryUnit.setBorder(titledBorder);

		Dimension imageLabelSize = new Dimension(100, 0);

		JLabel imageLabel = new JLabel(image);
		imageLabel.setMaximumSize(imageLabelSize);
		imageLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.BLACK);
		Border textPanelBorder = BorderFactory.createLineBorder(Color.GRAY);
		textPanel.setBorder(textPanelBorder);
		textPanel.setMaximumSize(new Dimension(90, 0));

		inventoryUnit.add(Box.createVerticalGlue());
		inventoryUnit.add(imageLabel);
		inventoryUnit.add(Box.createVerticalGlue());
		if (string.contentEquals("Minerals")) {
			m_mineralsLabel = new JLabel("0");
			m_mineralsLabel.setForeground(Color.RED);
			m_mineralsLabel.setFont(font);
			m_mineralsLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
			textPanel.add(m_mineralsLabel);
		}
		if (string.contentEquals("Electronics")) {
			m_toolsLabel = new JLabel("0");
			m_toolsLabel.setForeground(Color.RED);
			m_toolsLabel.setFont(font);
			m_toolsLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
			textPanel.add(m_toolsLabel);
		}
		inventoryUnit.add(textPanel);
		inventoryUnit.add(Box.createVerticalGlue());
		return inventoryUnit;
	}

	private JPanel initiateLifeEnergyPanel() {
		JPanel HPStamina = new JPanel();
		BoxLayout BLWestSorth = new BoxLayout(HPStamina, BoxLayout.Y_AXIS);
		HPStamina.setLayout(BLWestSorth);
		HPStamina.setBackground(Color.DARK_GRAY);

		LineBorder progressBarBorder = (LineBorder) BorderFactory.createLineBorder(Color.BLACK, 3);
		m_health = createProgressBar(Color.RED, progressBarBorder);
		m_drone = createProgressBar(Color.YELLOW, progressBarBorder);
		HPStamina.add(Box.createVerticalGlue());
		HPStamina.add(m_health);
		HPStamina.add(Box.createVerticalGlue());
		HPStamina.add(m_drone);
		HPStamina.add(Box.createVerticalGlue());
		return HPStamina;
	}

	private JProgressBar createProgressBar(Color color, Border border) {
		BasicProgressBarUI progressUI = new BasicProgressBarUI();
		JProgressBar progress = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		progress.setUI(progressUI);
		progress.setBorder(null);
		progress.setBorder(border);
		progress.setForeground(color);
		progress.setBackground(Color.GRAY);
		progress.setMaximumSize(new Dimension(50, 200));
		progress.setValue(100);
		return progress;
	}

	private JPanel initiateViewModePanel() {
		JPanel viewModePanel = new JPanel();

		Border viewModeBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder viewModeTitledBorder = BorderFactory.createTitledBorder(viewModeBorder, "View Mode");
		viewModeTitledBorder.setTitleColor(Color.BLACK);
		viewModeTitledBorder.setTitleJustification(TitledBorder.CENTER);

		viewModePanel.setBorder(viewModeTitledBorder);
		viewModePanel.setBackground(Color.DARK_GRAY);

		m_viewModeLabel = new JLabel(m_weaponArray[3]);
		viewModePanel.add(m_viewModeLabel);

		return viewModePanel;
	}

	private JPanel initiateAmmoPanel(Font font) {
		JPanel ammoPanel = new JPanel();
		ammoPanel.setBackground(Color.DARK_GRAY);

		// Ajout d'une border à ammo
		Border ammoBorder = BorderFactory.createLineBorder(Color.BLACK);
		m_ammoTitledBorder = BorderFactory.createTitledBorder(ammoBorder, "Ammo");
		m_ammoTitledBorder.setTitleColor(Color.BLACK);
		m_ammoTitledBorder.setTitleJustification(TitledBorder.CENTER);

		ammoPanel.setBorder(m_ammoTitledBorder);

		// Label des munitions
		m_ammo = new JLabel("10/10");
		m_ammo.setFont(font);
		m_ammo.setForeground(Color.BLACK);

		ammoPanel.add(m_ammo);
		ammoPanel.setMaximumSize(new Dimension(120, 30));
		return ammoPanel;
	}

	private JPanel initiateStatPanel(Font font) {
		JPanel stats = new JPanel();
		stats.setBackground(Color.DARK_GRAY);
		stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
		Dimension statsDimension = new Dimension(120, 80);
		stats.setMaximumSize(statsDimension);
		stats.setPreferredSize(statsDimension);

		// Border pour les stats
		Border blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder statsBorder = BorderFactory.createTitledBorder(blackLineBorder, "Stats");
		statsBorder.setTitleColor(Color.BLACK);
		statsBorder.setTitleJustification(TitledBorder.CENTER);
		stats.setBorder(statsBorder);

		// Label du niveau
		m_level = new JLabel("Level : 45");
		m_level.setForeground(Color.BLACK);
		m_level.setFont(font);
		m_level.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		stats.add(m_level);

		// Label des points
		m_score = new JLabel("450 pts");
		m_score.setForeground(Color.BLACK);
		m_score.setFont(font);
		m_score.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		stats.add(m_score);
		return stats;
	}

	private CompassWidget initiateCompass() {
		CompassWidget compass = new CompassWidget();
		compass.setBackground(new Color(18, 16, 38));
		compass.setForeground(Color.BLACK);
		Dimension CompassSize = new Dimension(120, 120);
		compass.setMinimumSize(CompassSize);
		compass.setMaximumSize(CompassSize);
		compass.setPreferredSize(CompassSize);
		return compass;
	}

	private JPanel initiateUpgradePanel() {
		JPanel upgradePanel = new JPanel();
		upgradePanel.setLayout(new BoxLayout(upgradePanel, BoxLayout.Y_AXIS));
		upgradePanel.setBackground(Color.DARK_GRAY);

		// Le Titre
		JLabel upgradeLabel = new JLabel("UPGRADES");
		upgradeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		upgradeLabel.setForeground(Color.BLACK);
		upgradeLabel.setFont(new Font("monospaced", Font.BOLD, 16));

		initiateUpgradeButtons();

		// Le Layout
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.insets = new Insets(5, 0, 5, 0);

		// Espace bouton
		JPanel statUpgrade = new JPanel();
		statUpgrade.setBackground(Color.DARK_GRAY);
		statUpgrade.setLayout(gbl);

		for (JButton jButton : m_statButtons) {
			statUpgrade.add(jButton, gbc);
		}

		JPanel uniqUpgrade = new JPanel();
		uniqUpgrade.setBackground(Color.DARK_GRAY);
		uniqUpgrade.setLayout(gbl);

		for (JButton jButton : m_uniqButtons) {
			uniqUpgrade.add(jButton, gbc);
		}

		JScrollPane statScrollButton = createScrollPane(statUpgrade);
		JScrollPane uniqScrollButton = createScrollPane(uniqUpgrade);

		TitledBorder improvementTitledBorder = createTitleBorder("Improvement");
		statScrollButton.setBorder(improvementTitledBorder);
		TitledBorder gadgetTitledBorder = createTitleBorder("Gadget");
		uniqScrollButton.setBorder(gadgetTitledBorder);

		// ScrollBar personnalisé
		JScrollBar statScrollBar = createScrollBar();
		statScrollButton.setVerticalScrollBar(statScrollBar);
		JScrollBar uniqScrollBar = createScrollBar();
		uniqScrollButton.setVerticalScrollBar(uniqScrollBar);

		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setForeground(Color.BLACK);

		upgradePanel.add(Box.createVerticalStrut(10));
		upgradePanel.add(sep);
		upgradePanel.add(Box.createVerticalStrut(10));
		upgradePanel.add(upgradeLabel);
		upgradePanel.add(Box.createVerticalStrut(5));
		upgradePanel.add(statScrollButton);
		upgradePanel.add(uniqScrollButton);
		return upgradePanel;
	}

	private JScrollBar createScrollBar() {
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setPreferredSize(new Dimension(5, 100));

		// UI Du scrollbar
		BasicScrollBarUI statScrollUI = new BasicScrollBarUI() {
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
				thumbColor = new Color(150, 150, 150);
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
		scrollBar.setUI(statScrollUI);
		scrollBar.setUnitIncrement(30);
		return scrollBar;
	}

	private TitledBorder createTitleBorder(String string) {
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, string);
		titledBorder.setTitleColor(Color.BLACK);
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		return titledBorder;
	}

	private JScrollPane createScrollPane(JPanel panel) {
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(110, 300));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(Color.DARK_GRAY);
		return scrollPane;
	}

	private void initiateUpgradeButtons() {
		LinkedList<Upgrade> statUpgrades = Model.getModel().getStatUpgrade();
		m_statButtons = new LinkedList<UpgradeButton>();
		for (Upgrade upg : statUpgrades) {
			UpgradeButton upgradeButton = new UpgradeButton(upg, m_elecImage, m_mineImage);
			m_statButtons.add(upgradeButton);
		}
		LinkedList<Upgrade> uniqUpgrades = Model.getModel().getUniqUpgrade();
		m_uniqButtons = new LinkedList<UpgradeButton>();
		for (Upgrade upg : uniqUpgrades) {
			UpgradeButton upgradeButton = new UpgradeButton(upg, m_elecImage, m_mineImage);
			m_uniqButtons.add(upgradeButton);
		}
	}

	private JPanel initiateHorloge() {
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
		return timePanel;
	}

	private ImageIcon[] initiateWeaponArray() {
		ImageIcon[] array = new ImageIcon[6];

		array[0] = new ImageIcon(
				new ImageIcon("sprites/Weapon0.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		array[1] = new ImageIcon(
				new ImageIcon("sprites/Weapon1.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		array[2] = new ImageIcon(
				new ImageIcon("sprites/Weapon2.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		array[3] = new ImageIcon(
				new ImageIcon("sprites/VueRessourceIcon.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		array[4] = new ImageIcon(
				new ImageIcon("sprites/VueEnemyIcon.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		array[5] = new ImageIcon(
				new ImageIcon("sprites/MarkerSingle.png").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
		return array;
	}

	private void initiateToolTip() {
		ToolTipManager.sharedInstance().setInitialDelay(0);
		UIManager.put("ToolTip.background", new Color(150, 150, 150));
		LineBorder tooltipBorder = (LineBorder) BorderFactory.createLineBorder(Color.BLACK);
		UIManager.put("ToolTip.border", tooltipBorder);
	}

	public void refreshHUD() {
		Model model = Model.getModel();
		Tank tank = model.getTank();
		Drone drone = model.getDrone();
		TankBody tankBody = tank.getBody();
		VisionType vision = model.getVisionType();

		// Timer
		updateTime();

		// Boussole
		updateBoussole();

		Model.getModel().getDrone();
		m_level.setText("Level : ".concat(Integer.toString(Model.getModel().getLevel())));

		// Barres HP et Drone
		m_health.setMaximum(tankBody.getMaxHealth());
		m_health.setValue(tankBody.getHealth());
		m_drone.setMaximum(drone.getMaxHealth());
		m_drone.setValue(drone.getHealth());

		// Level
		m_level.setText("Level : " + Integer.toString(Model.getModel().getLevel()));

		// Score
		m_score.setText(Integer.toString(model.getScore().getScore()));

		// Minerals, Electronics, Weapons et Markers
		m_toolsLabel.setText(Integer.toString(tank.getInventory().getQuantity(MaterialType.ELECTRONIC)));
		m_mineralsLabel.setText(Integer.toString(tank.getInventory().getQuantity(MaterialType.MINERAL)));

		if (vision != m_vision) {
			updateATHOrganisation(m_vision, vision);
			m_vision = vision;
		}
		updateCurrentATH();
		m_view.m_canvas.requestFocusInWindow();
	}

	private void updateCurrentATH() {
		Model model = Model.getModel();
		Tank tank = model.getTank();
		Turret tankTurret = tank.getTurret();
		Weapon weapon = tankTurret.getWeapon();
		Drone drone = model.getDrone();
		switch (m_vision) {
			case TANK:
				m_weaponImage.setIcon(m_weaponArray[tankTurret.getIndexWeapon()]);
				m_ammo.setText(weapon.getNbShotLeft() + "/" + weapon.getCapacity());
				updateButtons();
				break;
			case ENEMIES:
				m_ammo.setText(drone.getNbMarker() + "/" + drone.getMaxMarkers());
			case RESSOURCES:
				m_ammo.setText(drone.getNbMarker() + "/" + drone.getMaxMarkers());
		}
	}

	private void updateButtons() {
		for (UpgradeButton button : m_statButtons) {
			button.setEnabled(button.getUpgrade().isAvaible());
			button.updatePrice();
		}
		for (UpgradeButton button : m_uniqButtons) {
			button.setEnabled(button.getUpgrade().isAvaible());
			button.updatePrice();
		}
	}

	private void updateATHOrganisation(VisionType prev, VisionType next) {
		if ((prev == VisionType.RESSOURCES || prev == VisionType.ENEMIES) && next == VisionType.TANK) {
			m_weaponTitledBorder.setTitle("Weapon");
			m_ammoTitledBorder.setTitle("Ammo");
			m_East.remove(m_viewModePanel);
			m_East.add(m_upgrade);
		}
		if (prev == VisionType.TANK && (next == VisionType.RESSOURCES || next == VisionType.ENEMIES)) {
			m_weaponTitledBorder.setTitle("Marker");
			m_weaponImage.setIcon(m_weaponArray[5]);
			m_ammoTitledBorder.setTitle("Marker");
			m_East.remove(m_upgrade);
			m_East.add(m_viewModePanel);
		}
		if (prev == VisionType.ENEMIES && next == VisionType.RESSOURCES) {
			m_viewModeLabel.setIcon(m_weaponArray[3]);
		}
		if (next == VisionType.ENEMIES && prev == VisionType.RESSOURCES) {
			m_viewModeLabel.setIcon(m_weaponArray[4]);
		}
		m_East.invalidate();
		m_East.validate();
		m_East.repaint();
	}

	private void updateBoussole() {
		Model model = Model.getModel();
		Entity played = model.getPlayed();
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

			double angle = (double) Math.atan(((double) (yMark - y)) / ((double) (xMark - x)));
			angle = Math.toDegrees(angle);
			if (xMark < x) {
				angle += 180;
			}
			m_compass.addArrow(null, (int) angle);
		}
	}

	private void updateTime() {
		long time = Model.getModel().getTime();
		long seconde = time / 1000;
		long minute = seconde / 60;
		seconde = seconde % 60;
		long heure = minute / 60;
		minute = minute % 60;
		long digit1, digit2;
		if (heure > 0) {
			digit1 = heure;
			digit2 = minute;
		} else {
			digit1 = minute;
			digit2 = seconde;
		}
		String timeString = "";
		if (digit1 <= 9) {
			timeString += "0" + digit1;
		} else {
			timeString += digit1;
		}
		if (time % 1000 < 500)
			timeString += ":";
		else
			timeString += " ";
		if (digit2 <= 9) {
			timeString += "0" + digit2;
		} else {
			timeString += digit2;
		}
		m_time.setText(timeString);
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

	private class UpgradeButton extends JButton {

		private static final long serialVersionUID = 1L;
		private String m_elecCost;
		private String m_mineCost;
		private Image m_electronics;
		private Image m_minerals;
		private String m_text;
		private String m_descript;
		private Upgrade m_upgrade;
		private String m_level;

		public UpgradeButton(Upgrade upg, ImageIcon elec, ImageIcon mine) {
			super();
			m_upgrade = upg;
			m_elecCost = Integer.toString(upg.getCostElec());
			m_mineCost = Integer.toString(upg.getCostMine());
			m_level = "Level : " + Integer.toString(upg.getLevel());
			m_text = upg.getName();
			m_descript = upg.getDescription();
			m_electronics = elec.getImage();
			m_minerals = mine.getImage();
			this.setForeground(Color.BLACK);
			this.setBackground(new Color(150, 150, 150));
			this.setPreferredSize(new Dimension(95, 75));
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
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					m_view.m_controller.upgradeClicked(m_upgrade);
				}
			});
			setBorder(null);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			int space = getHeight() - 35;
			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
			g.drawLine(0, space, getWidth() - 1, space);
			g.drawLine(0, space + 15, getWidth() - 1, space + 15);
			g.drawLine(getWidth() / 2, space + 15, getWidth() / 2, getHeight() - 1);
			g.drawImage(m_minerals, 3, space + 15 + 3, 15, 15, null);
			g.drawImage(m_electronics, 3 + getWidth() / 2, space + 15 + 3, 15, 15, null);
			FontRenderContext frc = new FontRenderContext(null, true, false);
			Rectangle2D rect = g.getFont().getStringBounds(m_level, 0, m_level.length(), frc);
			g.drawString(m_level, (int) ((getWidth() - rect.getWidth()) / 2), space + 15 - 2);
			g.drawString(m_mineCost, 6 + 15, getHeight() - 5);
			g.drawString(m_elecCost, 6 + 15 + getWidth() / 2, getHeight() - 5);
			paintTitle(g, space);
			if (!isEnabled()) {
				Color color = new Color(0F, 0F, 0F, 0.60F);
				g.setColor(color);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
			paintBorder(g);
		}

		private void paintTitle(Graphics g, int space) {
			FontRenderContext frc = new FontRenderContext(null, true, false);
			String[] mots = m_text.split(" ");
			Font font = g.getFont();
			Rectangle2D mot1, mot2, mot3;
			int x, y;
			switch (mots.length) {
				case 1:
					font = font.deriveFont(15F);
					mot1 = font.getStringBounds(mots[0], 0, mots[0].length(), frc);
					g.setFont(font);
					y = (int) (space - ((double) (space - mot1.getHeight()) / 2));
					System.out.println("y");
					x = (int) ((double) (getWidth() - mot1.getWidth()) / 2);
					g.drawString(mots[0], x, y);
					break;
				case 2:
					font = font.deriveFont(14F);
					mot1 = font.getStringBounds(mots[0], 0, mots[0].length(), frc);
					mot2 = font.getStringBounds(mots[1], 0, mots[1].length(), frc);
					g.setFont(font);
					double space2 = (double) space / 2;
					y = (int) (space2 - ((double) (space2 - mot1.getHeight()) / 2));
					x = (int) ((double) (getWidth() - mot1.getWidth()) / 2);
					g.drawString(mots[0], x, y - 1);
					y = (int) (space - ((space2 - mot2.getHeight()) / 2));
					x = (int) ((double) (getWidth() - mot2.getWidth()) / 2);
					g.drawString(mots[1], x, y - 1);
					break;
				case 3:
					font = font.deriveFont(12F);
					mot1 = font.getStringBounds(mots[0], 0, mots[0].length(), frc);
					mot2 = font.getStringBounds(mots[1], 0, mots[1].length(), frc);
					mot3 = font.getStringBounds(mots[2], 0, mots[2].length(), frc);
					g.setFont(font);
					double space3 = (double) space / 3;
					y = (int) (space3 - ((double) (space3 - mot1.getHeight()) / 2));
					x = (int) ((double) (getWidth() - mot1.getWidth()) / 2);
					g.drawString(mots[0], x, y - 1);
					y = (int) (space3 * 2 - ((space3 - mot2.getHeight()) / 2));
					x = (int) ((double) (getWidth() - mot2.getWidth()) / 2);
					g.drawString(mots[1], x, y - 1);
					y = (int) (space - ((space3 - mot3.getHeight()) / 2));
					x = (int) ((double) (getWidth() - mot3.getWidth()) / 2);
					g.drawString(mots[2], x, y - 1);
					break;
			}

		}

		public Upgrade getUpgrade() {
			return m_upgrade;
		}

		public void updatePrice() {
			m_elecCost = Integer.toString(m_upgrade.getCostElec());
			m_mineCost = Integer.toString(m_upgrade.getCostMine());
		}
	}

	public void refreshUpgrade() {
		m_East.remove(m_upgrade);
		m_upgrade = initiateUpgradePanel();
		m_East.add(m_upgrade);
		
	}
}
