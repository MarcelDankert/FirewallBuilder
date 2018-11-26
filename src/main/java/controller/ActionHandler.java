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
	private MyGui mf;
	private FileBuilder fb;
	private StringBuilder stringBuilder;

	public ActionHandler(MyGui mainFrame) {
		this.mf = mainFrame;
		this.fb = new FileBuilder();
		fb.createFile(new File("firewall.sh"));
		this.stringBuilder = new StringBuilder();
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 * Bedingungen für das erste Panel
		 */
		if (e.getSource() == mf.getPanelOneBtn()) {
			if (mf.getRegelNameTf().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Bitte geben sie einen Regelnamen ein.", "Fehlende Eingaben",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				fb.setName("echo \"" + mf.getRegelNameTf().getText() + "\"\r\n" + fb.getMap().get("IPT") + " -A ");
				stringBuilder.append(fb.getName());
				mf.getPanelOneBtn().setEnabled(false);
				mf.getPanelTwoBtn().setEnabled(true);
				mf.getAusgabeArea().setText(stringBuilder.toString());
			}
		}

		/*
		 * Bedingungen für das zweite Panel
		 */
		if (e.getSource() == mf.getPanelTwoBtn()) {
			if (mf.getRichtungCombo().getSelectedItem() == null || mf.getProtokollCombo().getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Bitte wählen sie Richtung und Protokoll.", "Fehlende Eingaben",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				fb.setRichtung((String) mf.getRichtungCombo().getSelectedItem());
				fb.setProtokoll((String) mf.getProtokollCombo().getSelectedItem());
				stringBuilder.append(fb.getRichtung() + " -p " + fb.getMap().get(fb.getProtokoll()));
				mf.getAusgabeArea().setText(stringBuilder.toString());
				mf.getPanelTwoBtn().setEnabled(false);
				mf.getPanelThreeBtn().setEnabled(true);
				mf.getAddPortBtn().setEnabled(true);
				if (fb.getProtokoll().equals("ICMP")) {
					mf.getSinglePortTf().setEnabled(false);
					mf.getMultiPortsTf().setEnabled(false);
					mf.getAddPortBtn().setEnabled(false);
				}
			}
		}

		/*
		 * Bedingungen für das dritte Panel
		 */
		if (e.getSource() == mf.getPanelThreeBtn()) {
			if (mf.getQuelleTf().getText().isEmpty() || mf.getZielTf().getText().isEmpty()
					|| mf.getMacTf().getText().isEmpty()
					|| (mf.getMultiPortsTf().getText().isEmpty() && !fb.getProtokoll().equals("ICMP"))) {
				JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen.", "Fehlende Eingaben",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				fb.setQuelle(" -s " + mf.getQuelleTf().getText());
				fb.setZiel(" -d " + mf.getZielTf().getText());
				fb.setMac(mf.getMacTf().getText());
				mf.getPanelThreeBtn().setEnabled(false);
				mf.getSaveBtn().setEnabled(true);
				if (fb.getPorts().size() == 0) {
					fb.setPortpara("");
					fb.setMultiport("");
				} else if (fb.getPorts().size() == 1) {
					fb.setPortpara(" --dport ");
				} else if (fb.getPorts().size() > 1){
					fb.setPortpara(" --dports ");
					fb.setMultiport(fb.getMap().get("MP"));
				}
				stringBuilder
						.append(fb.getMultiport() + fb.getMap().get("MAC") + fb.getMac() + fb.getQuelle() + fb.getZiel()
								+ fb.getPortpara() + String.join(",", fb.getPorts()) + " " + fb.getMap().get("R"));
				mf.getAusgabeArea().setText(stringBuilder.toString());
			}
		}
		if (e.getSource() == mf.getAddPortBtn()) {
			if (!mf.getSinglePortTf().getText().isEmpty()) {
				fb.getPorts().add(mf.getSinglePortTf().getText());
				mf.getSinglePortTf().setText(null);
				mf.getMultiPortsTf().setText(fb.getPorts().toString());
			}
		}
		// Eventhandling für den Save Button
		if (e.getSource() == mf.getSaveBtn()) {
System.out.println(fb.getPorts().size());
			try {
				fb.cutEnd("firewall.sh");
				fb.setNewRule(stringBuilder.toString() + fb.getEnd());
				fb.appendNewRule(fb.getNewRule());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Regel gespeichert.", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			mf.resetWindow();
			fb.resetFileBuilder();
			stringBuilder = new StringBuilder();

		}
		/*
		 * Der Neue Regel Button setzt alle Felder und Buttons zurück
		 */
		if (e.getSource() == mf.getNewBtn()) {
			mf.resetWindow();
			fb.resetFileBuilder();
			stringBuilder = new StringBuilder();
		}
		/*
		 * Der Exit Button beendet das Programm und schließt das Fenster
		 */
		if (e.getSource() == mf.getExitBtn()) {
			System.exit(0);
		}

	}

}
