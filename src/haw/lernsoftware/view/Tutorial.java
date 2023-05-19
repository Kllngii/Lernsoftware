package haw.lernsoftware.view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;


/**
 * Die Tutorial des Programms.
 */
public class Tutorial extends HAWView {

	Logger log = Logger.getLogger(getClass());
	private javax.swing.border.Border border = javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED);
	private GUI gui;
	JLabel ueberschrift = new JLabel("Tutorial");
	
	JLabel text1 = new JLabel();
	JLabel bildLabel1 = new JLabel();
	
	// Skaliere das ImageIcon
    int x1 = 700; // Gewünschte Breite des Bildes
    int y1 = 400; // Gewünschte Höhe des Bildes
    int x = 500; // Gewünschte Breite des Bildes
    int y = 300; // Gewünschte Höhe des Bildes
	
	

	public Tutorial(GUI gui) {
		this.gui = gui;
		JPanel view = new JPanel();
		panel = new JScrollPane(view);
		view.add(constructStartseite());
	}

	private JComponent constructStartseite() {
		
		ueberschrift.setFont(ueberschrift.getFont().deriveFont(50f));
		//FormBuilder hinzufügen
		JComponent inhalt = FormBuilder.create() // Rote Linien zeichnen
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu")//, center:200dlu, 200dlu") //
				.rows("p, p, p, 5dlu, p, p, p, 5dlu, p, 5dlu, p, 5dlu, p, p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_Ueberschrift.text")).xyw(1, 2, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz1.text")).xyw(1, 3, 2) //
				.add(new JLabel(resizeImage(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD1)), x1, y1) )).xyw(3,3,3) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz2.text")).xyw(1, 5, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz31.text")).xyw(1, 6, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz3.text")).xyw(1, 7, 2) //
				.add(new JLabel(resizeImage(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD2)), x, y) )).xyw(3,7,3) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz4.text")).xyw(1, 9, 2) //
				.add(new JLabel(resizeImage(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD3)), x, y) )).xyw(3,9,3) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz5.text")).xyw(1, 11, 2) //
				.add(new JLabel(resizeImage(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD4)), x, y) )).xyw(3,11,3) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz6.text")).xyw(1, 13, 2) //
				.add(new JLabel(resizeImage(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD5)), x, y) )).xyw(3,13,3) //
				
				
				.build(); //
		return FormBuilder.create()
				  .columns("p")
				  .rows("p")
				  .addScrolled(inhalt) .xy(1, 1)
				  .debug(true)
				  .build();
		//test
	}

	private ImageIcon resizeImage(ImageIcon img, int width, int height) {
		Image image = img.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
	}

}
