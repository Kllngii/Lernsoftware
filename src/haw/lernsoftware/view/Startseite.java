package haw.lernsoftware.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.text.html.HTMLEditorKit;

import org.apache.log4j.Logger;

import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;

/**
 * Die Startseite des Programms.
 */
public class Startseite extends HAWView {
	
	Logger log = Logger.getLogger(getClass());
	JPanel jp;
	
	
	public Startseite() {
		constructStartseite();

		//panel.add(buildContent());
	}

	private void constructStartseite() {
		//panel.add(new JLabel("Das hier ist die Startseite"));
		
		
		jp = new JPanel();
		jp.setPreferredSize(new java.awt.Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		//jp.setLayout(new FlowLayout(1, 100, 100)); // align from left to right, horizantal gap, vertical gap 
		GridBagLayout gridbag = new GridBagLayout();
	    jp.setLayout(gridbag);
	    jp.setBorder(new EmptyBorder(0,300,0,300));
	    GridBagConstraints c = new GridBagConstraints();
	    c.anchor = GridBagConstraints.NORTH;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weighty = 1.0;
	    c.insets = new Insets(5,20,0,20);
	    
	    HTMLEditorKit kit = new HTMLEditorKit();
	    
	    JLabel ueberschrift = new JLabel("Lernsoftware Startseite");
	    ueberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	    ueberschrift.setFont(ueberschrift.getFont().deriveFont(40f));
	    //c.fill = GridBagConstraints.CENTER;
	    
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0; 
		jp.add(ueberschrift, c);
	    
		JEditorPane einleitungText = new JEditorPane();
		einleitungText.setEditorKit(kit);
		einleitungText.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.einleitungstext"));
		//einleitungText.setLineWrap(true);
		//einleitungText.setWrapStyleWord(true);
		einleitungText.setEditable(false);
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1; 
		jp.add(einleitungText, c);
		
		
		JEditorPane functionText1 = new JEditorPane();
		functionText1.setEditorKit(kit);
		functionText1.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.tutorial"));
		//functionText1.setLineWrap(true);
		//functionText1.setWrapStyleWord(true);
		functionText1.setEditable(false);
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;      //make this component tall
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		jp.add(functionText1, c);
		
		JEditorPane functionText2 = new JEditorPane();
		functionText2.setEditorKit(kit);
		functionText2.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.aufgaben"));
		//functionText2.setLineWrap(true);
		//functionText2.setWrapStyleWord(true);
		functionText2.setEditable(false);
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		jp.add(functionText2, c);
		
		JEditorPane functionText3 = new JEditorPane();
		functionText3.setEditorKit(kit);
		functionText3.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.sandbox"));
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 2;
		jp.add(functionText3, c);
		
		
	   
        
		
		/*
		jp.add(ueberschrift);
		jp.add(einleitungText);
		jp.add(functionText1);
		jp.add(functionText2);
		jp.add(functionText3);
		
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
