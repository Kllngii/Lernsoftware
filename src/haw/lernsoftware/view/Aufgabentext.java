package haw.lernsoftware.view;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

public class Aufgabentext extends HAWView {

	Logger log = Logger.getLogger(getClass());

	// fügt dem panel von Aufgabentext einen JComponent zu
	public Aufgabentext() {

		panel.add(buildContentText(), BorderLayout.NORTH);
		panel.add(buildContentProgressBar(), BorderLayout.SOUTH);

	}

	// erstellt einen JComponent
	public JComponent buildContentText() {
		JLabel titleField = new JLabel("Title");
		JLabel authorField = new JLabel("Title");
		JLabel priceField = new JLabel("Title");

		// gibt einen JComponent zurück, der
		return FormBuilder.create().debug(true) // Rote Linien zeichnen
				.columns("left:90dlu, 3dlu, 200dlu").rows("p, $lg, p, $lg, p").padding(Paddings.DIALOG).add("_Title:")
				.xy(1, 1).add(titleField).xy(3, 1).add("_Author:").xy(1, 3).add(authorField).xy(3, 3).add("_Price:")
				.xy(1, 5).add(priceField).xy(3, 5).build();
	}

	public JComponent buildContentProgressBar() {
		JLabel titleField = new JLabel("Title");
		JLabel authorField = new JLabel("Title");
		JLabel priceField = new JLabel("Title");

		return FormBuilder.create().columns("left:90dlu, 3dlu, 200dlu").rows("p, $lg, p, $lg, p")
				.padding(Paddings.DIALOG).add("_Title:").xy(1, 1).add(titleField).xy(3, 1).add("_Author:").xy(1, 3)
				.add(authorField).xy(3, 3).add("_Price:").xy(1, 5).add(priceField).xy(3, 5).build();
	}

	// main
	public static void main(String[] args) {
		new Aufgabentext();
	}

}
