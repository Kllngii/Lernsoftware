package haw.lernsoftware.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import haw.lernsoftware.view.liniendiagramm.LinienDiagramm;

public class GUI implements ActionListener {
	
	Logger log = Logger.getLogger(getClass());

	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	JMenuItem menuItemSpeichern = new JMenuItem("Speichern");
	JMenuItem menuItemLaden = new JMenuItem("Laden");
	JMenuItem file2 = new JMenuItem("Element 3");
	JMenuItem exercise0 = new JMenuItem("Element 1");
	JMenuItem exercise1 = new JMenuItem("Element 2");
	JMenuItem exercise2 = new JMenuItem("Element 3");
	JMenuItem menuItemStartseite = new JMenuItem("Startseite");
	JMenuItem menuItemLiniendiagramm = new JMenuItem("Liniengraph");
	JMenuItem menuItemAufgabentext = new JMenuItem("Aufgabentext");

	private LinienDiagramm liniendiagrammView = new LinienDiagramm();
	private Startseite startseitenView = new Startseite();
	private Aufgabentext aufgabentextView = new Aufgabentext();

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

	private void constructMenubar() {
		JMenu dateiMenü = new JMenu("Datei");
		JMenu menuFile2 = new JMenu("Aufgaben");
		JMenu fensterMenü = new JMenu("Fenster");

		menuBar.add(dateiMenü);
		menuBar.add(menuFile2);
		menuBar.add(fensterMenü);
		
		dateiMenü.add(menuItemSpeichern);
		dateiMenü.add(menuItemLaden);
		dateiMenü.add(file2);
		menuItemSpeichern.addActionListener(this);
		menuItemLaden.addActionListener(this);
		file2.addActionListener(this);

		menuFile2.add(exercise0);
		menuFile2.add(exercise1);
		menuFile2.add(exercise2);

		fensterMenü.add(menuItemStartseite);
		fensterMenü.add(menuItemLiniendiagramm);
		fensterMenü.add(menuItemAufgabentext);
		menuItemStartseite.addActionListener(this);
		menuItemLiniendiagramm.addActionListener(this);
		menuItemAufgabentext.addActionListener(this);
	}
	
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
