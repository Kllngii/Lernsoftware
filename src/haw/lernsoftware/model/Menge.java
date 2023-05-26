package haw.lernsoftware.model;

import static haw.lernsoftware.Konst.DIGITS;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Menge {

	private String name;
	private Ereignismenge möglicheEreignisse;
	private List<Elementarereignis> ereignisse;
	private int order;

	public Menge(String name, Ereignismenge möglicheEreignisse, List<Elementarereignis> ereignisse, int order) {
		super();
		this.name = name;
		this.möglicheEreignisse = möglicheEreignisse;
		this.ereignisse = ereignisse;
		this.order = order;
	}

	public Menge vereinigt(Menge m) {
		List<Elementarereignis> vereinigteMenge = new ArrayList<>(ereignisse);
		for (Elementarereignis e : m.getEreignisse())
			if (!vereinigteMenge.contains(e))
				vereinigteMenge.add(e);
		return new Menge("Beispielmenge", möglicheEreignisse, vereinigteMenge, order);
	}

	public Menge geschnitten(Menge m) {
		List<Elementarereignis> schnittMenge = new ArrayList<>();
		for (Elementarereignis e : m.getEreignisse())
			if (ereignisse.contains(e))
				schnittMenge.add(e);
		return new Menge("Beispielmenge", möglicheEreignisse, schnittMenge, order);
	}

	public Menge negiert() {
		List<Elementarereignis> negierteMenge = new ArrayList<>(möglicheEreignisse.getEreignisse());
		for (Elementarereignis e : ereignisse)
			negierteMenge.remove(e);
		return new Menge("Beispielmenge", möglicheEreignisse, negierteMenge, order);
	}
	
	public String bedingteWSK(Menge m) {
		String fracProbability = "0";
		double decimalProbability = 0.0;
		for (int i = 0; i < ereignisse.size(); i++) {
			decimalProbability += ereignisse.get(i).getProbability();
			fracProbability = addFracProbability(fracProbability, ereignisse.get(i).getProbString());
		}

		decimalProbability = Math.round(decimalProbability * Math.pow(10.0, DIGITS)) / Math.pow(10.0, DIGITS);
		String output = fracProbability + " = " + Double.toString(decimalProbability);
		return output;
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

	public int getOrder() {
		return order;
	}

	public String getProbability() {
		String fracProbability = "0";
		for (int i = 0; i < ereignisse.size(); i++) {
			fracProbability = addFracProbability(fracProbability, ereignisse.get(i).getProbString());
		}

		String output = fracProbability + " = " + Double.toString(this.getDecimalProbability());
		return output;
	}
	
	public double getDecimalProbability() {
		double decimalProbability = 0.0;
		for (int i = 0; i < ereignisse.size(); i++) {
			decimalProbability += ereignisse.get(i).getProbability();
		}
		decimalProbability = Math.round(decimalProbability * Math.pow(10.0, DIGITS)) / Math.pow(10.0, DIGITS);
		return decimalProbability;
	}

	public int GCF(int a, int b) {
		if (b == 0)
			return a;
		else
			return (GCF(b, a % b));
	}

	// Addiere die beiden Brüche frac1 und frac2 (jeweils gegeben im Format "x/y"
	// bzw. "z")
	public String addFracProbability(String frac1, String frac2) {
		int num;
		int denum;
		int frac1num;
		int frac1denum;
		int frac2num;
		int frac2denum;
		int gcf;

		if (frac1 == "0" || frac1 == "1") {
			frac1 = String.format("%s/1", frac1);
		}
		if (frac2 == "0" || frac2 == "1") {
			frac2 = String.format("%s/1", frac2);
		}

		if (frac1.contains("/")) {
			String[] rat1 = frac1.split("/");
			frac1num = Integer.parseInt(rat1[0]);
			frac1denum = Integer.parseInt(rat1[1]);
		} else {
			return "error no fraction in frac1";
		}
		if (frac2.contains("/")) {
			String[] rat2 = frac2.split("/");
			frac2num = Integer.parseInt(rat2[0]);
			frac2denum = Integer.parseInt(rat2[1]);
		} else {
			return "error no fraction in frac2";
		}

		num = frac1num * frac2denum + frac2num * frac1denum;
		denum = frac1denum * frac2denum;
		gcf = GCF(denum, num);
		num = num / gcf;
		denum = denum / gcf;

		return String.format("%d/%d", num, denum);
	}
}
