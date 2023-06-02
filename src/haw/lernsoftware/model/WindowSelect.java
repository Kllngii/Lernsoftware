package haw.lernsoftware.model;

import java.util.Arrays;

/**
 * Ein Enum, welches das zuletzt geöffnete Fenster symbolisch beschreibt.
 * @author Lasse Kelling
 */
public enum WindowSelect {
	AUFGABENTEXT("aufgabentext"),
	LINIENDIAGRAMM("liniendiagramm"),
	STARTSEITE("startseite"),
	TUTORIAL("tutorial"),
	TUTORIAL2("tutorial2");
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

	static WindowSelect parse(String string) {
		WindowSelect val = AUFGABENTEXT;
		for(WindowSelect ws : values()) {
			if(ws.identifier.equalsIgnoreCase(string))
				val = ws;
		}
		return val;
	}
	
}
