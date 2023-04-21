package haw.lernsoftware.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

/**
 * Die Hilfeseite des Programms.
 * @author Sebastian Holz
 */
public class Hilfe extends HAWView implements ActionListener{

	
	private JButton Test = new JButton("Test");
	private JButton Test2 = new JButton("Test2");
	private JButton Test3 = new JButton("Test3");	
	private JLabel text = new JLabel("Werbung wfsfd dsfsf sf sefsdf sdg sfg s");

	public  Hilfe() {
		
		//Fenster erstellen
		JFrame fenster = new JFrame("Hilfe");

		fenster.setSize(800, 800);
		fenster.getContentPane().add(panel);
		
		JPanel jpanel = new JPanel();

		panel.add(buildContentMenu(), BorderLayout.WEST);
		panel.add(buildContentText(), BorderLayout.EAST);

		fenster.setVisible(true);
		
		
	}


	public JComponent buildContentMenu() {		
		
		Test.addActionListener(this);
		Test2.addActionListener(this);
		Test3.addActionListener(this);

		
		return FormBuilder.create()
				.columns("left:90dlu")
				.rows("p, 3dlu, p, 3dlu, p")
				.debug(true)                                 // Rote Linien zeichnen
				.padding(Paddings.DIALOG)
				.add(Test)  .xy(1, 1)
				.add(Test2) .xy(1, 3)
				.add(Test3)  .xy(1, 5)
				.build();
		

	}

	public JComponent buildContentText() {
		

		return FormBuilder.create()
				.columns("left:300dlu")
				.rows("p,3dlu,p,400dlu")
				.debug(true)                                 // Rote Linien zeichnen
				.padding(Paddings.DIALOG)
				.add("Überschrift") .xy(1, 1)
				.add("________________") .xy(1, 2)
				.add(text)  .xy(1, 4)
				.build();

	}

	public static void main(String[] args) {
		new Hilfe();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == Test) {
			text = new JLabel("Werbung 1");
		}
		if(e.getSource() == Test2) {
			text = new JLabel("Werbung 2");
		}
		if(e.getSource() == Test3) {
			text = new JLabel("Werbung 3");
		}
	}
}
