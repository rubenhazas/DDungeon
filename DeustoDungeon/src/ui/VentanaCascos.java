package ui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class VentanaCascos extends JFrame{
	private static final long serialVersionUID = 1L;
	public DefaultListModel<String>listModel ;
	public JList<String> listaCascos = new JList<String>();
	public VentanaMenu menu;
	public VentanaCascos armas = this;
	public JPanel panel = new JPanel();
	
	public VentanaCascos(VentanaMenu m) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300, 400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		menu= m;
		listModel = new DefaultListModel<String>();
		
		try {
			listModel.addAll(menu.miDB.getCascos());
		} catch (Exception e) {
		}
		panel.setLayout(new MigLayout("", "[36.00][364.00px][]", "[261px]"));
		listaCascos = new JList<String>(listModel);
		panel.add(listaCascos, "cell 1 0,grow");
		getContentPane().add(panel);
		
	}
}
