package haw.lernsoftware.model;

import static haw.lernsoftware.Konst.DIGITS;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

/**
 * Eine Menge kennt sowohl alle möglichen Elemente als auch die enthaltenen
 * Elemente.
 * 
 * @author Lasse Kelling
 */
public class Menge {

	private String name;
	private Ereignismenge möglicheEreignisse;
	private List<Elementarereignis> ereignisse;
	private int order;
	private String userProb;
	private boolean calculateProbability = true;
	private boolean editable = true;
	private boolean deleteable = true;
	private boolean correct = false;

	public Menge(String name, Ereignismenge möglicheEreignisse, List<Elementarereignis> list, int order,
			boolean calculateProbability, boolean editable, boolean deleteable) {
		super();
		this.name = name;
		this.möglicheEreignisse = möglicheEreignisse;
		this.ereignisse = list;
		this.order = order;
		this.userProb = "P = ";

		this.calculateProbability = calculateProbability;
		this.editable = editable;
		this.deleteable = deleteable;
	}

	public Menge vereinigt(Menge m) {
		List<Elementarereignis> vereinigteMenge = new ArrayList<>(ereignisse);
		for (Elementarereignis e : m.getEreignisse())
			if (!vereinigteMenge.contains(e))
				vereinigteMenge.add(e);
		return new Menge("Beispielmenge", möglicheEreignisse, vereinigteMenge, order, m.isCalculateProbability(),
				m.isEditable(), m.isDeleteable());
	}

	public Menge geschnitten(Menge m) {
		List<Elementarereignis> schnittMenge = new ArrayList<>();
		for (Elementarereignis e : m.getEreignisse())
			if (ereignisse.contains(e))
				schnittMenge.add(e);
		return new Menge("Beispielmenge", möglicheEreignisse, schnittMenge, order, m.isCalculateProbability(),
				m.isEditable(), m.isDeleteable());
	}

	public Menge negiert() {
		List<Elementarereignis> negierteMenge = new ArrayList<>(möglicheEreignisse.getEreignisse());
		for (Elementarereignis e : ereignisse)
			negierteMenge.remove(e);
		return new Menge("Beispielmenge", möglicheEreignisse, negierteMenge, order, isCalculateProbability(),
				isEditable(), isDeleteable());
	}

	public static Menge negiert(Menge m) {
		return m.negiert();
	}

	public void addElementar(Elementarereignis eEreignis) {
		ereignisse.add(eEreignis);
	}

	public void deleteElementar(Elementarereignis eEreignis) {
		ereignisse.remove(eEreignis);
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

	public String getUserProbability() {
		return this.userProb;
	}

	public String getProbability() {
		String output = this.getFracProbability() + " = " + Double.toString(this.getDecimalProbability());
		return output;
	}

	public String getFracProbability() {
		String fracProbability = "0";
		for (int i = 0; i < ereignisse.size(); i++) {
			fracProbability = addFracProbability(fracProbability, ereignisse.get(i).getProbString());
		}

		if (fracProbability.equals("0/1"))
			fracProbability = "0";
		else if (fracProbability.equals("1/1"))
			fracProbability = "1";

		return fracProbability;
	}

	public double getDecimalProbability() {
		double decimalProbability = 0.0;
		for (int i = 0; i < ereignisse.size(); i++) {
			decimalProbability += ereignisse.get(i).getProbability();
		}
		decimalProbability = Math.round(decimalProbability * Math.pow(10.0, DIGITS)) / Math.pow(10.0, DIGITS);
		return decimalProbability;
	}

	public String getConditionalProbability(Menge m) {
		String fracProbability = this.divideFracProbability(this.geschnitten(m).getFracProbability(),
				m.getFracProbability());
		double decimalProbability = this.geschnitten(m).getDecimalProbability() / m.getDecimalProbability();
		if (m.getDecimalProbability() == 0.0) {
			decimalProbability = 0.0;
		}
		decimalProbability = Math.round(decimalProbability * Math.pow(10.0, DIGITS)) / Math.pow(10.0, DIGITS);
		String output = fracProbability + " = " + Double.toString(decimalProbability);
		return output;
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

	// Teile Bruch frac1 durch frac2 (jeweils gegeben im Format "x/y" bzw. "z")
	public String divideFracProbability(String frac1, String frac2) {
		int num;
		int denum;
		int frac1num;
		int frac1denum;
		int frac2num;
		int frac2denum;
		int gcf;

		if (frac2 == "0" || frac2 == "0/1") {
			return "0";
		}
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

		num = frac1num * frac2denum;
		denum = frac1denum * frac2num;
		gcf = GCF(denum, num);
		num = num / gcf;
		denum = denum / gcf;

		if (num == 0)
			return "0";
		else if (num == denum)
			return "1";
		else
			return String.format("%d/%d", num, denum);
	}

	public void setUserProb(String m) {
		this.userProb = m;
	}

	public boolean isCalculateProbability() {
		return calculateProbability;
	}

	public boolean isEditable() {
		return editable;
	}

	public boolean isDeleteable() {
		return deleteable;
	}

	@JSONPropertyIgnore
	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}
