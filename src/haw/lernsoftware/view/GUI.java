package haw.lernsoftware.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.apache.log4j.Logger;

import haw.lernsoftware.model.Aufgabe;
import haw.lernsoftware.model.Model;
import haw.lernsoftware.view.liniendiagramm.LinienDiagramm;

/**
 * Die GUI-Klasse definiert den äußersten Container des Fensters
 * @author Lasse Kelling
 *
 */
public class GUI implements ActionListener {
	
	private Model model = new Model(List.of(new Aufgabe("Aufgabentext A"), new Aufgabe("Aufgabentext B"), new Aufgabe("Aufgabentext C")));
	
	Logger log = Logger.getLogger(getClass());

	private JFrame frame;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem menuItemSpeichern = new JMenuItem("Speichern");
	private JMenuItem menuItemLaden = new JMenuItem("Laden");
	private JMenuItem menuItemStartseite = new JMenuItem("Startseite");
	private JMenuItem menuItemLiniendiagramm = new JMenuItem("Liniengraph");
	private JMenuItem menuItemAufgabentext = new JMenuItem("Aufgabentext");

	private LinienDiagramm liniendiagrammView = new LinienDiagramm();
	private Startseite startseitenView = new Startseite();
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

		menuBar.add(dateiMenü);
		menuBar.add(fensterMenü);
		
		dateiMenü.add(menuItemSpeichern);
		dateiMenü.add(menuItemLaden);
		menuItemSpeichern.addActionListener(this);
		menuItemLaden.addActionListener(this);

		fensterMenü.add(menuItemStartseite);
		fensterMenü.add(menuItemLiniendiagramm);
		fensterMenü.add(menuItemAufgabentext);
		menuItemStartseite.addActionListener(this);
		menuItemLiniendiagramm.addActionListener(this);
		menuItemAufgabentext.addActionListener(this);
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
		} else if(e.getSource() == menuItemLaden) {
			
		} else if(e.getSource() == menuItemLiniendiagramm) {
			log.debug("Wechsle zum Liniendiagramm");
			layout.show(frame.getContentPane(), "liniendiagramm");
		} else if(e.getSource() == menuItemAufgabentext) {
			log.debug("Wechsle zum Aufgabentext");
			layout.show(frame.getContentPane(), "aufgabentext");
		} else if(e.getSource() == menuItemStartseite) {
			log.debug("Wechsle zur Startseite");
			layout.show(frame.getContentPane(), "startseite");
		}
	}

}
