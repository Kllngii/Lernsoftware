package haw.lernsoftware.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
 */
public class Startseite extends HAWView {

	//Definition und Init.
	Logger log = Logger.getLogger(getClass());
	private GUI gui;
	private JPanel view = new JPanel();
	JLabel functionText1 = new JLabel();
	JLabel functionText2 = new JLabel();
	JLabel functionText3 = new JLabel();
	EmptyBorder eBorder = new EmptyBorder(12, 12, 12, 12); // oben, rechts, unten, links
	LineBorder lBorder = new LineBorder(new Color(100, 100, 100));
	MatteBorder mBorder = new MatteBorder(4, 4, 4, 4, Color.DARK_GRAY);

	public Startseite(GUI gui) {
		this.gui = gui;				
		
		
		panel = new JScrollPane(view);
		view.add(constructStartseite());			
		((JScrollPane)panel).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		((JScrollPane)panel).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//Image Background = ResourceProvider.loadImage(Konst.Background_JPEG);
		//panel.setLayout(new BorderLayout());
		//panel.setContentPane(new JLabel(new ImageIcon(Background)));
		//panel.setLayout(new FlowLayout());
		//panel.add(constructStartseite());
		//panel.add(constructStartseite());

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
		return FormBuilder.create() 
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu")//, center:200dlu, 200dlu") //
				.rows("100dlu, 160dlu, p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 5) //
				.add(einleitungText).xyw(1, 2, 5) //
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
			((JComponent) e.getSource()).setBorder(BorderFactory.createCompoundBorder(mBorder, eBorder));
		}
		public void mouseExited(java.awt.event.MouseEvent e) {
			((JComponent) e.getSource()).setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		}
	};
	//Methode: Rahmen auf normal definierte Werte setzen 
	public void setNormalBorder(JLabel textLabel) {
		textLabel.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));
		textLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

}
