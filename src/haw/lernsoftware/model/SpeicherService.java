package haw.lernsoftware.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;

import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;

/**
 * Speichert das Model ab, um nach einem Programmneustart den alten Fortschritt
 * fortzuführen
 * 
 * @author Lasse Kelling
 *
 */
public class SpeicherService {
	private final Logger log = Logger.getLogger(getClass());
	private List<String> errors = new ArrayList<>();
	private static List<Aufgabe> geladeneAufgaben = new ArrayList<Aufgabe>();

	public static class ModelWithErrors implements Serializable {
		private static final long serialVersionUID = -63082038168922917L;
		private final Model model;
		private final Collection<String> errors;

		public ModelWithErrors(Model model, Collection<String> errors) {
			super();
			this.model = model;
			this.errors = errors;
		}

		public Model getModel() {
			return model;
		}

		public Collection<String> getErrors() {
			return errors;
		}
	}

	public class SkippingObjectOutputStream extends ObjectOutputStream {
		public SkippingObjectOutputStream(OutputStream out) throws IOException {
			super(out);
			enableReplaceObject(true);
		}

		@Override
		protected Object replaceObject(Object obj) throws IOException {
			if ((obj instanceof Serializable))
				return obj;
			log.debug(obj.toString() + " ist nicht Serializable!");
			return null;
		}
	}

	private static Preferences getRoot() {
		return Preferences.userRoot().node(Konst.PREFERENCES_ROOT_KEY);
	}

	public static void speichereInPreferences(String key, String value) {
		getRoot().put(key, value);
	}

	public static String ladeAusPreferences(String key) {
		return getRoot().get(key, "{}");
	}

	/**
	 * Speichert ein Model in den Java-Preferences
	 * 
	 * @param model
	 */
	@Deprecated(since = "02.06.2023")
	public void speichereInPreferences(ModelWithErrors model) {
		log.info("Speichere das Model in den Preferences!");

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				SkippingObjectOutputStream oos = new SkippingObjectOutputStream(bos)) {
			oos.writeObject(model);
			byte[] bytes = bos.toByteArray();

			getRoot().putByteArray(Konst.MODEL_KEY, bytes);
			log.debug("Model mit " + bytes.length + " Bytes wurde im Node " + getRoot().node(Konst.MODEL_KEY)
					+ " gespeichert!");
		} catch (IOException e) {
			log.error("Fehler beim Speichern in den Preferences!", e);
		}
	}

	public Model ladeModel() {
		String jsonStr = ladeAusPreferences(Konst.MODEL_KEY);
		if (!jsonStr.equals("{}")) {
			Model m = Model.fromJSON(jsonStr);
			if (m.getAufgaben().size() == ladeAufgaben().size()) {
				// Nur wenn sich die Anzahl der Aufgaben nicht geändert hat wird der alte Stand
				// geladen
				m.setAufgaben(ladeAufgaben());
				return m;
			}
		}
		return new Model(ladeAufgaben());
	}

	public static List<Aufgabe> ladeAufgaben() {
		if (geladeneAufgaben.size() != 0)
			return geladeneAufgaben; // Quick-return ohne Debugausgaben...
		Logger.getLogger(SpeicherService.class).info("Lade Aufgaben!");

		geladeneAufgaben = List.of(
				// new
				// Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN,
				// "aufgabe1.text"), "aufgabentext_test.png"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe1.text"),
						"elementare_aufgabe1.em", "ereignisse_aufgabe1.em", "ereignisse_start_aufgabe1.em"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe2.text"),
						"elementare_aufgabe2.em", "ereignisse_aufgabe2.em", "ereignisse_start_aufgabe2.em"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe3.text"),
						"elementare_aufgabe3.em", "ereignisse_aufgabe3.em", "ereignisse_start_aufgabe3.em"),
				// new
				// Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN,
				// "aufgabe2.text"), "elementare_aufgabe4.em", "ereignisse_aufgabe4.em"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe4.text"),
						"elementare_aufgabe4.em", "ereignisse_aufgabe4.em", "ereignisse_start_aufgabe4.em"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe5.text"),
						"elementare_aufgabe5.em", "ereignisse_aufgabe5.em", "ereignisse_start_aufgabe5.em"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe6.text"),
						"elementare_aufgabe6.em", "ereignisse_aufgabe6.em", "ereignisse_start_aufgabe6.em"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe7.text"),
						"elementare_aufgabe7.em", "ereignisse_aufgabe7.em", "ereignisse_start_aufgabe7.em"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe8.text"),
						"Aufgabe8_LeeresDiagramm_Bild.png", "Aufgabe8_Lösung_Bild.png", "elementare_aufgabe8.em",
						"ereignisse_aufgabe8.em", "ereignisse_start_aufgabe8.em"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe9.text"),
						"Aufgabe9_LeeresDiagramm.png", "Aufgabe9_Lösung.png"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe10.text"),
						"Aufgabe10_LeeresDiagramm_Bild.png", "Aufgabe10_Lösung_Bild.png"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe11.text"),
						"Aufgabe11_LeeresDiagramm_Bild.png", "Aufgabe11_Lösung_Bild.png"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe12.text"),
						"Aufgabe12_LeeresDiagramm_Bild.png", "Aufgabe12_Lösung_Bild.png"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe13.text"),
						"Aufgabe13_LeeresDiagramm_Bild.png", "Aufgabe13_Lösung_Bild.png"));
		// new
		// Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN,
		// "aufgabe9.text")),
		// new Aufgabe("Zum Bearbeiten der nächsten Aufgabensammlung klicken Sie im
		// Reiter auf 'Aufgaben'."));

		return geladeneAufgaben;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void addError(String e) {
		this.errors.add(e);
	}

}
