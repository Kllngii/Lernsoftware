package haw.lernsoftware.view;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JButton;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

/**
 * Die Hilfeseite des Programms
 */
public class Hilfe extends HAWView {

	public  Hilfe() {
		//Fenster erstellen
		JFrame fenster = new JFrame("Hilfe");

		fenster.setSize(800, 800);

		fenster.getContentPane().add(panel);

		panel.add(buildContentMenu(), BorderLayout.WEST);
		panel.add(buildContentText(), BorderLayout.EAST);

		fenster.setVisible(true);


	}


	public JComponent buildContentMenu() {
		JButton Test = new JButton("Test");
		JButton Test2 = new JButton("Test2");
		JButton Test3 = new JButton("Test3");

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

		JLabel text = new JLabel("Werbung wfsfd dsfsf sf sefsdf sdg sfg s");
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
}
