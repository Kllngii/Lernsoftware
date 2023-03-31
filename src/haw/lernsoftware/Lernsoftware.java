package haw.lernsoftware;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import haw.lernsoftware.view.GUI;

public class Lernsoftware extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8769057448432932551L;
	
	public static GUI Plotter;

	public Lernsoftware() {
		
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		Plotter = new GUI();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
	}

}
