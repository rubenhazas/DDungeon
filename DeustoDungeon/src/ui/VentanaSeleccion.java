package ui;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import datos.Aliado;
import datos.Unidad;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class VentanaSeleccion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaSeleccion ventana= this;
	public Aliado a;
	public Unidad u;
	public JPanel panelFondo = new JPanel();
	public JPanel panel1 = new JPanel();
	public JButton volver = new JButton("Volver");
	public JLabel nombre = new JLabel("Nombre");
	public JButton buscarPersonaje = new JButton("Seleccionar personaje");
	public JButton pelear = new JButton("Pelear");
	public JList<String> listaPersonajes;
	public DefaultListModel<String> listModel= new DefaultListModel<String>();
	public JLabel fondo;
	public  VentanaSeleccion(VentanaMenu v)  {
		
		VentanaMenu menu = v;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500,300,400,360);
		setResizable(false);
		panel1.setBackground(new Color(0, 0, 0,0));
		panel1.setBounds(-3, 5, 401, 313);
		
		
		
		panel1.setLayout(new MigLayout("", "[122.00][136.00][75.00][]", "[][][210.00][13.00][]"));
		getContentPane().add(panelFondo);
		
		listModel=new DefaultListModel<String>();
		try {
			listModel.addAll(menu.miDB.obtenerAliados());
		} catch (Exception e) {
			// TODO: handle exception
		}
		panel1.add(volver, "cell 0 0,alignx left,aligny center");
		volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				ventana.dispose();
			}
		});
			panelFondo.setLayout(null);
			nombre.setForeground(Color.WHITE);
		
			panel1.add(nombre, "cell 1 1");
		
		listaPersonajes = new JList<String>(listModel);
		panel1.add(listaPersonajes,"cell 1 2,grow");
		
		
		panel1.add(buscarPersonaje, "cell 0 4");
		
		panel1.add(pelear, "cell 2 4");
		
		panelFondo.add(panel1);
		
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoSeleccion.png")));
		fondo.setBounds(-3, 0, 397, 331);
		panelFondo.add(fondo);
		buscarPersonaje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
						try {
							a = menu.miDB.obtenerAliado(listModel.get(listaPersonajes.getSelectedIndex()));
							if(a!= null) {
								JOptionPane.showMessageDialog(null, "Personaje seleccionado con exito");
							}else {
								JOptionPane.showMessageDialog(null, "El personaje no existe");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
			}
		});
		
		pelear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = (int) (Math.random()*((6-1)+1))+1;
				
				try {
					u = menu.miDB.obtenerUnidad(i);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				VentanaCombate ventanaCombate = new VentanaCombate(menu,a,u);
				ventanaCombate.setVisible(true);
				ventana.dispose();
			}
		});
	}
	
	
}
