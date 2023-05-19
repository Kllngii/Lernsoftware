package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Taskbar.Feature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
/*
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
*/
import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;

/**
 * Die Hilfeseite des Programms.
 * @author Sebastian Holz
 */
public class Hilfe extends HAWView implements ActionListener{

	
	private JButton Button1 = new JButton("Allgemein");
	private JButton Button2 = new JButton("Test2");
	private JButton Button3 = new JButton("Test3");	
	private JLabel Ueberschrift = new JLabel("");
	private JTextArea text = new JTextArea(22,50);	
	
	// Eventuell wird das noch mal benötigt
	//private String bla = new String(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text5"));
	
	public  Hilfe() {
		
		//Fenster erstellen
		JFrame fenster = new JFrame("Hilfe");
		
		// Buttons sollen etwas machen
		Button1.addActionListener(this);
		Button2.addActionListener(this);
		Button3.addActionListener(this);
		
		// Textfeld einstellen
		text.setEditable(false);
		Color color = panel.getBackground();
		text.setBackground(color);

		// Fenster einstellen
		fenster.setSize(500, 400);
		fenster.getContentPane().add(panel);
		fenster.setResizable(false);
		
		// ContentText zum Panel hinzufügen
		panel.add(buildContentText());

		// Ein Hilfe-Icon dem Fenster hinzufügen
		Image icon = ResourceProvider.loadImage(Konst.HILFE_ICON);
		final Taskbar taskbar = Taskbar.getTaskbar();
		if(taskbar.isSupported(Feature.ICON_IMAGE))
			taskbar.setIconImage(icon);
		if(icon != null)
			fenster.setIconImage(icon);

		fenster.setVisible(true);
		
		
	}

	// Layout designer
	public JComponent buildContentText() {				
		return FormBuilder.create()
				
				.columns("pref, 200dlu")
				.rows("10dlu,top:200dlu") 				
				.debug(true)
				.padding(Paddings.DIALOG)
				.add(Ueberschrift) .xy(2, 1)
				.addStack(Button1,Button2,Button3) .xy(1, 2, "fill,center")
				.add(text) .xy(2, 2)
				.border(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.DARK_GRAY))
				.build();
	}

	// Starte das Fenster
	public static void main(String[] args) {
		new Hilfe();
	}


	// Aktionen die die Buttons machen sollen
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == Button1) {
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text"));
			Ueberschrift.setText("Allgemein");
		}
		if(e.getSource() == Button2) {
			Ueberschrift.setText("Überschrift2");
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text5"));
			text.setWrapStyleWord(true);
			text.setLineWrap(true);
			
		}
		if(e.getSource() == Button3) {
			Ueberschrift.setText("Überschrift3");
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text3"));
			text.setWrapStyleWord(true);
			text.setLineWrap(true);
		}
		panel.repaint();
	}
}
