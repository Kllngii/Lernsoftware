package haw.lernsoftware.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
		if(!jsonStr.equals("{}")) {
			Model m = Model.fromJSON(jsonStr);
			if(m.getAufgaben().size() == ladeAufgaben().size()) {
				//Nur wenn sich die Anzahl der Aufgaben nicht geändert hat wird der alte Stand geladen
				m.setAufgaben(ladeAufgaben());
				return m;
			}
		}
		return new Model(ladeAufgaben());
	}

	/**
	 * Lädt ein Model aus den Java-Preferences
	 * 
	 * @return
	 */
	@Deprecated(since = "02.06.2023")
	private ModelWithErrors ladeAusPreferences() {
		log.info("Lade aus den Prefs!");
		ModelWithErrors fromPrefs = null;
		byte[] bytes = getRoot().getByteArray(Konst.MODEL_KEY, null);
		if (bytes != null) {
			try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
					ObjectInputStream ois = new ObjectInputStream(bis)) {
				fromPrefs = (ModelWithErrors) ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				log.error("Fehler beim Laden aus den Preferences!", e);
			}
		} else
			log.info("Die Preferences enthalten noch kein gespeichertes Model!");

		return fromPrefs;
	}

	public static List<Aufgabe> ladeAufgaben() {
		Logger.getLogger(SpeicherService.class).info("Lade Aufgaben!");
		// TODO Aufgaben hier hinzufügen
		return List.of(
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe1.text"),
						"aufgabentext_test.png"),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe2.text")),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe3.text")),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe4.text")),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe5.text")),
				new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "aufgabe6.text")),
				new Aufgabe("Zum Bearbeiten der nächsten Aufgabensammlung klicken Sie im Reiter auf 'Aufgaben'."));
	}

	public List<String> getErrors() {
		return errors;
	}

	public void addError(String e) {
		this.errors.add(e);
	}

}
