package haw.lernsoftware.model;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

import haw.lernsoftware.resources.ResourceProvider;

public class Aufgabe implements Serializable {
	private static final long serialVersionUID = -3727129919178718459L;
	private String text;
	private Image img;
	
	private boolean hasImage = false;
	private boolean hasLiniendiagramm = false;
	private Ereignismenge eMenge;
	private List<Menge> ereignisse;
	
	public Aufgabe(String text) {
		this.text = text;
	}
	public Aufgabe(String text, String pathToImage) {
		this.text = text;
		this.img = ResourceProvider.loadImage(pathToImage);
		
		hasImage = true;
	}
	public Aufgabe(String text, String pathToImage, String pathToLiniendiagrammElementare, String pathToLiniendiagrammEreignisse) {
		this.text = text;
		this.img = ResourceProvider.loadImage(pathToImage);
		this.eMenge = Ereignismenge.elementareFromJSON(ResourceProvider.getFileContentAsString(pathToLiniendiagrammElementare));
		this.ereignisse = Ereignismenge.ereignisseFromJSON(ResourceProvider.getFileContentAsString(pathToLiniendiagrammEreignisse), eMenge);
		
		hasImage = true;
		hasLiniendiagramm = true;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public Image getImage() {
		return img;
	}
	public void setImage(Image img) {
		this.img = img;
	}
	public boolean hasImage() {
		return hasImage;
	}
	public boolean hasLiniendiagramm() {
		return hasLiniendiagramm;
	}
	public Ereignismenge geteMenge() {
		return eMenge;
	}
	public void seteMenge(Ereignismenge eMenge) {
		this.eMenge = eMenge;
	}
	public List<Menge> getEreignisse() {
		return ereignisse;
	}
	public void setEreignisse(List<Menge> ereignisse) {
		this.ereignisse = ereignisse;
	}
	
	
}
