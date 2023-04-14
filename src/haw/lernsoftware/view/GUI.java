package haw.lernsoftware.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import haw.lernsoftware.Lernsoftware;

public class GUI extends JPanel implements ActionListener {
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu dateiMenü = new JMenu("Datei");
	private JMenuItem menuItemSpeichern = new JMenuItem("Speichern");
	private JMenuItem file1 = new JMenuItem("Element 2");
	private JMenuItem file2 = new JMenuItem("Element 3");
	
	private JMenu menuFile2 = new JMenu("Aufgaben");
	private JMenuItem exercise0 = new JMenuItem("Element 1");
	private JMenuItem exercise1 = new JMenuItem("Element 2");
	private JMenuItem exercise2 = new JMenuItem("Element 3");
	
	private JMenu fensterMenü = new JMenu("Fenster");
	private JMenuItem menuItemLinienGraph = new JMenuItem("Liniengraph (Lasses Spielbereich)");
	private JMenuItem menuItemAufgabentext = new JMenuItem("Aufgabentext (Oles Spielbereich)");

	private JFrame frame;
	
	private LinienDiagramm liniendiagrammView;

	private JPanel currentPanel;

	
	public GUI(JFrame frame) {
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setResizable(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		menuBar.add(dateiMenü);
		dateiMenü.add(menuItemSpeichern);
		dateiMenü.add(file1);
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
		
		frame.setJMenuBar(menuBar);
		
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
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
