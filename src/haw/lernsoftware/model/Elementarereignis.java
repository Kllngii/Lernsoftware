package haw.lernsoftware.model;

import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

/**
 * Ein Elementarereignis ist das Abbild eines möglichen Ausgangs. Alle dazu benötigten Daten werden hier gespeichert
 * 
 * @author Lasse Kelling
 *
 */
public class Elementarereignis {
	private String name;
	private double probability;
	private String probString;
	private int order;
	private boolean bedingt = false;
	
	public Elementarereignis(String name, String probString, int order) {
		super();
		this.name = name;
		this.probString = probString;
		this.order = order;
		
		calcProbability();
	}
	
	private void calcProbability() {
		String[] arr = probString.split("/");
		
		double den = Double.valueOf(arr[0]);
		double num = Double.valueOf(arr[1]);
		probability = den / num;
	}
	public String getName() {
		return name;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getProbString() {
		return probString;
	}
	public void setProbString(String probString) {
		this.probString = probString;
	}
	@JSONPropertyIgnore
	public double getProbability() {
		return probability;
	}
	public boolean isBedingt() {
		return bedingt;
	}

	public void setBedingt(boolean bedingt) {
		this.bedingt = bedingt;
	}

	@Override
	public String toString() {
		return "Elementarereignis [name=" + name + ", probability=" + probability + ", probString=" + probString
				+ ", order=" + order + "]";
	}

	public JSONObject toJSON() {
		JSONObject element = new JSONObject(this);
		return element;
	}
	public static Elementarereignis fromJSON(String jsonString) {
		JSONObject json = new JSONObject(jsonString);
		return new Elementarereignis(json.getString("name"), json.getString("probString"), json.getInt("order"));
	}
}
