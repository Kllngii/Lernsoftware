package haw.lernsoftware.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Die Ereignismenge enthält alle möglichen Elementarereignisse zur späteren Verwendung
 * 
 * @author Lasse Kelling
 *
 */
public class Ereignismenge {
	
	Logger log = Logger.getLogger(getClass());
	
	private List<Elementarereignis> ereignisse = new ArrayList<>();

	public Ereignismenge(List<Elementarereignis> ereignisse) {
		super();
		this.ereignisse = ereignisse;
	}

	/**
	 * Überprüft eine Ereignismenge auf Plausibilität.
	 * 
	 * Die Ereignisse einer Ereignismenge dürfen nicht:
	 * <li> null sein
	 * <li> eine Gesamtwahrscheinlichkeit >1 haben
	 * @return true, wenn alle Checks erfolgreich waren
	 */
	public boolean vaildate() {
		if(ereignisse == null)
			return false;
		
		double gesamt = 0;
		for(Elementarereignis e : ereignisse)
			gesamt += e.getProbability();
		if(gesamt > 1) //TODO floatingPoint-Fehler beachten
			return false;
		
		return true;
	}
	public List<Elementarereignis> getEreignisse() {
		return ereignisse;
	}

	public void setEreignisse(List<Elementarereignis> ereignisse) {
		this.ereignisse = ereignisse;
	}
	
	public void putEreignis(Elementarereignis e) {
		ereignisse.add(e);
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		JSONArray ereignisJson = new JSONArray();
		
		for(Elementarereignis e : ereignisse)
			ereignisJson.put(e.toJSON());
		
		json.put("ereignisse", ereignisJson);
		return json;
	}

	public void putAllEreignisse(Elementarereignis... eList) {
		for(Elementarereignis e : eList)
			ereignisse.add(e);
	}

	public static Ereignismenge fromJSON(String jsonString) {
		Logger log = Logger.getLogger(Ereignismenge.class);
		JSONObject json = new JSONObject(jsonString);
		JSONArray arr = json.getJSONArray("ereignisse");
		List<Elementarereignis> eList = new ArrayList<>();
		
		arr.forEach(a -> {
			log.debug("Lese ein: " + a);
			if(a instanceof JSONObject j) {
				eList.add(Elementarereignis.fromJSON(j.toString()));
			}
		});
		return new Ereignismenge(eList);
	}
}
