package ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class VentanaPecheras extends JFrame {
	private static final long serialVersionUID = 1L;
	public DefaultListModel<String> listModel;
	public JList<String> listaCascos = new JList<String>();
	public VentanaMenu menu;
	public VentanaPecheras armas = this;
	public JPanel panel = new JPanel();
	public JLabel fondo;
	private Logger logger = Logger.getLogger(VentanaPecheras.class.getName());
	public VentanaPecheras(VentanaMenu m) {
		logger.log(Level.INFO, "Creando la ventana");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		menu = m;
		listModel = new DefaultListModel<String>();

		try {
			logger.log(Level.INFO, "Buscando pecheras para el JList");
			listModel.addAll(menu.miDB.getPecheras());
		} catch (Exception e) {
		}
		panel.setLayout(null);
		listaCascos = new JList<String>(listModel);
		listaCascos.setBounds(11, 11, 180, 249);
		panel.add(listaCascos);
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoMenu.png")));
		fondo.setBounds(0, 0, 394, 271);
		panel.add(fondo);
		getContentPane().add(panel);

	}
}
