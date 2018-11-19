package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.FileBuilder;
import view.MyGui;

public class ActionHandler implements ActionListener {
	private MyGui mf;
	private FileBuilder fb;

	public ActionHandler(MyGui mainFrame) {
		this.mf = mainFrame;
		this.fb = new FileBuilder();
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 * Bedingungen für das erste Panel
		 */
		if(e.getSource()==mf.getPanelOneBtn()) {
			if (mf.getRegelNameTf().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Bitte geben sie einen Regelnamen ein.",
						"Fehlende Eingaben", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				fb.setName("echo \"" + mf.getRegelNameTf().getText() + "\"\r\n");
				mf.getPanelOneBtn().setEnabled(false);
				mf.getPanelTwoBtn().setEnabled(true);
				mf.getAusgabeArea().setText(fb.getName());
			}
		}
		
		/*
		 * Bedingungen für das zweite Panel
		 */
		if (e.getSource()==mf.getPanelTwoBtn()) {
			if (mf.getRichtungCombo().getSelectedItem() == null || mf.getProtokollCombo().getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Bitte wählen sie Richtung und Protokoll.",
						"Fehlende Eingaben", JOptionPane.INFORMATION_MESSAGE);
			} else {
				fb.setRichtung((String) mf.getRichtungCombo().getSelectedItem());
				fb.setProtokoll((String) mf.getProtokollCombo().getSelectedItem());
				mf.getAusgabeArea().setText(mf.getAusgabeArea().getText()+fb.getRichtung() + " " + fb.getProtokoll());
				mf.getPanelTwoBtn().setEnabled(false);
				mf.getPanelThreeBtn().setEnabled(true);
				mf.getAddPortBtn().setEnabled(true);
				System.out.println(fb.getRichtung() + " " + fb.getMap().get(fb.getProtokoll()));
			}
		}
		
		/*
		 * Bedingungen für das dritte Panel
		 */
		if (e.getSource()==mf.getPanelThreeBtn()) {
			if (mf.getQuelleTf().getText().isEmpty() || mf.getZielTf().getText().isEmpty() 
					|| mf.getMultiPortsTf().getText().isEmpty() || mf.getMacTf().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen.",
						"Fehlende Eingaben", JOptionPane.INFORMATION_MESSAGE);
			} else {
				fb.setQuelle(mf.getQuelleTf().getText());
				fb.setZiel(mf.getZielTf().getText());
				fb.setMac(mf.getMacTf().getText());
				mf.getPanelThreeBtn().setEnabled(false);
				mf.getSaveBtn().setEnabled(true);
				mf.getAusgabeArea().setText(fb.buildNewRule());
			}
		}
		if (e.getSource()==mf.getAddPortBtn()) {
			if (!mf.getSinglePortTf().getText().isEmpty()) {
				fb.getPorts().add(mf.getSinglePortTf().getText());
				mf.getSinglePortTf().setText(null);
				mf.getMultiPortsTf().setText(fb.getPorts().toString());
			}
		}
		
		
		
		if (e.getSource() == mf.getSaveBtn()) {
			if (mf.getRegelNameTf().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Bitte überprüfen Sie, ob alle Felder korrekt ausgefüllt sind.",
						"Fehlende Eingaben", JOptionPane.INFORMATION_MESSAGE);
			} else {
				mf.getAusgabeArea().setText("#" + mf.getRegelNameTf().getText());
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("firewall.sh"))) {
					try {
						writer.write(mf.getRegelNameTf().getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				mf.resetWindow();
				fb.resetFileBuilder();
			}
		}
		/*
		 * Der Neue Regel Button setzt alle Felder und Buttons zurück
		 */
		if (e.getSource() == mf.getNewBtn()) {
			mf.resetWindow();
			fb.resetFileBuilder();
		}
		/*
		 * Der Exit Button beendet das Programm und schließt das Fenster
		 */
		if (e.getSource() == mf.getExitBtn()) {
			System.exit(0);
		}

	}

}
