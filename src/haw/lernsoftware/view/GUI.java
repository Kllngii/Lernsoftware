package haw.lernsoftware.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import org.apache.log4j.Logger;

import haw.lernsoftware.Konst;
import haw.lernsoftware.model.Model;
import haw.lernsoftware.model.SpeicherService;
import haw.lernsoftware.model.WindowSelect;
import haw.lernsoftware.resources.ResourceProvider;
import haw.lernsoftware.view.liniendiagramm.LinienDiagramm;

/**
 * Die GUI-Klasse definiert den äußersten Container des Fensters
 * @author Lasse Kelling
 *
 */
public class GUI implements ActionListener {
	private SpeicherService sp = new SpeicherService();
	private Model model = sp.ladeModel();

	private Logger log = Logger.getLogger(getClass());

	private JFrame frame;

	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem menuItemSpeichern = new JMenuItem("Speichern");
	private JMenuItem menuItemLaden = new JMenuItem("Laden");
	private JMenuItem menuItemStartseite = new JMenuItem("Startseite");
	private JMenuItem menuItemLiniendiagramm = new JMenuItem("Liniengraph");
	private JMenuItem menuItemAufgabentext = new JMenuItem("Aufgabentext");
	private JMenuItem menuItemHilfe = new JMenuItem("Hilfe");
	private JMenuItem menuItemTutorial = new JMenuItem("Tutorial");
	private JMenuItem menuItemLeicht = new JMenuItem("Leicht");
	private JMenuItem menuItemMittel = new JMenuItem("Mittel");
	private JMenuItem menuItemSchwer = new JMenuItem("Schwer");
	private JButton hilfeButton = new JButton("Hilfe");

	private LinienDiagramm liniendiagrammView = new LinienDiagramm();
	private Startseite startseitenView = new Startseite(this);
	private Aufgabentext aufgabentextView = new Aufgabentext(model, this);
	private Tutorial tutorialView = new Tutorial(this);
	private Tutorial2 tutorial2View = new Tutorial2(this);
	private Tutorial3 tutorial3View = new Tutorial3(this);

	public GUI(JFrame frame) {
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setResizable(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		constructMenubar();
		frame.setJMenuBar(menuBar);
		constructContentPanel();
	}

	/**
	 * Erstellt die Menubar des Fensters.
	 */
	private void constructMenubar() {
		
		JMenu dateiMenü = new JMenu("Datei");
		JMenu fensterMenü = new JMenu("Fenster");
		JMenu fensterAufgabentyp = new JMenu("Aufgaben");

		menuBar.add(dateiMenü);
		menuBar.add(fensterMenü);
		menuBar.add(fensterAufgabentyp);
		menuBar.add(hilfeButton);

		dateiMenü.add(menuItemSpeichern);
		dateiMenü.add(menuItemLaden);
		menuItemSpeichern.addActionListener(this);
		menuItemLaden.addActionListener(this);

		Image hilfeImg = ResourceProvider.loadImage(Konst.HILFE_ICON);
		Image ldImg = ResourceProvider.loadImage(Konst.LINIENDIAGRAMM_ICON);
		Image startseiteImg = ResourceProvider.loadImage(Konst.STARTSEITE_ICON);
		Image aufgabentextImg = ResourceProvider.loadImage(Konst.AUFGABENTEXT_ICON);
		Image SpeichernImg = ResourceProvider.loadImage(Konst.SPEICHERN_ICON);
		Image LadenImg = ResourceProvider.loadImage(Konst.LADEN_ICON);
		Image TutorialImg = ResourceProvider.loadImage(Konst.TUTORIAL_ICON);
		Image LeichtImg = ResourceProvider.loadImage(Konst.LEICHT_ICON);
		Image MittelImg = ResourceProvider.loadImage(Konst.MITTEL_ICON);
		Image SchwerImg = ResourceProvider.loadImage(Konst.SCHWER_ICON);
		menuItemHilfe.setIcon(new ImageIcon(hilfeImg.getScaledInstance(16, 16, 0)));
		menuItemLiniendiagramm.setIcon(new ImageIcon(ldImg.getScaledInstance(16, 16, 0)));
		menuItemStartseite.setIcon(new ImageIcon(startseiteImg.getScaledInstance(16, 16, 0)));
		menuItemAufgabentext.setIcon(new ImageIcon(aufgabentextImg.getScaledInstance(16, 16, 0)));
		menuItemSpeichern.setIcon(new ImageIcon(SpeichernImg.getScaledInstance(16, 16, 0)));
		menuItemLaden.setIcon(new ImageIcon(LadenImg.getScaledInstance(16, 16, 0)));
		menuItemTutorial.setIcon(new ImageIcon(TutorialImg.getScaledInstance(16, 16, 0)));
		menuItemLeicht.setIcon(new ImageIcon(LeichtImg.getScaledInstance(16, 16, 0)));
		menuItemMittel.setIcon(new ImageIcon(MittelImg.getScaledInstance(16, 16, 0)));
		menuItemSchwer.setIcon(new ImageIcon(SchwerImg.getScaledInstance(16, 16, 0)));
		hilfeButton.setIcon(new ImageIcon(hilfeImg.getScaledInstance(16, 16, 0)));	
		hilfeButton.setOpaque(false);
		hilfeButton.setContentAreaFilled(false);
		hilfeButton.setBorderPainted(false);

		fensterMenü.add(menuItemStartseite);
		fensterMenü.add(menuItemLiniendiagramm);
		fensterMenü.add(menuItemAufgabentext);
		fensterMenü.add(menuItemHilfe);
		menuItemStartseite.addActionListener(this);
		menuItemLiniendiagramm.addActionListener(this);
		menuItemAufgabentext.addActionListener(this);
		menuItemHilfe.addActionListener(this);

		hilfeButton.addActionListener(this);
		
		fensterAufgabentyp.add(menuItemTutorial);
		fensterAufgabentyp.add(menuItemLeicht);
		fensterAufgabentyp.add(menuItemMittel);
		fensterAufgabentyp.add(menuItemSchwer);
		menuItemTutorial.addActionListener(this);
		menuItemLeicht.addActionListener(this);
		menuItemMittel.addActionListener(this);
		menuItemSchwer.addActionListener(this);
	}

	/**
	 * Erstellt das ContentPanel des Fensters.
	 */
	private void constructContentPanel() {
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new CardLayout());
		contentPane.add(startseitenView.panel, "startseite");
		contentPane.add(liniendiagrammView.panel, "liniendiagramm");
		contentPane.add(aufgabentextView.panel, "aufgabentext");
		contentPane.add(tutorialView.panel, "tutorial");
		contentPane.add(tutorial2View.panel, "tutorial2");
		contentPane.add(tutorial3View.panel, "tutorial3");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
		if(e.getSource() == menuItemSpeichern) {
			log.debug("Speichere!");
			SpeicherService.speichereInPreferences(Konst.MODEL_KEY, model.toJSON());
		} else if(e.getSource() == menuItemLaden) {
			log.debug("Lade!");
			model = sp.ladeModel();
			layout.show(frame.getContentPane(), model.getSelectedWindow().getIdentifier());
		} else if(e.getSource() == menuItemLiniendiagramm) {
			log.info("Wechsle zum Liniendiagramm");
			this.switchToView(WindowSelect.LINIENDIAGRAMM);
		} else if(e.getSource() == menuItemAufgabentext) {
			log.info("Wechsle zum Aufgabentext");
			this.switchToView(WindowSelect.AUFGABENTEXT);
		} else if(e.getSource() == menuItemStartseite) {
			log.info("Wechsle zur Startseite");
			this.switchToView(WindowSelect.STARTSEITE);
		} else if(e.getSource() == menuItemHilfe) {
			log.info("Öffne das Hilfe-Fenster!");
			new Hilfe();
		} else if(e.getSource() == menuItemTutorial) {
			log.info("Öffne das Tutorial-Fenster!");
			this.switchToView(WindowSelect.TUTORIAL);
		} else if(e.getSource() == menuItemLeicht) {
			log.info("Öffne das Leicht-Fenster!");
			aufgabentextView.setI(0);
			this.switchToView(WindowSelect.AUFGABENTEXT);
		} else if(e.getSource() == menuItemMittel) {
			log.info("Öffne das Mittel-Fenster!");
			aufgabentextView.setI(3);
			this.switchToView(WindowSelect.AUFGABENTEXT);
		} else if(e.getSource() == menuItemSchwer) {
			log.info("Öffne das Schwer-Fenster!");
			aufgabentextView.setI(5);
			this.switchToView(WindowSelect.AUFGABENTEXT);
		}else if(e.getSource() == hilfeButton) {
			log.info("Öffne das Hilfe-Fenster!");
			new Hilfe();
		}
	}

	public void switchToView(WindowSelect ws) {
		CardLayout layout = (CardLayout) frame.getContentPane().getLayout();

		if(ws == WindowSelect.AUFGABENTEXT) {
			log.debug("Wechsle zum Aufgabentext");
			layout.show(frame.getContentPane(), WindowSelect.AUFGABENTEXT.getIdentifier());
			model.setSelectedWindow(WindowSelect.AUFGABENTEXT);
		} else if (ws == WindowSelect.LINIENDIAGRAMM) {
			log.debug("Wechsle zum Liniendiagramm");
			liniendiagrammView.rebase(model.getMengen(), model.getStartMengen(), model.geteMenge());
			layout.show(frame.getContentPane(), WindowSelect.LINIENDIAGRAMM.getIdentifier());
			model.setSelectedWindow(WindowSelect.LINIENDIAGRAMM);
		} else if (ws == WindowSelect.STARTSEITE) {
			log.debug("Wechsle zur Startseite");
			layout.show(frame.getContentPane(), WindowSelect.STARTSEITE.getIdentifier());
			model.setSelectedWindow(WindowSelect.STARTSEITE);
		} else if (ws == WindowSelect.TUTORIAL) {
			log.debug("Wechsle zum Tutorial");
			layout.show(frame.getContentPane(), WindowSelect.TUTORIAL.getIdentifier());
			model.setSelectedWindow(WindowSelect.TUTORIAL);
		} else if (ws == WindowSelect.TUTORIAL2) {
			log.debug("Wechsle zur Tutorial 2");
			layout.show(frame.getContentPane(), WindowSelect.TUTORIAL2.getIdentifier());
			model.setSelectedWindow(WindowSelect.TUTORIAL2);
		} else if (ws == WindowSelect.TUTORIAL3) {
			log.debug("Wechsle zur Tutorial 2");
			layout.show(frame.getContentPane(), WindowSelect.TUTORIAL3.getIdentifier());
			model.setSelectedWindow(WindowSelect.TUTORIAL3);
		}
	}
	
}
