package haw.lernsoftware.model;

import java.io.Serializable;
import java.util.List;

/**
 * Jeder Zustand, der im Fenster angezeigt wird ist hier gespeichert.
 * Jeder Nutzerinput muss die jeweilige Stelle im Model aktualisieren
 * 
 * @author Lasse Kelling
 *
 */
public class Model implements Serializable {
	private static final long serialVersionUID = 3639339910529002338L;
	private WindowSelect selectedWindow = WindowSelect.STARTSEITE;
	private List<Aufgabe> aufgaben;
	private Aufgabe currentAufgabe;
	
	public Model(List<Aufgabe> aufgaben) {
		this.aufgaben = aufgaben;
		this.currentAufgabe = aufgaben.get(0);
	}

	public List<Aufgabe> getAufgaben() {
		return aufgaben;
	}

	public void setAufgaben(List<Aufgabe> aufgaben) {
		this.aufgaben = aufgaben;
	}

	public Aufgabe getCurrentAufgabe() {
		return currentAufgabe;
	}

	public void setCurrentAufgabe(Aufgabe currentAufgabe) {
		this.currentAufgabe = currentAufgabe;
	}

	public WindowSelect getSelectedWindow() {
		return selectedWindow;
	}

	public void setSelectedWindow(WindowSelect selectedWindow) {
		this.selectedWindow = selectedWindow;
	}
	
}
