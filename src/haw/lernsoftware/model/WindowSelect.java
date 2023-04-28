package haw.lernsoftware.model;

/**
 * Ein Enum, welches das zuletzt geöffnete Fenster symbolisch beschreibt.
 * @author Lasse Kelling
 */
public enum WindowSelect {
	AUFGABENTEXT("aufgabentext"),
	LINIENDIAGRAMM("liniendiagramm"),
	STARTSEITE("startseite"),
	;
	
	private final String identifier;
	
	WindowSelect(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}
	
	@Override
	public String toString() {
		return identifier;
	}
	
	
}
