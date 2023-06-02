package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.RenderingHints;

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
	JLabel ueberschrift = new JLabel("Tutorial 3");
	JLabel text1 = new JLabel();
	JLabel bildLabel1 = new JLabel();
	int diameter = 20;
	JButton tutorialZurück = new JButton("zurück") {
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			int diameter = Math.min(getWidth(), getHeight());
			Shape circle = new Ellipse2D.Double((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
			g2.setColor(getBackground());
			g2.fill(circle);

			super.paintComponent(g2);

			g2.dispose();
		}

		@Override
		protected void paintBorder(Graphics g) {
			// Kein Rahmen zeichnen
		}
	};
	// Skalierung für das ImageIcon
	int x = 500; // Gewünschte Breite des Bildes
	int y = 400; // Gewünschte Höhe des Bildes

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
		//hallo
		// Überschrift Textgröße ändern
		ueberschrift.setFont(ueberschrift.getFont().deriveFont(50f));

		JPanel buttonAnordnung = new JPanel();
		buttonAnordnung.setLayout(new FlowLayout());
		buttonAnordnung.add(tutorialZurück);
		//buttonAnordnung.add(tutorialNext);
		//Button konfigurieren
		//tutorialNext.addActionListener(this);
		tutorialZurück.addActionListener(this);
		//tutorialZurück.setForeground(Color.WHITE);
		//tutorialZurück.setBackground(Color.BLACK);




		//FormBuilder erstellen 
		JComponent inhalt = FormBuilder.create()
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu") //
				.rows("p, p, p, p, p, p, p, p, p, p, p, p, p, p,p,p,p") //
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
			log.debug("Wechsle zum Tutorial 2");
			gui.switchToView(WindowSelect.TUTORIAL2);
		}		
	}

}