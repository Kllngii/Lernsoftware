package haw.lernsoftware.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseMotionListener;

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
import org.w3c.dom.events.MouseEvent;
import java.awt.event.MouseAdapter;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Cursor;


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
		functionText1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		functionText1.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(java.awt.event.MouseEvent e) {
				System.out.println("Tutorial");
			}
		});
		//functionText1.insets = new Insets(10,10,10,10);

		//Aufgaben
		JLabel functionText2 = new JLabel();
		functionText2.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.aufgaben"));
		functionText2.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		functionText2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		functionText2.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(java.awt.event.MouseEvent e) {
				System.out.println("Aufgaben");
			}
		});
		
		
		//Sandbox 
		JLabel functionText3 = new JLabel();
		functionText3.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.sandbox"));
		functionText3.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		functionText3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		functionText3.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(java.awt.event.MouseEvent e) {
				System.out.println("Sandbox");
			}
		});
		

		//FormBuilder hinzufügen statt gridbagLayout

		// gibt einen JComponent zurück, der .debug(true)
		return FormBuilder.create() // Rote Linien zeichnen
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu")//, center:200dlu, 200dlu") //
				.rows("100dlu, 200dlu, p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 5) //
				.add(einleitungText).xyw(1, 2, 5) //
				.add(functionText1).xy(1, 3) //
				.add(functionText2).xy(3, 3) //
				.add(functionText3).xy(5, 3) //
				.build(); //
	}
}
