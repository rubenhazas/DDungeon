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
	public JLabel nivel = new JLabel("Nivel");
	public JLabel atkFis = new JLabel("Ataque Fisico");
	public JLabel atkMag = new JLabel("Ataque Magico");
	public JLabel vida = new JLabel("Vida");
	public JLabel defFis = new JLabel("Defensa Fisica");
	public JLabel defMag = new JLabel("Defensa Magica");
	public JLabel pasivaRacial = new JLabel("Pasiva Racial");
	
	private JPanel contentPane;
	
	public VentanaPersonaje(VentanaMenu v) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 400, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
	}
	
	
	

}
