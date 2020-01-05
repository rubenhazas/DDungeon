package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class VentanaSeleccion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaSeleccion ventana= this;
	public Aliado a;
	public Unidad u;
	public  VentanaSeleccion(VentanaMenu v) {
		
		VentanaMenu menu = v;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300,400,170);
		setResizable(false);
		
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new MigLayout("", "[11.00][106.00][55.00][79.00][75.00]", "[][][][13.00][]"));
		getContentPane().add(panel1);
		
		JButton volver = new JButton("Volver");
		panel1.add(volver, "cell 1 0,alignx left,aligny center");
		volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				ventana.dispose();
			}
		});
		JLabel nombre = new JLabel("Nombre");
		panel1.add(nombre, "cell 2 1");
		JTextField nombret = new JTextField();
		panel1.add(nombret, "cell 3 1,growx,aligny center");
		
		JButton buscarPersonaje = new JButton("Buscar Personaje");
		panel1.add(buscarPersonaje, "cell 1 4");
		JButton pelear = new JButton("Pelear");
		panel1.add(pelear, "cell 4 4");
		
		buscarPersonaje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
						try {
							a = menu.miDB.obtenerAliado(nombret.getText());
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
