package ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;


import datos.Arma;
import datos.Casco;
import datos.Pechera;

public class VentanaPersonaje extends JFrame {

	public JLabel nom = new JLabel("Nombre");
	public JLabel des = new JLabel("Descripcion");
	public JLabel atkFis = new JLabel("Ataque Fisico");
	public JLabel atkMag = new JLabel("Ataque Magico");
	public JLabel vida = new JLabel("Vida");
	public JLabel defFis = new JLabel("Defensa Fisica");
	public JLabel defMag = new JLabel("Defensa Magica");
	
	public JTextField nomt = new JTextField();
	public JTextField dest = new JTextField();
	public JTextField atkFist = new JTextField();
	public JTextField atkMagt = new JTextField();
	public JTextField defFist = new JTextField();
	public JTextField defMagt = new JTextField();
	
	public JButton crearPersonaje= new JButton("Crear Personaje");
	public JButton volver = new JButton("Volver");
	
	
	
	public VentanaPersonaje(VentanaMenu v) {
		VentanaMenu menu= v;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 400, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		JPanel panel1 = new JPanel();
		panel1.add(nom);
		panel1.add(nomt);
		panel1.add(des);
		panel1.add(dest);
		panel1.add(atkFis);
		panel1.add(atkFist);
		panel1.add(atkMag);
		panel1.add(atkMagt);
		panel1.add(defFis);
		panel1.add(defFist);
		panel1.add(defMag);
		panel1.add(defMagt);
		JPanel panel2 = new JPanel();
		panel2.add(crearPersonaje);
		panel2.add(volver);
		
		add(panel1,BorderLayout.CENTER);
		add(panel2,BorderLayout.SOUTH);
	}
	
	
	

}
