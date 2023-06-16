package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.model.Aufgabe;
import haw.lernsoftware.model.Model;
import haw.lernsoftware.model.WindowSelect;
import haw.lernsoftware.view.liniendiagramm.LinienDiagramm;

public class Aufgabentext extends HAWView implements ActionListener {
	private Model model;
	private List<Aufgabe> aufgaben;
	private JProgressBar progress;
	private int i;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
		refreshAufgabenview();
	}

	private final Logger log = Logger.getLogger(getClass());

	private JLabel titleTaskLabel = new JLabel("Aufgabe X:");
	private JButton nextTaskButton = new CircleButton("Weiter");
	private JButton previousTaskButton = new CircleButton("Zurück");
	private JButton toLiniendiagrammButton = new CircleButton("Liniendiagramm");
	private JTextArea aufgabenText = new JTextArea("if you can read this, report a bug");
	private JTextArea loesungText = new JTextArea("if you can read this, report a bug");
	private JComponent linienpanel;

	private ImageIcon bild;
	private JLabel aufgabenBild;
	private GUI gui;

	// fügt dem Panel von Aufgabentext einen JComponent zu
	public Aufgabentext(Model model, GUI gui) {
		this.model = model;
		this.gui = gui;
		aufgaben = model.getAufgaben();

		JPanel view = new JPanel();
		panel = new JScrollPane(view);
		view.add(buildContentText());
		JScrollBar scroll = new JScrollBar();
		scroll.setUnitIncrement(16);
		((JScrollPane) panel).setVerticalScrollBar(scroll);
		refreshAufgabenview();
		// panel.add(buildContentText());

	}

	// erstellt einen JComponent
	public JComponent buildContentText() {
		i = aufgaben.indexOf(model.getCurrentAufgabe());

		previousTaskButton.setEnabled(false);
		titleTaskLabel.setText("Aufgabe: " + (i + 1));

		progress = new JProgressBar(0, aufgaben.size() - 1);
		progress.setValue(aufgaben.indexOf(model.getCurrentAufgabe()));
		progress.setStringPainted(true);

		// Aufgabentext erstellen und formatieren
		aufgabenText.setText(model.getCurrentAufgabe().getText());
		aufgabenText.setLineWrap(true);
		aufgabenText.setWrapStyleWord(true);
		aufgabenText.setPreferredSize(new Dimension(100, 150));
		aufgabenText.setEditable(false);
		Color color = panel.getBackground();
		aufgabenText.setBackground(color);
		aufgabenText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));

		// Aufgabentext erstellen und formatieren
		loesungText.setText("Hier die Lösung eingeben!");
		loesungText.setLineWrap(true);
		loesungText.setWrapStyleWord(true);
		loesungText.setPreferredSize(new Dimension(100, 100));
		loesungText.setEditable(true);
		loesungText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		// loesungText.setBackground(color);

		// TODO was ist wenn auch noch Liniendiagramme dazukommen
		if (model.getCurrentAufgabe().hasImage() == true) {
			bild = new ImageIcon(model.getCurrentAufgabe().getImage());
			aufgabenBild = new JLabel(bild);
		} else {
			bild = new ImageIcon();
			aufgabenBild = new JLabel("");
		}

		// Button Listener
		previousTaskButton.addActionListener(this);
		nextTaskButton.addActionListener(this);
		toLiniendiagrammButton.addActionListener(this);

		// gibt einen JComponent zurück mit nur Text
		return FormBuilder.create().debug(true) // Rote Linien zeichnen
				.columns("100dlu, 5dlu, center:200dlu, 5dlu, 100dlu") //
				.rows("p, 20dlu, p, $lg, top:90dlu, p, top:10dlu, p, top:50dlu") //
				.padding(Paddings.DIALOG) //
				.add(titleTaskLabel).xy(3, 1) //
				.add(toLiniendiagrammButton).xy(5, 1) //
				.add(previousTaskButton).xy(1, 2) //
				.add(progress).xy(3, 2) //
				.add(nextTaskButton).xy(5, 2) //
				.addSeparator("Aufgabentext").xyw(1, 3, 5) //
				.add(aufgabenText).xyw(1, 5, 5) //
				.add(aufgabenBild).xyw(1, 6, 5) // Muss noch als Funktion Variabel gemacht werden um Bilder zu laden
				.add(linienpanel).xyw(1, 8, 5) //
				.add(loesungText).xyw(1, 9, 5) //
				.build(); //
	}

	// Bildgröße anpassen
	private ImageIcon resizeImage(ImageIcon img, int width) {
		float height = (float) width / (float) img.getIconWidth() * (float) img.getIconHeight();
		Image image = img.getImage();
		// double ratio = (width/height);
		// log.debug("Höhe " + img.getIconHeight() + "Breite " + img.getIconWidth());
		// double widthratio = width * ratio;
		// int height1 = (int) widthratio;
		Image resizedImage = image.getScaledInstance(width, Math.round(height), Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	private void refreshAufgabenview() {
		if (i == 0) {
			previousTaskButton.setEnabled(false);
		} else if (i == aufgaben.size() - 1) {
			titleTaskLabel.setText("Glückwunsch!");
			nextTaskButton.setEnabled(false);
		} else {
			previousTaskButton.setEnabled(true);
			nextTaskButton.setEnabled(true);
		}

		model.setCurrentAufgabe(aufgaben.get(i));

		Aufgabe current = model.getCurrentAufgabe();

		aufgabenText.setText(current.getText());
		titleTaskLabel.setText("Aufgabe: " + (i + 1));
		progress.setValue(i);

		if (model.getCurrentAufgabe().hasImage() == true) {
			bild.setImage(model.getCurrentAufgabe().getImage());
			aufgabenBild.setIcon(resizeImage(bild, 750));
			log.debug("lade bild!");
		} else {
			aufgabenBild.setIcon(null);
			log.debug("kein Bild!");
		}

		if (current.hasLiniendiagramm()) {
			LinienDiagramm liniendiagramm = new LinienDiagramm(current.geteMenge(), current.getEreignisse(),
					current.getStartEreignisse());
			linienpanel = liniendiagramm.panel;
		} else
			linienpanel = new JPanel();

		panel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == toLiniendiagrammButton) {
			log.debug("Wechsle zum Liniendiagramm");
			gui.switchToView(WindowSelect.LINIENDIAGRAMM);
		}
		if (e.getSource() == previousTaskButton) {
			i--;
		}
		if (e.getSource() == nextTaskButton) {
			i++;
		}
		refreshAufgabenview();

	}
}
