package haw.lernsoftware;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import haw.lernsoftware.view.GUI;

import org.json.JSONArray;
import org.json.JSONObject;

import haw.lernsoftware.model.Elementarereignis;
import haw.lernsoftware.model.Ereignismenge;
import haw.lernsoftware.resources.ResourceProvider;

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
		
		EventQueue.invokeLater(() -> {
			new Lernsoftware();
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
	}

}
