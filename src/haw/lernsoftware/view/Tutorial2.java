package haw.lernsoftware.view;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;


/**
 * Die Tutorial des Programms.
 */
public class Tutorial2 extends HAWView {
	//Definition und Init.
	Logger log = Logger.getLogger(getClass());
	private javax.swing.border.Border border = javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED);
	private GUI gui;
	JLabel ueberschrift = new JLabel("Tutorial für mittelschwere Aufgaben");
	JLabel text1 = new JLabel();
	JLabel bildLabel1 = new JLabel();
	
	// Skalierung für das ImageIcon
    int x1 = 700; // Gewünschte Breite des Bildes
    int y1 = 400; // Gewünschte Höhe des Bildes
    int x = 500; // Gewünschte Breite des Bildes
    int y = 300; // Gewünschte Höhe des Bildes
	
	//Erstellen eines Fensters mit ScrollBar
	public Tutorial2(GUI gui) {
		this.gui = gui;
		JPanel view = new JPanel();
		panel = new JScrollPane(view);
		view.add(constructStartseite());
		JScrollBar scroll = new JScrollBar();
		scroll.setUnitIncrement(16);
		((JScrollPane)panel).setVerticalScrollBar(scroll);
	}

	private JComponent constructStartseite() {
		
		// Überschrift Textgröße ändern
		ueberschrift.setFont(ueberschrift.getFont().deriveFont(50f));
		//FormBuilder erstellen 
		JComponent inhalt = FormBuilder.create()
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu") //
				.rows("p, p, p, 5dlu, p, p, p, 5dlu, p, 5dlu, p, 5dlu, p, p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_Ueberschrift.text")).xyw(1, 2, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_absatz1.text")).xyw(1, 3, 2) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_zwischenUeberschrift.text")).xyw(3,3,3) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_absatz2.text")).xyw(1, 5, 2) //
				.add(new JLabel(resizeImage(new ImageIcon(ResourceProvider.loadImage(Konst.FOLGEN_EINFÜHRUNG_BILD_1)), x, y) )).xyw(3, 5, 3) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_absatz3.text")).xyw(1, 7, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_absatz4.text")).xyw(1, 9, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial3_absatz5.text")).xyw(1, 11, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial4_absatz6.text")).xyw(1, 13, 5) //
				
				.build(); //
		// Formbuilder "inhalt" in einen neuen Formbuilder mit ScrollBar einfügen und zurückgeben
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

}
