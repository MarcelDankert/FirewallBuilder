/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.ActionHandler;

/**
 * @author mcl@ITMO1801
 * 
 *         Diese Klasse baut die grafische Oberfläche zusammen Diese besteht aus
 *         einem Fenster, welches mit 5 JPanels gefüllt wird Im 1. Panel gibt
 *         man der neuen Regel die man hinzufügen möchte einen Namen Im 2. Panel
 *         stellt man die Richtung und den Protokolltyp ein Im 3. Panel legt man
 *         die IP-Adressen, Ports und MAC fest Das 4. Panel zeigt eine Vorschau
 *         der neuen Regel Das 5. Panel enthält dann die Buttons zum Speichern,
 *         Erstellen und Beenden
 */
public class MyGui extends JFrame {
	// Bausatz für Panel 1
	private JPanel panelRegelName;
	private JTextField regelNameTf;
	private JPanel okPanelOne;
	private JButton panelOneBtn;
	// Bausatz für Panel 2
	private JPanel panelCheckBoxen;
	private JComboBox richtungCombo, protokollCombo;
	private JPanel okPanelTwo;
	private JButton panelTwoBtn;
	// Bausatz für Panel 3
	private JPanel panelTextFelder, portPanel;
	private JTextField quelleTf, zielTf, singlePortTf, multiPortsTf, macTf;
	private JPanel okPanelThree;
	private JButton panelThreeBtn, addPortBtn;
	// Bausatz für Panel 4
	private JPanel panelAusgabe;
	private JTextArea ausgabeArea;
	// Bausatz für Panel 5
	private JPanel panelButtons;
	private JButton saveBtn, newBtn, exitBtn;
	private ActionHandler handler;

	public MyGui() {
		handler = new ActionHandler(this);
		// Fenster erstellen und Layout setzen
		setSize(400, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Firewall-Builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
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
		// Panels hinzufügen

		// Panel 1 zusammenbauen und hinzufügen
		panelRegelName = new JPanel();
		panelRegelName.setLayout(new GridLayout(3, 0));
		panelRegelName.setBorder(BorderFactory.createTitledBorder("1. Regelname festlegen"));
		regelNameTf = new JTextField();
		panelRegelName.add(new JLabel("Bitte geben Sie einen Regelname ein."));
		panelRegelName.add(regelNameTf);
		okPanelOne = new JPanel();
		okPanelOne.setLayout(new GridLayout(1, 4));
		okPanelOne.add(new JLabel());
		okPanelOne.add(new JLabel());
		okPanelOne.add(new JLabel());
		panelOneBtn = new JButton("OK");
		panelOneBtn.addActionListener(handler);
		okPanelOne.add(panelOneBtn);
		panelRegelName.add(okPanelOne);
		add(panelRegelName);

		// Panel 2 zusammenbauen und hinzufügen
		panelCheckBoxen = new JPanel();
		panelCheckBoxen.setLayout(new GridLayout(3, 2));
		panelCheckBoxen.setBorder(BorderFactory.createTitledBorder("2. Richtung und Protokoll wählen."));
		String[] richtung = { null, "INPUT", "FORWARD", "OUTPUT" };
		String[] protokoll = { null, "TCP", "UDP", "ICMP" };
		richtungCombo = new JComboBox(richtung);
		richtungCombo.addActionListener(handler);
		protokollCombo = new JComboBox(protokoll);
		protokollCombo.addActionListener(handler);
		panelCheckBoxen.add(new JLabel("Richtung"));
		panelCheckBoxen.add(new JLabel("Protokoll"));
		panelCheckBoxen.add(richtungCombo);
		panelCheckBoxen.add(protokollCombo);
		panelCheckBoxen.add(new JLabel());

		okPanelTwo = new JPanel();
		okPanelTwo.setLayout(new GridLayout(1, 2));
		okPanelTwo.add(new JLabel());
		panelTwoBtn = new JButton("OK");		
		panelTwoBtn.addActionListener(handler);
		okPanelTwo.add(panelTwoBtn);
		panelCheckBoxen.add(okPanelTwo);
		add(panelCheckBoxen);

		// Panel 3 zusammenbauen und hinzufügen
		panelTextFelder = new JPanel();
		panelTextFelder.setLayout(new GridLayout(6, 2));
		panelTextFelder.setBorder(BorderFactory.createTitledBorder("3. Adressen und Ports eingeben"));
		quelleTf = new JTextField();
		zielTf = new JTextField();
		singlePortTf = new JTextField();
		multiPortsTf = new JTextField();
		multiPortsTf.setEditable(false);
		macTf = new JTextField();
		panelTextFelder.add(new JLabel("Quelle"));
		panelTextFelder.add(quelleTf);
		panelTextFelder.add(new JLabel("Ziel"));
		panelTextFelder.add(zielTf);
		portPanel = new JPanel(new GridLayout(1, 2));
		addPortBtn = new JButton("+");
		addPortBtn.addActionListener(handler);
		panelTextFelder.add(new JLabel("Ports hinzufügen"));
		panelTextFelder.add(portPanel);
		portPanel.add(singlePortTf);
		portPanel.add(addPortBtn);
		panelTextFelder.add(new JLabel("Liste der Ports"));
		panelTextFelder.add(multiPortsTf);
		panelTextFelder.add(new JLabel("Mac-Adresse"));
		panelTextFelder.add(macTf);
		panelTextFelder.add(new JLabel());
		okPanelThree = new JPanel();
		okPanelThree.setLayout(new GridLayout(1, 2));
		okPanelThree.add(new JLabel());
		panelThreeBtn = new JButton("OK");
		panelThreeBtn.addActionListener(handler);
		okPanelThree.add(panelThreeBtn);
		panelTextFelder.add(okPanelThree);
		add(panelTextFelder);
		// Panel 4 zusammenbauen und hinzufügen
		panelAusgabe = new JPanel();
		panelAusgabe.setBorder(BorderFactory.createTitledBorder("4. Vorschau überprüfen"));
		ausgabeArea = new JTextArea(10, 30);
		ausgabeArea.setLineWrap(true);
		panelAusgabe.add(ausgabeArea);
		add(panelAusgabe);
		// Panel 5 zusammenbauen und hinzufügen
		panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(0, 3));
		panelButtons.setBorder(BorderFactory.createTitledBorder("5. Speichern, Neu Anfangen oder Beenden"));
		saveBtn = new JButton("Speichern");
		saveBtn.addActionListener(handler);
		newBtn = new JButton("Neue Regel");
		newBtn.addActionListener(handler);
		exitBtn = new JButton("Beenden");
		exitBtn.addActionListener(handler);
		panelButtons.add(saveBtn);
		panelButtons.add(newBtn);
		panelButtons.add(exitBtn);
		add(panelButtons);
		this.resetWindow();
		pack();
	}

	public void resetWindow() {
		// Buttons einstellen
		panelOneBtn.setEnabled(true);
		panelTwoBtn.setEnabled(false);
		addPortBtn.setEnabled(false);
		panelThreeBtn.setEnabled(false);
		saveBtn.setEnabled(false);
		// Textfelder leeren
		regelNameTf.setText(null);
		richtungCombo.setSelectedIndex(0);
		protokollCombo.setSelectedIndex(0);
		zielTf.setText(null);
		quelleTf.setText(null);
		multiPortsTf.setText(null);
		singlePortTf.setText(null);
		macTf.setText(null);
		ausgabeArea.setText(null);
		singlePortTf.setEnabled(true);
		multiPortsTf.setEnabled(true);
	}

	public JButton getAddPortBtn() {
		return addPortBtn;
	}

	public void setAddPortBtn(JButton addPortBtn) {
		this.addPortBtn = addPortBtn;
	}

	public JButton getPanelOneBtn() {
		return panelOneBtn;
	}

	public void setPanelOneBtn(JButton panelOneBtn) {
		this.panelOneBtn = panelOneBtn;
	}

	public JButton getPanelTwoBtn() {
		return panelTwoBtn;
	}

	public void setPanelTwoBtn(JButton panelTwoBtn) {
		this.panelTwoBtn = panelTwoBtn;
	}

	public JButton getPanelThreeBtn() {
		return panelThreeBtn;
	}

	public void setPanelThreeBtn(JButton panelThreeBtn) {
		this.panelThreeBtn = panelThreeBtn;
	}

	public JPanel getPanelRegelName() {
		return panelRegelName;
	}

	public void setPanelRegelName(JPanel panelRegelName) {
		this.panelRegelName = panelRegelName;
	}

	public JTextField getRegelNameTf() {
		return regelNameTf;
	}

	public void setRegelNameTf(JTextField regelNameTf) {
		this.regelNameTf = regelNameTf;
	}

	public JPanel getPanelCheckBoxen() {
		return panelCheckBoxen;
	}

	public void setPanelCheckBoxen(JPanel panelCheckBoxen) {
		this.panelCheckBoxen = panelCheckBoxen;
	}

	public JComboBox getRichtungCombo() {
		return richtungCombo;
	}

	public void setRichtungCombo(JComboBox richtungCombo) {
		this.richtungCombo = richtungCombo;
	}

	public JComboBox getProtokollCombo() {
		return protokollCombo;
	}

	public void setProtokollCombo(JComboBox protokollCombo) {
		this.protokollCombo = protokollCombo;
	}

	public JPanel getPanelTextFelder() {
		return panelTextFelder;
	}

	public void setPanelTextFelder(JPanel panelTextFelder) {
		this.panelTextFelder = panelTextFelder;
	}

	public JTextField getQuelleTf() {
		return quelleTf;
	}

	public void setQuelleTf(JTextField quelleTf) {
		this.quelleTf = quelleTf;
	}

	public JTextField getZielTf() {
		return zielTf;
	}

	public void setZielTf(JTextField zielTf) {
		this.zielTf = zielTf;
	}

	public JTextField getSinglePortTf() {
		return singlePortTf;
	}

	public void setSinglePortTf(JTextField singlePortTf) {
		this.singlePortTf = singlePortTf;
	}

	public JTextField getMultiPortsTf() {
		return multiPortsTf;
	}

	public void setMultiPortsTf(JTextField multiPortsTf) {
		this.multiPortsTf = multiPortsTf;
	}

	public JTextField getMacTf() {
		return macTf;
	}

	public void setMacTf(JTextField macTf) {
		this.macTf = macTf;
	}

	public JPanel getPanelAusgabe() {
		return panelAusgabe;
	}

	public void setPanelAusgabe(JPanel panelAusgabe) {
		this.panelAusgabe = panelAusgabe;
	}

	public JTextArea getAusgabeArea() {
		return ausgabeArea;
	}

	public void setAusgabeArea(JTextArea ausgabeArea) {
		this.ausgabeArea = ausgabeArea;
	}

	public JPanel getPanelButtons() {
		return panelButtons;
	}

	public void setPanelButtons(JPanel panelButtons) {
		this.panelButtons = panelButtons;
	}

	public JButton getSaveBtn() {
		return saveBtn;
	}

	public void setSaveBtn(JButton saveBtn) {
		this.saveBtn = saveBtn;
	}

	public JButton getNewBtn() {
		return newBtn;
	}

	public void setNewBtn(JButton newBtn) {
		this.newBtn = newBtn;
	}

	public JButton getExitBtn() {
		return exitBtn;
	}

	public void setExitBtn(JButton exitBtn) {
		this.exitBtn = exitBtn;
	}

}
