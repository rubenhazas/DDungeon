package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu window = new VentanaMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		JPanel panel = new JPanel(null);
		frame.add(panel);
		
		JButton crearPersonajes = new JButton("Crear Personaje");
		crearPersonajes.setBounds(30, 300, 130, 30);
		panel.add(crearPersonajes);
		crearPersonajes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPersonaje ventanaPersonaje = new VentanaPersonaje();
				ventanaPersonaje.setVisible(true);
				ventanaPersonaje.setResizable(false);
				frame.dispose();
			}
		});
		
	}
}
