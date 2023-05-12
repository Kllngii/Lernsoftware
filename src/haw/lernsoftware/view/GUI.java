package haw.lernsoftware.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.apache.log4j.Logger;

import haw.lernsoftware.Konst;
import haw.lernsoftware.model.Model;
import haw.lernsoftware.model.SpeicherService;
import haw.lernsoftware.model.SpeicherService.ModelWithErrors;
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
	private Model model = new Model(sp.ladeAufgaben());

	private Logger log = Logger.getLogger(getClass());

	private JFrame frame;

	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem menuItemSpeichern = new JMenuItem("Speichern");
	private JMenuItem menuItemLaden = new JMenuItem("Laden");
	private JMenuItem menuItemStartseite = new JMenuItem("Startseite");
	private JMenuItem menuItemLiniendiagramm = new JMenuItem("Liniengraph");
	private JMenuItem menuItemAufgabentext = new JMenuItem("Aufgabentext");
	private JMenuItem fensterHilfe = new JMenuItem("Hilfe");
	private JMenuItem menuItemTutorial = new JMenuItem("Tutorial");
	private JMenuItem menuItemLeicht = new JMenuItem("Leicht");
	private JMenuItem menuItemMedium = new JMenuItem("Medium");
	private JMenuItem menuItemSchwer = new JMenuItem("Schwer");

	private LinienDiagramm liniendiagrammView = new LinienDiagramm();
	private Startseite startseitenView = new Startseite(this);
	private Aufgabentext aufgabentextView = new Aufgabentext(model);

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

		dateiMenü.add(menuItemSpeichern);
		dateiMenü.add(menuItemLaden);
		menuItemSpeichern.addActionListener(this);
		menuItemLaden.addActionListener(this);

		Image img = ResourceProvider.loadImage(Konst.HILFE_ICON);
		fensterHilfe.setIcon(new ImageIcon(img.getScaledInstance(16, 16, 0)));

		fensterMenü.add(menuItemStartseite);
		fensterMenü.add(menuItemLiniendiagramm);
		//fensterMenü.add(fensterAufgabentyp);
		fensterMenü.add(menuItemAufgabentext);
		fensterMenü.add(fensterHilfe);
		menuItemStartseite.addActionListener(this);
		menuItemLiniendiagramm.addActionListener(this);
		menuItemAufgabentext.addActionListener(this);
		fensterHilfe.addActionListener(this);
		
		fensterAufgabentyp.add(menuItemTutorial);
		fensterAufgabentyp.add(menuItemLeicht);
		fensterAufgabentyp.add(menuItemMedium);
		fensterAufgabentyp.add(menuItemSchwer);
		menuItemTutorial.addActionListener(this);
		menuItemLeicht.addActionListener(this);
		menuItemMedium.addActionListener(this);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
		if(e.getSource() == menuItemSpeichern) {
			log.debug("Speichere!");
			sp.speichereInPreferences(new ModelWithErrors(model, new ArrayList<String>()));
		} else if(e.getSource() == menuItemLaden) {
			log.debug("Lade!");
			ModelWithErrors modelAndErrors = sp.ladeAusPreferences();
			if(modelAndErrors == null)
				log.debug("Es war kein Model zum Laden verfügbar.");
			else {
				if(modelAndErrors.getErrors().size() != 0)
					modelAndErrors.getErrors().stream().map(str -> "Es trat ein Fehler beim Speichern/Laden auf: " + str).forEach(log::warn);
				model = modelAndErrors.getModel();
				layout.show(frame.getContentPane(), model.getSelectedWindow().getIdentifier());
			}
		} else if(e.getSource() == menuItemLiniendiagramm) {
			log.debug("Wechsle zum Liniendiagramm");
			this.switchToView(WindowSelect.LINIENDIAGRAMM);
		} else if(e.getSource() == menuItemAufgabentext) {
			log.debug("Wechsle zum Aufgabentext");
			this.switchToView(WindowSelect.AUFGABENTEXT);
		} else if(e.getSource() == menuItemStartseite) {
			log.debug("Wechsle zur Startseite");
			this.switchToView(WindowSelect.STARTSEITE);
		} else if(e.getSource() == fensterHilfe) {
			log.debug("Öffne das Hilfe-Fenster!");
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
			layout.show(frame.getContentPane(), WindowSelect.LINIENDIAGRAMM.getIdentifier());
			model.setSelectedWindow(WindowSelect.LINIENDIAGRAMM);
		} else if (ws == WindowSelect.STARTSEITE) {
			log.debug("Wechsle zur Startseite");
			layout.show(frame.getContentPane(), WindowSelect.STARTSEITE.getIdentifier());
			model.setSelectedWindow(WindowSelect.STARTSEITE);
		}
	}
}
