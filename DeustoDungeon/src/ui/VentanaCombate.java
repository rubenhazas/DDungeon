package ui;

import java.awt.BorderLayout;

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
	public int token=0;
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
	
	
	public VentanaCombate(VentanaMenu v, Aliado a, Unidad u) {
		menu= v;
		aliado= a;
		unidad= u;
		miHilo.run();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300,500,350);
		setResizable(false);
		
		getContentPane().add(panel1,BorderLayout.CENTER);
		panel1.setLayout(new MigLayout("", "[83.00px][][123px][][]", "[23px][][][][][][][][][][][][][][][]"));
		
		panel1.add(volver, "cell 0 1,alignx center");
		
		nombreAliado.setText(aliado.getNom());
		
		panel1.add(nombreAliado, "cell 2 6");
		
		panel1.add(vidaAli, "cell 1 7");
		
		panel1.add(vidaUni, "cell 3 7");
		
		panel1.add(ataquePrincipal, "cell 2 11");
		
		panel1.add(ataqueSecundario, "cell 4 11");
		
		vidaAliado.setText(""+(aliado.getVida()+aliado.getCasco().getBuffVida()));
		panel1.add(vidaAliado,"cell 2 7,alignx left,aligny center");
		vidaUnidad.setText(""+unidad.getVida());
		panel1.add(vidaUnidad, "cell 4 7");
		nombreUnidad.setText(unidad.getNom()+"el "+unidad.getRaza());
		panel1.add(nombreUnidad, "cell 4 6");
		
		
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				ventana.dispose();
			}
		});
		
		ataquePrincipal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(token==0) {
					int dañoExtra=0;
					int daño=0;
					int vidaTemporal = Integer.parseInt(vidaUnidad.getText());
					
					if(aliado.getArma().getTipo()=="espada"||aliado.getArma().getTipo()=="arco" ) {
						dañoExtra = aliado.getArma().getBuffAtkFis();
						daño= dañoExtra + aliado.ataque1(aliado.getAtkFis());
						vidaTemporal= vidaTemporal - (daño-unidad.getDefFis());
					}else if(aliado.getArma().getTipo()=="baston" ) {
						dañoExtra = aliado.getArma().getBuffAtkMag();
						daño= dañoExtra + aliado.ataque1(aliado.getAtkMag());
						vidaTemporal= vidaTemporal - (daño-unidad.getDefMag());
					}
				JOptionPane.showMessageDialog(null, "Tu "+aliado.getArma().getTipo()+" ha hecho: "+ daño+" puntos de daño" );
				if(vidaTemporal<=0) {
					JOptionPane.showMessageDialog(null, "Has ganado");
					menu.setVisible(true);
					ventana.dispose();
				}else {
					vidaUnidad.setText(""+vidaTemporal);
					token=1;
					}
				}
			}
		});
		ataqueSecundario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(token==0) {
					int dañoExtra=0;
					int daño=0;
					int vidaTemporal = Integer.parseInt(vidaUnidad.getText());
					if(aliado.getArma().getTipo()=="espada"||aliado.getArma().getTipo()=="arco" ) {
						dañoExtra = aliado.getArma().getBuffAtkFis();
						daño= dañoExtra + aliado.ataque1(aliado.getAtkFis());
						vidaTemporal= vidaTemporal - (daño-unidad.getDefFis());
					}else if(aliado.getArma().getTipo()=="baston" ) {
						dañoExtra = aliado.getArma().getBuffAtkMag();
						daño= dañoExtra + aliado.ataque1(aliado.getAtkMag());
						vidaTemporal= vidaTemporal - (daño-unidad.getDefMag());
					}
				JOptionPane.showMessageDialog(null, "Tu "+aliado.getArma().getTipo()+" ha hecho: "+ daño+" puntos de daño" );
				if(vidaTemporal<=0) {
					JOptionPane.showMessageDialog(null, "Has ganado");
					menu.setVisible(true);
					ventana.dispose();
				}else {
					vidaUnidad.setText(""+vidaTemporal);
					token=1;
					}
				}
			}
		});
	
	}
	
	
	public class HiloCombate extends Thread {

		private boolean continuar=true;
		
		public void detener() {
			continuar=false;
		}
		
		public void run() {
			while(continuar) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(token==1) {
					if(turnosUnidad==3) {
						int daño=0;
						int vidaTemporal = Integer.parseInt(vidaAliado.getText());
						
						if(unidad.getRaza()=="orco" || unidad.getRaza()=="esqueleto") {
							daño = unidad.ataque2(unidad.getAtkFis());
							vidaTemporal= vidaTemporal - (daño-(aliado.getDefFis()+aliado.getCasco().getBuffDefFis()+aliado.getPechera().getBuffDefFis()));
						}else if (unidad.getRaza()=="nigromante") {
							daño = unidad.ataque2(unidad.getAtkMag());
							vidaTemporal= vidaTemporal - (daño-(aliado.getDefMag()+aliado.getCasco().getBuffDefMag()+aliado.getPechera().getBuffDefMag()));
						}
						if(vidaTemporal<=0) {
							JOptionPane.showMessageDialog(null, "Has perdido");
							menu.setVisible(true);
							ventana.dispose();
							miHilo.detener();
						}else {
							vidaAliado.setText(""+vidaTemporal);
							token=0;
							turnosUnidad=0;
							
						}
					JOptionPane.showMessageDialog(null, unidad.getNom()+ " te ha hecho: "+ daño+" puntos de daño" );
					}else {
						int daño=0;
						int vidaTemporal = Integer.parseInt(vidaAliado.getText());
						if(unidad.getRaza()=="orco" || unidad.getRaza()=="esqueleto") {
							daño = unidad.ataque1(unidad.getAtkFis());
							vidaTemporal= vidaTemporal - (daño-(aliado.getDefFis()+aliado.getCasco().getBuffDefFis()+aliado.getPechera().getBuffDefFis()));
						}else if (unidad.getRaza()=="nigromante") {
							daño = unidad.ataque1(unidad.getAtkMag());
							vidaTemporal= vidaTemporal - (daño-(aliado.getDefMag()+aliado.getCasco().getBuffDefMag()+aliado.getPechera().getBuffDefMag()));
						}
						if(vidaTemporal<=0) {
							JOptionPane.showMessageDialog(null, "Has perdido");
							menu.setVisible(true);
							ventana.dispose();
							miHilo.detener();
						}else {
							vidaAliado.setText(""+vidaTemporal);
							token=0;
							turnosUnidad= turnosUnidad+1;
						}
					JOptionPane.showMessageDialog(null, unidad.getNom()+ " te ha hecho: "+ daño+" puntos de daño" );
					}
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
