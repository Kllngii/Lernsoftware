package haw.lernsoftware.view;

import javax.swing.JLabel;

import org.apache.log4j.Logger;

/**
 * Die Startseite des Programms.
 */
public class Startseite extends HAWView {
	
	Logger log = Logger.getLogger(getClass());
	
	public Startseite() {
		constructStartseite();
	}

	private void constructStartseite() {
		panel.add(new JLabel("Hier könnte Ihre Werbung stehen, dieses Mal auf der Startseite..."));
	}
}
