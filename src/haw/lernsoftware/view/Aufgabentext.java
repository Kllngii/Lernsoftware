package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.model.Aufgabe;
import haw.lernsoftware.model.Model;

public class Aufgabentext extends HAWView implements ActionListener {
	private Model model;
	private List<Aufgabe> aufgaben;
	private JProgressBar progress;

	private final Logger log = Logger.getLogger(getClass());

	private JLabel titleTaskLabel = new JLabel("Aufgabe X:");
	private JButton nextTaskButton = new JButton("NEXT");
	private JButton previousTaskButton = new JButton("PREVIOUS");
	private JTextArea aufgabenText = new JTextArea("if you can read this, report a bug");
	private String aufgText = new String();

	// fügt dem panel von Aufgabentext einen JComponent zu
	public Aufgabentext(Model model) {
		this.model = model;
		aufgaben = model.getAufgaben();
		panel.add(buildContentText());

	}

	// erstellt einen JComponent
	public JComponent buildContentText() {

		previousTaskButton.setEnabled(false);
		titleTaskLabel.setText("Aufgabe: 1");

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

		// Button Listener
		previousTaskButton.addActionListener(this);
		nextTaskButton.addActionListener(this);

		// gibt einen JComponent zurück, der
		return FormBuilder.create().debug(true) // Rote Linien zeichnen
				.columns("100dlu, center:200dlu, 100dlu") //
				.rows("p, 20dlu, p, $lg, top:300dlu") //
				.padding(Paddings.DIALOG) //
				.add(titleTaskLabel).xy(2, 1) //
				.add(previousTaskButton).xy(1, 2) //
				.add(progress).xy(2, 2) //
				.add(nextTaskButton).xy(3, 2) //
				.addSeparator("Aufgabentext").xyw(1, 3, 3) //
				.add(aufgabenText).xyw(1, 5, 3) //
				.build(); //
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int i = aufgaben.indexOf(model.getCurrentAufgabe());

		if (e.getSource() == previousTaskButton) {
			i--;
			model.setCurrentAufgabe(aufgaben.get(i));
			aufgabenText.setText(model.getCurrentAufgabe().getText());
			titleTaskLabel.setText("Aufgabe: " + (i + 1));
			progress.setValue(i);
		}
		if (e.getSource() == nextTaskButton) {
			i++;
			model.setCurrentAufgabe(aufgaben.get(i));
			aufgabenText.setText(model.getCurrentAufgabe().getText());
			titleTaskLabel.setText("Aufgabe: " + (i + 1));
			progress.setValue(i);
		}
		panel.repaint();

		if (i == 0) {
			previousTaskButton.setEnabled(false);
		} else if (i == aufgaben.size() - 1) {
			titleTaskLabel.setText("Glückwunsch!");
			nextTaskButton.setEnabled(false);
		} else {
			previousTaskButton.setEnabled(true);
			nextTaskButton.setEnabled(true);
		}
import javax.swing.JLabel;

import org.apache.log4j.Logger;

public class Aufgabentext extends HAWView {

	Logger log = Logger.getLogger(getClass());

	public Aufgabentext() {
		constructAufgabentext();
	}

	private void constructAufgabentext() {
		panel.add(new JLabel("Ich bin der Aufgabentext :)"));
	}
}
