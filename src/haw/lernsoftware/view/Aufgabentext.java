package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

	private final Logger log = Logger.getLogger(getClass());

	private JLabel titleTaskLabel = new JLabel("Aufgabe X:");
	private JButton nextTaskButton = new JButton("Weiter");
	private JButton previousTaskButton = new JButton("Zurück");
	private JButton toLiniendiagrammButton = new JButton("Liniendiagramm");
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
		aufgabenText.setPreferredSize(new Dimension(100, 100));
		aufgabenText.setEditable(false);
		Color color = panel.getBackground();
		aufgabenText.setBackground(color);

		// Aufgabentext erstellen und formatieren
		loesungText.setText("Hier die Lösung eingeben!");
		loesungText.setLineWrap(true);
		loesungText.setPreferredSize(new Dimension(100, 100));
		loesungText.setEditable(true);
		// loesungText.setBackground(color);

		// TODO was ist wenn auch noch Liniendiagramme dazukommen
		if (model.getCurrentAufgabe().hasImage() == true) {
			bild = new ImageIcon(model.getCurrentAufgabe().getImage());
			aufgabenBild = new JLabel(bild);
		} else {
			aufgabenBild = new JLabel("");
		}

		// Button Listener
		previousTaskButton.addActionListener(this);
		nextTaskButton.addActionListener(this);
		toLiniendiagrammButton.addActionListener(this);

		// gibt einen JComponent zurück mit nur Text
		return FormBuilder.create() // Rote Linien zeichnen
				.columns("100dlu, 5dlu, center:200dlu, 5dlu, 100dlu") //
				.rows("p, 20dlu, p, $lg, top:100dlu, p, p, top:50dlu") //
				.padding(Paddings.DIALOG) //
				.add(titleTaskLabel).xy(3, 1) //
				.add(toLiniendiagrammButton).xy(5, 1) //
				.add(previousTaskButton).xy(1, 2) //
				.add(progress).xy(3, 2) //
				.add(nextTaskButton).xy(5, 2) //
				.addSeparator("Aufgabentext").xyw(1, 3, 5) //
				.add(aufgabenText).xyw(1, 5, 5) //
				.add(aufgabenBild).xyw(1, 6, 5) // Muss noch als Funktion Variabel gemacht werden um Bilder zu laden
				.add(linienpanel).xyw(1, 7, 5) //
				.add(loesungText).xyw(1, 8, 5).build(); //
	}

	private void refreshAufgabenview() {
		model.setCurrentAufgabe(aufgaben.get(i));

		Aufgabe current = model.getCurrentAufgabe();

		aufgabenText.setText(current.getText());
		titleTaskLabel.setText("Aufgabe: " + (i + 1));
		progress.setValue(i);

		if (current.hasImage()) { // aktualisiert falls bild vorhanden...
			bild.setImage(current.getImage());
			aufgabenBild.setIcon(bild);
		} else
			aufgabenBild.setIcon(null);

		if (current.hasLiniendiagramm()) {
			LinienDiagramm liniendiagramm = new LinienDiagramm(current.geteMenge(), current.getEreignisse());
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

		if (i == 0) {
			previousTaskButton.setEnabled(false);
		} else if (i == aufgaben.size() - 1) {
			titleTaskLabel.setText("Glückwunsch!");
			nextTaskButton.setEnabled(false);
		} else {
			previousTaskButton.setEnabled(true);
			nextTaskButton.setEnabled(true);
		}
	}
}
