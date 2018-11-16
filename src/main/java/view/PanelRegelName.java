package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegelName extends JPanel{
	private JTextField regelNameTf;
	public PanelRegelName() {
		setLayout(new GridLayout(2,0));
		setBorder(BorderFactory.createTitledBorder("Regelname"));
		regelNameTf = new JTextField();
		add(new JLabel("Bitte geben Sie einen Regelname ein."));
		add(regelNameTf);
	}
}
