package haw.lernsoftware.model.i18n;

import haw.lernsoftware.resources.ResourceProvider;

/**
 * @author Lasse Kelling
 */
public class TranslationProvider {
	public static String getTranslation(String key) {
		//TODO i18n bauen
		return ResourceProvider.loadStringFromProperties("i18n.de-de", key);
	}
}
