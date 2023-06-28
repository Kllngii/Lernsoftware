package haw.lernsoftware.view.liniendiagramm;

import static haw.lernsoftware.Konst.BORDER_X;
import static haw.lernsoftware.Konst.BORDER_Y;
import static haw.lernsoftware.Konst.STD_FONTSIZE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import haw.lernsoftware.model.Elementarereignis;
import haw.lernsoftware.model.Ereignismenge;
import haw.lernsoftware.model.Menge;
import haw.lernsoftware.model.WindowSelect;
import haw.lernsoftware.view.GUI;
import haw.lernsoftware.view.HAWView;

/** 
 * Das Herzstück des Programms, wird verwendet um ein {@link Liniendiagramm} zu zeichen.
 * 
 * @author Simon Fiebich
 * @see #rebase(List, Ereignismenge)
 * @see Ereignismenge
 * @see Menge
 */

public class LinienDiagramm extends HAWView implements MouseListener {
	private int numberEreignisse;
	private int numberElementare;
	private boolean bedingtMode = false;
	private boolean enableNeueEreignisse = true;

	private Logger log = Logger.getLogger(getClass());
	private List<Menge> mengen;
	private List<Menge> zielMengen;
	private Ereignismenge eMenge;

	private List<Integer> spaltenCoord = new ArrayList<Integer>();
	private List<Integer> zeilenCoord = new ArrayList<Integer>();
	private List<MouseInteract> mouseInteractions = new ArrayList<MouseInteract>();

	private int selectedColumn = -1;
	private int selectedRow = -1;
	private Menge eingetreten;

	public LinienDiagramm(Ereignismenge eMenge, List<Menge> mengen, List<Menge> startMengen, GUI gui) {
		this.eMenge = eMenge;
		this.zielMengen = mengen;
		this.mengen = startMengen;

		if (eMenge.validate())
			constructDiagramm(this.mengen, this.eMenge, gui);
	}

	/**
	 * Ein Konstruktor nur für die Initialisierung des GUIs!
	 */
	public LinienDiagramm(GUI gui) {
		eMenge = new Ereignismenge(new ArrayList<Elementarereignis>());
		mengen = new ArrayList<Menge>();

		if (eMenge.validate())
			constructDiagramm(mengen, eMenge, gui);
	}

	private void constructDiagramm(List<Menge> mengen, Ereignismenge e, GUI gui) {
		panel = new DrawingPanel(this);
		numberEreignisse = mengen.size();
		numberElementare = e.getEreignisse().size();

		panel.addMouseListener(this);
		InputMap im = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, InputEvent.SHIFT_DOWN_MASK, false), "switch");
		panel.getActionMap().put("switch", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("Wechsle das Fenster per Keybind!");
				gui.switchToView(WindowSelect.AUFGABENTEXT);
			}
		});
	}
	
	/**
	 * Führt einen Rebase aus und betrachtet fortan neue Daten. Nur für den externen Gebrauch - beispielsweise beim Aufgabenwechsel - bestimmt
	 * @param mengen
	 * @param startMengen
	 * @param e
	 * @see #rebase(List, Ereignismenge)
	 */
	public void rebase(List<Menge> mengen, List<Menge> startMengen, Ereignismenge e) {
		this.zielMengen = mengen;
		rebase(startMengen, e);
	}
	
	/**
	 * Führt einen Rebase aus und betrachtet fortan neue Daten. Für den internen Gebrauch innerhalb einer Aufgabe bestimmt
	 * @param mengen
	 * @param e
	 * @see #rebase(List, Ereignismenge) 
	 */
	private void rebase(List<Menge> mengen, Ereignismenge e) {
		this.mengen = mengen;
		this.eMenge = e;
		numberEreignisse = mengen.size();
		numberElementare = e.getEreignisse().size();
		log.debug("Neue Daten geladen!");
		
		panel.repaint();
	}

	// Elementarereignis in Spalte "order" in der Menge enthalten?
	private boolean linesegment(Menge menge, int order) {
		for (int k = 0; k < menge.getEreignisse().size(); k++) {
			if (menge.getEreignisse().get(k).getOrder() == order) {
				return true;
			}
		}
		return false;
	}

	private int offset(Graphics2D g2d) {
		int maxlength = Integer.valueOf(g2d.getFontMetrics().stringWidth("P = 000/000"));
		for (int j = 0; j < numberEreignisse; j++) {
			int leftLength = Integer.valueOf(g2d.getFontMetrics().stringWidth(mengen.get(j).getName()));
			if (leftLength > maxlength) {
				maxlength = leftLength;
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
		g2d.setStroke(new BasicStroke(1));
		Dimension d = panel.getSize();
		int diagWidth = d.width - 20;
		int diagHeight = d.height - 20;
		int linewidth = 40;
		int offsetlr = offset(g2d);
		int currentLeftBorder = BORDER_X + offsetlr;

		// Überschrift + Rahmen
		g2d.setFont(new Font("default", Font.BOLD, STD_FONTSIZE * 3/2));
		g2d.setColor(Color.GRAY);
		g2d.drawString("Wechsel zur Aufgabe mit SHIFT", d.width / 2 - g2d.getFontMetrics().stringWidth("Wechsel zur Aufgabe mit CTRL + G")/2, 30);
		g2d.setFont(new Font("default", Font.PLAIN, STD_FONTSIZE));
		g2d.setColor(Color.BLACK);
		g2d.drawLine(BORDER_X + offsetlr, BORDER_Y + 10, BORDER_X + diagWidth - offsetlr, BORDER_Y + 10);
		g2d.drawLine(BORDER_X + offsetlr, BORDER_Y + 10, BORDER_X + offsetlr, BORDER_Y + diagHeight);
		g2d.drawLine(BORDER_X + diagWidth - offsetlr, BORDER_Y + 10, BORDER_X + diagWidth - offsetlr, BORDER_Y + diagHeight);

		spaltenCoord.clear();
		zeilenCoord.clear();

		// Spalten
		currentLeftBorder = BORDER_X + offsetlr;
		for (int i = 0; i < numberElementare; i++) {
			int currentWidth = (int) (eMenge.getEreignisse().get(i).getProbability() * (double) (diagWidth-2*offsetlr));
			g2d.setColor(Color.BLACK);
			g2d.drawLine(currentLeftBorder, BORDER_Y + 10, currentLeftBorder, BORDER_Y + diagHeight);
			spaltenCoord.add(currentLeftBorder);
			if (eMenge.getEreignisse().get(i).isBedingt() && bedingtMode) {
				g2d.setColor(Color.CYAN);
				g2d.fillRect(currentLeftBorder + 1, BORDER_Y + 12, currentWidth - 1, numberEreignisse*linewidth - 2);
			}

			g2d.setColor(Color.BLACK);
			g2d.drawString(eMenge.getEreignisse().get(i).getName(), currentLeftBorder + currentWidth / 2  - g2d.getFontMetrics().stringWidth(eMenge.getEreignisse().get(i).getName()) / 2, BORDER_Y);
			g2d.setColor(Color.GRAY);
			g2d.drawString(eMenge.getEreignisse().get(i).getProbString(), currentLeftBorder + currentWidth / 2  - g2d.getFontMetrics().stringWidth(eMenge.getEreignisse().get(i).getProbString()) / 2, BORDER_Y + linewidth * numberEreignisse + 30);
			g2d.setColor(Color.BLACK);

			if(selectedColumn == i) {
				g2d.setColor(new Color(0.85f, 1f, 1f, 0.8f));
				g2d.fillRect(currentLeftBorder + 1, BORDER_Y + 12, currentWidth - 1, numberEreignisse*linewidth - 2);
				log.info("Zeichne bei (" + currentLeftBorder + currentWidth / 2 + ", " + BORDER_Y + 10 + linewidth + ")");
			}

			g2d.setColor(Color.BLACK);
			currentLeftBorder += currentWidth;
		}
		g2d.setColor(Color.BLACK);
		spaltenCoord.add(currentLeftBorder);
		g2d.drawLine(BORDER_X + diagWidth - offsetlr, BORDER_Y + 10, BORDER_X + diagWidth - offsetlr, BORDER_Y + diagHeight);

		// Zeilen
		g2d.setStroke(new BasicStroke(2));
		for (int j = 0; j < numberEreignisse; j++) {
			// Name des Ereignisses
			currentLeftBorder = BORDER_X + offsetlr;
			if (mengen.get(j).equals(eingetreten) && bedingtMode) {
				g2d.setFont(new Font("default", Font.BOLD, STD_FONTSIZE));
			}
			g2d.drawString(mengen.get(j).getName(), BORDER_X, BORDER_Y + 10 + j*linewidth + linewidth*4/7);
			g2d.setFont(new Font("default", Font.PLAIN, STD_FONTSIZE));

			// Liniensegmente
			boolean correct = false;
			for (int i = 0; i < numberElementare; i++) {
				int currentWidth = (int) (eMenge.getEreignisse().get(i).getProbability() * (double) (diagWidth-2*offsetlr));
				if (mengen.get(j).equals(eingetreten) && bedingtMode) {
					g2d.setColor(Color.BLUE);
				}
				
				if (linesegment(mengen.get(j), i+1)) {
					for (Menge ziel : zielMengen) {
						if (ziel.getName().equals(mengen.get(j).getName())) {
							correct = true; // Es gibt eine entsprechende Namens-Übereinstimmung, daher potenziell korrekt
							for (int k = 0; k < numberElementare; k++) {
								if (linesegment(ziel, k+1) != linesegment(mengen.get(j), k+1))
									correct = false;
							}
						}
					}
					
					if (correct && mengen.get(j).isEditable()) {
						g2d.setColor(new Color(42, 112, 37));
						g2d.setStroke(new BasicStroke(3));
					}
					
					else {
						g2d.setColor(Color.BLACK);
						g2d.setStroke(new BasicStroke(1));
					}
					g2d.drawLine(currentLeftBorder, BORDER_Y + 10 + j*linewidth + linewidth/2, currentLeftBorder + currentWidth, BORDER_Y + 10 + j*linewidth + linewidth/2);
					g2d.setColor(Color.BLACK);
					g2d.setStroke(new BasicStroke(1));
				}
				
				currentLeftBorder += currentWidth;
			}

			// Wahrscheinlichkeiten am rechten Rand
			g2d.setColor(Color.BLACK);
			zeilenCoord.add(BORDER_Y + 10 + j*linewidth + linewidth/2 - 2*BORDER_X);

			// Wenn in Zeile mengen.get(j) die WSK berechnet werden soll
			if (mengen.get(j).isCalculateProbability()) {
				g2d.setFont(new Font("default", Font.BOLD, STD_FONTSIZE));
				boolean isCorrect = mengen.get(j).getFracProbability().equals(mengen.get(j).getUserProbability());
				if(isCorrect)
					g2d.setColor(new Color(42, 112, 37));
				else
					g2d.setColor(Color.RED);
				
				mengen.get(j).setCorrect(isCorrect);
				g2d.drawString(mengen.get(j).getUserProbability() + (isCorrect ? (" = " + mengen.get(j).getDecimalProbability()) : ""), BORDER_X + diagWidth - offsetlr + 10, BORDER_Y + 10 + j*linewidth + linewidth*6/10);
				g2d.setFont(new Font("default", Font.PLAIN, STD_FONTSIZE));
				g2d.setColor(Color.BLACK);
			} else {
				if (bedingtMode) {
					g2d.drawString(mengen.get(j).getProbability(), BORDER_X + diagWidth - offsetlr + 10, BORDER_Y + 10 + j*linewidth + linewidth*4/10);
					g2d.setColor(Color.BLUE);
					g2d.drawString(mengen.get(j).getConditionalProbability(eingetreten), BORDER_X + diagWidth - offsetlr + 10, BORDER_Y + 10 + j*linewidth + linewidth*8/10);
					g2d.setColor(Color.BLACK);
				} else {
					g2d.drawString(mengen.get(j).getProbability(), BORDER_X + diagWidth - offsetlr + 10, BORDER_Y + 10 + j*linewidth + linewidth*6/10);
				}
			}
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
	public void mousePressed(MouseEvent e) {
		Koordinate koord = getPosition(e);
		MouseInteract mi = new MouseInteract(koord, System.currentTimeMillis());
		mouseInteractions.add(mi);
		log.debug(mi);

		if (e.getButton() == MouseEvent.BUTTON1) {
			int length = mouseInteractions.size();

			//Zwei aufeinanderfolgende Klicks in unter 0,5 Sekunden -> Auswertung starten
			if (length >= 2 && (mouseInteractions.get(length-1).timeStamp() -  mouseInteractions.get(length-2).timeStamp()) < 500) {
				Koordinate current = mouseInteractions.get(length-1).koord();
				Koordinate last = mouseInteractions.get(length-2).koord();

				if (current.spalte() == -1 && last.spalte() == -1 && current.zeile() == last.zeile() && current.zeile() < mengen.size()) {
					log.debug("Zeile " + current.zeile() + " wurde gewählt!");
					//bedingt für alle auf false
					eMenge.getEreignisse().stream().forEach(ereignis -> ereignis.setBedingt(false));
					bedingtMode = false;
					if (current.zeile() != -1 && selectedRow != current.zeile()) {
						bedingtMode = true;
						if (current.zeile() < mengen.size()) {
							eingetreten = mengen.get(current.zeile());
							for (int i = 0; i < eingetreten.getEreignisse().size(); i++) {
								eingetreten.getEreignisse().get(i).setBedingt(true);
							}
						}
					}
					selectedRow = (selectedRow == current.zeile() ? -1 : current.zeile());
					mouseInteractions.clear();
				}

				else if (current.zeile() == -1 && last.zeile() == -1 && current.spalte() == last.spalte()) {
					log.debug("Spalte " + current.spalte() + " wurde gewählt!");
					selectedColumn = (selectedColumn == current.spalte() ? -1 : current.spalte());
					mouseInteractions.clear();
				}

				else if (current.spalte() == eMenge.getEreignisse().size() && last.spalte() == eMenge.getEreignisse().size() && current.zeile() == last.zeile()) {
					if (mengen.get(current.zeile()).isCalculateProbability()) {
						log.debug("Zeile " + current.zeile() + " in der rechtesten Spalte wurde gewählt!");
						boolean accepted = false;
						while(!accepted) {
							if(mengen.get(current.zeile()).isCorrect())
								break;
							String m = JOptionPane.showInputDialog("Wahrscheinlichkeit des Ereignisses " + mengen.get(current.zeile()).getName() + " (als gekürzter Bruch):");
							if (m != null) {
								if(m.matches("([0-9]+)/([0-9]+)") || m.equals("0") || m.equals("1")) {
									mengen.get(current.zeile()).setUserProb(m);
									rebase(mengen, eMenge);
									accepted = true;
								}
							} else
								accepted = true;
						}
					}
					mouseInteractions.clear();
				}

				else if (current.zeile() >= mengen.size() && current.zeile() == last.zeile() && current.spalte() == last.spalte() && enableNeueEreignisse) {
					if (enableNeueEreignisse) {
						log.debug("NEUES EREIGNIS HINZUFÜGEN");
						String m = JOptionPane.showInputDialog("Name des neuen Ereignisses:");
						if (m != null) {
							mengen.add(new Menge(m, eMenge, new ArrayList<Elementarereignis>(), mengen.size() + 1, false, true, true));
							rebase(mengen, eMenge);
						}
					}
					mouseInteractions.clear();
				}

				else if (current.zeile() == last.zeile() && current.spalte() == last.spalte()) {
					if (mengen.get(current.zeile()).isEditable()) {
						if (mengen.get(current.zeile()).getEreignisse().contains(eMenge.getEreignisse().get(current.spalte()))) {
							mengen.get(current.zeile()).deleteElementar(eMenge.getEreignisse().get(current.spalte()));
							log.debug("Von Ereignis " + mengen.get(current.zeile()).getName() + " wurde das Elementarereignis " + eMenge.getEreignisse().get(current.spalte()).getName() + " gelöscht!");
						} else {
							mengen.get(current.zeile()).addElementar(eMenge.getEreignisse().get(current.spalte()));
							log.debug("Dem Ereignis " + mengen.get(current.zeile()).getName() + " wurde das Elementarereignis " + eMenge.getEreignisse().get(current.spalte()).getName() + " hinzugefügt!");
						}
	
						if (bedingtMode) {
							// bedingt für alle auf false
							eMenge.getEreignisse().stream().forEach(ereignis -> ereignis.setBedingt(false));
							for (int i = 0; i < eingetreten.getEreignisse().size(); i++) {
								eingetreten.getEreignisse().get(i).setBedingt(true);
							}
						}
					}
					mouseInteractions.clear();
				}
				panel.repaint();
			}

		} else if (e.getButton() == MouseEvent.BUTTON3) {
			int length = mouseInteractions.size();
			Koordinate current = mouseInteractions.get(length-1).koord();
			
			if (current.zeile() >= 0 && current.zeile() < numberEreignisse) {
				if (mengen.get(current.zeile()).isDeleteable()) {
					log.debug("Ereignis " + mengen.get(current.zeile()).getName() + " wurde gelöscht!");
					bedingtMode = false;
					mengen.remove(current.zeile());

					rebase(mengen, eMenge);
				}
				mouseInteractions.clear();
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
