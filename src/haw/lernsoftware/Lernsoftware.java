package haw.lernsoftware;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Lernsoftware extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8769057448432932551L;

	public Lernsoftware() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		getContentPane().setLayout(new BorderLayout());
		
		setVisible(true);
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		EventQueue.invokeLater(() -> {
			new Lernsoftware();
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
	}

}
