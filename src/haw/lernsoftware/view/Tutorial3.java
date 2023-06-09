package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.Konst;
import haw.lernsoftware.model.WindowSelect;
import haw.lernsoftware.resources.ResourceProvider;


/**
 * Die Tutorial des Programms.
 */
public class Tutorial3 extends HAWView implements ActionListener{
	//Definition und Init.
	Logger log = Logger.getLogger(getClass());
	private javax.swing.border.Border border = javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED);
	private GUI gui;
	private JLabel ueberschrift = new JLabel("Tutorial 3");
	private JLabel text1 = new JLabel();
	private JLabel bildLabel1 = new JLabel();
	private JButton tutorialZurueck = new CircleButton("zurück");
	private JButton tutorialNext = new CircleButton("nächstes Tutorial");


	// Skalierung für das ImageIcon
	int x = 500; // Gewuenschte Breite des Bildes
	int y = 400; // Gewuenschte H�he des Bildes

	//Erstellen eines Fensters mit ScrollBar
	public Tutorial3(GUI gui) {

		this.gui = gui;
		JPanel view = new JPanel();
		panel = new JScrollPane(view);
		view.add(constructStartseite());
		JScrollBar scroll = new JScrollBar();
		scroll.setUnitIncrement(16);
		((JScrollPane)panel).setVerticalScrollBar(scroll);
	}

	private JComponent constructStartseite() {
		// ueberschrift Textgroeße ändern
		ueberschrift.setFont(ueberschrift.getFont().deriveFont(50f));

		// JPanel fuer die Buttons, um sie in einem neuen Layout anzuordnen
		JPanel buttonAnordnung = new JPanel();
		buttonAnordnung.setLayout(new FlowLayout());
		buttonAnordnung.add(tutorialZurueck);
		buttonAnordnung.add(tutorialNext);
		tutorialNext.getModel().setEnabled(false);

		//Button konfigurieren
		tutorialNext.addActionListener(this);
		tutorialZurueck.addActionListener(this);


		//FormBuilder erstellen 
		JComponent inhalt = FormBuilder.create()
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu") //
				.rows("p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 4) //
				.add(buttonAnordnung).xyw(5, 1, 1)
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial3_ueberschrift.text")).xyw(1, 2, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial3_absatz1.text")).xyw(1, 3, 5) //
				.add(new JLabel(" ")).xy(1, 4) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial3_absatz2.text")).xyw(1,5,5) //
				.add(new JLabel(" ")).xy(1, 6) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial3_absatz3.text")).xyw(1, 7, 5) //
				.add(new JLabel(" ")).xy(1, 8) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial3_absatz4.text")).xyw(1, 9, 5) //
				.add(new JLabel(" ")).xy(1, 10) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial3_absatz5.text")).xyw(1, 11, 5) //
				.add(new JLabel(" ")).xy(1, 12) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial3_zwischenUeberschrift.text")).xyw(1, 13, 5) //
				.add(new JLabel(" ")).xy(1, 14) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial3_absatz6.text")).xyw(1, 15, 2) //
				.add(new JLabel(resizeImage(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BEDINGTE_W)), x, y) )).xyw(3, 15, 3) //
				.build(); //
		// Formbuilder "inhalt" in einen neuen Formbuilder mit ScrollBar einf�gen und zur�ckgeben
		return FormBuilder.create()
				.columns("p")
				.rows("p")
				.add(inhalt) .xy(1, 1)
				.debug(true)
				.build();

	}

	//Bildgröße anpassen
	private ImageIcon resizeImage(ImageIcon img, int width, int height) {
		Image image = img.getImage();
		Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == tutorialZurueck) {
			log.debug("Wechsle zum Tutorial 2");
			gui.switchToView(WindowSelect.TUTORIAL2);
		}		
	}

}
