package haw.lernsoftware.view;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.Lernsoftware;
import haw.lernsoftware.model.Ereignismenge;
import haw.lernsoftware.model.Menge;
import haw.lernsoftware.resources.ResourceProvider;

public class Startseite extends HAWView {
	
	Logger log = Logger.getLogger(getClass());
	
	public Startseite() {
		constructStartseite();

		panel.add(buildContent());
	}

	private void constructStartseite() {
		panel.add(new JLabel("Das hier ist die Startseite"));

	}
	
	public JComponent buildContent() {
		// Einfügen der Elemente
		JLabel ueberschrift = new JLabel("Lernsoftware");
		JTextArea einleitungText = new JTextArea("Dieser Werkzeugkasten ebnet den Einstieg in das Thema „Freie Software“ in der Schule und stellt einige für die schulische Arbeit geeignete Werkzeuge vor, sodass Lehrkräfte in kurzer Zeit entscheiden können, ob und wie diese ihnen bei ihrer Arbeit helfen können. Die Auswahl erhebt natürlich keinen Anspruch auf Vollständigkeit, präsentiert jedoch eine Übersicht über Software zu allen schulisch relevanten Bereichen und porträtiert detailliert und praxisbezogen sieben wichtige Werkzeuge samt pädagogischer Einschätzung.");
		einleitungText.setLineWrap(true);
		einleitungText.setWrapStyleWord(true);
		
		JLabel priceField = title("Title");
		return FormBuilder.create()
				//.columns("$label, $label, [100dlu, pref]")
				.columns("center: pref, default, default")
				.rows("p, p, p, p, p")
				.padding(Paddings.DIALOG)
				.add(ueberschrift) .xy(1, 1)
				.add(einleitungText).xy(1, 3)
				.add(priceField) .xy(3, 5)
				.build();
	}
}
