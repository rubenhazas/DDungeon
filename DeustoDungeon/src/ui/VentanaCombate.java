package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import datos.Aliado;
import datos.Unidad;
import net.miginfocom.swing.MigLayout;


	
	
public class VentanaCombate extends JFrame {
	
	
	
	public VentanaCombate ventana= this;
	public Aliado aliado;
	public Unidad unidad;
	public VentanaCombate(VentanaMenu v, Aliado a, Unidad u) {
		VentanaMenu menu= v;
		aliado= a;
		unidad= u;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300,500,500);
		setResizable(false);
		getContentPane().setLayout(new MigLayout("", "[494px]", "[10px]"));
		JPanel panel1 = new JPanel();
		getContentPane().add(panel1, "cell 0 0,growx,aligny top");
		panel1.setLayout(new MigLayout("", "[]", "[]"));
		
		
	}
}
