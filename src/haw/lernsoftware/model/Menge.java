package haw.lernsoftware.model;

import java.util.ArrayList;
import java.util.List;
import static haw.lernsoftware.Konst.DIGITS;

import org.json.JSONObject;

/**
 * Eine Menge enthält einen Anteil der Ereignismenge und stellt eine Reihe im Liniendiagramm da.
 * 
 * @author Lasse Kelling
 *
 */
public class Menge {
	//Für die JSON-(Re)konstrunktion
	//TODO derzeit unused -> clean code?
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
	
	public String getProbability() {
		String fracProbability = "0";
		double decimalProbability = 0.0;
		for (int i = 0; i < ereignisse.size(); i++) {
			decimalProbability += ereignisse.get(i).getProbability();
			fracProbability = addFracProbability(fracProbability, ereignisse.get(i).getProbString());
		}
		
		decimalProbability = Math.round(decimalProbability*Math.pow(10.0,DIGITS))/Math.pow(10.0,DIGITS);
		String output = fracProbability + " = " + Double.toString(decimalProbability);
		return output;
	}

	// Addiere die beiden Brüche frac1 und frac2 (jeweils gegeben im Format "x/y" bzw. "z")
	public String addFracProbability(String frac1, String frac2) {
		
		return "x/y";
	}
}
