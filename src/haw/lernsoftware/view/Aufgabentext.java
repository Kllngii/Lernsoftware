package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.model.Model;

public class Aufgabentext extends HAWView {
	private Model model;
	private Logger log = Logger.getLogger(getClass());

	// fügt dem panel von Aufgabentext einen JComponent zu
	public Aufgabentext(Model model) {
		this.model = model;
		panel.add(buildContentText());
//		panel.add(buildContentProgressBar(), BorderLayout.CENTER);

	}

	// erstellt einen JComponent
	public JComponent buildContentText() {
		JLabel titleTaskLabel = new JLabel("Aufgabe X:");
		JButton nextTaskButton = new JButton("NEXT");
		JButton previousTaskButton = new JButton("PREVIOUS");
		JTextArea einleitungText = new JTextArea("Test \n wow");
		// JLabel einleitungText = new JLabel("<html>Test <BR> wow<html>");
		einleitungText.setLineWrap(true);
		einleitungText.setPreferredSize(new Dimension(100, 100));
		einleitungText.setEditable(false);
		Color color = panel.getBackground();
		einleitungText.setBackground(color);

		// gibt einen JComponent zurück, der
		return FormBuilder.create().debug(true) // Rote Linien zeichnen
				.columns("100dlu, center:200dlu, 100dlu") //
				.rows("p, 20dlu, p, $lg, top:300dlu") //
				.padding(Paddings.DIALOG) //
				.add(previousTaskButton).xy(1, 2) //
				.add(titleTaskLabel).xy(2, 1) //
				.add(nextTaskButton).xy(3, 2) //
				.addSeparator("Aufgabentext").xyw(1, 3, 3) //
				.add(einleitungText).xyw(1, 5, 3) //
				.build(); //
	}

//	public JComponent buildContentProgressBar() {
//		JLabel titleField = new JLabel("Title");
//		JLabel authorField = new JLabel("Title");
//		JLabel priceField = new JLabel("Title");
//		
//
//		return FormBuilder.create().columns("left:90dlu, 3dlu, 200dlu").rows("p, $lg, p, $lg, p")
//				.padding(Paddings.DIALOG).add("_Title:").xy(1, 1).add(titleField).xy(3, 1).add("_Author:").xy(1, 3)
//				.add(einleitungText).xy(3, 3).add("_Price:").xy(1, 5).add(priceField).xy(3, 5).build();
//	}

}
