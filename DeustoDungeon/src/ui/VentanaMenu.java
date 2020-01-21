package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import gestorDB.GestorDB;
import java.awt.Font;

import java.awt.Color;

public class VentanaMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	private VentanaMenu window = this;
	static GestorDB miDB;
	static Connection miConexion;

	public JButton crearPersonajes = new JButton("Crear Personaje");
	public JButton seleccionarPersonaje = new JButton("Seleccionar Personaje");
	public JLabel titulo = new JLabel("DeustoDungeon");
	public JLabel fondo;
	private Logger logger = Logger.getLogger(VentanaMenu.class.getName());

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					miDB = new GestorDB();
					miDB.conectar();
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
		logger.log(Level.INFO, "Creando la ventana");
		window.setBounds(500, 300, 450, 175);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setResizable(false);
		JPanel panel = new JPanel(null);
		window.getContentPane().add(panel);
		// boton para crear personajes
		crearPersonajes.setBounds(56, 76, 130, 30);
		panel.add(crearPersonajes);
		// listener para el boton de crear personajes
		crearPersonajes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPersonaje ventanaPersonaje = new VentanaPersonaje(window);
				ventanaPersonaje.setVisible(true);
				ventanaPersonaje.setResizable(false);
				window.dispose();

			}
		});
		// boton para seleccionar personajes
		seleccionarPersonaje.setBounds(230, 76, 170, 30);
		panel.add(seleccionarPersonaje);
		// listener para el boton de seleccionar personajes
		seleccionarPersonaje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccion ventanaSeleccion = new VentanaSeleccion(window);
				ventanaSeleccion.setVisible(true);
				logger.log(Level.INFO, "Cerrando la ventana del menu");
				window.dispose();
			}
		});
		titulo.setForeground(Color.WHITE);
		// titulo del proyecto
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setBounds(140, 27, 143, 30);
		panel.add(titulo);
		// imagen de fondo de la ventana
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoMenu.png")));
		fondo.setBounds(0, 0, 444, 149);
		panel.add(fondo);
	}
}
