package haw.lernsoftware.view.liniendiagramm;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Ein DrawingPanel macht den {@link Graphics} Kontext für das {@link LinienDiagramm} von außen zugänglich.
 * @author Lasse Kelling
 *
 */
public class DrawingPanel extends JPanel {
	
	private static final long serialVersionUID = 4122090545664176370L;
	private LinienDiagramm liniendiagramm;
	
	public DrawingPanel(LinienDiagramm l) {
		this.liniendiagramm = l;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		liniendiagramm.paintPanel(g);
	}
}
