package haw.lernsoftware.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * Oberklasse für alle anzeigbaren Klassen. Enthält viele praktische Funktionen für den Umgang mit Java.awt und Javax.swing GUIs
 * @author Lasse Kelling
 *
 */
public class HAWView {
	protected JComponent panel = new JPanel();
	

	protected final DefaultComponentFactory compFactory = new DefaultComponentFactory();
	
	/**
     * Erzeugt ein JLabel mit Tooltip-Text, für einen Formbuilder
     * @param text  Text und Tooltip-Text
     */
    protected JLabel title(String text) {
        return title(text, text);
    }
    protected JLabel title(String text, String tooltip) {
        JLabel label = compFactory.createTitle(text);
        label.setToolTipText(tooltip);
        return label;
    }
    
    /**
     * Versieht eine {@link JComponent} mit einem unsichtbaren Rand von soundso vielen Pixeln;
     * so wie bei Padding per CSS in HTML-Seiten. <br/>
     * @return  Entweder die Komponente selbst (mit neuer Border), oder ein neues JPanel mit entsprechender
     *          Border, das wiederum die Komponente enthält.
     */
    protected JComponent padding(JComponent comp, int top, int right, int bottom, int left) {
        Border outerBorder = new EmptyBorder(top, left, bottom, right);
        if (comp.getBorder() == null)
            comp.setBorder(outerBorder);
        else {
            if (comp instanceof JButton) {
                JPanel panel = new JPanel();
                panel.add(comp);
                panel.setBorder(outerBorder);
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                return panel;
            }
            else
                comp.setBorder(new CompoundBorder(outerBorder, comp.getBorder()));
        }
        
        return comp;
    }

    /**
     * Fügt nur links ein Padding ein. Alle anderen werden auf 0 gesetzt.
     * @see #padding(JComponent, int, int, int, int)
     * @see #paddingRight(JComponent, int)
     * @see #paddingTop(JComponent, int)
     * @see #paddingBottom(JComponent, int)
     */
    protected JComponent paddingLeft(JComponent comp, int left) {
        return padding(comp, 0, 0, 0, left);
    }

    /**
     * Fügt nur rechts ein Padding ein. Alle anderen werden auf 0 gesetzt.
     * @see #padding(JComponent, int, int, int, int)
     * @see #paddingLeft(JComponent, int)
     * @see #paddingTop(JComponent, int)
     * @see #paddingBottom(JComponent, int)
     */
    protected JComponent paddingRight(JComponent comp, int right) {
        return padding(comp, 0, right, 0, 0);
    }
    
    /**
     * Fügt nur oben ein Padding ein. Alle anderen werden auf 0 gesetzt.
     * @see #padding(JComponent, int, int, int, int)
     * @see #paddingLeft(JComponent, int)
     * @see #paddingRight(JComponent, int)
     * @see #paddingBottom(JComponent, int)
     */
    protected JComponent paddingTop(JComponent comp, int top) {
        return padding(comp, top, 0, 0, 0);
    }

    /**
     * Fügt nur unten ein Padding ein. Alle anderen werden auf 0 gesetzt.
     * @see #padding(JComponent, int, int, int, int)
     * @see #paddingLeft(JComponent, int)
     * @see #paddingRight(JComponent, int)
     * @see #paddingTop(JComponent, int)
     */
    protected JComponent paddingBottom(JComponent comp, int bottom) {
        return padding(comp, 0, 0, bottom, 0);
    }
    
    /**
     * Fügt ein symmetrisches Padding ein, bei dem oben und unten sowie links und rechts jeweils gleich groß sind.
     * @see #padding(JComponent, int, int, int, int)
     * @see #padding(JComponent, int)
     */
    protected JComponent padding(JComponent comp, int topAndBottom, int leftAndRight) {
            return padding(comp, topAndBottom, leftAndRight, topAndBottom, leftAndRight);
    }

    /**
     * Fügt ein rundherum gleiches Padding ein.
     * @see #padding(JComponent, int, int, int, int)
     * @see #padding(JComponent, int, int)
     */
    protected JComponent padding(JComponent comp, int allSides) {
        return padding(comp, allSides, allSides, allSides, allSides);
    }
}
