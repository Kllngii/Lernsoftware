package haw.lernsoftware;

import java.awt.BorderLayout;
import java.awt.Container;
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
	
	public GUI plotter = new GUI(this);

	public Lernsoftware() {
		super.setTitle("Lernsoftware");
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		
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
