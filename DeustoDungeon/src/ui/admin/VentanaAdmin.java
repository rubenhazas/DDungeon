package ui.admin;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import gestorDB.GestorDB;
import ui.main.VentanaLogin;
import javax.swing.JList;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	public JList<String> listPersonajes;
	public JLabel lblPersonajes;
	public JButton btnBorrarPersonajes;
	public VentanaAdmin window = this;
	public DefaultListModel<String> listModel;
	public DefaultListModel<String> listModelEnemigos;
	public JList<String> listEnemigos;
	public JButton btnAñadirEnemigos;
	public JButton btnBorrarEnemigos;
	public VentanaLogin loginWindow;
	public JLabel lblEnemigos;
	private Logger logger = Logger.getLogger(VentanaAdmin.class.getName());
	public GestorDB miDB;
	public JLabel fondo;

	public VentanaAdmin(VentanaLogin login) {
		try {
			miDB = new GestorDB();
			miDB.conectar();
		} catch (Exception e) {
		}
		getContentPane().setLayout(null);
		window.setBounds(500, 300, 450, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginWindow = login;
		listModel = new DefaultListModel<String>();
		listModelEnemigos = new DefaultListModel<String>();
		try {
			logger.log(Level.INFO, "Buscando los personajes");
			listModel.addAll(miDB.obtenerAliados());

			logger.log(Level.INFO, "Buscando los enemigos");
			listModelEnemigos.addAll(miDB.obtenerEnemigos());
		} catch (Exception e) {
		}
		// JList con los personajes
		listPersonajes = new JList<String>(listModel);
		listPersonajes.setBounds(10, 35, 100, 215);
		getContentPane().add(listPersonajes);
		lblPersonajes = new JLabel("Personajes");
		lblPersonajes.setForeground(Color.WHITE);
		lblPersonajes.setBounds(10, 11, 83, 14);
		getContentPane().add(lblPersonajes);

		// boton para borrar personajes
		btnBorrarPersonajes = new JButton("Borrar");
		btnBorrarPersonajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logger.log(Level.INFO, "Intentando eliminar un personaje");
					miDB.eliminarAliado(listModel.get(listPersonajes.getSelectedIndex()));
					listModel.addAll(miDB.obtenerAliados());
				} catch (Exception e2) {
				}
			}
		});
		btnBorrarPersonajes.setBounds(120, 32, 89, 23);
		getContentPane().add(btnBorrarPersonajes);
		// JList con los enemigos
		listEnemigos = new JList<String>(listModelEnemigos);
		listEnemigos.setBounds(324, 35, 100, 215);
		getContentPane().add(listEnemigos);

		lblEnemigos = new JLabel("Enemigos");
		lblEnemigos.setForeground(Color.WHITE);
		lblEnemigos.setBounds(324, 11, 100, 14);
		getContentPane().add(lblEnemigos);
		// boton para borrar los enemigos
		btnBorrarEnemigos = new JButton();
		btnBorrarEnemigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logger.log(Level.INFO, "Intentando eliminar un enemigo");
					miDB.eliminarUnidad(listModelEnemigos.get(listEnemigos.getSelectedIndex()));
					listModelEnemigos.addAll(miDB.obtenerEnemigos());
				} catch (Exception e2) {
				}
			}
		});
		btnBorrarEnemigos.setText("Borrar");
		btnBorrarEnemigos.setBounds(231, 32, 83, 23);
		getContentPane().add(btnBorrarEnemigos);
		// boton para añadir enemigos
		btnAñadirEnemigos = new JButton();
		btnAñadirEnemigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAñadirEnemigo enemigo = new VentanaAñadirEnemigo(window, listModelEnemigos.getSize());
				enemigo.setVisible(true);
				try {
					miDB.desconectar();
				} catch (Exception e2) {

				}
				window.dispose();
			}
		});
		btnAñadirEnemigos.setText("A\u00F1adir");
		btnAñadirEnemigos.setBounds(231, 66, 83, 23);

		getContentPane().add(btnAñadirEnemigos);
		// Imagen de fondo de la ventana
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoMenu.png")));
		fondo.setBounds(0, 0, 435, 261);
		getContentPane().add(fondo);

	}
}
