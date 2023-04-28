package haw.lernsoftware.view;

import javax.swing.JFrame;

@Deprecated
public class Aufgaben extends HAWView {

	public Aufgaben() {
		// Fenster erstellen
		JFrame fenster = new JFrame("AufgabenText");

		// fenster.setSize(1920, 1080);
		fenster.setSize(960, 540);
		fenster.setVisible(true);

	}

	public static void main(String[] args) {
		new Aufgaben();
	}
}
