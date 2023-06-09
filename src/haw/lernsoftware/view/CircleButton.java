package haw.lernsoftware.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;

import haw.lernsoftware.model.WindowSelect;

public class CircleButton extends JButton {
    private static final long serialVersionUID = 1L;

    public CircleButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed() || getModel().isRollover()) {
            g2.setColor(Color.DARK_GRAY);
        } else {
            g2.setColor(Color.BLACK);
        }
               
        int arc = 20; // Radius der abgerundeten Ecken
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border painting
    }
}

