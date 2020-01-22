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
					miDB.eliminarUnidad(listModel.get(listPersonajes.getSelectedIndex()));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnBorrarPersonajes.setBounds(120, 32, 89, 23);
		getContentPane().add(btnBorrarPersonajes);

	}
}
