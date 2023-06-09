package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.model.Aufgabe;
import haw.lernsoftware.model.Model;
import haw.lernsoftware.model.WindowSelect;
import haw.lernsoftware.view.TextPrompt.Show;
import haw.lernsoftware.view.liniendiagramm.LinienDiagramm;

/**
 * Der Aufgabentext ist das {@link HAWView}, welches die Darstellung der aktuellen Aufgabe übernimmt.
 * 
 * @author Moritz Koch
 */
public class Aufgabentext extends HAWView implements ActionListener {
	private Model model;
	private List<Aufgabe> aufgaben;
	private JProgressBar progress;
	private int i;

	// gibt i zurück
	public int getI() {
		return i;
	}

	// setzt i = dem gegebenen Wert (für leicht/mittel/schwer Aufgaben)
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
	private JTextArea loesungText = new JTextArea();
	private JButton zeigeloesung = new JButton("Lösung");
	private JComponent linienpanel;

	private ImageIcon bild;
	private JLabel aufgabenBild;
	private ImageIcon bild2;
	private JLabel aufgabenBild2;
	private GUI gui;

	// fügt dem Panel von Aufgabentext einen JComponent hinzu
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

	// erstellt einen JComponent (das Layout)
	public JComponent buildContentText() {
		i = aufgaben.indexOf(model.getCurrentAufgabe());

		// Startbedingungen für Aufgabe 1 (Programmstart)
		previousTaskButton.setEnabled(false);
		titleTaskLabel.setText("Aufgabe: " + (i + 1));

		// Fortschrittsanzeige
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
		// loesungText.setText("Hier die Lösung eingeben!");
		TextPrompt tp = new TextPrompt("Hier die Lösung eingeben!", loesungText, Show.FOCUS_LOST);
		loesungText.setLineWrap(true);
		loesungText.setWrapStyleWord(true);
		loesungText.setPreferredSize(new Dimension(100, 100));
		loesungText.setEditable(true);
		loesungText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));

		// loesungText.setBackground(color);

		// Wenn die Aufgabe ein Bild hat, wird dieses hinzugefügt
		if (model.getCurrentAufgabe().hasImage() == true) {
			bild = new ImageIcon(model.getCurrentAufgabe().getImage());
			bild2 = new ImageIcon(model.getCurrentAufgabe().getImage2());
			aufgabenBild = new JLabel(bild);
			aufgabenBild2 = new JLabel(bild2);
		} else {
			bild = new ImageIcon();
			aufgabenBild = new JLabel("");
			bild2 = new ImageIcon();
			aufgabenBild2 = new JLabel("");
		}

		zeigeloesung.setBackground(Color.LIGHT_GRAY);
		
		// Button Listener
		previousTaskButton.addActionListener(this);
		nextTaskButton.addActionListener(this);
		toLiniendiagrammButton.addActionListener(this);
		zeigeloesung.addActionListener(this);
		
		InputMap im = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, InputEvent.SHIFT_DOWN_MASK, false), "switch");
		panel.getActionMap().put("switch", new AbstractAction() {
			private static final long serialVersionUID = 7947985494429625517L;
			@Override
			public void actionPerformed(ActionEvent e) {
				log.debug("Wechsle das Fenster per Keybind!");
				gui.switchToView(WindowSelect.LINIENDIAGRAMM);
			}
		});
		
		// gibt einen JComponent zurück mit den Komponenten in fertigem Layout
		return FormBuilder.create() // .debug = Rote Linien zeichnen
				.columns("100dlu, 5dlu, center:200dlu, 5dlu, 100dlu") //
				.rows("p, 20dlu, p, $lg, top:90dlu, p, top:10dlu, p, top:10dlu, p, top:50dlu") //
				.padding(Paddings.DIALOG) //
				.add(titleTaskLabel).xy(3, 1) //
				.add(toLiniendiagrammButton).xy(5, 1) //
				.add(previousTaskButton).xy(1, 2) //
				.add(progress).xy(3, 2) //
				.add(nextTaskButton).xy(5, 2) //
				.addSeparator("Aufgabentext").xyw(1, 3, 5) //
				.add(aufgabenText).xyw(1, 5, 5) //
				.add(aufgabenBild).xyw(1, 6, 5) //
				.add(zeigeloesung).xyw(1, 8, 5) //
				.add(aufgabenBild2).xyw(1, 8, 5)
				.add(linienpanel).xyw(1, 10, 5) //
//				.add(loesungText).xyw(1, 11, 5) //
				.build(); //
	}

	// Bildgröße anpassen
	private ImageIcon resizeImage(ImageIcon img, int width) {
		float height = (float) width / (float) img.getIconWidth() * (float) img.getIconHeight();
		Image image = img.getImage();
		zeigeloesung.setPreferredSize(new Dimension(width, Math.round(height)));
		Image resizedImage = image.getScaledInstance(width, Math.round(height), Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	// auf die Inputs reagieren und das Bild anpassen
	private void refreshAufgabenview() {

		// Die vor und zurück Buttons werden am Anfang und Ende ausgegraut
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

		// Aufgaben Nummer Beschriftung
		aufgabenText.setText(current.getText());
		titleTaskLabel.setText("Aufgabe: " + (i + 1));
		progress.setValue(i);

		// Bild einfügen / ändern beim Aufgaben wechsel
		if (model.getCurrentAufgabe().hasImage() == true) {
			zeigeloesung.setVisible(true);
			bild.setImage(model.getCurrentAufgabe().getImage());
			aufgabenBild.setIcon(resizeImage(bild, 750));
			// log.debug("lade bild!");
			bild2.setImage(model.getCurrentAufgabe().getImage2());
			aufgabenBild2.setIcon(resizeImage(bild2, 750));
			// log.debug("lade bild!");
		} else {
			zeigeloesung.setVisible(false);
			aufgabenBild.setIcon(null);
			// log.debug("kein Bild!");
			aufgabenBild2.setIcon(null);
			// log.debug("kein Bild2!");
		}
		
		if (model.getCurrentAufgabe().hasLiniendiagramm())
			toLiniendiagrammButton.setEnabled(true);
		else
			toLiniendiagrammButton.setEnabled(false);

		// Liniendiagramm aktualisieren
		if (current.hasLiniendiagramm()) {
			LinienDiagramm liniendiagramm = new LinienDiagramm(current.geteMenge(), current.getEreignisse(),
					current.getStartEreignisse(), gui);
			linienpanel = liniendiagramm.panel;
		} else
			linienpanel = new JPanel();

		panel.repaint();
	}

	// Mit den Buttons zur vorherigen oder nächsten Aufgaben wechseln
	@Override
	public void actionPerformed(ActionEvent e) {
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
		if (e.getSource() == zeigeloesung) {
			zeigeloesung.setVisible(false);
			return;
		}
		refreshAufgabenview();

	}
}
