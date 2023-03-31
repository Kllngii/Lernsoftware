package haw.lernsoftware;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import haw.lernsoftware.model.Ereignismenge;
import haw.lernsoftware.model.Menge;
import haw.lernsoftware.resources.ResourceProvider;
import haw.lernsoftware.view.GUI;

public class Lernsoftware extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8769057448432932551L;
	
	public static GUI Plotter;

	public Lernsoftware() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		getContentPane().setLayout(new BorderLayout());
		super.setTitle("Lernsoftware");
		
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		Plotter = new GUI();
		
		Ereignismenge eMenge = Ereignismenge.fromJSON(ResourceProvider.getFileContentAsString("würfel.em").replace(" ", ""));
		
		System.out.println("Die Ereignismenge ist " + (eMenge.vaildate() ? "ok" : "fehlerhaft"));
		
		Menge mengeA = new Menge(eMenge, eMenge.getEreignisse().subList(0, 3));
		Menge mengeB = new Menge(eMenge, eMenge.getEreignisse().subList(1, 4));
		System.out.println(mengeA.vereinigt(mengeB).negiert().toJSON().toString(3));
		
		EventQueue.invokeLater(() -> {
			new Lernsoftware();
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
	}

}
