package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelAusgabe extends JPanel {
	private JTextArea ausgabeArea;
	public PanelAusgabe() {
		setBorder(BorderFactory.createTitledBorder("Vorschau"));		
		ausgabeArea = new JTextArea(10,30);
		add(ausgabeArea);
	}
}
