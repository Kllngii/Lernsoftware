package haw.lernsoftware.view;

import java.awt.EventQueue;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import org.apache.log4j.Logger;

//<<<<<<< HEAD
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.Size;

import haw.lernsoftware.Lernsoftware;
import haw.lernsoftware.model.Ereignismenge;
import haw.lernsoftware.model.Menge;
import haw.lernsoftware.resources.ResourceProvider;

//=======
/**
 * Die Startseite des Programms.
 */
//>>>>>>> bb58b5a08b76894a64ffd29f95e630b4f5ce36dd
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
	    
		JTextArea einleitungText = new JTextArea("Dieser Werkzeugkasten ebnet den Einstieg in das Thema „Freie Software“ in der Schule und stellt einige für die schulische Arbeit geeignete Werkzeuge vor, sodass Lehrkräfte in kurzer Zeit entscheiden können, ob und wie diese ihnen bei ihrer Arbeit helfen können. Die Auswahl erhebt natürlich keinen Anspruch auf Vollständigkeit, präsentiert jedoch eine Übersicht über Software zu allen schulisch relevanten Bereichen und porträtiert detailliert und praxisbezogen sieben wichtige Werkzeuge samt pädagogischer Einschätzung.");
		einleitungText.setLineWrap(true);
		einleitungText.setWrapStyleWord(true);
		einleitungText.setEditable(false);
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1; 
		jp.add(einleitungText, c);
		
		
		JTextArea functionText1 = new JTextArea("Tutorial");
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;      //make this component tall
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		jp.add(functionText1, c);
		
		JTextArea functionText2 = new JTextArea("Aufgaben");
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		jp.add(functionText2, c);
		
		JTextArea functionText3 = new JTextArea("Diagramm-Sandbox");
		c.fill = GridBagConstraints.HORIZONTAL;
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
