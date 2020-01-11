package ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;



import datos.Unidad;
import net.miginfocom.swing.MigLayout;

public class VentanaPersonaje extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public JButton pecheras= new JButton("Pecheras");
	
	public JButton crearPersonaje= new JButton("Crear Personaje");
	public JButton volver = new JButton("Volver");
	public JLabel fondo;
	public JPanel panelFondo;
	public VentanaPersonaje ventana= this;
	
	public VentanaPersonaje(VentanaMenu v) {
		VentanaMenu menu= v;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300, 400, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 11, 384, 413);
		panel1.setBackground(new Color(0,0,0,0));
		panelFondo= new JPanel();
		panelFondo.setLayout(null);
		panelFondo.add(panel1);
		
		panel1.setLayout(new MigLayout("", "[38px][7px][7px][135.00px][18.00px][5.00px][13px][][5px]", "[30.00px][30.00px][30.00][30.00][30.00][30.00][30.00][30.00][30.00][30.00][30.00][]"));
		nom.setForeground(Color.WHITE);
		panel1.add(nom, "cell 0 0,alignx left,aligny center");
		panel1.add(nomt, "cell 3 0,growx,aligny center");
		des.setForeground(Color.WHITE);
		panel1.add(des, "cell 0 1,alignx left,aligny center");
		panel1.add(dest, "cell 3 1,growx,aligny center");
		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 423, 384, 48);
		panel2.setBackground(new Color(0,0,0,0));
		panel2.add(crearPersonaje);
		panel2.add(volver);
		
		panelFondo.add(panel2);
		atkFis.setForeground(Color.WHITE);
		
		panel1.add(atkFis, "cell 0 2,alignx left,aligny center");
		panel1.add(atkFist, "cell 3 2,growx,aligny center");
		atkMag.setForeground(Color.WHITE);
		panel1.add(atkMag, "cell 0 3,alignx left,aligny center");
		panel1.add(atkMagt, "cell 3 3,growx,aligny center");
		vida.setForeground(Color.WHITE);
		panel1.add(vida, "cell 0 4");
		panel1.add(vidat, "cell 3 4,growx,aligny center");
		defFis.setForeground(Color.WHITE);
		panel1.add(defFis, "cell 0 5,alignx left,aligny center");
		panel1.add(defFist, "cell 3 5,growx,aligny center");
		defMag.setForeground(Color.WHITE);
		panel1.add(defMag, "cell 0 6,alignx left,aligny center");
		panel1.add(defMagt, "cell 3 6,growx,aligny center");
		nombreArma.setForeground(Color.WHITE);
		panel1.add(nombreArma, "cell 0 7");
		panel1.add(nombreArmat, "cell 3 7,growx,aligny center");
		
		panel1.add(armas, "cell 7 7,grow");
		tipoArma.setForeground(Color.WHITE);
		panel1.add(tipoArma, "cell 0 8");
		panel1.add(tipoArmat, "cell 3 8,growx,aligny center");
		casco.setForeground(Color.WHITE);
		panel1.add(casco, "cell 0 9");
		panel1.add(cascot, "cell 3 9,growx,aligny center");
		
		panel1.add(cascos, "cell 7 9,grow");
		pechera.setForeground(Color.WHITE);
		panel1.add(pechera, "cell 0 10");
		panel1.add(pecherat, "cell 3 10,growx,aligny center");
		
		panel1.add(pecheras, "cell 7 10,grow");
		
		
		
		
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoCreacion.png")));
		fondo.setBounds(0, 0, 394, 471);
		panelFondo.add(fondo);
		getContentPane().add(panelFondo);
		
		
		
		
		armas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaArmas armas = new VentanaArmas(menu);
				armas.setVisible(true);
			}
		});
		
		cascos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCascos cascos= new VentanaCascos(menu);
				cascos.setVisible(true);
			}
		});
		pecheras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPecheras pecheras= new VentanaPecheras(menu);
				pecheras.setVisible(true);
			}
		});
		crearPersonaje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Unidad unidad= new Unidad(nomt.getText(),dest.getText(),"humano", Integer.parseInt(atkFist.getText()),Integer.parseInt(atkMagt.getText())
						,Integer.parseInt(vidat.getText()),Integer.parseInt(defFist.getText()),Integer.parseInt(defMagt.getText())); 
				try {
					if(tipoArmat.getText().contentEquals("espada") || tipoArmat.getText().contentEquals("arco")||tipoArmat.getText().contentEquals("baston") ) {
						menu.miDB.guardarAliado(unidad, nombreArmat.getText(), tipoArmat.getText(), cascot.getText(), pecherat.getText());
						JOptionPane.showMessageDialog(null, "Personaje creado con exito");
					}else {
						 JOptionPane.showMessageDialog(null, "Tipo de arma incorrecta");
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
				ventana.dispose();
			}
		});
	}
	

	
	
	

}
