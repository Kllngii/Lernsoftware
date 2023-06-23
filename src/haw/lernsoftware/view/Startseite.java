package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
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
	
	private CompoundBorder unselectedBorder = BorderFactory.createCompoundBorder(new LineBorder(new Color(100, 100, 100)), new EmptyBorder(15, 100, 15, 15));
	private CompoundBorder selectedBorder = BorderFactory.createCompoundBorder(new MatteBorder(4, 4, 4, 4, Color.DARK_GRAY), new EmptyBorder(12, 97, 12, 12));

	public Startseite(GUI gui) {
		this.gui = gui;				
		
		//Scrollbar hinzufügen
		panel = new JScrollPane(view);
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
		functionText1.setText("Tutorial");
		functionText1.setFont(new Font("Dialog", Font.BOLD, 40));
		functionText1.addMouseListener(mL);
		functionText1.setBorder(unselectedBorder);
		functionText1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		//Aufgaben
		functionText2.setText("Aufgaben");
		functionText2.setFont(new Font("Dialog", Font.BOLD, 40));
		functionText2.addMouseListener(mL);
		functionText2.setBorder(unselectedBorder);
		functionText2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		//Sandbox 
		functionText3.setText("Sandbox");
		functionText3.setFont(new Font("Dialog", Font.BOLD, 40));
		functionText3.addMouseListener(mL);
		functionText3.setBorder(unselectedBorder);
		functionText3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//FormBuilder hinzufügen
		// gibt einen JComponent zurück, der .debug(true)
		return FormBuilder.create() 
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu")//
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
			((JComponent) e.getSource()).setBorder(selectedBorder);
			
		}
		public void mouseExited(java.awt.event.MouseEvent e) {
			((JComponent) e.getSource()).setBorder(unselectedBorder);
		}
	};
}
