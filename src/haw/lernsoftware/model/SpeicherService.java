package haw.lernsoftware.model;

import java.util.Collection;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;

import haw.lernsoftware.Konst;

public class SpeicherService {
	private static final String MODEL_KEY = "model";
	private final Logger log = Logger.getLogger(getClass());
	
	public static class ModelWithErrors {
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
	
}
