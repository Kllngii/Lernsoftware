package haw.lernsoftware.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Taskbar.Feature;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.RootPaneContainer;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;

/**
 * Die Hilfeseite des Programms.
 * @author Sebastian Holz
 */
public class Hilfe extends HAWView implements ActionListener{

	private Logger log = Logger.getLogger(getClass());
	
	private JFrame fenster = new JFrame("Hilfe");
	private JPanel view = new JPanel();
	private JButton ButtonAllgemein = new JButton("Allgemein");
	private JButton ButtonLadenSpeichern = new JButton("Laden/Speichern");
	private JButton ButtonAufgaben = new JButton("Aufgaben");	
	private JButton ButtonLiniengraph = new JButton("Liniengraph");
	private JButton ButtonWeitereHilfe = new CircleButton("Weitere Hilfe");
	private JButton ButtonFUN = new JButton();
	private JLabel Ueberschrift = new JLabel("Hilfe");
	private JTextArea text = new JTextArea(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text"),20,50);
	private int i = 0;
	private int j = 0;
	private Color color = panel.getBackground();
	
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	// Eventuell wird das noch mal benötigt
	//private String bla = new String(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text5"));
	
	public  Hilfe() {
		
		
		panel = new JScrollPane(view);
		view.add(buildContentText());			
		((JScrollPane)panel).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		((JScrollPane)panel).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		Image Background = ResourceProvider.loadImage(Konst.Background_JPEG);
		fenster.setLayout(new BorderLayout());
		fenster.setContentPane(new JLabel(new ImageIcon(Background)));
		fenster.setLayout(new FlowLayout());
		
		
		// Text für die Erstausgabe formatieren
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		// Buttons sollen etwas machen
		ButtonAllgemein.addActionListener(this);
		ButtonLadenSpeichern.addActionListener(this);
		ButtonAufgaben.addActionListener(this);
		ButtonLiniengraph.addActionListener(this);
		ButtonWeitereHilfe.addActionListener(this);
		ButtonFUN.addActionListener(this);
		


		try {
			Image img = ResourceProvider.loadImage(Konst.FUN_BUTTON_PNG);
			ButtonFUN.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}

	   // ButtonFUN.setIcon(new ImageIcon(Class.class.getResource(Konst.FUN_BUTTON_PNG)));

		
		// Textfeld einstellen
		text.setEditable(false);
		text.setBackground(color);


		// Ein Hilfe-Icon dem Fenster hinzufügen
		Image icon = ResourceProvider.loadImage(Konst.HILFE_ICON);
		final Taskbar taskbar = Taskbar.getTaskbar();
		if(taskbar.isSupported(Feature.ICON_IMAGE))
			taskbar.setIconImage(icon);
		if(icon != null)
			fenster.setIconImage(icon);

		// Fenster Zentrieren
	    //Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    
		// Fenster einstellen
	    int Width = (int) (dimension.getWidth() / Toolkit.getDefaultToolkit().getScreenResolution() * 45);
	    int Height = (int) (dimension.getHeight() / Toolkit.getDefaultToolkit().getScreenResolution() * 58);
		fenster.setSize(Width, Height);
		fenster.getContentPane().add(panel);
	    
	    int x = (int) ((dimension.getWidth() - fenster.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - fenster.getHeight()) / 2);
	    fenster.setLocation(x, y);
	    // Fenster zeige dich
	    ButtonFUN.setVisible(false);

	    
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
				.addStack(ButtonAllgemein,ButtonLadenSpeichern,ButtonAufgaben,ButtonLiniengraph,ButtonWeitereHilfe,ButtonFUN) .xy(1, 2, "fill,top")
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
			j++;
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
		if(e.getSource() == ButtonWeitereHilfe) {
			URI uri = null;
			try {
				uri = new URI("https://gidf.help/");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				log.error(e1);
			}
			Desktop dt = Desktop.getDesktop();
			try {
				dt.browse(uri.resolve(uri));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				log.error(e1);
			}
		}
		if(e.getSource() == ButtonFUN) {
			i++;
	        if ((i & 1) == 0) {
	        	view.setBackground(color);
	        } else {
	        	view.setBackground(Color.pink);
	        }
			
			Random rand = new Random();
			int x = rand.nextInt((int) dimension.getWidth());
			int y = rand.nextInt((int) dimension.getHeight());
			
	        fenster.setLocation(x, y);
	                
	        panel.repaint();	        
	        if(i < 10) {
	        	try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					log.error(e1);
				}
	        	ButtonFUN.doClick();
	        } else
	        	i = 0;
		}
	    if(j > 4) {
	    ButtonFUN.setVisible(true);
	    }
		panel.repaint();
	}
}
