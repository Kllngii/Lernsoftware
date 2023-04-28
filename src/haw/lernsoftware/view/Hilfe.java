package haw.lernsoftware.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;



import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;

/**
 * Die Hilfeseite des Programms.
 * @author Sebastian Holz
 */
public class Hilfe extends HAWView implements ActionListener{

	
	private JButton Button1 = new JButton("Test");
	private JButton Button2 = new JButton("Test2");
	private JButton Button3 = new JButton("Test3");	
	private JLabel text = new JLabel();

	

	private String bla = new String("Test sadgfsafg sdafasfashdflashflasfhsahfsaifhaswiofhsaihfvsoadhjfsaohfdsaodfhasioufhvasdoivhsad9iuhfvas9u");
	
	public  Hilfe() {
		
		//Fenster erstellen
		JFrame fenster = new JFrame("Hilfe");

		fenster.setSize(800, 750);
		fenster.getContentPane().add(panel);
		

		panel.add(buildContentMenu(), BorderLayout.WEST);
		panel.add(buildContentText(), BorderLayout.EAST);

		fenster.setVisible(true);
		
		
	}


	public JComponent buildContentMenu() {		
		
		Button1.addActionListener(this);
		Button2.addActionListener(this);
		Button3.addActionListener(this);

		
		return FormBuilder.create()
				.columns("center:90dlu")
				.rows("p,50dlu,p,50dlu,p,50dlu,p,50dlu,p,50dlu,p,50dlu,p,50dlu,60dlu")
				//.debug(true)                                 // Rote Linien zeichnen
				.padding(Paddings.DIALOG)
				.addStack(Button1) .xy(1, 2)
				.addStack(Button2) .xy(1, 4)
				.addStack(Button3) .xy(1, 6)
				.border(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.DARK_GRAY))
				.build();
		

	}

	public JComponent buildContentText() {
		

		return FormBuilder.create()
				.columns("left:300dlu")
				.rows("10dlu,top:400dlu")
				//.debug(true)                                 // Rote Linien zeichnen
				.padding(Paddings.DIALOG)
				.add("Überschrift") .xy(1, 1)
				.add(text)  .xy(1, 2)
				.border(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.DARK_GRAY))
				.build();

	}

	public static void main(String[] args) {
		new Hilfe();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == Button1) {
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text"));
		}
		if(e.getSource() == Button2) {
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text2"));
		}
		if(e.getSource() == Button3) {
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text3"));
		}
		panel.repaint();
	}
}
