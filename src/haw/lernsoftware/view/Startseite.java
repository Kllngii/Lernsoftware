package haw.lernsoftware.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.text.html.HTMLEditorKit;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Die Startseite des Programms.
 */
public class Startseite extends HAWView {

	Logger log = Logger.getLogger(getClass());
	JPanel jp;
	private javax.swing.border.Border border = javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED);
	
	
	public Startseite() {
		panel.add(constructStartseite());

		//panel.add(buildContent());
	}

	private JComponent constructStartseite() {
		//panel.add(new JLabel("Das hier ist die Startseite"));

		//Panel formatieren
		jp = new JPanel();
		jp.setPreferredSize(new java.awt.Dimension(Toolkit.getDefaultToolkit().getScreenSize()));

		//Layout
		/*GridBagLayout gridbag = new GridBagLayout();
		jp.setLayout(gridbag);
		jp.setBorder(new EmptyBorder(0,300,0,300));			//Creates an empty border with the specified insets.
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1.0;
		c.insets = new Insets(5,5,0,5);*/

		//HTMLEditorKit kit = new HTMLEditorKit();


		EmptyBorder eBorder = new EmptyBorder(10, 10, 10, 10); // oben, rechts, unten, links
		LineBorder lBorder = new LineBorder(new Color(100, 100, 100));


		//Überschrift
		JLabel ueberschrift = new JLabel("Lernsoftware Startseite");
		ueberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		ueberschrift.setFont(ueberschrift.getFont().deriveFont(40f));

		// Einleitungstext einfügen und ausrichten
		JLabel einleitungText = new JLabel();
		einleitungText.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.einleitungstext"));


		// Beschreibungstexte hinzufügen
		// Tutorial
		JLabel functionText1 = new JLabel();
		functionText1.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.tutorial"));
		functionText1.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		//functionText1.insets = new Insets(10,10,10,10);

		//Aufgaben
		JLabel functionText2 = new JLabel();
		functionText2.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.aufgaben"));
		functionText2.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));

		//Sandbox 
		JLabel functionText3 = new JLabel();
		functionText3.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.sandbox"));
		functionText3.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));


		//FormBuilder hinzufügen statt gridbagLayout

		// gibt einen JComponent zurück, der .debug(true)
		return FormBuilder.create() // Rote Linien zeichnen
				.columns("200dlu, 200dlu, 200dlu")//, center:200dlu, 200dlu") //
				.rows("100dlu, 200dlu, p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 3) //
				.add(einleitungText).xyw(1, 2, 3) //
				.add(functionText1).xy(1, 3) //
				.add(functionText2).xy(2, 3).padding(new EmptyBorder(0,100,0,100)) //
				.add(functionText3).xy(3, 3) //
				.build(); //
	}

	//public JComponent buildContent() {
	/*// Einfügen der Elemente
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
				.build();*/




	//}
}
