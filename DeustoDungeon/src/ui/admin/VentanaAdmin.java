package ui.admin;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import gestorDB.GestorDB;
import ui.user.VentanaPersonaje;
import ui.user.VentanaSeleccion;
import unidades.Aliado;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	public JList<String> listPersonajes;
	public JLabel lblPersonajes;
	public JButton btnBorrarPersonajes;
	public VentanaAdmin window = this;
	public DefaultListModel<String> listModel;
	public DefaultListModel<String> listModelEnemigos;
	public JList<String> listEnemigos;
	public JButton btnBorrarEnemigos;
	public JButton btnAñadirEnemigos;
	public JLabel lblEnemigos;
	public GestorDB miDB;
	public Connection miConnection;
	private Logger logger = Logger.getLogger(VentanaAdmin.class.getName());

	public VentanaAdmin(GestorDB db, Connection conn) {
		getContentPane().setLayout(null);
		window.setBounds(500, 300, 450, 300);
		miDB = db;
		miConnection = conn;
		listModel = new DefaultListModel<String>();
		try {
			logger.log(Level.INFO, "Buscando los personajes");
			listModel.addAll(miDB.obtenerAliados());
			logger.log(Level.INFO, "Buscando los enemigos");
			listModelEnemigos.addAll(miDB.obtenerEnemigos());
		} catch (Exception e) {
		}

		listPersonajes = new JList<String>(listModel);
		listPersonajes.setBounds(10, 35, 100, 215);
		getContentPane().add(listPersonajes);

		lblPersonajes = new JLabel("Personajes");
		lblPersonajes.setBounds(10, 11, 60, 14);
		getContentPane().add(lblPersonajes);

		btnBorrarPersonajes = new JButton("Borrar");
		btnBorrarPersonajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logger.log(Level.INFO, "Intentando eliminar un personaje");
					miDB.eliminarAliado(listModel.get(listPersonajes.getSelectedIndex()));
				} catch (Exception e2) {
				}
			}
		});
		btnBorrarPersonajes.setBounds(120, 32, 89, 23);
		getContentPane().add(btnBorrarPersonajes);
		
		listEnemigos = new JList<String>();
		listEnemigos.setBounds(324, 35, 100, 215);
		getContentPane().add(listEnemigos);
		
		lblEnemigos = new JLabel("Enemigos");
		lblEnemigos.setBounds(324, 11, 100, 14);
		getContentPane().add(lblEnemigos);
		
		btnAñadirEnemigos = new JButton();
		btnAñadirEnemigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logger.log(Level.INFO, "Intentando eliminar un enemigo");
					miDB.eliminarUnidad(listModel.get(listPersonajes.getSelectedIndex()));
				} catch (Exception e2) {
				}
			}
		});
		btnAñadirEnemigos.setText("Borrar");
		btnAñadirEnemigos.setBounds(231, 32, 83, 23);
		getContentPane().add(btnAñadirEnemigos);
		
		btnBorrarEnemigos  = new JButton();
		btnBorrarEnemigos.setText("A\u00F1adir");
		btnBorrarEnemigos.setBounds(231, 66, 83, 23);
		getContentPane().add(btnBorrarEnemigos);

	}
}
