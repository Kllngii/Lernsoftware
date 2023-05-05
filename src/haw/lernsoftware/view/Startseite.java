package haw.lernsoftware.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
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
		constructStartseite();

		//panel.add(buildContent());
	}

	private void constructStartseite() {
		//panel.add(new JLabel("Das hier ist die Startseite"));

		//Panel formatieren
		jp = new JPanel();
		jp.setPreferredSize(new java.awt.Dimension(Toolkit.getDefaultToolkit().getScreenSize()));

		//Layout
		GridBagLayout gridbag = new GridBagLayout();
		jp.setLayout(gridbag);
		jp.setBorder(new EmptyBorder(0,300,0,300));			//Creates an empty border with the specified insets.
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1.0;
		c.insets = new Insets(5,5,0,5);

		//HTMLEditorKit kit = new HTMLEditorKit();


		EmptyBorder eBorder = new EmptyBorder(10, 10, 10, 10); // oben, rechts, unten, links
		LineBorder lBorder = new LineBorder(new Color(100, 100, 100));


		//Überschrift
		JLabel ueberschrift = new JLabel("Lernsoftware Startseite");
		ueberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		ueberschrift.setFont(ueberschrift.getFont().deriveFont(40f));
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0; 
		jp.add(ueberschrift, c);

		// Einleitungstext einfügen und ausrichten
		JLabel einleitungText = new JLabel();
		einleitungText.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.einleitungstext"));
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;	//Gewichtung des Elements in einer Spalte
		c.gridwidth = 3;	//wie viele Spalten soll  das Element einnehmen
		c.gridx = 0;		//in welche Spalte beginnt das Element
		c.gridy = 1; 		//in welcher Zeile beginnt das Element
		jp.add(einleitungText, c);	//zum Panel hinzufügen


		// Beschreibungstexte hinzufügen
		// Tutorial
		JLabel functionText1 = new JLabel();
		functionText1.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.tutorial"));
		c.ipady = 0;      //make this component tall
		c.weightx = 0;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		functionText1.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		jp.add(functionText1, c);

		//Aufgaben
		JLabel functionText2 = new JLabel();
		functionText2.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.aufgaben"));
		c.weightx = 1;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		functionText2.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		jp.add(functionText2, c);

		//Sandbox 
		JLabel functionText3 = new JLabel();
		functionText3.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.sandbox"));
		c.weightx = 1;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 2;
		functionText3.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		jp.add(functionText3, c);


		//FormBuilder hinzufügen statt gridbagLayout
		/*
		// gibt einen JComponent zurück, der .debug(true)
				return FormBuilder.create() // Rote Linien zeichnen
						.columns("100dlu, center:200dlu, 100dlu") //
						.rows("p, 20dlu, p, $lg, top:300dlu") //
						.padding(Paddings.DIALOG) //
						.add(previousTaskButton).xy(1, 2) //
						.add(titleTaskLabel).xy(2, 1) //
						.add(nextTaskButton).xy(3, 2) //
						.addSeparator("Aufgabentext").xyw(1, 3, 3) //
						.add(einleitungText).xyw(1, 5, 3) //
						.build(); //

*/
		panel.add(jp);
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
