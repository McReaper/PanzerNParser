package info3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

import info3.game.automaton.Automaton;
import info3.game.model.entities.EntityFactory.MyEntities;
import info3.game.view.Animation;

public class Menu {

	GameConfiguration m_gameConfig;
	GameMain m_gameMain;
	JPanelImaged m_menu;
	JPanel m_infoMenu;
	JPanel m_mainMenu;
	JPanel m_configMenu;
	Border m_buttonBorder;
	BasicButtonUI m_buttonUI;
	private JPanel m_buttonPanel;
	private JLabel[] m_infos;
	private int m_current;
	private LineBorder m_menuBorder;
	Color m_darkGray;
	Color m_gray;

	Menu(GameMain gameMain, GameConfiguration gameConfiguration) {

		m_darkGray = new Color(130, 130, 130);
		m_gray = new Color(180, 180, 180);

		Border inset = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		LineBorder buttonLineBorder = (LineBorder) BorderFactory.createLineBorder(m_darkGray, 3);
		m_buttonBorder = BorderFactory.createCompoundBorder(buttonLineBorder, inset);
		m_menuBorder = (LineBorder) BorderFactory.createLineBorder(m_darkGray);
		m_gameMain = gameMain;
		m_gameConfig = gameConfiguration;

		m_buttonUI = new BasicButtonUI() {
			@Override
			protected void paintButtonPressed(Graphics g, AbstractButton b) {
				Color color = new Color(0F, 0F, 0F, 0.3F);
				g.setColor(color);
				g.fillRect(0, 0, b.getWidth(), b.getHeight());
			}
		};
		drawConfigMenu();
		drawMainMenu();
		drawInfoMenu();

		m_menu = new JPanelImaged();
		m_menu.setImage(GameConfiguration.SPRITE_PATH + "Menu.png");
		m_menu.setLayout(new BorderLayout());
		m_menu.add(m_mainMenu, BorderLayout.CENTER);
	}

	private void drawConfigMenu() {

		m_configMenu = new JPanel(new BorderLayout());
		m_configMenu.setOpaque(false);

		JPanel configPanel = new JPanel();
		configPanel.setOpaque(false);
		GridLayout configLayout = new GridLayout(MyEntities.values().length + 1, 3);
		configPanel.setLayout(configLayout);

		JPanel entitee = createLabel("Entity");
		JPanel automate = createLabel("Automate");
		JPanel animation = createLabel("Animation");
		configPanel.add(entitee);
		configPanel.add(automate);
		configPanel.add(animation);

		for (MyEntities entity : MyEntities.values()) {

			JPanel labelPanel = createLabel("" + entity);
			JComboBox<Automaton> autoList = createAutList(entity);
			JComboBox<Animation> aniList = createAniList(entity);
			configPanel.add(labelPanel);
			configPanel.add(autoList);
			configPanel.add(aniList);
		}

		JButton back = new JButton("Main menu");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_menu.remove(m_configMenu);
				m_menu.add(m_mainMenu, BorderLayout.CENTER);
				m_gameMain.refresh();
			}
		});
		back.setUI(m_buttonUI);
		back.setBorder(m_buttonBorder);
		back.setBackground(m_gray);
		back.setForeground(Color.BLACK);

		// Retour menu principal
		JPanel backPanel = new JPanel(new FlowLayout());
		backPanel.setOpaque(false);
		backPanel.setPreferredSize(new Dimension(500, 100));
		backPanel.add(back);

		m_configMenu.add(backPanel, BorderLayout.SOUTH);
		m_configMenu.add(Box.createVerticalStrut(100), BorderLayout.NORTH);
		m_configMenu.add(Box.createHorizontalStrut(30), BorderLayout.EAST);
		m_configMenu.add(Box.createHorizontalStrut(30), BorderLayout.WEST);
		m_configMenu.add(configPanel);
	}

	private JPanel createLabel(String string) {
		JPanel labelPanel = new JPanel();

		labelPanel.setBackground(m_gray);
		labelPanel.setBorder(m_menuBorder);
		JLabel entite = new JLabel(string);
		entite.setHorizontalAlignment(JLabel.CENTER);
		entite.setForeground(Color.BLACK);
		labelPanel.add(entite);
		return labelPanel;
	}

	private JComboBox<Automaton> createAutList(MyEntities entity) {
		int i = 0;
		int selectIndex = 0;
		Automaton automate = GameConfiguration.getConfig().getAutomaton(entity);
		HashMap<String, Automaton> automatonsAvailable = m_gameConfig.getAutomatonsAvailable();
		HashMap<MyEntities, Automaton> automatons = m_gameConfig.getAutomatonsConfig();
		Automaton[] autom = new Automaton[automatonsAvailable.size()];
		for (Automaton automaton : automatonsAvailable.values()) {
			autom[i] = automaton;
			if (automate == automaton) {
				selectIndex = i;
			}
			i++;
		}
		i = 0;
		JComboBox<Automaton> autComboBox = new JComboBox<Automaton>(autom);
		autComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<Automaton> cb = (JComboBox<Automaton>) e.getSource();
				Automaton aut = (Automaton) cb.getSelectedItem();
				automatons.put(entity, aut);
			}
		});

		ComboBoxUI cbui = autComboBox.getUI();
		int size = cbui.getAccessibleChildrenCount(autComboBox);
		for (int j = 0; j < size; j++) {
			Object child = cbui.getAccessibleChild(autComboBox, j);
			JComponent c = (JComponent) ((Container) child).getComponent(0);
			if (c instanceof JScrollPane) {
				((JScrollPane) c).setVerticalScrollBar(createScrollBar());
			}
		}

		autComboBox.setBorder(m_menuBorder);
		autComboBox.setBackground(m_gray);
		autComboBox.setSelectedIndex(selectIndex);
		return autComboBox;
	}

	private JComboBox<Animation> createAniList(MyEntities entity) {
		int i = 0;
		int selectIndex = 0;
		Animation anime = GameConfiguration.getConfig().getAnimation(entity);
		HashMap<String, Animation> animationsAvailable = m_gameConfig.getAnimationsAvailable();
		HashMap<MyEntities, Animation> animations = m_gameConfig.getAnimationsConfig();
		Animation[] anima = new Animation[animationsAvailable.size()];
		for (Animation animation : animationsAvailable.values()) {
			anima[i] = animation;
			if (anime == animation) {
				selectIndex = i;
			}
			i++;
		}
		i = 0;
		JComboBox<Animation> aniComboBox = new JComboBox<Animation>(anima);
		aniComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<Animation> cb = (JComboBox<Animation>) e.getSource();
				Animation ani = (Animation) cb.getSelectedItem();
				animations.put(entity, ani);
			}
		});
		aniComboBox.setBorder(m_menuBorder);
		aniComboBox.setBackground(m_gray);
		aniComboBox.setSelectedIndex(selectIndex);
		return aniComboBox;
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

	public void drawInfoMenu() {
		m_infoMenu = new JPanel();
		m_infoMenu.setOpaque(false);
		m_infoMenu.setLayout(new BorderLayout());

		m_buttonPanel = new JPanel(new FlowLayout());
		m_buttonPanel.setOpaque(false);
		m_buttonPanel.setPreferredSize(new Dimension(500, 100));

		// Retour menu principal
		JButton back = new JButton("Main menu");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_menu.remove(m_infoMenu);
				m_menu.add(m_mainMenu, BorderLayout.CENTER);
				m_gameMain.refresh();
			}
		});
		back.setUI(m_buttonUI);
		back.setBorder(m_buttonBorder);
		back.setBackground(m_gray);
		back.setForeground(Color.BLACK);

		// Page suivante
		JButton next = new JButton("->");
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_infoMenu.remove(m_infos[m_current]);
				m_current = (m_current + 1) % 3;
				m_infoMenu.add(m_infos[m_current], BorderLayout.CENTER);
				m_gameMain.refresh();
			}
		});
		next.setUI(m_buttonUI);
		next.setBorder(m_buttonBorder);
		next.setBackground(m_gray);
		next.setForeground(Color.BLACK);

		// Page Pr??c??dente
		JButton prev = new JButton("<-");
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_infoMenu.remove(m_infos[m_current]);
				m_current = m_current - 1;
				if (m_current < 0)
					m_current = 2;
				m_infoMenu.add(m_infos[m_current], BorderLayout.CENTER);
				m_gameMain.refresh();
			}
		});
		prev.setUI(m_buttonUI);
		prev.setBorder(m_buttonBorder);
		prev.setBackground(m_gray);
		prev.setForeground(Color.BLACK);

		m_buttonPanel.add(prev);
		m_buttonPanel.add(back);
		m_buttonPanel.add(next);

		Image img1 = null, img2 = null, img3 = null;
		try {
			img1 = ImageIO.read(new File(GameConfiguration.SPRITE_PATH + "InfoMenuRules.png"));
			img1 = img1.getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);
			img2 = ImageIO.read(new File(GameConfiguration.SPRITE_PATH + "InfoMenuControls.png"));
			img2 = img2.getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);
			img3 = ImageIO.read(new File(GameConfiguration.SPRITE_PATH + "InfoMenuHUD.png"));
			img3 = img3.getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);
		} catch (IOException e) {
			GameConfiguration
					.fileNotFound(GameConfiguration.SPRITE_PATH + "InfoMenuRules.png OR InfoMenuControls.png OR InfoMenuHUD.png");
		}
		ImageIcon rulesImg = new ImageIcon(img1);
		ImageIcon controlImg = new ImageIcon(img2);
		ImageIcon HUDImg = new ImageIcon(img3);

		m_infos = new JLabel[3];
		m_infos[0] = new JLabel(rulesImg);
		m_infos[1] = new JLabel(controlImg);
		m_infos[2] = new JLabel(HUDImg);

		m_infoMenu.add(m_infos[0], BorderLayout.CENTER);
		m_infoMenu.add(m_buttonPanel, BorderLayout.SOUTH);
		m_infoMenu.add(Box.createVerticalStrut(30), BorderLayout.NORTH);
		m_infoMenu.add(Box.createHorizontalStrut(100), BorderLayout.EAST);
		m_infoMenu.add(Box.createHorizontalStrut(100), BorderLayout.WEST);
	}

	public void drawMainMenu() {
		m_mainMenu = new JPanel();
		m_mainMenu.setOpaque(false);
		// Le layout en deux partie du menu
		GridLayout mainGrid = new GridLayout(2, 1);
		m_mainMenu.setLayout(mainGrid);

		// Partie haute
		JPanel upperPart = new JPanel();
		upperPart.setOpaque(false);
		upperPart.setBackground(Color.DARK_GRAY);

		// Layout pour l'upperPart
		BoxLayout upperPartLayout = new BoxLayout(upperPart, BoxLayout.Y_AXIS);
		upperPart.setLayout(upperPartLayout);

		// Le bouton launch
		Font fontLaunch = new Font(null, 0, 30);
		JButton launch = new JButton("Launch Game");
		launch.setUI(m_buttonUI);
		launch.setBackground(m_gray);
		launch.setForeground(Color.BLACK);
		launch.setFont(fontLaunch);
		launch.setBorder(m_buttonBorder);
		launch.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		launch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				m_gameMain.launch();
			}

		});

		// Remplissage upperPart
		upperPart.add(Box.createVerticalGlue());
		upperPart.add(launch);
		upperPart.add(Box.createRigidArea(new Dimension(10, 15)));

		// Zone du bas
		JPanel underPart = new JPanel();
		underPart.setOpaque(false);
		underPart.setBackground(Color.DARK_GRAY);

		// Layout du bas
		BoxLayout underPartLayout = new BoxLayout(underPart, BoxLayout.Y_AXIS);
		underPart.setLayout(underPartLayout);

		// Bouton des config
		Font fontConfig = new Font(null, 0, 25);

		// Bouton vers menu des infos
		JButton htp = new JButton("How to play");
		htp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_menu.remove(m_mainMenu);
				m_menu.add(m_infoMenu, BorderLayout.CENTER);
				m_gameMain.refresh();
			}
		});
		htp.setUI(m_buttonUI);
		htp.setFont(fontConfig);
		htp.setBorder(m_buttonBorder);
		htp.setBackground(m_gray);
		htp.setForeground(Color.BLACK);
		htp.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		// Bouton vers menu de config
		JButton config = new JButton("Configuration");
		config.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_menu.remove(m_mainMenu);
				m_menu.add(m_configMenu, BorderLayout.CENTER);
				m_gameMain.refresh();
			}
		});
		config.setUI(m_buttonUI);
		config.setFont(fontConfig);
		config.setBorder(m_buttonBorder);
		config.setBackground(m_gray);
		config.setForeground(Color.BLACK);
		config.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		// Remplissage bas
		underPart.add(Box.createRigidArea(new Dimension(10, 15)));
		underPart.add(htp);
		underPart.add(Box.createVerticalStrut(25));
		underPart.add(config);
		underPart.add(Box.createVerticalGlue());

		// Remplissage menu
		m_mainMenu.add(upperPart);
		m_mainMenu.add(underPart);
	}

	public JPanel getMenu() {
		return m_menu;
	}

	public class JPanelImaged extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		Image m_image;

		public JPanelImaged() {
			super();
			this.setOpaque(false);
		}

		public void setImage(String path) {
			try {
				m_image = (BufferedImage) ImageIO.read(new File(path));
			} catch (IOException e) {
				GameConfiguration.fileNotFound(path);
			}

		}

		@Override
		public void paint(Graphics g) {
			int width = this.getWidth();
			int height = m_image.getHeight(null) * width / m_image.getWidth(null);
			g.drawImage(m_image, 0, 0, width, height, null);
			g.setColor(new Color(67, 67, 67));
			g.fillRect(0, height - 4, width, this.getHeight());
			super.paint(g);
		}

	}

}
