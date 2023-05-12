package haw.lernsoftware.view.liniendiagramm;

import static haw.lernsoftware.Konst.BORDER_X;
import static haw.lernsoftware.Konst.BORDER_Y;
import static haw.lernsoftware.Konst.STD_LINEWIDTH;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import haw.lernsoftware.model.Ereignismenge;
import haw.lernsoftware.model.Menge;
import haw.lernsoftware.resources.ResourceProvider;
import haw.lernsoftware.view.HAWView;

public class LinienDiagramm extends HAWView implements MouseListener {
	
	private int linewidth = STD_LINEWIDTH;
	private int numberEreignisse;
	private int numberElementare;
	
	private Logger log = Logger.getLogger(getClass());
	private List<Menge> mengen;
	private Ereignismenge eMenge;
	
	private List<Integer> spaltenCoord = new ArrayList<Integer>();
	private List<Integer> zeilenCoord = new ArrayList<Integer>();
	
	public LinienDiagramm() {
		eMenge = Ereignismenge.elementareFromJSON(ResourceProvider.getFileContentAsString("elementare_würfel.em").replace(" ", ""));
		mengen = Ereignismenge.ereignisseFromJSON(ResourceProvider.getFileContentAsString("ereignisse_würfel.em").replace(" ", ""));

		log.info("Die Ereignismenge ist " + (eMenge.vaildate() ? "ok" : "fehlerhaft"));

		Menge mengeA = new Menge("kleiner gleich 3", eMenge, eMenge.getEreignisse().subList(0, 3));
		Menge mengeB = new Menge("zwischen 2 und 4", eMenge, eMenge.getEreignisse().subList(1, 4));
		Menge mengeC = new Menge("zwischen 4 und 5", eMenge, eMenge.getEreignisse().subList(3, 5));
		Menge mengeD = new Menge("alles", eMenge, eMenge.getEreignisse().subList(0, 6));
		Menge mengeE = new Menge("nix", eMenge, eMenge.getEreignisse().subList(0, 0));
		//Die Mengen brauchen noch einen Namen und eine Anordungsnummer
		
		mengen = List.of(mengeA, mengeB, mengeC, mengeD, mengeE);
		constructDiagramm(mengen, eMenge);
	}
	
	private void constructDiagramm(List<Menge> mengen, Ereignismenge e) {
		panel = new DrawingPanel(this);
		numberEreignisse = mengen.size();
		numberElementare = e.getEreignisse().size();
		
		panel.addMouseListener(this);
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
	
	// Elementarereignis in Spalte "order" in der Menge enthalten?
	private boolean linesegment (Menge menge, int order) {
		for (int k = 0; k < menge.getEreignisse().size(); k++) {
			if (menge.getEreignisse().get(k).getOrder() == order) {
				return true;
			}
		}
		return false;
	}
	
	private int offset(Graphics2D g2d) {
		int maxlength = 0;
		for (int j = 0; j < numberEreignisse; j++) {
			if (Integer.valueOf(g2d.getFontMetrics().stringWidth(mengen.get(j).getName())) > maxlength) {
				maxlength = Integer.valueOf(g2d.getFontMetrics().stringWidth(mengen.get(j).getName()));
			}
		}
		return maxlength + 30;
	}

	/**
	 * Fensterecken:        (BORDER_X, BORDER_Y) --------------------------- (BORDER_X + diagWidth,  BORDER_Y)
	 * 								 |													 |
	 *								 |													 |
	 * 								 |													 |
	 * 								 |													 |
	 * 			     (BORDER_X, BORDER_Y + diagHeight) ----------------- (BORDER_X + diagWidth, BORDER_Y + diagHeight)
	 */
	
	public void paintPanel(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Dimension d = panel.getSize();
		setLinewidth(g2d, STD_LINEWIDTH);
		int diagWidth = d.width - 20;
		int diagHeight = d.height - 20;
		int linewidth = 40;
		int offsetlr = offset(g2d);
		int currentLeftBorder = BORDER_X + offsetlr;
		
		// Rahmen
		g2d.drawLine(BORDER_X + offsetlr, BORDER_Y + 10, BORDER_X + diagWidth - offsetlr, BORDER_Y + 10);
		g2d.drawLine(BORDER_X + offsetlr, BORDER_Y + 10, BORDER_X + offsetlr, BORDER_Y + diagHeight);
		g2d.drawLine(BORDER_X + diagWidth - offsetlr, BORDER_Y + 10, BORDER_X + diagWidth - offsetlr, BORDER_Y + diagHeight);
		
		spaltenCoord.clear();
		zeilenCoord.clear();
		
		// Spalten
		setLinewidth(g2d, 1);
		currentLeftBorder = BORDER_X + offsetlr;
		for (int i = 0; i < numberElementare; i++) {
			g2d.drawString(eMenge.getEreignisse().get(i).getName(), currentLeftBorder + (int) (eMenge.getEreignisse().get(i).getProbability() * (double) (diagWidth-2*offsetlr)) / 2, BORDER_Y);
			g2d.drawLine(currentLeftBorder, BORDER_Y + 10, currentLeftBorder, BORDER_Y + diagHeight);
			spaltenCoord.add(currentLeftBorder);
			currentLeftBorder += (int) (eMenge.getEreignisse().get(i).getProbability() * (double) (diagWidth-2*offsetlr));
		}
		spaltenCoord.add(currentLeftBorder);
		g2d.drawLine(BORDER_X + diagWidth - offsetlr, BORDER_Y + 10, BORDER_X + diagWidth - offsetlr, BORDER_Y + diagHeight);
		
		// Zeilen
		setLinewidth(g2d, STD_LINEWIDTH);
		for (int j = 0; j < numberEreignisse; j++) {
			currentLeftBorder = BORDER_X + offsetlr;
			g2d.drawString(mengen.get(j).getName(), BORDER_X, BORDER_Y + 10 + j*linewidth + linewidth*4/7);
			g2d.setColor(Color.BLUE);
			for (int i = 0; i < numberElementare; i++) {
				if (linesegment(mengen.get(j), i+1)) {
					g2d.drawLine(currentLeftBorder, BORDER_Y + 10 + j*linewidth + linewidth/2, currentLeftBorder + (int) (eMenge.getEreignisse().get(i).getProbability() * (double) (diagWidth-2*offsetlr)), BORDER_Y + 10 + j*linewidth + linewidth/2);
				}
				currentLeftBorder += (int) (eMenge.getEreignisse().get(i).getProbability() * (double) (diagWidth-2*offsetlr));
			}
			g2d.setColor(Color.black);
			zeilenCoord.add(BORDER_Y + 10 + j*linewidth + linewidth/2 - 2*BORDER_X);
			g2d.drawString(mengen.get(j).getProbability(), BORDER_X + + diagWidth - offsetlr + 10, BORDER_Y + 10 + j*linewidth + linewidth*4/7);
		}
		zeilenCoord.add(BORDER_Y + 10 + numberEreignisse*linewidth + linewidth/2 - 2*BORDER_X);
	}
	
	public Koordinate getPosition(MouseEvent e) {
		int spalte = -1;
		for(Integer x : spaltenCoord) {
			if(x < e.getPoint().getX()) {
				spalte = spaltenCoord.indexOf(x);
			}
		}
		int zeile = -1;
		for(Integer y : zeilenCoord) {
			if(y < e.getPoint().getY()) {
				zeile = zeilenCoord.indexOf(y);
			}
		}
		return new Koordinate(zeile, spalte);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		log.info(getPosition(e));
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
