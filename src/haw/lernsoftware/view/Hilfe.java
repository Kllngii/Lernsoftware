package haw.lernsoftware.view;

import javax.swing.JFrame;



public class Hilfe extends HAWView {

	public Hilfe() {
		 // Fenster erstellen
	      JFrame fenster = new JFrame("Hilfe");
	      
	      fenster.setSize(1920, 1080);
	      fenster.setVisible(true);
	      
	}
	
	   public static void main(String[] args) {
			  new Hilfe();
		   }
}
