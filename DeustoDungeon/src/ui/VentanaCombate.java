package ui;


import java.awt.Color;
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
	public VentanaMenu menu = new VentanaMenu();
	public HiloCombate miHilo = new HiloCombate();
	// public int turno=0;
	public JLabel fondo;
	public JPanel panelFondo;

	public VentanaCombate(VentanaMenu v, Aliado a, Unidad u) {
		menu = v;
		aliado = a;
		unidad = u;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 500, 300);
		setResizable(false);
		panelFondo = new JPanel();
		fondo = new JLabel();
		// panelFondo.setBackground(new Color(0,0,0,0));
		panelFondo.setLayout(null);
		panel1.setBounds(0, 0, 494, 271);
		// panel1.setBackground(new Color(0,0,0,0));
		panelFondo.add(panel1);
		// foto de fondo, pero al actualizar los labels funciona mal
		// fondo = new JLabel(new
		// ImageIcon(getClass().getResource("/resources/fondoCombate1.png")));
		// fondo.setBounds(0, 0, 494, 271);
		// panelFondo.add(fondo);

		getContentPane().add(panelFondo);
		panel1.setLayout(new MigLayout("", "[83.00px][][123px][][]", "[23px][][][][][][][50.00][][]"));

		panel1.add(volver, "cell 0 1,alignx center");
		nombreAliado.setForeground(Color.BLACK);

		nombreAliado.setText(aliado.getNom());

		panel1.add(nombreAliado, "cell 2 5");
		nombreUnidad.setForeground(Color.BLACK);
		nombreUnidad.setText(unidad.getNom() + " el " + unidad.getRaza());
		panel1.add(nombreUnidad, "cell 4 5");
		vidaAli.setForeground(Color.BLACK);

		panel1.add(vidaAli, "cell 1 6");
		vidaAliado.setBackground(Color.WHITE);
		vidaAliado.setForeground(Color.BLACK);

		vidaAliado
				.setText("" + (aliado.getVida() + aliado.getCasco().getBuffVida() + aliado.getPechera().getBuffVida()));
		panel1.add(vidaAliado, "cell 2 6,alignx left,aligny center");
		vidaUni.setForeground(Color.BLACK);

		panel1.add(vidaUni, "cell 3 6");
		vidaUnidad.setBackground(Color.WHITE);
		vidaUnidad.setForeground(Color.BLACK);
		vidaUnidad.setText("" + unidad.getVida());
		vidaUnidad.setBackground(new Color(0, 0, 0, 0));
		panel1.add(vidaUnidad, "cell 4 6,alignx left,aligny center");

		panel1.add(ataquePrincipal, "cell 2 8");

		panel1.add(ataqueSecundario, "cell 4 8");

		ataqueSecundario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
				JOptionPane.showMessageDialog(null,
						"Tu " + aliado.getArma().getTipo() + " ha hecho: " + daño + " puntos de daño");
				if (vidaTemporal <= 0) {
					JOptionPane.showMessageDialog(null, "Has ganado");
					menu.setVisible(true);
					ventana.dispose();
				} else {
					vidaUnidad.setText("" + vidaTemporal);
					miHilo.run();
				}

			}

		});

		ataquePrincipal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
				JOptionPane.showMessageDialog(null,
						"Tu " + aliado.getArma().getTipo() + " ha hecho: " + dañoTotal + " puntos de daño");
				if (vidaTemporal <= 0) {
					JOptionPane.showMessageDialog(null, "Has ganado");
					menu.setVisible(true);
					ventana.dispose();
				} else {
					vidaUnidad.setText("" + vidaTemporal);
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
			if (turnosUnidad == 3) {
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
					dañoTotal = daño - (aliado.getDefFis() + aliado.getCasco().getBuffDefFis()
							+ aliado.getPechera().getBuffDefFis());
					if (dañoTotal >= 0) {
						vidaTemporal = vidaTemporal - (dañoTotal);
						JOptionPane.showMessageDialog(null,
								unidad.getNom() + " te ha hecho: " + dañoTotal + " puntos de daño");
					} else {
						JOptionPane.showMessageDialog(null, unidad.getNom() + " te ha hecho: 0 puntos de daño");
					}
				}
				if (vidaTemporal <= 0) {
					JOptionPane.showMessageDialog(null, "Has perdido");
					menu.setVisible(true);
					ventana.dispose();
				} else {
					vidaAliado.setText("" + vidaTemporal);
					turnosUnidad = 0;
				}

			} else {
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
					daño = unidad.ataque1(unidad.getAtkMag()) - (aliado.getDefMag() + aliado.getCasco().getBuffDefMag()
							+ aliado.getPechera().getBuffDefMag());
					dañoTotal = daño - (aliado.getDefFis() + aliado.getCasco().getBuffDefFis()
							+ aliado.getPechera().getBuffDefFis());
					if (dañoTotal >= 0) {
						vidaTemporal = vidaTemporal - (dañoTotal);
						JOptionPane.showMessageDialog(null,
								unidad.getNom() + " te ha hecho: " + dañoTotal + " puntos de daño");
					} else {
						JOptionPane.showMessageDialog(null, unidad.getNom() + " te ha hecho: 0 puntos de daño");
					}
				}
				if (vidaTemporal <= 0) {
					JOptionPane.showMessageDialog(null, "Has perdido");
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
