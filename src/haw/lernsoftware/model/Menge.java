package haw.lernsoftware.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

public class Menge {
	//Für die JSON-(Re)konstrunktion
	private int möglicheEreignisseID;
	
	private String name;
	private Ereignismenge möglicheEreignisse;
	private List<Elementarereignis> ereignisse;
	
	public Menge(String name, Ereignismenge möglicheEreignisse, List<Elementarereignis> ereignisse) {
		super();
		this.name = name;
		this.möglicheEreignisse = möglicheEreignisse;
		this.ereignisse = ereignisse;
	}

	public Menge vereinigt(Menge m) {
		List<Elementarereignis> vereinigteMenge = new ArrayList<>(ereignisse);
		for(Elementarereignis e : m.getEreignisse())
			if(!vereinigteMenge.contains(e))
				vereinigteMenge.add(e);
		return new Menge("Beispielmenge", möglicheEreignisse, vereinigteMenge);
	}
	
	public Menge geschnitten(Menge m) {
		List<Elementarereignis> schnittMenge = new ArrayList<>();
		for(Elementarereignis e : m.getEreignisse())
			if(ereignisse.contains(e))
				schnittMenge.add(e);
		return new Menge("Beispielmenge", möglicheEreignisse, schnittMenge);
	}
	
	public Menge negiert() {
		List<Elementarereignis> negierteMenge = new ArrayList<>(möglicheEreignisse.getEreignisse());
		for(Elementarereignis e : ereignisse)
			negierteMenge.remove(e);
		return new Menge("Beispielmenge", möglicheEreignisse, negierteMenge);
	}
	
	public static Menge negiert(Menge m) {
		return m.negiert();
	}
	
	/*
	 * *** Getter und Setter ***
	 */
	public JSONObject toJSON() {
		JSONObject element = new JSONObject(this);
		return element;
	}

	public Ereignismenge getMöglicheEreignisse() {
		return möglicheEreignisse;
	}
	
	public void setMöglicheEreignisse(Ereignismenge möglicheEreignisse) {
		this.möglicheEreignisse = möglicheEreignisse;
	}
	
	public List<Elementarereignis> getEreignisse() {
		return ereignisse;
	}
	
	public void setEreignisse(List<Elementarereignis> ereignisse) {
		this.ereignisse = ereignisse;
	}

	public String getName() {
		return name;
	}
}
