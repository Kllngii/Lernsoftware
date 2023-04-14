package haw.lernsoftware.view;

import javax.swing.JLabel;

import org.apache.log4j.Logger;

public class Aufgabentext extends HAWView {

	Logger log = Logger.getLogger(getClass());

	public Aufgabentext() {
		constructAufgabentext();
	}

	private void constructAufgabentext() {
		panel.add(new JLabel("Ich bin der Aufgabentext :)"));
	}
}
