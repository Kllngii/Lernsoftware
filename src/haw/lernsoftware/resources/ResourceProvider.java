package haw.lernsoftware.resources;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.apache.log4j.Logger;

import haw.lernsoftware.Konst;
/**
 * Verwaltet (lesende) Dateizugriffe auf die Dateien im resources-Package. Hier sollen bspw. Aufgabendateien abgelegt werden.
 * @author Lasse Kelling
 */
public class ResourceProvider {
	private static Logger log = Logger.getLogger(ResourceProvider.class);
	
	/**
	 * Liest eine Datei ein und gibt den Inhalt <b>aller Zeilen</b> zu einem String verbunden aus
	 * @param path Der Pfad der Datei
	 * @return Der Inhalt der Datei als String
	 * 
	 * @see #getFileContent(String)
	 */
	public static String getFileContentAsString(String path) {
		StringBuilder builder = new StringBuilder();
		for(String row : getFileContent(path))
			builder.append(row);
		
		return builder.toString();
	}
	/**
	 * Liest eine Datei zu einer Liste von {@link String Zeilen} ein.
	 * @param path Der Pfad der Datei
	 * @return Der Inhalt der Datei als Liste von Strings
	 */
	public static List<String> getFileContent(String path) {
		try {
		Objects.requireNonNull(path);
		URI pathURL = ResourceProvider.class.getResource(path).toURI();
		Path p = Paths.get(pathURL);
		log.debug("Einlesen der Datei " + path + " unter " + p.toAbsolutePath().toString());
		
		return Files.readAllLines(p);
		} catch(NullPointerException e) { //Falls reqireNonNull fehlschlägt
			log.error(path);
			log.error(e.getMessage());
		} catch (URISyntaxException e) { //Falls die pathURL ungültig ist
			log.error(path);
			log.error(e.getMessage());
		} catch (IOException e) { //Falls readAllLines fehlschlägt
			log.error(path);
			log.error(e.getMessage());
		}
		
		return new ArrayList<String>();
	}
	
	/**
	 * Lädt eine {@link Properties}-Datei und gibt den als <code>identifier</code> hinterlegten Wert der {@link Properties} zurück
	 * @param props Die zu ladene {@link Properties}-Datei vermutlich in der Klasse {@link Konst} zu finden
	 * @param identifier Der angeforderte Identifier
	 * @return Der String der Identifiers
	 * 
	 * @see Konst
	 */
	public static String loadStringFromProperties(String props, String identifier) {
		Properties p = new Properties();
		
		try(FileInputStream fis = new FileInputStream(Paths.get(ResourceProvider.class.getResource(props).toURI()).toFile())) {
			p.load(fis);
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (URISyntaxException e) {
			log.error(e.getMessage());
		}
		
		return (String) p.getOrDefault(identifier, "ERROR");
	}
	/**
	 * Lädt ein {@link Image} 
	 * @param path Der Pfad der Bilddatei relativ zum resources-Package
	 * @return das Bild
	 */
	public static Image loadImage(String path) {
		try {
			return Toolkit.getDefaultToolkit().getImage(ResourceProvider.class.getResource(path).toURI().toURL());
		} catch (MalformedURLException | URISyntaxException e) {
			log.error(e.getMessage());
		}
		return null;
	}
}
