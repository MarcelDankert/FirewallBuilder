package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.FileBuilder;
import view.MyGui;

public class ActionHandler implements ActionListener {
	private MyGui gui;
	private FileBuilder fileBuilder;
	private StringBuilder stringBuilder;

	public ActionHandler(MyGui mainFrame) {
		this.gui = mainFrame;
		this.fileBuilder = new FileBuilder();
		fileBuilder.createFile(new File("firewall.sh"));
		this.stringBuilder = new StringBuilder();
	}
	
	public void actionPerformed(ActionEvent e) {
		/*
		 * Bedingungen für das erste Panel
		 */
		if (e.getSource() == gui.getPanelOneBtn()) {
			if (gui.getRegelNameTf().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Bitte geben sie einen Regelnamen ein.", "Fehlende Eingaben",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				fileBuilder.setName("echo \"" + gui.getRegelNameTf().getText() + "\"\r\n" + fileBuilder.getMap().get("IPT") + " -A ");
				stringBuilder.append(fileBuilder.getName());
				gui.getPanelOneBtn().setEnabled(false);
				gui.getPanelTwoBtn().setEnabled(true);
				gui.getAusgabeArea().setText(stringBuilder.toString());
			}
		}
		/*
		 * Bedingungen für das zweite Panel
		 */
		if (e.getSource() == gui.getPanelTwoBtn()) {
			if (gui.getRichtungCombo().getSelectedItem() == null || gui.getProtokollCombo().getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Bitte wählen sie Richtung und Protokoll.", "Fehlende Eingaben",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				fileBuilder.setRichtung((String) gui.getRichtungCombo().getSelectedItem());
				fileBuilder.setProtokoll((String) gui.getProtokollCombo().getSelectedItem());
				stringBuilder.append(fileBuilder.getRichtung() + " -p " + fileBuilder.getMap().get(fileBuilder.getProtokoll()));
				gui.getAusgabeArea().setText(stringBuilder.toString());
				gui.getPanelTwoBtn().setEnabled(false);
				gui.getPanelThreeBtn().setEnabled(true);
				gui.getAddPortBtn().setEnabled(true);
				if (fileBuilder.getProtokoll().equals("ICMP")) {
					gui.getSinglePortTf().setEnabled(false);
					gui.getMultiPortsTf().setEnabled(false);
					gui.getAddPortBtn().setEnabled(false);
				}
			}
		}

		/*
		 * Bedingungen für das dritte Panel
		 */
		if (e.getSource() == gui.getPanelThreeBtn()) {
			if (gui.getQuelleTf().getText().isEmpty() || gui.getZielTf().getText().isEmpty()
					|| (gui.getMultiPortsTf().getText().isEmpty() && !fileBuilder.getProtokoll().equals("ICMP"))) {
				JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen.", "Fehlende Eingaben",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				fileBuilder.setQuelle(" -s " + gui.getQuelleTf().getText());
				fileBuilder.setZiel(" -d " + gui.getZielTf().getText());
				if (!gui.getMacTf().getText().isEmpty()) {
					fileBuilder.setMac(gui.getMacTf().getText());
				}
				gui.getPanelThreeBtn().setEnabled(false);
				gui.getSaveBtn().setEnabled(true);
				if (fileBuilder.getPorts().size() == 0) {
					fileBuilder.setPortpara("");
					fileBuilder.setMultiport("");
				} else if (fileBuilder.getPorts().size() == 1) {
					fileBuilder.setPortpara(" --dport ");
				} else if (fileBuilder.getPorts().size() > 1) {
					fileBuilder.setPortpara(" --dports ");
					fileBuilder.setMultiport(fileBuilder.getMap().get("MP"));
				}
				stringBuilder
						.append(fileBuilder.getMultiport() + fileBuilder.getMap().get("MAC") + fileBuilder.getMac() + fileBuilder.getQuelle() + fileBuilder.getZiel()
								+ fileBuilder.getPortpara() + String.join(",", fileBuilder.getPorts()) + " " + fileBuilder.getMap().get("R"));
				gui.getAusgabeArea().setText(stringBuilder.toString());
			}
		}
		if (e.getSource() == gui.getAddPortBtn()) {
			if (!gui.getSinglePortTf().getText().isEmpty()) {
				fileBuilder.getPorts().add(gui.getSinglePortTf().getText());
				gui.getSinglePortTf().setText(null);
				gui.getMultiPortsTf().setText(fileBuilder.getPorts().toString());
			}
		}
		// Eventhandling für den Save Button
		if (e.getSource() == gui.getSaveBtn()) {
			System.out.println(fileBuilder.getPorts().size());
			try {
				fileBuilder.cutEnd("firewall.sh");
				fileBuilder.setNewRule(stringBuilder.toString() + fileBuilder.getEnd());
				fileBuilder.appendNewRule(fileBuilder.getNewRule());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Regel gespeichert.", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			gui.resetWindow();
			fileBuilder.resetFileBuilder();
			stringBuilder = new StringBuilder();
		}
		/*
		 * Der Neue Regel Button setzt alle Felder und Buttons zurück
		 */
		if (e.getSource() == gui.getNewBtn()) {
			gui.resetWindow();
			fileBuilder.resetFileBuilder();
			stringBuilder = new StringBuilder();
		}
		/*
		 * Der Exit Button beendet das Programm und schließt das Fenster
		 */
		if (e.getSource() == gui.getExitBtn()) {
			System.exit(0);
		}
	}
}
