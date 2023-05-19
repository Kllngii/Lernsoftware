package haw.lernsoftware.view;

import javax.swing.JComponent;
import javax.swing.JLabel;
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

	public Tutorial(GUI gui) {
		this.gui = gui;
		panel.add(constructStartseite());
	}

	private JComponent constructStartseite() {
		
		
		text1.setText(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_Ueberschrift.text"));
		
		//FormBuilder hinzufügen
		// gibt einen JComponent zurück, der .debug(true)
		return FormBuilder.create() // Rote Linien zeichnen
				.columns("200dlu, 10dlu ,200dlu, 10dlu, 200dlu")//, center:200dlu, 200dlu") //
				.rows("100dlu, p, p,p") //
				.padding(Paddings.DIALOG) //
				.add(ueberschrift).xyw(1, 1, 5) //
				.add(text1).xyw(1, 2, 5) //
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz1.text")).xyw(1, 3, 5) //
				// hier muss ein bild rein
				.add(ResourceProvider.loadStringFromProperties(Konst.PROPERTIES_AUFGABEN, "tutorial1_absatz2.text")).xyw(1, 4, 5) //
				.build(); //
		//test
	}


}
