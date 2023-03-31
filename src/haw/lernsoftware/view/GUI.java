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

	
	public GUI() {
		JFrame frame = new JFrame("Funktionsplotter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setResizable(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		menuBar.add(menuFile1);
		menuFile1.add(file0);
		menuFile1.add(file1);
		menuFile1.add(file2);
		
		menuBar.add(menuFile2);
		menuFile2.add(exercise0);
		menuFile2.add(exercise1);
		menuFile2.add(exercise2);
		
		frame.setJMenuBar(menuBar);
		
		JPanel panel1 = new JPanel();
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(panel1);
		
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
