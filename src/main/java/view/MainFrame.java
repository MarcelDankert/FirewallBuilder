/**
 * 
 */
package view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * @author s15
 *
 */
public class MainFrame extends JFrame{
	private JPanel regelNamePan, checkboxenPan, textfelderPan, ausgabePan;
	private JTextField regelNameTf, startIpTf, zielIpTf, macTf, portTf;
	private JLabel regelNameLa, startIpLa, zielIpLa, macLa, portLa;

	public MainFrame(){
		// Fenster erstellen und Layout setzen
		this.setSize(400,800);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Firewall-Builder");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		GridLayout grid = new GridLayout(4,0);
		this.setLayout(grid);
		// Panels hinzufügen
		this.regelNamePan = new JPanel();
		this.add(regelNamePan);
		this.checkboxenPan = new JPanel();
		this.add(checkboxenPan);
		this.textfelderPan = new JPanel();
		this.add(textfelderPan);
		this.ausgabePan = new JPanel();
		this.add(ausgabePan);
		// Elemente auf die Panels verteilen
		this.regelNameLa = new JLabel("Regelname"); 
		this.startIpLa = new JLabel("Start-IP"); 
		this.zielIpLa = new JLabel("Ziel-IP"); 
		this.macLa = new JLabel("Mac-Adresse"); 
		this.portLa = new JLabel("Ports");
		this.regelNameTf = new JTextField(); 
		this.startIpTf = new JTextField();   
		this.zielIpTf = new JTextField();
		this.macTf = new JTextField(); 
		this.portTf = new JTextField();
		this.regelNamePan.add(regelNameLa);
		this.regelNamePan.add(regelNameTf);
		pack();
		
	}
	

}
