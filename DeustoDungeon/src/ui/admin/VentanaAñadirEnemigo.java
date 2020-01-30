package ui.admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gestorDB.GestorDB;
import net.miginfocom.swing.MigLayout;
import unidades.Unidad;

public class VentanaAñadirEnemigo extends JFrame {

	private static final long serialVersionUID = 1L;

	public JLabel nom = new JLabel("Nombre");
	public JLabel des = new JLabel("Descripcion");
	public JLabel atkFis = new JLabel("Ataque Fisico");
	public JLabel atkMag = new JLabel("Ataque Magico");
	public JLabel vida = new JLabel("Vida");
	public JLabel defFis = new JLabel("Defensa Fisica");
	public JLabel defMag = new JLabel("Defensa Magica");
	public JLabel raza = new JLabel("Raza");

	public JTextField nomt = new JTextField();
	public JTextField dest = new JTextField();
	public JTextField atkFist = new JTextField();
	public JTextField atkMagt = new JTextField();
	public JTextField vidat = new JTextField();
	public JTextField defFist = new JTextField();
	public JTextField defMagt = new JTextField();
	public JTextField razat = new JTextField();

	public JButton crearPersonaje = new JButton("Crear Personaje");
	public JButton volver = new JButton("Volver");
	public JLabel fondo;
	public JPanel panelFondo;
	public VentanaAñadirEnemigo window = this;
	public VentanaAdmin ventanaAdmin;
	public GestorDB miDB;
	private Logger logger = Logger.getLogger(VentanaAñadirEnemigo.class.getName());

	public VentanaAñadirEnemigo(VentanaAdmin v, int i) {
		logger.log(Level.INFO, "Creando ventana de añadir enemigo");
		ventanaAdmin = v;
		try {
			miDB = new GestorDB();
			miDB.conectar();
		} catch (Exception e) {
			// TODO: handle exception
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 400, 411);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 11, 384, 301);
		panel1.setBackground(new Color(0, 0, 0, 0));
		panelFondo = new JPanel();
		panelFondo.setLayout(null);
		panelFondo.add(panel1);
		// añadir todos los elementos al panel
		panel1.setLayout(new MigLayout("", "[98.00px][135.00px][51.00px][87.00px]",
				"[30.00px][30.00px][30.00][30.00][30.00][30.00][30.00][30.00]"));
		// JLabel y JTextField del nombre
		nom.setForeground(Color.WHITE);
		panel1.add(nom, "cell 0 0,alignx left,aligny center");
		panel1.add(nomt, "cell 1 0,growx,aligny center");
		// JLabel y JTextField de la descripcion
		des.setForeground(Color.WHITE);
		panel1.add(des, "cell 0 1,alignx left,aligny center");
		panel1.add(dest, "cell 1 1,growx,aligny center");

		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 323, 384, 48);
		panel2.setBackground(new Color(0, 0, 0, 0));
		panel2.add(crearPersonaje);
		panel2.add(volver);

		panelFondo.add(panel2);
		// JLabel y JTextField del ataque
		atkFis.setForeground(Color.WHITE);
		panel1.add(atkFis, "cell 0 2,alignx left,aligny center");
		panel1.add(atkFist, "cell 1 2,growx,aligny center");
		// JLabel y JTextField del ataque magico
		atkMag.setForeground(Color.WHITE);
		panel1.add(atkMag, "cell 0 3,alignx left,aligny center");
		panel1.add(atkMagt, "cell 1 3,growx,aligny center");
		// JLabel y JTextField de la vida
		vida.setForeground(Color.WHITE);
		panel1.add(vida, "cell 0 4");
		panel1.add(vidat, "cell 1 4,growx,aligny center");
		// JLabel y JTextField de la defensa fisica
		defFis.setForeground(Color.WHITE);
		panel1.add(defFis, "cell 0 5,alignx left,aligny center");
		panel1.add(defFist, "cell 1 5,growx,aligny center");
		// JLabel y JTextField de la defensa magica
		defMag.setForeground(Color.WHITE);
		panel1.add(defMag, "cell 0 6,alignx left,aligny center");
		panel1.add(defMagt, "cell 1 6,growx,aligny center");
		raza.setForeground(Color.WHITE);
		// JLabel y JTextField de la raza
		raza.setForeground(Color.WHITE);
		panel1.add(raza, "cell 0 7");
		panel1.add(razat, "cell 1 7,growx");

		// Imagen de fondo de la ventana
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoCreacion.png")));
		fondo.setBounds(0, 0, 394, 382);
		panelFondo.add(fondo);
		getContentPane().add(panelFondo);

		// listener del boton de crear enemigos
		crearPersonaje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Creando el enemigo");

				Unidad unidad = new Unidad(nomt.getText(), razat.getText(), dest.getText(),
						Integer.parseInt(atkFist.getText()), Integer.parseInt(atkMagt.getText()),
						Integer.parseInt(vidat.getText()), Integer.parseInt(defFist.getText()),
						Integer.parseInt(defMagt.getText()));

				try {
					// System.out.println(i);
					int a = i + 1;
					miDB.guardarUnidad(unidad, a);

					JOptionPane.showMessageDialog(null, "Enemigo creado con exito");
					logger.log(Level.INFO, "Enemigo creado");
				} catch (Exception e2) {
				}
			}
		});
		// listener del boton de volver
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin admin = new VentanaAdmin(v.loginWindow);
				admin.setVisible(true);
				window.dispose();
				try {
					miDB.desconectar();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
	}

}
