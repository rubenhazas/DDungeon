package ui.user;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import gestorDB.GestorDB;
import ui.main.VentanaMenu;

public class VentanaArmas extends JFrame {

	private static final long serialVersionUID = 1L;
	public DefaultListModel<String> listModel;
	public JList<String> listaArmas = new JList<String>();
	public VentanaMenu menu;
	public VentanaArmas armas = this;
	public JPanel panel = new JPanel();
	public JLabel fondo;
	private Logger logger = Logger.getLogger(VentanaArmas.class.getName());

	public VentanaArmas(VentanaMenu m) {
		logger.log(Level.INFO, "Creando la ventana de armas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		menu = m;
		//listModel donde metemos las armas
		listModel = new DefaultListModel<String>();

		try {
			logger.log(Level.INFO, "Buscando armas para el JList");
			listModel.addAll(menu.miDB.getArmas());
		} catch (Exception e) {
		}
		panel.setLayout(null);
		//JList con las armas
		listaArmas = new JList<String>(listModel);
		listaArmas.setBounds(10, 11, 180, 249);
		panel.add(listaArmas);
		// imagen de fondo de la ventana
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoMenu.png")));
		fondo.setBounds(0, 0, 394, 271);
		panel.add(fondo);

		getContentPane().add(panel);

	}
}
