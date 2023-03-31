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

import haw.lernsoftware.Lernsoftware;

public class GUI extends JPanel implements ActionListener{
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuFile1 = new JMenu("Datei");
	private JMenuItem file0 = new JMenuItem("Element 1");
	private JMenuItem file1 = new JMenuItem("Element 2");
	private JMenuItem file2 = new JMenuItem("Element 3");
	
	private JMenu menuFile2 = new JMenu("Aufgaben");
	private JMenuItem exercise0 = new JMenuItem("Element 1");
	private JMenuItem exercise1 = new JMenuItem("Element 2");
	private JMenuItem exercise2 = new JMenuItem("Element 3");

	
	public GUI(Lernsoftware lernsoftware) {
		lernsoftware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lernsoftware.setLocationByPlatform(true);
		lernsoftware.setResizable(true);
		lernsoftware.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		menuBar.add(menuFile1);
		menuFile1.add(file0);
		menuFile1.add(file1);
		menuFile1.add(file2);
		
		menuBar.add(menuFile2);
		menuFile2.add(exercise0);
		menuFile2.add(exercise1);
		menuFile2.add(exercise2);
		
		lernsoftware.setJMenuBar(menuBar);
		
		JPanel panel1 = new JPanel();
		Container contentPane = lernsoftware.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(panel1);
		
		lernsoftware.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
