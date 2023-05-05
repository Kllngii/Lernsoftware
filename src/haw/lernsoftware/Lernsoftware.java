package haw.lernsoftware;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceRoyale;

import haw.lernsoftware.model.Ereignismenge;
import haw.lernsoftware.model.Menge;
import haw.lernsoftware.resources.ResourceProvider;
import haw.lernsoftware.view.GUI;
import haw.lernsoftware.view.HAWView;
//test
public class Lernsoftware extends HAWView {

	private final Logger log = Logger.getLogger(getClass());

	private JFrame frame;

	public GUI plotter;

	public Lernsoftware() {
		initialize();
	}

	private void initialize() {
		PlasticLookAndFeel.setPlasticTheme(new ExperienceRoyale());
		frame = new JFrame();
		frame.setTitle(Konst.SO_HEISST_DAS_DING);

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = new Dimension(720, 480);
		frame.setSize(size.width, size.height);
		frame.setLocation((screensize.width - size.width) / 2, (screensize.height - size.height) / 2);
		log.info("Baue jetzt den Frame");
		long timestart = System.currentTimeMillis();
		log.info(System.getProperty("os.name"));
		
		//Plattformspezifischer Code
		if(System.getProperty("os.name").startsWith("Mac OS X")) { //MacOS
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		    System.setProperty("apple.awt.graphics.UseQuartz", "true");
		    //TODO Icon hinzufügen und setzen?
		}
		
		plotter = new GUI(frame);

		frame.setVisible(true);
		log.info("Das Programm brauchte " + (System.currentTimeMillis()-timestart) + "ms zum Starten!");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			new Lernsoftware();
		});

		//FIXME Testcode später entfernen
		Logger log = Logger.getLogger(Lernsoftware.class);
		Ereignismenge eMenge = Ereignismenge.fromJSON(ResourceProvider.getFileContentAsString("würfel.em").replace(" ", ""));

		log.info("Die Ereignismenge ist " + (eMenge.vaildate() ? "ok" : "fehlerhaft"));

		Menge mengeA = new Menge("Beispielmenge", eMenge, eMenge.getEreignisse().subList(0, 3));
		Menge mengeB = new Menge("Beispielmenge", eMenge, eMenge.getEreignisse().subList(1, 4));
		log.info(mengeA.vereinigt(mengeB).negiert().toJSON().toString(0));
	}
}
