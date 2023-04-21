package haw.lernsoftware.view;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;

/**
 * Die Startseite des Programms.
 */
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
		JTextArea einleitungText = new JTextArea(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.einleitungstext"));
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
