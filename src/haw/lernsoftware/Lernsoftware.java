package haw.lernsoftware;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import haw.lernsoftware.resources.ResourceProvider;
import haw.lernsoftware.view.GUI;
import haw.lernsoftware.view.HAWView;

//TODO Liniendiagramm: gewählte Zeile einfärben
//FIXME Speichern funktioniert nicht

/**
 * Die Hauptklasse und der Einstiegpunkt des Programms
 * @author Lasse Kelling
 *
 */
public class Lernsoftware extends HAWView {

	private final Logger log = Logger.getLogger(getClass());

	private JFrame frame;

	public GUI plotter;

	public Lernsoftware() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle(Konst.SO_HEISST_DAS_DING);

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = new Dimension(720, 480);
		frame.setSize(size.width, size.height);
		frame.setLocation((screensize.width - size.width) / 2, (screensize.height - size.height) / 2);
		log.info("Baue jetzt den Frame");
		long timestart = System.currentTimeMillis();
		log.info("Ich werde heute auf " + System.getProperty("os.name") + " ausgeführt. " + (System.getProperty("os.name").startsWith("Mac") ? "Welch eine Freude!" : "Ist ganz ok..."));

		Image icon = ResourceProvider.loadImage(Konst.ICON_PATH);
		if(icon != null)
			frame.setIconImage(icon);
		else
			log.warn("Das Icon konnte nicht geladen werden!");

		//Plattformspezifischer Code
		if(System.getProperty("os.name").startsWith("Mac OS X")) {
			//XXX MacOS-spezifisches Setup hier
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("apple.awt.graphics.UseQuartz", "true");
			final Taskbar taskbar = Taskbar.getTaskbar();
			taskbar.setIconImage(icon);
			System.setProperty("com.apple.mrj.application.apple.menu.about.name",Konst.SO_HEISST_DAS_DING);
			System.setProperty("apple.awt.textantialiasing", "true");
			System.setProperty("apple.awt.graphics.EnableQ2DX","true");
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				log.warn(e);
			}
		} else if(System.getProperty("os.name").startsWith("Windows")) {
			//XXX Windows-spezifisches Setup hier
		} else {
			//XXX Linux-spezifisches Setup hier
		}
		plotter = new GUI(frame);

		frame.setVisible(true);
		log.info("Das Programm brauchte " + (System.currentTimeMillis()-timestart) + "ms zum Starten!");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			new Lernsoftware(); //Lernsoftware aus dem richtigen Thread starten
		});
	}
}
