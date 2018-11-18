package starter;

import java.io.File;

import model.FileBuilder;
import view.MyGui;

public class MainExec {
	public static void main(String[] args) {
		FileBuilder fileBuilder = new FileBuilder();
		String dateiName = "firewall.sh";
		fileBuilder.createFile(new File(dateiName));
		MyGui gui = new MyGui();
	}
}
