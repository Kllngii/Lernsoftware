package haw.lernsoftware.view;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.apache.log4j.Logger;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import haw.lernsoftware.Konst;
import haw.lernsoftware.resources.ResourceProvider;


/**
 * Die Tutorial des Programms.
 */
public class Tutorial extends HAWView {

	Logger log = Logger.getLogger(getClass());
	private javax.swing.border.Border border = javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED);
	private GUI gui;
	JLabel ueberschrift = new JLabel("Tutorial");
	JLabel text1 = new JLabel();
	JLabel bildLabel1 = new JLabel();
	private ImageIcon bild1 = new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD1));
	JPanel bild = new JPanel();
	JPanel uPanel = new JPanel();
	
	

	public Tutorial(GUI gui) {
		this.gui = gui;

		JPanel view = new JPanel();
		panel = new JScrollPane(view);
		view.add(constructStartseite());
	}

	private JComponent constructStartseite() {
		
		text1.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_Ueberschrift.text"));
		//bild.add(bild1);
		 bildLabel1.setIcon(bild1);
		 //bildLabel.setPreferredSize(new Dimension(500, 300));
		 String[] texte = new String[200] ;
		 for(int i = 0; i<100;i++) {
			 texte[i]= ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz"+i+".text");
		 }
		 log.info("Der erste Text ist:"+texte[7]);
		//FormBuilder hinzufügen
		return FormBuilder.create() // Rote Linien zeichnen
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu")//, center:200dlu, 200dlu") //
				.rows("20dlu, p, p, p, p, p, p, p,p,p,p,p,p,p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 5) //
				.add(text1).xyw(1, 2, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz1.text")).xyw(1, 3, 5) //
				.add(new JLabel(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD1)))).xyw(1,4,5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz2.text")).xyw(1, 5, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz3.text")).xyw(1, 6, 5) //
				.add(new JLabel(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD2)))).xyw(1,7,5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz4.text")).xyw(1, 6, 5) //
				.add(new JLabel(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD3)))).xyw(1,7,5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz5.text")).xyw(1, 6, 5) //
				.add(new JLabel(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD4)))).xyw(1,7,5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz6.text")).xyw(1, 6, 5) //
				.add(new JLabel(new ImageIcon(ResourceProvider.loadImage(Konst.EINFÜHRUNG_BILD5)))).xyw(1,7,5) //
				
				
				.build(); //
		
		//test
	}


}
