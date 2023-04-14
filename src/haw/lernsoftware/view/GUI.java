package haw.lernsoftware.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GUI extends JPanel implements ActionListener {

	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	JMenuItem menuItemSpeichern = new JMenuItem("Speichern");
	JMenuItem menuItemLaden = new JMenuItem("Laden");
	JMenuItem file2 = new JMenuItem("Element 3");
	JMenuItem exercise0 = new JMenuItem("Element 1");
	JMenuItem exercise1 = new JMenuItem("Element 2");
	JMenuItem exercise2 = new JMenuItem("Element 3");
	JMenuItem menuItemLinienGraph = new JMenuItem("Liniengraph");
	JMenuItem menuItemAufgabentext = new JMenuItem("Aufgabentext");

	private LinienDiagramm liniendiagrammView;

	private JPanel currentPanel;


	public GUI(JFrame frame) {
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setResizable(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		constructMenubar();

		frame.setJMenuBar(menuBar);


		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
	}

	private void constructMenubar() {

		JMenu dateiMenü = new JMenu("Datei");

		JMenu menuFile2 = new JMenu("Aufgaben");

		JMenu fensterMenü = new JMenu("Fenster");

		menuBar.add(dateiMenü);
		dateiMenü.add(menuItemSpeichern);
		dateiMenü.add(menuItemLaden);
		dateiMenü.add(file2);
		menuItemSpeichern.setActionCommand("speichern");
		menuItemSpeichern.addActionListener(this);
		dateiMenü.addActionListener(this);

		menuBar.add(menuFile2);
		menuFile2.add(exercise0);
		menuFile2.add(exercise1);
		menuFile2.add(exercise2);

		menuBar.add(fensterMenü);
		fensterMenü.add(menuItemLinienGraph);
		menuItemLinienGraph.addActionListener(this);
		fensterMenü.add(menuItemAufgabentext);
		menuItemAufgabentext.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuItemSpeichern) {
			System.out.println("Speichere!");
		} else if(e.getSource() == menuItemLinienGraph) {
			System.out.println("Wechsle zum LinienGraph");
			frame.getContentPane().add(liniendiagrammView);
			frame.getContentPane().remove(currentPanel);
			currentPanel = liniendiagrammView;
		} else if(e.getSource() == menuItemAufgabentext) {
			System.out.println("Wechsle zum Aufgabentext");
		}
	}

}
