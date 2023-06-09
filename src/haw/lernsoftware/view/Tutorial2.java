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
public class Tutorial2 extends HAWView implements ActionListener{
	//Definition und Init.
	Logger log = Logger.getLogger(getClass());
	private javax.swing.border.Border border = javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED);
	private GUI gui;
	private JLabel ueberschrift = new JLabel("Tutorial 2");
	private JLabel text1 = new JLabel();
	private JLabel bildLabel1 = new JLabel();
	private JButton tutorialNext = new CircleButton("nächstes Tutorial");
	private JButton tutorialZurück = new CircleButton("zurück");
	
	// Skalierung für das ImageIcon
    int x = 500; // Gewünschte Breite des Bildes
    int y = 400; // Gewünschte Höhe des Bildes
	
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
		//hallo
		// Überschrift Textgröße ändern
		ueberschrift.setFont(ueberschrift.getFont().deriveFont(50f));
		JPanel buttonAnordnung = new JPanel();
		buttonAnordnung.setLayout(new FlowLayout());
		buttonAnordnung.add(tutorialZurück);
		buttonAnordnung.add(tutorialNext);
		//Button konfigurieren
		tutorialNext.addActionListener(this);
		tutorialNext.setForeground(Color.WHITE);
		tutorialNext.setContentAreaFilled(false);
		tutorialNext.setBorderPainted(false);
		tutorialZurück.addActionListener(this);
		tutorialZurück.setForeground(Color.WHITE);
		tutorialZurück.setContentAreaFilled(false);
		tutorialZurück.setBorderPainted(false);

		
		//FormBuilder erstellen 
		JComponent inhalt = FormBuilder.create()
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu") //
				.rows("p, p, p, p, p, p, p, p, p, p, p, p, p, p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 4) //
				.add(buttonAnordnung).xyw(5, 1, 1) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_ueberschrift.text")).xyw(1, 2, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_absatz1.text")).xyw(1, 3, 5) //
				.add(new JLabel(" ")).xy(1, 4) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_zwischenUeberschrift.text")).xyw(1,5,2) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_absatz2.text")).xyw(1, 6, 2) //
				.add(new JLabel(resizeImage(new ImageIcon(ResourceProvider.loadImage(Konst.FOLGEN_EINFÜHRUNG_BILD_1)), x, y) )).xyw(3, 6, 3) //
				.add(new JLabel(" ")).xy(1, 7) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_absatz3.text")).xyw(1, 8, 5) //
				.add(new JLabel(" ")).xy(1, 9) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_absatz4.text")).xyw(1, 10, 5) //
				.add(new JLabel(" ")).xy(1, 11) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_absatz5.text")).xyw(1, 12, 5) //
				.add(new JLabel(" ")).xy(1, 13) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial2_absatz6.text")).xyw(1, 14, 5) //
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
		if(e.getSource() == tutorialZurück) {
			log.debug("Wechsle zum Tutorial 1");
			gui.switchToView(WindowSelect.TUTORIAL);
		} else if(e.getSource() == tutorialNext) {
			log.debug("Wechsle zum Tutorial 3");
			gui.switchToView(WindowSelect.TUTORIAL3);
		}
	}

}
