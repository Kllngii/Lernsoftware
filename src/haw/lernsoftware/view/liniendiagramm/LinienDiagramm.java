package haw.lernsoftware.view.liniendiagramm;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
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
	
	private int linewidth = STD_LINEWIDTH;
	private int numberEreignisse;
	private int numberElementare;
	
	private Logger log = Logger.getLogger(getClass());
	private List<Menge> mengen;
	private Ereignismenge eMenge;
	
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
		numberEreignisse = mengen.size();
		numberElementare = e.getEreignisse().size();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			LinienDiagramm d = new LinienDiagramm();
			JFrame f = new JFrame();
			f.setContentPane(d.panel);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			f.setResizable(true);
			f.setSize(720,480);
			f.setVisible(true);
		});
	}

	
	
	private void setStroked(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(linewidth, BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,10.0f,new float[] {16.0f,20.0f},0.0f));
	}
	
	private void setLinewidth(Graphics2D g2d, int newWidth) {
		g2d.setStroke(new BasicStroke(newWidth));
		linewidth = newWidth;
	}

	/**
	 * Fensterecken:        (BORDER_X, BORDER_Y) --------------------------- (BORDER_X + d.width,  BORDER_Y)
	 * 								 |													 |
	 *								 |													 |
	 * 								 |													 |
	 * 								 |													 |
	 * 			     (BORDER_X, BORDER_Y + d.height) ----------------- (BORDER_X + d.width, BORDER_Y + d.height)
	 */
	
	public void paintPanel(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Dimension d = panel.getSize();
		setLinewidth(g2d, STD_LINEWIDTH);
		int diagWidth = d.width - 20;
		int diagHeight = d.height - 20;
		
		g2d.drawLine(BORDER_X, BORDER_Y + 10, BORDER_X + diagWidth, BORDER_Y + 10);
		g2d.drawLine(BORDER_X, BORDER_Y + 10, BORDER_X, BORDER_Y + diagHeight);
		
		for (int i = 0; i < numberElementare; i++) {
			g2d.drawString(eMenge.getEreignisse().get(i).getName(), BORDER_X + i*diagWidth/numberElementare + diagWidth/(2*numberElementare), BORDER_Y);
			g2d.drawLine(BORDER_X + i*(diagWidth/numberElementare), BORDER_Y + 10, BORDER_X + i*(diagWidth/numberElementare), BORDER_Y + diagHeight);
		}
		
		g2d.drawLine(BORDER_X + diagWidth, BORDER_Y + 10, BORDER_X + diagWidth, BORDER_Y + diagHeight);
		
		setLinewidth(g2d, 1);
		
		for (int j = 0; j <= numberEreignisse; j++) {
//			g2d.drawString(eMenge.getEreignisse().get(i).getName(), BORDER_X + i*diagWidth/numberElementare + diagWidth/(2*numberElementare), BORDER_Y);
			g2d.drawLine(BORDER_X, BORDER_Y + 10 + j*(diagHeight/numberEreignisse-100), BORDER_X + diagWidth, BORDER_Y + 10 + j*(diagHeight/numberEreignisse-100));
		}
	}
}
