package ui;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;

public class VentanaArmas extends JFrame{

	private static final long serialVersionUID = 1L;
	public DefaultListModel<String>listModel ;
	public JList<String> listaArmas = new JList<String>();
	public VentanaMenu menu;
	public VentanaArmas armas = this;
	public JPanel panel = new JPanel();
	public JLabel fondo;
	public VentanaArmas(VentanaMenu m) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500,300, 400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		menu= m;
		listModel = new DefaultListModel<String>();
		
		try {
			listModel.addAll(menu.miDB.getArmas());
		} catch (Exception e) {
		}
		panel.setLayout(null);
		listaArmas = new JList<String>(listModel);
		listaArmas.setBounds(10, 11, 180, 249);
		panel.add(listaArmas);
		
		fondo = new JLabel(new ImageIcon(getClass().getResource("/resources/fondoMenu.png")));
		fondo.setBounds(0, 0, 394, 271);
		panel.add(fondo);
		
		getContentPane().add(panel);
		
	}
}
