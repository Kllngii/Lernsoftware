package haw.lernsoftware.view;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

		panel.add(buildContent());

		fenster.setVisible(true);


	}


	public JComponent buildContent() {
		JLabel titleField = title("Title");
		JLabel authorField = title("Title");
		JLabel priceField = title("Title");
		return FormBuilder.create()
				.columns("left:90dlu, 3dlu, 200dlu")
				.rows("p, $lg, p, $lg, p")
				.padding(Paddings.DIALOG)
				.add("_Title:")  .xy(1, 1)
				.add(titleField) .xy(3, 1)
				.add("_Author:") .xy(1, 3)
				.add(authorField).xy(3, 3)
				.add("_Price:")  .xy(1, 5)
				.add(priceField) .xy(3, 5)
				.build();
	}

	public static void main(String[] args) {
		new Hilfe();
	}
}
