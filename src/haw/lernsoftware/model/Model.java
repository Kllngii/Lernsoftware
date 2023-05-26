package haw.lernsoftware.model;

import java.io.Serializable;
import java.util.List;

import haw.lernsoftware.resources.ResourceProvider;

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
	
	private List<Menge> mengen;
	private Ereignismenge eMenge;
	
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
		
		String ereignisStr = ResourceProvider.getFileContentAsString("elementare_aufgabe" + (aufgaben.indexOf(currentAufgabe) + 1) + ".em");
		String mengenStr = ResourceProvider.getFileContentAsString("ereignisse_aufgabe" + (aufgaben.indexOf(currentAufgabe) + 1) + ".em");
		
		this.eMenge =  Ereignismenge.elementareFromJSON(ereignisStr);
		this.mengen = Ereignismenge.ereignisseFromJSON(mengenStr, eMenge);
	}

	public WindowSelect getSelectedWindow() {
		return selectedWindow;
	}

	public void setSelectedWindow(WindowSelect selectedWindow) {
		this.selectedWindow = selectedWindow;
	}

	public List<Menge> getMengen() {
		return mengen;
	}

	public Ereignismenge geteMenge() {
		return eMenge;
	}
	
}
