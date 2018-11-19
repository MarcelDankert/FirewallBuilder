package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
				fb.setName(mf.getRegelNameTf().getText());
				mf.getPanelOneBtn().setEnabled(false);
				System.out.println(fb.getName());
			}
		}
		
		/*
		 * Bedingungen für das zweite Panel
		 */
		if (e.getSource()==mf.getPanelTwoBtn()) {
			if (mf.getRichtungCombo()==null || mf.getProtokollCombo() == null) {
				
			} else {

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
			}
		}
		/*
		 * Der Exit Button beendet das Programm und schließt das Fenster
		 */
		if (e.getSource() == mf.getExitBtn()) {
			System.exit(0);
		}

	}

}
