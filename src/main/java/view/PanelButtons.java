package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButtons extends JPanel{
	private JButton saveBtn, newBtn, exitBtn;

	public PanelButtons() {
		setLayout(new GridLayout(0,3));
		setBorder(BorderFactory.createTitledBorder("Steuerung"));
		saveBtn = new JButton("Speichern"); 
		newBtn = new JButton("Neue Regel");
		exitBtn = new JButton("Beenden"); 
		add(saveBtn);
		add(newBtn);
		add(exitBtn);
	}

}
