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
import javax.swing.border.MatteBorder;

import haw.lernsoftware.Konst;
import haw.lernsoftware.model.WindowSelect;
import haw.lernsoftware.resources.ResourceProvider;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;


/**
 * Die Startseite des Programms.
 */
public class Startseite extends HAWView {

	Logger log = Logger.getLogger(getClass());
	JPanel jp;
	private javax.swing.border.Border border = javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED);
	private GUI gui;
	JLabel functionText1 = new JLabel();
	JLabel functionText2 = new JLabel();
	JLabel functionText3 = new JLabel();
	EmptyBorder eBorder = new EmptyBorder(10, 10, 10, 10); // oben, rechts, unten, links
	LineBorder lBorder = new LineBorder(new Color(100, 100, 100));
	MatteBorder mBorder = new MatteBorder(4, 4, 4, 4, Color.DARK_GRAY);

	public Startseite(GUI gui) {
		this.gui = gui;
		panel.add(constructStartseite());

	}

	private JComponent constructStartseite() {
		//Panel formatieren

		//Überschrift
		JLabel ueberschrift = new JLabel("Lernsoftware Startseite");
		ueberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		ueberschrift.setFont(ueberschrift.getFont().deriveFont(40f));

		// Einleitungstext einfügen und ausrichten
		JLabel einleitungText = new JLabel();
		einleitungText.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.einleitungstext"));


		// Beschreibungstexte hinzufügen
		// Tutorial
		functionText1.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.tutorial"));
		setNormalBorder(functionText1);
		functionText1.addMouseListener(mL);

		//Aufgaben
		functionText2.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.aufgaben"));
		setNormalBorder(functionText2);
		functionText2.addMouseListener(mL);

		//Sandbox 
		functionText3.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.sandbox"));
		setNormalBorder(functionText3);
		functionText3.addMouseListener(mL);

		//FormBuilder hinzufügen
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

	MouseAdapter mL = new MouseAdapter() {

		public void mouseReleased(java.awt.event.MouseEvent e) {

			if(e.getSource() == functionText1) {
				log.debug("Wechsle zum Tutorial");
			} else if(e.getSource() == functionText2) {
				log.debug("Wechsle zum Aufgabentext");
				gui.switchToView(WindowSelect.AUFGABENTEXT);
			}else if(e.getSource() == functionText3) {
				log.debug("Wechsle zum Liniendiagramm");
				gui.switchToView(WindowSelect.LINIENDIAGRAMM);
			}
		}
		public void mouseEntered(java.awt.event.MouseEvent e) {
			((JComponent) e.getSource()).setBorder(BorderFactory.createCompoundBorder(mBorder, eBorder));
		}
		public void mouseExited(java.awt.event.MouseEvent e) {
			((JComponent) e.getSource()).setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		}
	};
	
	public void setNormalBorder(JLabel textLabel) {
		textLabel.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		textLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

}
