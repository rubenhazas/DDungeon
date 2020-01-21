package ui;



import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;



public class VentanaCascos extends JFrame {
	private static final long serialVersionUID = 1L;
	public DefaultListModel<String> listModel;
	public JList<String> listaCascos = new JList<String>();
	public VentanaMenu menu;
	public VentanaCascos armas = this;
	public JPanel panel = new JPanel();
	public JLabel fondo;

	public VentanaCascos(VentanaMenu m) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		menu = m;
		listModel = new DefaultListModel<String>();

		try {
			listModel.addAll(menu.miDB.getCascos());
		} catch (Exception e) {
		}
		panel.setLayout(null);
		listaCascos = new JList<String>(listModel);
		listaCascos.setBounds(10, 11, 180, 249);
		panel.add(listaCascos);

		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoMenu.png")));
		fondo.setBounds(0, 0, 394, 271);
		panel.add(fondo);

		getContentPane().add(panel);

	}
}
