package haw.lernsoftware.model;

import java.io.Serializable;

public class Aufgabe implements Serializable {
	private static final long serialVersionUID = -3727129919178718459L;
	private String text;
	
	public Aufgabe(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
