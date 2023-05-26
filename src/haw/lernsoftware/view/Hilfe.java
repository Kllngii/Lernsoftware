package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Taskbar.Feature;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;

/**
 * Die Hilfeseite des Programms.
 * @author Sebastian Holz
 */
public class Hilfe extends HAWView implements ActionListener{

	
	private JButton ButtonAllgemein = new JButton("Allgemein");
	private JButton ButtonLadenSpeichern = new JButton("Laden/Speichern");
	private JButton ButtonAufgaben = new JButton("Aufgaben");	
	private JButton ButtonLiniengraph = new JButton("Liniengraph");
	private JLabel Ueberschrift = new JLabel("Hilfe");
	private JScrollBar scroll = new JScrollBar();
	private JTextArea text = new JTextArea(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text"),20,50);	
	
	// Eventuell wird das noch mal benötigt
	//private String bla = new String(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text5"));
	
	public  Hilfe() {
		
		
		
		//Fenster erstellen
		JFrame fenster = new JFrame("Hilfe");
		
		JPanel view = new JPanel();
		panel = new JScrollPane(view);
		view.add(buildContentText());			
		((JScrollPane)panel).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		((JScrollPane)panel).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		
		// Text für die Erstausgabe formatieren
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		// Buttons sollen etwas machen
		ButtonAllgemein.addActionListener(this);
		ButtonLadenSpeichern.addActionListener(this);
		ButtonAufgaben.addActionListener(this);
		ButtonLiniengraph.addActionListener(this);
		
		// Textfeld einstellen
		text.setEditable(false);
		Color color = panel.getBackground();
		text.setBackground(color);


		// Ein Hilfe-Icon dem Fenster hinzufügen
		Image icon = ResourceProvider.loadImage(Konst.HILFE_ICON);
		final Taskbar taskbar = Taskbar.getTaskbar();
		if(taskbar.isSupported(Feature.ICON_IMAGE))
			taskbar.setIconImage(icon);
		if(icon != null)
			fenster.setIconImage(icon);

		// Fenster Zentrieren
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    
		// Fenster einstellen
	    int Width = (int) (dimension.getWidth() / Toolkit.getDefaultToolkit().getScreenResolution() * 50);
	    int Height = (int) (dimension.getHeight() / Toolkit.getDefaultToolkit().getScreenResolution() * 50);
		fenster.setSize(Width, Height);
		fenster.getContentPane().add(panel);
	    
	    int x = (int) ((dimension.getWidth() - fenster.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - fenster.getHeight()) / 2);
	    fenster.setLocation(x, y);
	    
	    // Fenster zeige dich
		fenster.setVisible(true);
		
		
	}

	// Layout designer
	public JComponent buildContentText() {				
				JComponent inhalt = FormBuilder.create() //
				
				.columns("pref, 200dlu")
				.rows("10dlu,top:200dlu") 				
				//.debug(true)
				.padding(Paddings.DIALOG)
				.add(Ueberschrift) .xy(2, 1)
				.addStack(ButtonAllgemein,ButtonLadenSpeichern,ButtonAufgaben,ButtonLiniengraph) .xy(1, 2, "fill,top")
				//.add(text) .xy(2, 2)
				.addScrolled(text) .xy(2, 2)
				.border(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.DARK_GRAY))
				.build();
				
				return FormBuilder.create()
						.columns("p")
						  .rows("p")
						  .addScrolled(inhalt) .xy(1, 1)
						  .debug(true)
						  .build();
	}

	// Starte das Fenster
	public static void main(String[] args) {
		new Hilfe();
	}


	// Aktionen die die Buttons machen sollen
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ButtonAllgemein) {
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text2"));
			text.setWrapStyleWord(true);
			text.setLineWrap(true);
			Ueberschrift.setText("Allgemein");
		}
		if(e.getSource() == ButtonLadenSpeichern) {
			Ueberschrift.setText("Laden/Speichern");
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text3"));
			text.setWrapStyleWord(true);
			text.setLineWrap(true);
			
		}
		if(e.getSource() == ButtonAufgaben) {
			Ueberschrift.setText("Aufgaben");
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text4"));
			text.setWrapStyleWord(true);
			text.setLineWrap(true);
		}
		if(e.getSource() == ButtonLiniengraph) {
			Ueberschrift.setText("Liniengraph");
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text5"));
			text.setWrapStyleWord(true);
			text.setLineWrap(true);
		}
		panel.repaint();
	}
}
