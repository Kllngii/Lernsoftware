package haw.lernsoftware.view.liniendiagramm;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

import haw.lernsoftware.model.Ereignismenge;
import haw.lernsoftware.model.Menge;
import haw.lernsoftware.resources.ResourceProvider;
import haw.lernsoftware.view.HAWView;

/**
 * Zeichnet ein Liniendiagramm
 * @author Lasse Kelling, Ole Schweim
 *
 * @see HAWView
 */
public class LinienDiagramm extends HAWView {
	
	Logger log = Logger.getLogger(getClass());
	List<Menge> mengen;
	
	public LinienDiagramm() {
		panel = new DrawingPanel(this);
		Ereignismenge eMenge = Ereignismenge.fromJSON(ResourceProvider.getFileContentAsString("würfel.em").replace(" ", ""));

		log.info("Die Ereignismenge ist " + (eMenge.vaildate() ? "ok" : "fehlerhaft"));

		Menge mengeA = new Menge(eMenge, eMenge.getEreignisse().subList(0, 3));
		Menge mengeB = new Menge(eMenge, eMenge.getEreignisse().subList(1, 4));
		
		constructDiagramm(List.of(mengeA, mengeB));
		panel.getSize();
	}
	
	private void constructDiagramm(List<Menge> mengen) {
		panel.add(new JLabel("Hier könnte Ihre Werbung stehen"));
		this.mengen = mengen;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			LinienDiagramm d = new LinienDiagramm();
			JFrame f = new JFrame();
			f.setContentPane(d.panel);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setResizable(true);
			f.setSize(720, 480);
			f.setVisible(true);
		});

	}

	public void paintPanel(Graphics g) {
		g.drawLine(0, 0, 100, 100);
	}

}
