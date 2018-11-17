package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.MyGui;

public class ActionHandler implements ActionListener {
	private MyGui mf;
	

	public ActionHandler(MyGui mainFrame) {
		this.mf = mainFrame;
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==mf.getSaveBtn()) {
			if (mf.getRegelNameTf().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Bitte überprüfen Sie, ob alle Felder korrekt ausgefüllt sind.", "Fehlende Eingaben", JOptionPane.INFORMATION_MESSAGE); 
			}
			else {
				mf.getAusgabeArea().setText("#" + mf.getRegelNameTf().getText());
			}
		}
		if (e.getSource()==mf.getExitBtn()) {
			System.exit(0);
		}
		
	}

}
