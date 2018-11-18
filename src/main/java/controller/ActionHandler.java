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
	}

	public void actionPerformed(ActionEvent e) {
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
		if (e.getSource() == mf.getExitBtn()) {
			System.exit(0);
		}

	}

}
