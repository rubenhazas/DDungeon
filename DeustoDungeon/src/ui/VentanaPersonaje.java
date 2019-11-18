package ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

import com.sun.java.util.jar.pack.Attribute.Layout;

import datos.Arma;
import datos.Casco;
import datos.Pechera;

public class VentanaPersonaje extends JFrame {

	public JLabel nom = new JLabel("Nombre");
	public JLabel des = new JLabel("Descripcion");
	public JLabel nivel = new JLabel("Nivel");
	public JLabel atkFis = new JLabel("Ataque Fisico");
	public JLabel atkMag = new JLabel("Ataque Magico");
	public JLabel vida = new JLabel("Vida");
	public JLabel defFis = new JLabel("Defensa Fisica");
	public JLabel defMag = new JLabel("Defensa Magica");
	public JLabel pasivaRacial = new JLabel("Pasiva Racial");
	
	public JTextField nomt = new JTextField("Nombre");
	public JTextField dest = new JTextField("Descripcion");
	public JTextField nivelt = new JTextField("Nivel");
	public JTextField atkFist = new JTextField("Ataque Fisico");
	public JTextField atkMagt = new JTextField("Vida");
	public JTextField defFist = new JTextField("Defensa Fisica");
	public JTextField defMagt = new JTextField("Defensa Magica");
	public JTextField pasivaRacialt = new JTextField("Pasiva Racial");
	
	private JPanel contentPane;
	
	public VentanaPersonaje(VentanaMenu v) {
		VentanaMenu menu= v;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 400, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBounds(20, 20, 300, 600);
		contentPane.setLayout(new GridLayout(9,2));
		contentPane.add(nom);
		contentPane.add(nomt);
		contentPane.add(des);
		contentPane.add(dest);
		contentPane.add(nivel);
		contentPane.add(nivelt);
		contentPane.add(atkFis);
		contentPane.add(atkFist);
		contentPane.add(atkMag);
		contentPane.add(atkMagt);
		contentPane.add(defFis);
		contentPane.add(defFist);
		contentPane.add(defMag);
		contentPane.add(defMagt);
		contentPane.add(nom);
		contentPane.add(nom);
		
	}
	
	
	

}
