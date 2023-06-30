package haw.lernsoftware.model;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

import haw.lernsoftware.resources.ResourceProvider;

/**
 * Speichert alle Informationen zu einer Aufgabe. Unteranderem umfasst das:
 * <li>die Aufgabenstellung
 * <li>dazugehörige Bilder
 * <li>die Daten des Liniendiagramms
 * 
 * @author Lasse Kelling
 */
public class Aufgabe implements Serializable {
	private static final long serialVersionUID = -3727129919178718459L;
	private String text;
	//TODO Umbauen, um eine Liste von Bildern statt dieses hard-coding-Monsters zu akzeptieren
	private Image img;
	private Image img2;

	private boolean hasImage = false;
	private boolean hasLiniendiagramm = false;
	private Ereignismenge eMenge;
	private List<Menge> ereignisse;
	private List<Menge> startEreignisse;

	public Aufgabe(String text) {
		this.text = text;
	}

	public Aufgabe(String text, String pathToImage, String pathToImage2) {
		this.text = text;
		this.img = ResourceProvider.loadImage(pathToImage);
		this.img2 = ResourceProvider.loadImage(pathToImage2);

		hasImage = true;
	}

	public Aufgabe(String text, String pathToLiniendiagrammElementare, String pathToEreignisse,
			String pathToStartEreignisse) {
		this.text = text;
		this.eMenge = Ereignismenge
				.elementareFromJSON(ResourceProvider.getFileContentAsString(pathToLiniendiagrammElementare));
		this.ereignisse = Ereignismenge.ereignisseFromJSON(ResourceProvider.getFileContentAsString(pathToEreignisse),
				eMenge);
		this.startEreignisse = Ereignismenge
				.ereignisseFromJSON(ResourceProvider.getFileContentAsString(pathToStartEreignisse), eMenge);

		hasImage = false;
		hasLiniendiagramm = true;
	}

	public Aufgabe(String text, String pathToImage, String pathToImage2, String pathToLiniendiagrammElementare,
			String pathToEreignisse, String pathToStartEreignisse) {
		this.text = text;
		this.img = ResourceProvider.loadImage(pathToImage);
		this.img2 = ResourceProvider.loadImage(pathToImage2);
		this.eMenge = Ereignismenge
				.elementareFromJSON(ResourceProvider.getFileContentAsString(pathToLiniendiagrammElementare));
		this.ereignisse = Ereignismenge.ereignisseFromJSON(ResourceProvider.getFileContentAsString(pathToEreignisse),
				eMenge);
		this.startEreignisse = Ereignismenge
				.ereignisseFromJSON(ResourceProvider.getFileContentAsString(pathToStartEreignisse), eMenge);

		hasImage = true;
		hasLiniendiagramm = false;
	}
	
	public Aufgabe(String text, String pathToImage, String pathToLiniendiagrammElementare,
			String pathToEreignisse, String pathToStartEreignisse) {
		this.text = text;
		this.img = ResourceProvider.loadImage("Aufgabe7_Platzhalter_Bild.png");
		this.img2 = ResourceProvider.loadImage(pathToImage);
		this.eMenge = Ereignismenge
				.elementareFromJSON(ResourceProvider.getFileContentAsString(pathToLiniendiagrammElementare));
		this.ereignisse = Ereignismenge.ereignisseFromJSON(ResourceProvider.getFileContentAsString(pathToEreignisse),
				eMenge);
		this.startEreignisse = Ereignismenge
				.ereignisseFromJSON(ResourceProvider.getFileContentAsString(pathToStartEreignisse), eMenge);

		hasImage = true;
		hasLiniendiagramm = true;
	}

	public String getText() {
		return text;
	}

	public Image getImage() {
		return img;
	}

	public Image getImage2() {
		return img2;
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

	public List<Menge> getEreignisse() {
		return ereignisse;
	}

	public List<Menge> getStartEreignisse() {
		return startEreignisse;
	}

}
