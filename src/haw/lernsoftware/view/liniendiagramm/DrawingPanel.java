package haw.lernsoftware.view.liniendiagramm;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
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
