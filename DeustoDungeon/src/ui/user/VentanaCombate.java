package ui.user;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import ui.main.VentanaMenu;
import unidades.Aliado;
import unidades.Unidad;

import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class VentanaCombate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VentanaCombate ventana = this;
	public Aliado aliado;
	public Unidad unidad;
	public int turnosUnidad = 0;

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
	public VentanaMenu menu;
	public HiloCombate miHilo = new HiloCombate();
	public JLabel fondo;
	public JPanel panelFondo;
	private Logger logger = Logger.getLogger(VentanaCombate.class.getName());

	public VentanaCombate(VentanaMenu v, Aliado a, Unidad u) {
		logger.log(Level.INFO, "Creando la ventana de combate");
		menu = v;
		aliado = a;
		unidad = u;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 500, 300);
		setResizable(false);
		panelFondo = new JPanel();
		fondo = new JLabel();
		panelFondo.setLayout(null);
		panel1.setBounds(0, 0, 494, 271);
		panel1.setBackground(new Color(0,0,0,0));
		panelFondo.add(panel1);
		getContentPane().add(panelFondo);
		panel1.setLayout(new MigLayout("", "[83.00px][][123px][][]", "[23px][][][][][][][50.00][][]"));
		//boton para volver a la ventana anterior
		panel1.add(volver, "cell 0 1,alignx center");
		//JLabel del nombre del aliado
		nombreAliado.setForeground(Color.BLACK);
		nombreAliado.setText(aliado.getNom());
		panel1.add(nombreAliado, "cell 2 5");
		//JLabel del nombre del enemigo
		nombreUnidad.setForeground(Color.BLACK);
		nombreUnidad.setText(unidad.getNom() + " el " + unidad.getRaza());
		panel1.add(nombreUnidad, "cell 4 5");
		//JLabel de la vida del aliado
		vidaAli.setForeground(Color.BLACK);
		panel1.add(vidaAli, "cell 1 6");
		//JLabel con la vida del aliado
		vidaAliado.setBackground(new Color(0,0,0,0));
		vidaAliado.setForeground(Color.BLACK);
		vidaAliado.setText("" + (aliado.getVida() + aliado.getCasco().getBuffVida() + aliado.getPechera().getBuffVida()));
		panel1.add(vidaAliado, "cell 2 6,alignx left,aligny center");
		//JLabel de la vida del enemigo
		vidaUni.setForeground(Color.BLACK);
		panel1.add(vidaUni, "cell 3 6");
		//JLabel con la vida del enemigo
		vidaUnidad.setBackground(new Color(0,0,0,0));
		vidaUnidad.setForeground(Color.BLACK);
		vidaUnidad.setText("" + unidad.getVida());
		vidaUnidad.setBackground(new Color(0, 0, 0, 0));
		panel1.add(vidaUnidad, "cell 4 6,alignx left,aligny center");
		//botones de ataque del jugador
		panel1.add(ataquePrincipal, "cell 2 8");
		panel1.add(ataqueSecundario, "cell 4 8");
		//action listener del ataque secundario del jugador
		ataqueSecundario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//calculo del daño que hace el ataque secundario del jugador
				int dañoExtra = 0;
				int daño = 0;
				int dañoTotal = 0;
				int vidaTemporal = Integer.parseInt(vidaUnidad.getText());
				if (aliado.getArma().getTipo().contentEquals("espada")
						|| aliado.getArma().getTipo().contentEquals("arco")) {
					dañoExtra = aliado.getAtkFis();
					daño = (dañoExtra + aliado.ataque2(aliado.getArma().getBuffAtkFis()));
					dañoTotal = daño - unidad.getDefFis();
					vidaTemporal = vidaTemporal - (dañoTotal);

				} else if (aliado.getArma().getTipo().contentEquals("baston")) {
					dañoExtra = aliado.getAtkMag();
					daño = (dañoExtra + aliado.ataque2(aliado.getArma().getBuffAtkMag()));
					dañoTotal = daño - unidad.getDefMag();
					vidaTemporal = vidaTemporal - (dañoTotal);

				}
				logger.log(Level.INFO, "Ataque del jugador");
				JOptionPane.showMessageDialog(null,
						"Tu " + aliado.getArma().getTipo() + " ha hecho: " + daño + " puntos de daño");
				if (vidaTemporal <= 0) {
					JOptionPane.showMessageDialog(null, "Has ganado");
					menu.setVisible(true);
					logger.log(Level.INFO, "Se ha acabado el combate porque has ganado");
					ventana.dispose();
				} else {
					vidaUnidad.setText("" + vidaTemporal);
					logger.log(Level.INFO, "Turno del enemigo");
					miHilo.run();

				}

			}

		});
		//listener del ataque principal del jugador
		ataquePrincipal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//calculo del daño que hace el ataque principal del jugador
				int dañoExtra = 0;
				int daño = 0;
				int vidaTemporal = Integer.parseInt(vidaUnidad.getText());
				int dañoTotal = 0;
				if (aliado.getArma().getTipo().contentEquals("espada")
						|| aliado.getArma().getTipo().contentEquals("arco")) {
					dañoExtra = aliado.getAtkFis();
					daño = (dañoExtra + aliado.ataque1(aliado.getArma().getBuffAtkFis()));
					dañoTotal = daño - unidad.getDefFis();
					vidaTemporal = vidaTemporal - (dañoTotal);
				} else if (aliado.getArma().getTipo().contentEquals("baston")) {
					dañoExtra = aliado.getAtkMag();
					daño = (dañoExtra + aliado.ataque1(aliado.getArma().getBuffAtkMag()));
					dañoTotal = daño - unidad.getDefMag();
					vidaTemporal = vidaTemporal - (dañoTotal);
				}
				logger.log(Level.INFO, "Ataque del jugador");
				JOptionPane.showMessageDialog(null,
						"Tu " + aliado.getArma().getTipo() + " ha hecho: " + dañoTotal + " puntos de daño");
				if (vidaTemporal <= 0) {
					JOptionPane.showMessageDialog(null, "Has ganado");
					menu.setVisible(true);
					logger.log(Level.INFO, "Se ha acabado el combate porque has ganado");
					ventana.dispose();
				} else {
					vidaUnidad.setText("" + vidaTemporal);
					logger.log(Level.INFO, "Turno del enemigo");
					miHilo.run();
				}
			}
		});

		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				logger.log(Level.INFO, "Volviendo a la ventana anterior");
				ventana.dispose();
			}
		});

	}

	public class HiloCombate extends Thread {

		public void run() {
			if (turnosUnidad == 3) {
				//calculo del daño que hace el ataque secyndario del enemigo
				int daño = 0;
				int vidaTemporal = Integer.parseInt(vidaAliado.getText());
				int dañoTotal = 0;
				if (unidad.getRaza().contentEquals("orco") || unidad.getRaza().contentEquals("esqueleto")) {
					daño = unidad.ataque2(unidad.getAtkFis());
					dañoTotal = daño - (aliado.getDefFis() + aliado.getCasco().getBuffDefFis()
							+ aliado.getPechera().getBuffDefFis());
					if (dañoTotal >= 0) {
						vidaTemporal = vidaTemporal - (dañoTotal);
						JOptionPane.showMessageDialog(null,
								unidad.getNom() + " te ha hecho: " + dañoTotal + " puntos de daño");
					} else {
						JOptionPane.showMessageDialog(null, unidad.getNom() + " te ha hecho: 0 puntos de daño");
					}

				} else if (unidad.getRaza().contentEquals("nigromante")) {
					daño = unidad.ataque2(unidad.getAtkMag());
					dañoTotal = daño -  - (aliado.getDefMag() + aliado.getCasco().getBuffDefMag()
							+ aliado.getPechera().getBuffDefMag());
					if (dañoTotal >= 0) {
						vidaTemporal = vidaTemporal - (dañoTotal);
						JOptionPane.showMessageDialog(null,
								unidad.getNom() + " te ha hecho: " + dañoTotal + " puntos de daño");
					} else {
						JOptionPane.showMessageDialog(null, unidad.getNom() + " te ha hecho: 0 puntos de daño");
					}
				}
				logger.log(Level.INFO, "Ataque del enemigo");
				if (vidaTemporal <= 0) {
					JOptionPane.showMessageDialog(null, "Has perdido");
					logger.log(Level.INFO, "Se ha acabado el combate porque has perdido");
					menu.setVisible(true);
					ventana.dispose();
				} else {
					vidaAliado.setText("" + vidaTemporal);
					turnosUnidad = 0;
				}
			} else {
				//calculo del daño que hace el ataque principal del enemigo
				int daño = 0;
				int vidaTemporal = Integer.parseInt(vidaAliado.getText());
				int dañoTotal = 0;
				if (unidad.getRaza().contentEquals("orco") || unidad.getRaza().contentEquals("esqueleto")) {

					daño = unidad.ataque1(unidad.getAtkFis());
					dañoTotal = daño - (aliado.getDefFis() + aliado.getCasco().getBuffDefFis()
							+ aliado.getPechera().getBuffDefFis());
					if (dañoTotal >= 0) {
						vidaTemporal = vidaTemporal - (dañoTotal);
						JOptionPane.showMessageDialog(null,
								unidad.getNom() + " te ha hecho: " + dañoTotal + " puntos de daño");
					} else {
						JOptionPane.showMessageDialog(null, unidad.getNom() + " te ha hecho: 0 puntos de daño");
					}
				} else if (unidad.getRaza().contentEquals("nigromante")) {
					daño = unidad.ataque1(unidad.getAtkMag());
					dañoTotal = daño -  - (aliado.getDefMag() + aliado.getCasco().getBuffDefMag()
							+ aliado.getPechera().getBuffDefMag());
					if (dañoTotal >= 0) {
						vidaTemporal = vidaTemporal - (dañoTotal);
						JOptionPane.showMessageDialog(null,
								unidad.getNom() + " te ha hecho: " + dañoTotal + " puntos de daño");
					} else {
						JOptionPane.showMessageDialog(null, unidad.getNom() + " te ha hecho: 0 puntos de daño");
					}
				}
				logger.log(Level.INFO, "Ataque del enemigo");
				if (vidaTemporal <= 0) {
					JOptionPane.showMessageDialog(null, "Has perdido");
					logger.log(Level.INFO, "Se ha acabado el combate porque has perdido");
					menu.setVisible(true);
					ventana.dispose();
				} else {
					vidaAliado.setText("" + vidaTemporal);
					turnosUnidad = turnosUnidad + 1;
				}

			}
		}

	}

}
