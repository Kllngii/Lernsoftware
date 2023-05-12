package haw.lernsoftware.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	private static final String MODEL_KEY = "model";
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

	private Preferences getRoot() {
		return Preferences.userRoot().node(Konst.PREFERENCES_ROOT_KEY);
	}

	/**
	 * Speichert ein Model in den Java-Preferences
	 * 
	 * @param model
	 */
	public void speichereInPreferences(ModelWithErrors model) {
		log.info("Speichere das Model in den Preferences!");

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(model);
			byte[] bytes = bos.toByteArray();

			getRoot().putByteArray(MODEL_KEY, bytes);
			log.debug("Model mit " + bytes.length + " Bytes wurde im Node " + getRoot().node(MODEL_KEY)
					+ " gespeichert!");
		} catch (IOException e) {
			log.error("Fehler beim Speichern in den Preferences!", e);
		}
	}

	/**
	 * Lädt ein Model aus den Java-Preferences
	 * 
	 * @return
	 */
	public ModelWithErrors ladeAusPreferences() {
		ModelWithErrors fromPrefs = null;
		byte[] bytes = getRoot().getByteArray(MODEL_KEY, null);
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

	public List<Aufgabe> ladeAufgaben() {
		log.debug("Lade Aufgaben!");
		return List.of(new Aufgabe(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text2")), new Aufgabe("Aufgabentext B"), new Aufgabe("Aufgabentext C"),
				new Aufgabe("Zum Bearbeiten der nächsten Aufgabensammlung klicken Sie im Reiter auf 'Aufgaben'."));
	}

	public List<String> getErrors() {
		return errors;
	}

	public void addError(String e) {
		this.errors.add(e);
	}

}
