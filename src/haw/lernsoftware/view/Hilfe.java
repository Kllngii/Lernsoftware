package haw.lernsoftware.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Taskbar.Feature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

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
	private JLabel Ueberschrift = new JLabel("Überschrift");
	private JTextArea test = new JTextArea(8,200);

	

	private String bla = new String(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text5"));
	
	public  Hilfe() {
		
		//Fenster erstellen
		JFrame fenster = new JFrame("Hilfe");

		fenster.setSize(800, 750);
		fenster.getContentPane().add(panel);
		//fenster.setResizable(false);
		

		panel.add(buildContentMenu(), BorderLayout.WEST);
		panel.add(buildContentText(), BorderLayout.EAST);
		
		Image icon = ResourceProvider.loadImage(Konst.HILFE_ICON);
		final Taskbar taskbar = Taskbar.getTaskbar();
		if(taskbar.isSupported(Feature.ICON_IMAGE))
			taskbar.setIconImage(icon);
		if(icon != null)
			fenster.setIconImage(icon);

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
				.debug(true)                                 // Rote Linien zeichnen
				.padding(Paddings.DIALOG)
				.add(Ueberschrift) .xy(1, 1)
				//.add(bla)  .xy(1, 2)
				.add(test) .xy(1, 2)
				.border(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.DARK_GRAY))
				.build();

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;



public class Hilfe extends HAWView {

	public  Hilfe() {
		//Fenster erstellen
		JFrame fenster = new JFrame("Hilfe");

		fenster.setSize(800, 800);

		fenster.getContentPane().add(panel);

		panel.add(buildContent());

		fenster.setVisible(true);


	}


	public JComponent buildContent() {
		JLabel titleField = title("Title");
		JLabel authorField = title("Title");
		JLabel priceField = title("Title");
		return FormBuilder.create()
				.columns("left:90dlu, 3dlu, 200dlu")
				.rows("p, $lg, p, $lg, p")
				.padding(Paddings.DIALOG)
				.add("_Title:")  .xy(1, 1)
				.add(titleField) .xy(3, 1)
				.add("_Author:") .xy(1, 3)
				.add(authorField).xy(3, 3)
				.add("_Price:")  .xy(1, 5)
				.add(priceField) .xy(3, 5)
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
			Ueberschrift.setText("Überschrift1");
		}
		if(e.getSource() == Button2) {
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text2"));
			Ueberschrift.setText("Überschrift2");
			test.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text5"));
			test.setWrapStyleWord(true);
			test.setLineWrap(true);
			//test.setPreferredSize(new Dimension(400,400));
			//test.setRows(4);
			
		}
		if(e.getSource() == Button3) {
			text.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_HILFE, "hilfe.text3"));
			Ueberschrift.setText("Überschrift3");
		}
		panel.repaint();
	}
}
