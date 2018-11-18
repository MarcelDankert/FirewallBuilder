package starter;

import java.io.File;

import model.FileBuilder;
import view.MyGui;

public class MainExec {
	public static void main(String[] args) {
		FileBuilder fileBuilder = new FileBuilder();
		fileBuilder.createFile(new File("firewall.sh"));
		MyGui gui = new MyGui();
	}
}
