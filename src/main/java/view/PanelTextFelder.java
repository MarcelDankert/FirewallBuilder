package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelTextFelder extends JPanel {
	private JTextField quelleTf, zielTf, singlePortTf, multiPortsTf, macTf;

	public PanelTextFelder() {
		setLayout(new GridLayout(5, 2));
		setBorder(BorderFactory.createTitledBorder("Adressen und Ports"));
		quelleTf = new JTextField();
		zielTf = new JTextField();
		singlePortTf = new JTextField();
		multiPortsTf = new JTextField();
		macTf = new JTextField();
		add(new JLabel("Quelle")); add(quelleTf);
		add(new JLabel("Ziel")); add(zielTf);
		add(new JLabel("Einzelner Port")); add(singlePortTf);
		add(new JLabel("Mehrere Ports")); add(multiPortsTf);
		add(new JLabel("Mac-Adresse")); add(macTf);
	}

}
