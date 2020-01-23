package ui.main;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import usuario.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldPassword;
	private VentanaRegistro window = this;
	private VentanaLogin login;
	public JLabel fondo;
	private Logger logger = Logger.getLogger(VentanaRegistro.class.getName());

	public VentanaRegistro(VentanaLogin v) {
		logger.log(Level.INFO, "Creando ventana de registro");
		login = v;
		getContentPane().setLayout(null);
		window.setBounds(500, 300, 450, 175);
		window.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setBounds(0, 0, 444, 146);
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[120.00][166.00,grow][126.00]", "[19.00][25.00][29.00][45.00]"));

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.WHITE);
		panel.add(lblNombre, "cell 0 1,alignx center,aligny center");
		// textField para introducir el usuario
		textFieldNombre = new JTextField();
		panel.add(textFieldNombre, "cell 1 1,growx");
		textFieldNombre.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		panel.add(lblPassword, "cell 0 2,alignx center");
		// textField para introducir la contraseña
		textFieldPassword = new JTextField();
		panel.add(textFieldPassword, "cell 1 2,growx");
		textFieldPassword.setColumns(10);
		// Imagen de fondo de la ventana
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoMenu.png")));
		fondo.setBounds(0, 0, 444, 146);
		getContentPane().add(fondo);

		JButton registrar = new JButton();
		registrar.setText("Registrar");
		registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login.miDB.guardarUser(new User(textFieldNombre.getText(), textFieldPassword.getText(), 0));
					logger.log(Level.INFO, "Usuario registrado");
					window.dispose();
				} catch (Exception e2) {
				}
			}
		});
		panel.add(registrar, "cell 2 3,growx");
	}
}
