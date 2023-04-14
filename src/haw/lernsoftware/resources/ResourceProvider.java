package haw.lernsoftware.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
/**
 * Verwaltet (lesende) Dateizugriffe auf die Dateien im resources-Package. Hier sollen bspw. Aufgabendateien abgelegt werden.
 * @author Lasse Kelling
 *
 */
public class ResourceProvider {
	static Logger log = Logger.getLogger(ResourceProvider.class);
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
		log.debug("Suche nach der Datei " + path + " unter " + p.toAbsolutePath().toString());
		
		return Files.readAllLines(p);
		} catch(NullPointerException e) { //Falls reqireNonNull fehlschlägt
			e.printStackTrace();
		} catch (URISyntaxException e) { //Falls die pathURL ungültig ist
			e.printStackTrace();
		} catch (IOException e) { //Falls readAllLines fehlschlägt
			e.printStackTrace();
		}
		
		return new ArrayList<String>();
	}
}
