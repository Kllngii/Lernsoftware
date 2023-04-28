package haw.lernsoftware.view.liniendiagramm;

import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

import haw.lernsoftware.model.Ereignismenge;
import haw.lernsoftware.model.Menge;
import haw.lernsoftware.resources.ResourceProvider;
import haw.lernsoftware.view.HAWView;

public class LinienDiagramm extends HAWView {
	
	private int sizeLeft = 150;
	private int sizeRight = 300;
	private int sizeLine;
	private int numberMenge;
	private int numberElemente;
	private int plusX = haw.lernsoftware.Konst.borderDistanceX;;
	private int plusY = haw.lernsoftware.Konst.borderDistanceY;;
	
	Logger log = Logger.getLogger(getClass());
	List<Menge> mengen;
	Ereignismenge eMenge;
	
	public LinienDiagramm() {
		eMenge = Ereignismenge.fromJSON(ResourceProvider.getFileContentAsString("würfel.em").replace(" ", ""));

		log.info("Die Ereignismenge ist " + (eMenge.vaildate() ? "ok" : "fehlerhaft"));

		Menge mengeA = new Menge(eMenge, eMenge.getEreignisse().subList(0, 3));
		Menge mengeB = new Menge(eMenge, eMenge.getEreignisse().subList(1, 4));
		Menge mengeC = new Menge(eMenge, eMenge.getEreignisse().subList(3, 5));
		//Die Mengen brauchen noch einen Namen und eine Anordungsnummer
		
		constructDiagramm(List.of(mengeA, mengeB, mengeC), eMenge);
	}
	
	private void constructDiagramm(List<Menge> mengen, Ereignismenge e) {
		panel = new DrawingPanel(this);
		numberMenge = mengen.size();
		numberElemente = e.getEreignisse().size();
		panel.repaint();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			LinienDiagramm d = new LinienDiagramm();
			JFrame f = new JFrame();
			f.setContentPane(d.panel);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			f.setResizable(true);

			f.setVisible(true);
		});

	}

	public void paintPanel(Graphics g) {
		
		
		((Graphics2D) g).setStroke(new BasicStroke(1));
		g.drawLine(plusX+sizeLeft, plusY, panel.getSize().width - sizeRight - plusX, plusY);
		
		g.drawLine(plusX+sizeLeft, plusY, plusY+sizeLeft, 100*(numberMenge+1));
		g.drawLine(plusX+sizeLeft, plusY, plusY+sizeLeft, 100*(numberMenge+1));
		
		g.drawString(eMenge.getEreignisse().get(1).getName(), 0, 10);
		
		((Graphics2D) g).setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,10.0f,new float[] {16.0f,20.0f},0.0f));
		
	}

}
