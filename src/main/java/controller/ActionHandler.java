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
	private StringBuilder stringBuilder;
	
	

	public ActionHandler(MyGui mainFrame) {
		this.mf = mainFrame;
		this.fb = new FileBuilder();
		this.stringBuilder = new StringBuilder();
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 * Bedingungen f�r das erste Panel
		 */
		if(e.getSource()==mf.getPanelOneBtn()) {
			if (mf.getRegelNameTf().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Bitte geben sie einen Regelnamen ein.",
						"Fehlende Eingaben", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				fb.setName("echo \"" + mf.getRegelNameTf().getText() + "\"\r\n" + fb.getMap().get("IPT") + " -A ");
				stringBuilder.append(fb.getName());
				mf.getPanelOneBtn().setEnabled(false);
				mf.getPanelTwoBtn().setEnabled(true);
				mf.getAusgabeArea().setText(stringBuilder.toString());
			}
		}
		
		/*
		 * Bedingungen f�r das zweite Panel
		 */
		if (e.getSource()==mf.getPanelTwoBtn()) {
			if (mf.getRichtungCombo().getSelectedItem() == null || mf.getProtokollCombo().getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Bitte w�hlen sie Richtung und Protokoll.",
						"Fehlende Eingaben", JOptionPane.INFORMATION_MESSAGE);
			} else {
				fb.setRichtung((String) mf.getRichtungCombo().getSelectedItem());
				fb.setProtokoll((String) mf.getProtokollCombo().getSelectedItem());
				stringBuilder.append(fb.getRichtung()+" -p " + fb.getMap().get(fb.getProtokoll()));
				mf.getAusgabeArea().setText(stringBuilder.toString());
				mf.getPanelTwoBtn().setEnabled(false);
				mf.getPanelThreeBtn().setEnabled(true);
				mf.getAddPortBtn().setEnabled(true);
				if (fb.getProtokoll().equals("ICMP")) {
					mf.getSinglePortTf().setEnabled(false);
					mf.getMultiPortsTf().setEnabled(false);
					mf.getAddPortBtn().setEnabled(true);
				}
			}
		}
		
		/*
		 * Bedingungen f�r das dritte Panel
		 */
		if (e.getSource()==mf.getPanelThreeBtn()) {
			if (mf.getQuelleTf().getText().isEmpty() || mf.getZielTf().getText().isEmpty() 
					|| mf.getMacTf().getText().isEmpty() || (mf.getMultiPortsTf().getText().isEmpty() && !fb.getProtokoll().equals("ICMP"))) {
				JOptionPane.showMessageDialog(null, "Bitte alle Felder ausf�llen.",
						"Fehlende Eingaben", JOptionPane.INFORMATION_MESSAGE);
			} else {
				fb.setQuelle(" -s " + mf.getQuelleTf().getText());
				fb.setZiel(" -d " + mf.getZielTf().getText());
				fb.setMac(mf.getMacTf().getText());
				mf.getPanelThreeBtn().setEnabled(false);
				mf.getSaveBtn().setEnabled(true);
				if (fb.getPorts().size()==0) {
					fb.setPortpara("");
				}
				else if (fb.getPorts().size()==1) {
					fb.setPortpara(" --dport ");
				}
				else {
					fb.setPortpara(" --dports ");
				}
				stringBuilder.append(" " + fb.getMap().get("MAC") + fb.getMac() + fb.getQuelle() + fb.getZiel() + fb.getPortpara() + String.join(",", fb.getPorts()) + " " + fb.getMap().get("R"));
				mf.getAusgabeArea().setText(stringBuilder.toString());
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
				JOptionPane.showMessageDialog(null, "Bitte �berpr�fen Sie, ob alle Felder korrekt ausgef�llt sind.",
						"Fehlende Eingaben", JOptionPane.INFORMATION_MESSAGE);
			} else {
				mf.getAusgabeArea().setText("#" + mf.getRegelNameTf().getText());
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("firewall.sh"))) {
					try {
						writer.write(stringBuilder.toString());
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
		 * Der Neue Regel Button setzt alle Felder und Buttons zur�ck
		 */
		if (e.getSource() == mf.getNewBtn()) {
			mf.resetWindow();
			fb.resetFileBuilder();
		}
		/*
		 * Der Exit Button beendet das Programm und schlie�t das Fenster
		 */
		if (e.getSource() == mf.getExitBtn()) {
			System.exit(0);
		}

	}

}
