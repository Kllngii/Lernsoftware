package haw.lernsoftware.model;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

import haw.lernsoftware.model.SpeicherService.ModelWithErrors;
import haw.lernsoftware.resources.ResourceProvider;

/**
 * Jeder Zustand, der im Fenster angezeigt wird ist hier gespeichert.
 * Jeder Nutzerinput muss die jeweilige Stelle im Model aktualisieren
 * 
 * @author Lasse Kelling
 *
 */
public class Model implements Serializable {
	private Logger log = Logger.getLogger(getClass());
	private static final long serialVersionUID = 3639339910529002338L;
	private WindowSelect selectedWindow = WindowSelect.STARTSEITE;
	private List<Aufgabe> aufgaben;
	private Aufgabe currentAufgabe;
	
	private int currentAufgabeID = 0;

	private List<Menge> mengen;
	private Ereignismenge eMenge;

	public Model(List<Aufgabe> aufgaben) {
		log.info("Initialisiere Model mit " + aufgaben.size() + " Aufgaben!");
		this.aufgaben = aufgaben;
		setCurrentAufgabe(aufgaben.get(0));
	}
	
	public Model(WindowSelect selectedWindow, int currentAufgabeID) {
		super();
		this.selectedWindow = selectedWindow;
		this.currentAufgabeID = currentAufgabeID;
		this.aufgaben = SpeicherService.ladeAufgaben();
		this.currentAufgabe = aufgaben.get(currentAufgabeID);
	}

	@JSONPropertyIgnore
	public List<Aufgabe> getAufgaben() {
		return aufgaben;
	}

	public void setAufgaben(List<Aufgabe> aufgaben) {
		this.aufgaben = aufgaben;
	}

	@JSONPropertyIgnore
	public Aufgabe getCurrentAufgabe() {
		return currentAufgabe;
	}

	public void setCurrentAufgabe(Aufgabe currentAufgabe) {
		this.currentAufgabe = currentAufgabe;
		currentAufgabeID = aufgaben.indexOf(currentAufgabe);

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

	@JSONPropertyIgnore
	public List<Menge> getMengen() {
		return mengen;
	}

	@JSONPropertyIgnore
	public Ereignismenge geteMenge() {
		return eMenge;
	}

	public int getCurrentAufgabeID() {
		return currentAufgabeID;
	}

	public void setCurrentAufgabeID(int currentAufgabeID) {
		this.currentAufgabeID = currentAufgabeID;
	}

	public static Model stripErrors(ModelWithErrors mwe) {
		Logger localLog = Logger.getLogger(Model.class);
		if(mwe.getErrors().size() != 0)
			mwe.getErrors().stream().map(str -> "Es trat ein Fehler beim Speichern/Laden auf: " + str).forEach(localLog::warn);
		return mwe.getModel();
	}
	
	public String toJSON() {
		JSONObject json =  new JSONObject(this);
		log.info(json.toString());
		return json.toString();
	}
	public static Model fromJSON(String jsonStr) {
		try {
			JSONObject json = new JSONObject(jsonStr);
			Logger.getLogger(Model.class).debug("Baue ein Model aus der JSON: " + json);
			return new Model(WindowSelect.parse(json.getString("selectedWindow")), json.getInt("currentAufgabeID"));
		} catch(JSONException e) {
			Logger.getLogger(Model.class).debug("Fehler beim Laden der Model-JSON... Fallback!");
			new SpeicherService();
			return new Model(SpeicherService.ladeAufgaben());
		}
		
	}
}
