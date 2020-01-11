package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datos.Aliado;
import datos.Unidad;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


	
	
public class VentanaCombate extends JFrame {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VentanaCombate ventana= this;
	public Aliado aliado;
	public Unidad unidad;
	public int turnosUnidad=0;
	
	public JPanel panel1 = new JPanel();
	public JButton volver = new JButton("Volver");
	public JLabel nombreAliado = new JLabel();
	public JLabel vidaAli = new JLabel("Vida");
	public JLabel vidaUni = new JLabel("Vida");
	public JButton ataquePrincipal = new JButton("Ataque principal");
	public JButton ataqueSecundario = new JButton("Ataque secundario");
	public JLabel vidaAliado = new JLabel();
	public JLabel vidaUnidad = new JLabel();
	public JLabel nombreUnidad = new JLabel();
	public VentanaMenu menu= new VentanaMenu();
	public HiloCombate miHilo= new HiloCombate();
	
	public JLabel fondo;
	public JPanel panelFondo;
	
	public VentanaCombate(VentanaMenu v, Aliado a, Unidad u) {
		menu= v;
		aliado= a;
		unidad= u;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300,500,300);
		setResizable(false);
		panelFondo= new JPanel();
		fondo= new JLabel();
		panelFondo.setBackground(new Color(0,0,0,0));
		panelFondo.setLayout(null);
		panel1.setBounds(0, 0, 494, 271);
		panel1.setBackground(new Color(0,0,0,0));
		panelFondo.add(panel1);
		
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoCombate1.png")));
		fondo.setBounds(0, 0, 494, 271);
		
		
		panelFondo.add(fondo);
		
		getContentPane().add(panelFondo);
		panel1.setLayout(new MigLayout("", "[83.00px][][123px][][]", "[23px][][][][][][][50.00][][]"));
		
		panel1.add(volver, "cell 0 1,alignx center");
		nombreAliado.setForeground(Color.WHITE);
		
		nombreAliado.setText(aliado.getNom());
		
		panel1.add(nombreAliado, "cell 2 5");
		nombreUnidad.setForeground(Color.WHITE);
		nombreUnidad.setText(unidad.getNom()+" el "+unidad.getRaza());
		panel1.add(nombreUnidad, "cell 4 5");
		vidaAli.setForeground(Color.WHITE);
		
		panel1.add(vidaAli, "cell 1 6");
		vidaAliado.setForeground(Color.WHITE);
		
		vidaAliado.setText(""+(aliado.getVida()+aliado.getCasco().getBuffVida()+aliado.getPechera().getBuffVida()));
		panel1.add(vidaAliado,"cell 2 6,alignx left,aligny center");
		vidaUni.setForeground(Color.WHITE);
		
		panel1.add(vidaUni, "cell 3 6");
		vidaUnidad.setForeground(Color.WHITE);
		vidaUnidad.setText(""+unidad.getVida());
		panel1.add(vidaUnidad, "cell 4 6");
		
		panel1.add(ataquePrincipal, "cell 2 8");
		
		panel1.add(ataqueSecundario, "cell 4 8");
		ataqueSecundario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					int da�oExtra=0;
					int da�o=0;
					int da�oTotal=0;
					int vidaTemporal = Integer.parseInt(vidaUnidad.getText());
					if(aliado.getArma().getTipo().contentEquals("espada")||aliado.getArma().getTipo().contentEquals("arco")) {
						da�oExtra = aliado.getAtkFis();
						da�o= (da�oExtra + aliado.ataque2(aliado.getArma().getBuffAtkFis()));
						da�oTotal= da�o- unidad.getDefFis();
						vidaTemporal= vidaTemporal -(da�oTotal);
						
					}else if(aliado.getArma().getTipo().contentEquals("baston")) {
						da�oExtra = aliado.getAtkMag();
						da�o= (da�oExtra + aliado.ataque2(aliado.getArma().getBuffAtkMag()));
						da�oTotal = da�o- unidad.getDefMag();
						vidaTemporal= vidaTemporal -(da�oTotal);
						
					}
				JOptionPane.showMessageDialog(null, "Tu "+aliado.getArma().getTipo()+" ha hecho: "+ da�o+" puntos de da�o" );
				if(vidaTemporal<=0) {
					JOptionPane.showMessageDialog(null, "Has ganado");
					menu.setVisible(true);
					ventana.dispose();
				}else {
					vidaUnidad.setText(""+vidaTemporal);
					miHilo.run();
					}
				
				}
			
		});
		
		ataquePrincipal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					int da�oExtra=0;
					int da�o=0;
					int vidaTemporal = Integer.parseInt(vidaUnidad.getText());
					int da�oTotal=0;
					if(aliado.getArma().getTipo().contentEquals("espada")||aliado.getArma().getTipo().contentEquals("arco") ) {
						da�oExtra =aliado.getAtkFis() ;
						da�o= (da�oExtra + aliado.ataque1(aliado.getArma().getBuffAtkFis()));
						da�oTotal= da�o - unidad.getDefFis();
						vidaTemporal= vidaTemporal - (da�oTotal);
					}else if(aliado.getArma().getTipo().contentEquals("baston") ) {
						da�oExtra = aliado.getAtkMag();
						da�o= (da�oExtra + aliado.ataque1(aliado.getArma().getBuffAtkMag()));
						da�oTotal =da�o -unidad.getDefMag();
						vidaTemporal= vidaTemporal - (da�oTotal);
					}
				JOptionPane.showMessageDialog(null, "Tu "+aliado.getArma().getTipo()+" ha hecho: "+ da�oTotal+" puntos de da�o" );
				if(vidaTemporal<=0) {
					JOptionPane.showMessageDialog(null, "Has ganado");
					menu.setVisible(true);
					ventana.dispose();
				}else {
					vidaUnidad.setText(""+vidaTemporal);
					miHilo.run();
					}
			}
		});
		
		
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				ventana.dispose();
			}
		});
	
	}
	
	
	public class HiloCombate extends Thread {

		public void run() {
			
				if(turnosUnidad==3) {
						int da�o=0;
						int vidaTemporal = Integer.parseInt(vidaAliado.getText());
						int da�oTotal=0;
						if(unidad.getRaza().contentEquals("orco")|| unidad.getRaza().contentEquals("esqueleto")) {
							da�o = unidad.ataque2(unidad.getAtkFis());
							da�oTotal = da�o -(aliado.getDefFis()+aliado.getCasco().getBuffDefFis()+aliado.getPechera().getBuffDefFis());
							if(da�oTotal>=0) {
								vidaTemporal= vidaTemporal - (da�oTotal);
								JOptionPane.showMessageDialog(null, unidad.getNom()+ " te ha hecho: "+ da�oTotal+" puntos de da�o" );
							}else {
								JOptionPane.showMessageDialog(null, unidad.getNom()+ " te ha hecho: 0 puntos de da�o" );
							}
							
						}else if (unidad.getRaza().contentEquals("nigromante")) {
							da�o = unidad.ataque2(unidad.getAtkMag());
							da�oTotal = da�o -(aliado.getDefFis()+aliado.getCasco().getBuffDefFis()+aliado.getPechera().getBuffDefFis());
							if(da�oTotal>=0) {
								vidaTemporal= vidaTemporal - (da�oTotal);
								JOptionPane.showMessageDialog(null, unidad.getNom()+ " te ha hecho: "+ da�oTotal+" puntos de da�o" );
							}else {
								JOptionPane.showMessageDialog(null, unidad.getNom()+ " te ha hecho: 0 puntos de da�o" );
							}
						}
						if(vidaTemporal<=0) {
							JOptionPane.showMessageDialog(null, "Has perdido");
							menu.setVisible(true);
							ventana.dispose();
						}else {
							vidaAliado.setText(""+vidaTemporal);
							turnosUnidad=0;
						}
					
					}else {
						int da�o=0;
						int vidaTemporal = Integer.parseInt(vidaAliado.getText());
						int da�oTotal=0;
						if(unidad.getRaza().contentEquals("orco")||unidad.getRaza().contentEquals("esqueleto")) {
							
							da�o = unidad.ataque1(unidad.getAtkFis());
							da�oTotal = da�o -(aliado.getDefFis()+aliado.getCasco().getBuffDefFis()+aliado.getPechera().getBuffDefFis());
							if(da�oTotal>=0) {
								vidaTemporal= vidaTemporal - (da�oTotal);
								JOptionPane.showMessageDialog(null, unidad.getNom()+ " te ha hecho: "+ da�oTotal+" puntos de da�o" );
							}else {
								JOptionPane.showMessageDialog(null, unidad.getNom()+ " te ha hecho: 0 puntos de da�o" );
							}
						}else if (unidad.getRaza().contentEquals("nigromante")) {
							da�o = unidad.ataque1(unidad.getAtkMag())-(aliado.getDefMag()+aliado.getCasco().getBuffDefMag()+aliado.getPechera().getBuffDefMag());
							da�oTotal = da�o -(aliado.getDefFis()+aliado.getCasco().getBuffDefFis()+ aliado.getPechera().getBuffDefFis());
							if(da�oTotal>=0) {
								vidaTemporal= vidaTemporal - (da�oTotal);
								JOptionPane.showMessageDialog(null, unidad.getNom()+ " te ha hecho: "+ da�oTotal+" puntos de da�o" );
							}else {
								JOptionPane.showMessageDialog(null, unidad.getNom()+ " te ha hecho: 0 puntos de da�o" );
							}
						}
						if(vidaTemporal<=0) {
							JOptionPane.showMessageDialog(null, "Has perdido");
							menu.setVisible(true);
							ventana.dispose();
						}else {
							vidaAliado.setText(""+vidaTemporal);
							turnosUnidad= turnosUnidad+1;
						}
					
					}
			}
		
	}
	
	
}
