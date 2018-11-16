/**
 * 
 */
package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author s15
 *
 */
public class MainFrame extends JFrame{
	private PanelRegelName panelRegelName;
	private PanelComboBoxen panelCheckBoxen;
	private PanelTextFelder panelTextFelder;
	private PanelAusgabe panelAusgabe;
	private PanelButtons panelButtons;

	public MainFrame(){
		// Fenster erstellen und Layout setzen
		setSize(400,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Firewall-Builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		// Panels hinzufügen
		panelRegelName = new PanelRegelName();
		add(panelRegelName);
		panelCheckBoxen = new PanelComboBoxen();
		add(panelCheckBoxen);
		panelTextFelder = new PanelTextFelder();
		add(panelTextFelder);
		panelAusgabe = new PanelAusgabe();
		add(panelAusgabe);
		panelButtons = new PanelButtons();
		add(panelButtons);
		pack();
	}
	
}
