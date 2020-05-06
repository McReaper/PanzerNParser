package info3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS;

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

	Menu(GameMain gameMain, GameConfiguration gameConfiguration) {

		Border inset = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		LineBorder buttonLineBorder = (LineBorder) BorderFactory.createLineBorder(new Color(130, 130, 130), 3);
		m_buttonBorder = BorderFactory.createCompoundBorder(buttonLineBorder, inset);

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
		try {
			drawInfoMenu();
		} catch (IOException e) {
			System.err.println("Info menu could not be loaded");
			e.printStackTrace();
		}

		m_menu = new JPanelImaged();
		m_menu.setImage("sprites/Menu.png");
		m_menu.setLayout(new BorderLayout());
		m_menu.add(m_mainMenu, BorderLayout.CENTER);
	}

	private void drawConfigMenu() {
		m_configMenu = new JPanel(new BorderLayout());
		m_configMenu.setOpaque(false);
		JPanel configPanel = new JPanel();
		configPanel.setOpaque(false);
		GridLayout configLayout = new GridLayout(17, 3);
		configPanel.setLayout(configLayout);

		LineBorder border = (LineBorder) BorderFactory.createLineBorder(Color.BLACK);
		for(MyEntities entity : MyEntities.values()) {
			JPanel labelPanel = new JPanel();
			labelPanel.setBackground(new Color(180,180,180));
			labelPanel.setBorder(border);
			JLabel entite = new JLabel(""+entity);
			entite.setHorizontalAlignment(JLabel.CENTER);
			entite.setForeground(Color.BLACK);
			labelPanel.add(entite);
			JComboBox<String> autoList = createAutList(entity);
			JComboBox<String> aniList = createAniList(entity);
			configPanel.add(labelPanel);
			configPanel.add(autoList);
			configPanel.add(aniList);
		}
		// Retour menu principal
		JPanel backPanel = new JPanel(new FlowLayout());
		backPanel.setOpaque(false);
		backPanel.setPreferredSize(new Dimension(500, 100));
		
		Color buttonColor = new Color(180, 180, 180);
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
		back.setBackground(buttonColor);
		back.setForeground(Color.BLACK);
		backPanel.add(back);
		
		m_configMenu.add(backPanel, BorderLayout.SOUTH);
		m_configMenu.add(Box.createVerticalStrut(100), BorderLayout.NORTH);
		m_configMenu.add(Box.createHorizontalStrut(30), BorderLayout.EAST);
		m_configMenu.add(Box.createHorizontalStrut(30), BorderLayout.WEST);
		m_configMenu.add(configPanel);
	}
	
	private JComboBox<String> createAutList(MyEntities entity) {
		int i = 0;
		int selectIndex = 0;
		Automaton automate = GameConfiguration.getConfig().getAutomaton(entity);
		HashMap<MyEntities, Automaton> m_automatons = m_gameConfig.getAutomatons();
		String[] str = new String[m_automatons.size()];
		for (Automaton automaton : m_automatons.values()) {
			str[i] = automaton.getName();
			if(automate == automaton) {
				selectIndex = i;
			}
			i++;
		}
		i = 0;
		JComboBox<String> autComboBox = new JComboBox<String>(str);
		for (Automaton automaton : m_automatons.values()) {
			autComboBox.setSelectedIndex(i);
			autComboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					m_automatons.put(entity, automaton);
				}
			});
		}
		autComboBox.setSelectedIndex(selectIndex);
		return autComboBox;
	}
	
	private JComboBox<String> createAniList(MyEntities entity) {
		int i = 0;
		int selectIndex = 0;
		Animation anime = GameConfiguration.getConfig().getAnimation(entity);
		HashMap<MyEntities, Animation> m_animations = m_gameConfig.getAnimations();
		String[] str = new String[m_animations.size()];
		for (Animation animation : m_animations.values()) {
			str[i] = animation.getName();
			if(anime == animation) {
				selectIndex = i;
			}
			i++;
		}
		i = 0;
		JComboBox<String> aniComboBox = new JComboBox<String>(str);
		for (Animation animation : m_animations.values()) {
			aniComboBox.setSelectedIndex(i);
			aniComboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					m_animations.put(entity, animation);
				}
			});
		}
		aniComboBox.setSelectedIndex(selectIndex);
		return aniComboBox;
	}

	public void drawInfoMenu() throws IOException {
		m_infoMenu = new JPanel();
		m_infoMenu.setOpaque(false);
		m_infoMenu.setLayout(new BorderLayout());

		Color buttonColor = new Color(180, 180, 180);
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
		back.setBackground(buttonColor);
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
		next.setBackground(buttonColor);
		next.setForeground(Color.BLACK);

		// Page Précédente
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
		prev.setBackground(buttonColor);
		prev.setForeground(Color.BLACK);

		m_buttonPanel.add(prev);
		m_buttonPanel.add(back);
		m_buttonPanel.add(next);

		Image img1 = ImageIO.read(new File("sprites/InfoMenuRules.png"));
		img1 = img1.getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);
		Image img2 = ImageIO.read(new File("sprites/InfoMenuControls.png"));
		img2 = img2.getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);
		Image img3 = ImageIO.read(new File("sprites/InfoMenuHUD.png"));
		img3 = img3.getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);
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
		Color buttonColor = new Color(180, 180, 180);
		launch.setBackground(buttonColor);
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
		htp.setBackground(buttonColor);
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
		config.setBackground(buttonColor);
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
				e.printStackTrace();
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
