package haw.lernsoftware.view.liniendiagramm;

import java.awt.BasicStroke;
import java.awt.Dimension;
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

import static haw.lernsoftware.Konst.*;

public class LinienDiagramm extends HAWView {
	
	private int sizeLeft = 150;
	private int sizeRight = 300;
	private int sizeLine;
	private int numberMenge;
	private int numberElemente;
	
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
	private void setStroked(Graphics2D g2d) {
		setStroked(g2d, STD_LINEWIDTH);
	}
	private void setStroked(Graphics2D g2d, int width) {
		g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,10.0f,new float[] {16.0f,20.0f},0.0f));
	}

	/**
	 * Fenster von oben links (BORDER_X|BORDER_Y) -> unten rechts (BORDER_X + d.width | BORDER_Y + d.height)
	 */
	public void paintPanel(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Dimension d = panel.getSize();
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine(BORDER_X+sizeLeft, BORDER_Y, panel.getSize().width - sizeRight - BORDER_X, BORDER_Y);
		
		g2d.drawLine(BORDER_X+sizeLeft, BORDER_Y, BORDER_Y+sizeLeft, 100*(numberMenge+1));
		g2d.drawLine(BORDER_X+sizeLeft, BORDER_Y, BORDER_Y+sizeLeft, 100*(numberMenge+1));
		
		g2d.drawString(eMenge.getEreignisse().get(1).getName(), 0, BORDER_Y);
		
		setStroked(g2d);
		
	}

}
