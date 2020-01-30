package ui.user;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import gestorDB.GestorDB;
import net.miginfocom.swing.MigLayout;
import unidades.Unidad;

public class VentanaPersonaje extends JFrame {

	private static final long serialVersionUID = 1L;
	/*
	 * labels de las distintas cosas que puedes seleccionar de tu personaje
	 */
	public JLabel nom = new JLabel("Nombre");
	public JLabel des = new JLabel("Descripcion");
	public JLabel atkFis = new JLabel("Ataque Fisico");
	public JLabel atkMag = new JLabel("Ataque Magico");
	public JLabel vida = new JLabel("Vida");
	public JLabel defFis = new JLabel("Defensa Fisica");
	public JLabel defMag = new JLabel("Defensa Magica");
	public JLabel nombreArma = new JLabel("Nombre del arma");
	public JLabel tipoArma = new JLabel("Tipo de arma");
	public JLabel casco = new JLabel("Nombre del casco");
	public JLabel pechera = new JLabel("Nombre de la pechera");
	/*
	 * JTextsFields de los datos
	 */
	public JTextField nomt = new JTextField();
	public JTextField dest = new JTextField();
	public JTextField atkFist = new JTextField();
	public JTextField atkMagt = new JTextField();
	public JTextField vidat = new JTextField();
	public JTextField defFist = new JTextField();
	public JTextField defMagt = new JTextField();
	public JTextField nombreArmat = new JTextField();
	public JTextField tipoArmat = new JTextField();
	public JTextField cascot = new JTextField();
	public JTextField pecherat = new JTextField();
	public JButton armas = new JButton("Armas");
	public JButton cascos = new JButton("Cascos");
	public JButton pecheras = new JButton("Pecheras");

	public JButton crearPersonaje = new JButton("Crear Personaje");
	public JButton volver = new JButton("Volver");
	public JLabel fondo;
	public JPanel panelFondo;
	public VentanaPersonaje ventana = this;
	public GestorDB miDB;
	private Logger logger = Logger.getLogger(VentanaPersonaje.class.getName());

	public VentanaPersonaje(VentanaMenu v) {
		logger.log(Level.INFO, "Creando la ventana de creacion de personaje");
		try {
			miDB = new GestorDB();
			miDB.conectar();
		} catch (Exception e) {
			// TODO: handle exception
		}
		VentanaMenu menu = v;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 400, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 11, 384, 413);
		panel1.setBackground(new Color(0, 0, 0, 0));
		panelFondo = new JPanel();
		panelFondo.setLayout(null);
		panelFondo.add(panel1);
		// añadir todos los elementos al panel
		panel1.setLayout(new MigLayout("", "[38px][7px][7px][135.00px][18.00px][5.00px][13px][][5px]",
				"[30.00px][30.00px][30.00][30.00][30.00][30.00][30.00][30.00][30.00][30.00][30.00][]"));
		// JLabel y JTextField del nombre
		nom.setForeground(Color.WHITE);
		panel1.add(nom, "cell 0 0,alignx left,aligny center");
		panel1.add(nomt, "cell 3 0,growx,aligny center");
		// JLabel y JTextField de la descripcion
		des.setForeground(Color.WHITE);
		panel1.add(des, "cell 0 1,alignx left,aligny center");
		panel1.add(dest, "cell 3 1,growx,aligny center");

		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 423, 384, 48);
		panel2.setBackground(new Color(0, 0, 0, 0));
		panel2.add(crearPersonaje);
		panel2.add(volver);

		panelFondo.add(panel2);
		// JLabel y JTextField del ataque
		atkFis.setForeground(Color.WHITE);
		panel1.add(atkFis, "cell 0 2,alignx left,aligny center");
		panel1.add(atkFist, "cell 3 2,growx,aligny center");
		// JLabel y JTextField del ataque magico
		atkMag.setForeground(Color.WHITE);
		panel1.add(atkMag, "cell 0 3,alignx left,aligny center");
		panel1.add(atkMagt, "cell 3 3,growx,aligny center");
		// JLabel y JTextField de la vida
		vida.setForeground(Color.WHITE);
		panel1.add(vida, "cell 0 4");
		panel1.add(vidat, "cell 3 4,growx,aligny center");
		// JLabel y JTextField de la defensa fisica
		defFis.setForeground(Color.WHITE);
		panel1.add(defFis, "cell 0 5,alignx left,aligny center");
		panel1.add(defFist, "cell 3 5,growx,aligny center");
		// JLabel y JTextField de la defensa magica
		defMag.setForeground(Color.WHITE);
		panel1.add(defMag, "cell 0 6,alignx left,aligny center");
		panel1.add(defMagt, "cell 3 6,growx,aligny center");
		// JLabel, JTextField y JButton del arma
		nombreArma.setForeground(Color.WHITE);
		panel1.add(nombreArma, "cell 0 7");
		panel1.add(nombreArmat, "cell 3 7,growx,aligny center");
		panel1.add(armas, "cell 7 7,grow");
		// JLabel y JTextField del tipo de arma
		tipoArma.setForeground(Color.WHITE);
		panel1.add(tipoArma, "cell 0 8");
		panel1.add(tipoArmat, "cell 3 8,growx,aligny center");
		// JLabel, JTextField y JButton del casco
		casco.setForeground(Color.WHITE);
		panel1.add(casco, "cell 0 9");
		panel1.add(cascot, "cell 3 9,growx,aligny center");
		panel1.add(cascos, "cell 7 9,grow");
		// JLabel, JTextField y JButton de la pechera
		pechera.setForeground(Color.WHITE);
		panel1.add(pechera, "cell 0 10");
		panel1.add(pecherat, "cell 3 10,growx,aligny center");
		panel1.add(pecheras, "cell 7 10,grow");
		// Imagen de fondo de la ventana
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoCreacion.png")));
		fondo.setBounds(0, 0, 394, 471);
		panelFondo.add(fondo);
		getContentPane().add(panelFondo);
		// listener del boton de la ventana de armas
		armas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaArmas armas = new VentanaArmas(menu);
				armas.setVisible(true);
			}
		});
		// listener del boton de la ventana de cascos
		cascos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCascos cascos = new VentanaCascos(menu);
				cascos.setVisible(true);
			}
		});
		// listener del boton de la ventana de pecheras
		pecheras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPecheras pecheras = new VentanaPecheras(menu);
				pecheras.setVisible(true);
			}
		});
		// listener del boton de crear personajes
		crearPersonaje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Creando el personaje");
				Unidad unidad = new Unidad(nomt.getText(), dest.getText(), "humano",
						Integer.parseInt(atkFist.getText()), Integer.parseInt(atkMagt.getText()),
						Integer.parseInt(vidat.getText()), Integer.parseInt(defFist.getText()),
						Integer.parseInt(defMagt.getText()));
				try {
					if (tipoArmat.getText().contentEquals("espada") || tipoArmat.getText().contentEquals("arco")
							|| tipoArmat.getText().contentEquals("baston")) {
						miDB.guardarAliado(unidad, nombreArmat.getText(), tipoArmat.getText(), cascot.getText(),
								pecherat.getText());
						miDB.desconectar();
						JOptionPane.showMessageDialog(null, "Personaje creado con exito");
						logger.log(Level.INFO, "Personaje creado");
					} else {
						JOptionPane.showMessageDialog(null, "Tipo de arma incorrecta");
						logger.log(Level.WARNING, "Tipo de arma incorrecto");
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				logger.log(Level.INFO, "Volviendo a la ventana anterior");
				ventana.dispose();
			}
		});
	}

}
