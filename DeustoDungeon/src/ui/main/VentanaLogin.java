package ui.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import gestorDB.GestorDB;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import ui.admin.VentanaAdmin;
import usuario.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	public static GestorDB miDB;
	public static Connection miConexion;
	private JTextField textFieldNombre;
	private JTextField textFieldPassword;
	private VentanaLogin window = this;
	public JLabel fondo;
	private Logger logger = Logger.getLogger(VentanaLogin.class.getName());
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					miDB = new GestorDB();
					miDB.conectar();
					VentanaLogin window = new VentanaLogin();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaLogin() {
		logger.log(Level.INFO,"Creando ventana de login");
		getContentPane().setLayout(null);
		window.setBounds(500, 300, 450, 175);
		window.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,0,0));
		panel.setBounds(0, 0, 444, 146);
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[120.00][166.00,grow][126.00]", "[19.00][25.00][29.00][][]"));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.WHITE);
		panel.add(lblNombre, "cell 0 1,alignx center,aligny center");
		
		textFieldNombre = new JTextField();
		panel.add(textFieldNombre, "cell 1 1,growx");
		textFieldNombre.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		panel.add(lblPassword, "cell 0 2,alignx center");
		
		textFieldPassword = new JTextField();
		panel.add(textFieldPassword, "cell 1 2,growx");
		textFieldPassword.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					miDB.guardarUser(new User(textFieldNombre.getText(), textFieldPassword.getText(),0));
					logger.log(Level.INFO,"Usuario registrado");
				} catch (Exception e2) {
				}
				
			}
		});
		panel.add(btnRegister, "cell 0 4,alignx center");
		// Imagen de fondo de la ventana
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoMenu.png")));
		fondo.setBounds(0, 0, 435, 146);
		getContentPane().add(fondo);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = miDB.comprobarUser(textFieldNombre.getText(), textFieldPassword.getText());
					
					if(i==0) {
						JOptionPane.showMessageDialog(null,"El usuario no existe");
						logger.log(Level.WARNING,"El usuario introducido no es correcto");
					}else if(i==1) {
						VentanaMenu ventanaMenu = new VentanaMenu(window);
						JOptionPane.showMessageDialog(null,"El login ha sido correcto");
						ventanaMenu.setVisible(true);
						logger.log(Level.INFO,"Login correcto");
						miDB.desconectar();
						window.dispose();
					}else if(i==2) {
						VentanaAdmin ventanaAdmin= new VentanaAdmin(window);
						JOptionPane.showMessageDialog(null,"El login como admin ha sido correcto");
						ventanaAdmin.setVisible(true);
						logger.log(Level.INFO,"Login como admin correcto");
						miDB.desconectar();
						window.dispose();
					}
				} catch (Exception e2) {
				}
			}
		});
		panel.add(btnLogin, "cell 2 4,alignx center");
		
		
	}
}
