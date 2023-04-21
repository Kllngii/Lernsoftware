package haw.lernsoftware.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

<<<<<<<Updated upstream
import haw.lernsoftware.model.Aufgabe;
import haw.lernsoftware.model.Model;=======
import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;

public class Aufgabentext extends HAWView {
	>>>>>>>
	Stashed changes

public class Aufgabentext extends HAWView {
	private Model model;
	private Logger log = Logger.getLogger(getClass());

	// fügt dem panel von Aufgabentext einen JComponent zu
	public Aufgabentext(Model model) {
		this.model = model;
		panel.add(buildContentText(), BorderLayout.NORTH);
		panel.add(buildContentProgressBar(), BorderLayout.SOUTH);

	}

	// erstellt einen JComponent
	public JComponent buildContentText() {
		JLabel titleTaskLabel = new JLabel("Aufgabe X:");
		JButton nextTaskButton = new JButton("NEXT");
		JButton previousTaskButton = new JButton("PREVIOUS");

		// gibt einen JComponent zurück, der
		return FormBuilder.create().debug(true) // Rote Linien zeichnen
				.columns("left:90dlu, 3dlu, 200dlu").rows("p, $lg, p, $lg, p").padding(Paddings.DIALOG).add("_Title:")
				.xy(1, 1).add(titleTaskLabel).xy(3, 1).add("_Author:").xy(1, 3).add(nextTaskButton).xy(3, 3)
				.add("_Price:").xy(1, 5).add(previousTaskButton).xy(3, 5).build();
	}

	//JTextArea einleitungText = new JTextArea(
	//		ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.einleitungstext"));

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
		Model testModel = new Model(List.of(new Aufgabe("Aufgabentext A"), new Aufgabe("Aufgabentext B"), new Aufgabe("Aufgabentext C")));
		new Aufgabentext(testModel);
	}

}
