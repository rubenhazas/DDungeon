package ui.user;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.miginfocom.swing.MigLayout;
import ui.main.VentanaMenu;
import unidades.Aliado;
import unidades.Unidad;

import java.awt.Color;

public class VentanaSeleccion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaSeleccion ventana = this;
	public Aliado a;
	public Unidad u;
	public JPanel panelFondo = new JPanel();
	public JPanel panel1 = new JPanel();
	public JButton volver = new JButton("Volver");
	public JLabel nombre = new JLabel("Nombre");
	public JButton buscarPersonaje = new JButton("Seleccionar personaje");
	public JButton pelear = new JButton("Pelear");
	public JList<String> listaPersonajes;
	public DefaultListModel<String> listModel = new DefaultListModel<String>();
	public JLabel fondo;
	private Logger logger = Logger.getLogger(VentanaSeleccion.class.getName());
	public VentanaSeleccion(VentanaMenu v) {
		logger.log(Level.INFO, "Creando la ventana de seleccion de personajes");
		VentanaMenu menu = v;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 400, 360);
		setResizable(false);
		panel1.setBackground(new Color(0, 0, 0, 0));
		panel1.setBounds(-3, 5, 401, 313);
		panel1.setLayout(new MigLayout("", "[122.00][136.00][75.00][]", "[][][210.00][13.00][]"));
		getContentPane().add(panelFondo);
		
		//ListModel en el que vamos a meter los personajes
		listModel = new DefaultListModel<String>();
		
		try {
			logger.log(Level.INFO, "Buscando personajes para el JList");
			listModel.addAll(menu.miDB.obtenerAliados());
		} catch (Exception e) {
		}
		panel1.add(volver, "cell 0 0,alignx left,aligny center");
		//listener para el boton de volver
		volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				logger.log(Level.INFO, "Volviendo a la ventana anterior");
				ventana.dispose();
			}
		});
		panelFondo.setLayout(null);
		//JLabel nombre
		nombre.setForeground(Color.WHITE);
		panel1.add(nombre, "cell 1 1");
		//JList con los personajes
		listaPersonajes = new JList<String>(listModel);
		panel1.add(listaPersonajes, "cell 1 2,grow");
		//JButton para buscar el personaje
		panel1.add(buscarPersonaje, "cell 0 4");
		//JButton para comenzar la pelea
		panel1.add(pelear, "cell 2 4");

		panelFondo.add(panel1);
		//Imagen de fondo de la ventana
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoSeleccion.png")));
		fondo.setBounds(-3, 0, 397, 331);
		panelFondo.add(fondo);
		//listener para el boton de seleccionar personaje
		buscarPersonaje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					a = menu.miDB.obtenerAliado(listModel.get(listaPersonajes.getSelectedIndex()));
					logger.log(Level.INFO, "Seleccionando el personaje");
					if (a != null) {
						logger.log(Level.INFO, "Personaje seleccionado con exito");
						JOptionPane.showMessageDialog(null, "Personaje seleccionado con exito");
					} else {
						logger.log(Level.INFO, "El personaje no existe");
						JOptionPane.showMessageDialog(null, "El personaje no existe");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//listener para el boton de pelear
		pelear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Comenzando el combate");
				logger.log(Level.INFO, "Seleccionando enemigo");
				try {
					int i = (int) (Math.random() * ((menu.miDB.obtenerEnemigos().size() - 1) + 1)) + 1;
					u = menu.miDB.obtenerUnidad(i);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				VentanaCombate ventanaCombate = new VentanaCombate(menu, a, u);
				ventanaCombate.setVisible(true);
				logger.log(Level.INFO, "Cerramos la ventana de seleccion de personaje");
				ventana.dispose();
			}
		});
	}

}
