package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelComboBoxen extends JPanel {
	private JComboBox richtungCombo, protokollCombo;
	

	public PanelComboBoxen() {
		setLayout(new GridLayout(2, 3));
		setBorder(BorderFactory.createTitledBorder("Richtung und Protokoll wählen."));
		String[] richtung = {"INPUT", "FORWARD", "OUTPUT"};
		String[] protokoll = {"TCP", "UDP", "ICMP"};
		richtungCombo = new JComboBox(richtung);
		protokollCombo = new JComboBox(protokoll);
		add(new JLabel("Richtung"));
		add(new JLabel());
		add(new JLabel("Protokoll"));
		add(richtungCombo);
		add(new JLabel());
		add(protokollCombo);
	}

}
