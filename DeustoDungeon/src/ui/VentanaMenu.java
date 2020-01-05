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

public class VentanaMenu extends JFrame {

	/**
	 * Launch the application.
	 */
	private VentanaMenu window = this;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu window = new VentanaMenu();
					window.setVisible(true);
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
		window.setBounds(400, 100, 600, 400);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setResizable(false);
		JPanel panel = new JPanel(null);
		window.add(panel);
		
		JButton crearPersonajes = new JButton("Crear Personaje");
		crearPersonajes.setBounds(30, 300, 130, 30);
		panel.add(crearPersonajes);
		crearPersonajes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPersonaje ventanaPersonaje = new VentanaPersonaje(window);
				ventanaPersonaje.setVisible(true);
				ventanaPersonaje.setResizable(false);
				window.dispose();
				
			}
		});	
	}
}
