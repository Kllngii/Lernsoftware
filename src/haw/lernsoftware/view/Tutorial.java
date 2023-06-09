package haw.lernsoftware.view;

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
public class Tutorial extends HAWView implements ActionListener {
	//Definition und Init.
	Logger log = Logger.getLogger(getClass());
	private GUI gui;
	private JLabel ueberschrift = new JLabel("Tutorial 1");
	private JButton tutorialNext = new CircleButton("nächstes Tutorial");
	
	// Skalierung für das ImageIcon
	int x1 = 700; // Gewünschte Breite des Bildes
	int y1 = 400; // Gewünschte Höhe des Bildes
	int x = 500; // Gewünschte Breite des Bildes
	int y = 300; // Gewünschte Höhe des Bildes

	//Erstellen eines Fensters mit ScrollBar
	public Tutorial(GUI gui) {
		this.gui = gui;
		JPanel view = new JPanel();
		panel = new JScrollPane(view);
		view.add(constructStartseite());
		JScrollBar scroll = new JScrollBar();
		scroll.setUnitIncrement(16);
		((JScrollPane)panel).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		((JScrollPane)panel).setVerticalScrollBar(scroll);

	}

	private JComponent constructStartseite() {

		// Überschrift Textgröße ändern
		ueberschrift.setFont(ueberschrift.getFont().deriveFont(50f));

		JPanel buttonAnordnung = new JPanel();
		buttonAnordnung.setLayout(new FlowLayout());
		buttonAnordnung.add(tutorialNext);
		
		//Button konfigurieren
		tutorialNext.addActionListener(this);

		//FormBuilder erstellen 
		JComponent inhalt = FormBuilder.create()
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu") //
				.rows("p, p, p, 5dlu, p, p, p, 5dlu, p, 5dlu, p, 5dlu, p, p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 3) //
				.add(buttonAnordnung).xyw(5, 1, 1) //
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == tutorialNext) {
			log.debug("Wechsle zum Tutorial 2");
			gui.switchToView(WindowSelect.TUTORIAL2);
		}
	}

}
