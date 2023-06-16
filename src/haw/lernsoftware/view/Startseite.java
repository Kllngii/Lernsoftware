package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.Konst;
import haw.lernsoftware.model.WindowSelect;
import haw.lernsoftware.resources.ResourceProvider;

/**
 * Die Startseite des Programms.
 * @author Moritz Koch
 */
public class Startseite extends HAWView {

	//Definition und Init.
	Logger log = Logger.getLogger(getClass());
	private GUI gui;
	private JPanel view = new JPanel();
	private JLabel functionText1 = new JLabel();
	private JLabel functionText2 = new JLabel();
	private JLabel functionText3 = new JLabel();
	EmptyBorder emptyBorder = new EmptyBorder(12, 100, 12, 12); // oben, rechts, unten, links
	EmptyBorder thickEmptyBorder = new EmptyBorder(15, 100, 15, 15);
	LineBorder lBorder = new LineBorder(new Color(100, 100, 100));
	MatteBorder mBorder = new MatteBorder(4, 4, 4, 4, Color.DARK_GRAY);

	public Startseite(GUI gui) {
		this.gui = gui;				
		
		//Scrollbar hinzufügen
		panel = new JScrollPane(view);
		panel.setBorder(emptyBorder);
		view.setBackground(new Color(230, 230, 230));
		panel.setBackground(new Color(230, 230, 230));
		view.add(constructStartseite());			
		((JScrollPane)panel).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		((JScrollPane)panel).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	}

	private JComponent constructStartseite() {

		//Überschrift
		JLabel ueberschrift = new JLabel("Lernsoftware Startseite");
		ueberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		ueberschrift.setFont(ueberschrift.getFont().deriveFont(40f));

		// Einleitungstext einfügen und ausrichten
		JLabel einleitungText = new JLabel();
		einleitungText.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.einleitungstext"));

		// Beschreibungstexte hinzufügen
		// Tutorial
		functionText1.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.tutorial2"));
		setNormalBorder(functionText1);
		functionText1.addMouseListener(mL);
		functionText1.setBorder(BorderFactory.createCompoundBorder(mBorder, emptyBorder));
		functionText1.setBorder(BorderFactory.createCompoundBorder(lBorder, thickEmptyBorder));

		//Aufgaben
		functionText2.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.aufgaben2"));
		setNormalBorder(functionText2);
		functionText2.addMouseListener(mL);
		functionText2.setBorder(BorderFactory.createCompoundBorder(mBorder, emptyBorder));
		functionText2.setBorder(BorderFactory.createCompoundBorder(lBorder, thickEmptyBorder));

		//Sandbox 
		functionText3.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_STARTSEITE, "startseite.sandbox2"));
		setNormalBorder(functionText3);
		functionText3.addMouseListener(mL);
		functionText3.setBorder(BorderFactory.createCompoundBorder(mBorder, emptyBorder));
		functionText3.setBorder(BorderFactory.createCompoundBorder(lBorder, thickEmptyBorder));
		
		//FormBuilder hinzufügen
		// gibt einen JComponent zurück, der .debug(true)
		return FormBuilder.create() 
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu")//, center:200dlu, 200dlu") //
				.rows("100dlu, 160dlu, p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 5) //
				.add(padding(einleitungText, 10)).xyw(1, 2, 5) //
				.add(functionText1).xy(1, 3) //
				.add(functionText2).xy(3, 3) //
				.add(functionText3).xy(5, 3) //
				.build(); //
	}

	// JLabel Weiterleitung auf die jeweiligen Seiten
	MouseAdapter mL = new MouseAdapter() {

		public void mouseReleased(java.awt.event.MouseEvent e) {

			if(e.getSource() == functionText1) {
				gui.switchToView(WindowSelect.TUTORIAL);
			} else if(e.getSource() == functionText2) {
				gui.switchToView(WindowSelect.AUFGABENTEXT);
			}else if(e.getSource() == functionText3) {
				gui.switchToView(WindowSelect.LINIENDIAGRAMM);
			}
		}
		// Rahmenänderung, wenn der Mauszeiger auf das JLabel gerichtet ist 
		public void mouseEntered(java.awt.event.MouseEvent e) {
			((JComponent) e.getSource()).setBorder(BorderFactory.createCompoundBorder(mBorder, emptyBorder));
		}
		public void mouseExited(java.awt.event.MouseEvent e) {
			((JComponent) e.getSource()).setBorder(BorderFactory.createCompoundBorder(lBorder, thickEmptyBorder));
		}
	};
	//Methode: Rahmen auf normal definierte Werte setzen 
	public void setNormalBorder(JLabel textLabel) {
		textLabel.setBorder(BorderFactory.createCompoundBorder(lBorder, emptyBorder));
		textLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

}
