package model;
/**
 * @author s15
 *
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileBuilder {
	private String name, richtung, protokoll, quelle, ziel, mac, newRule, portpara, multiport, end;
	private ArrayList<String> ports;
	private HashMap<String, String> map;
	private BufferedWriter bw;

	public FileBuilder() {
		this.resetFileBuilder();
		this.map = new HashMap<>();
		this.multiport = "";
		this.end = "\r\n" + "\r\n" + "echo \"catch all\"\r\n" + "/sbin/iptables -A INPUT -j DROP\r\n"
				+ "/sbin/iptables -A OUTPUT -j DROP\r\n" + "/sbin/iptables -A FORWARD -j DROP\r\n" + "\r\n"
				+ "/sbin/sysctl -w net.ipv4.ip_forward=1";
		map.put("IPT", "/sbin/iptables");
		map.put("TCP", " tcp -m tcp");
		map.put("UDP", " udp -m udp");
		map.put("ICMP", " icmp -m icmp --icmp-type 8/0");
		map.put("MAC", " -m mac --mac-source ");
		map.put("MP", " -m multiport");
		map.put("R", "-m conntrack --ctstate NEW -j ACCEPT");
	}

	public void createFile(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
				this.writeNewFile();
			} catch (IOException e) {
				System.err.println("Error creating " + file.toString());
			}
		}
	}

	public void appendNewRule(String newRule) throws IOException {

		try {
			// APPEND MODE SET HERE
			bw = new BufferedWriter(new FileWriter("firewall.sh", true));
			bw.write(newRule);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally { // always close the file
			if (bw != null) {
				bw.close();
			}
		}
	}

	public void cutEnd(String fileName) throws IOException {
		List<String> lines = Files.readAllLines(new File(fileName).toPath());
		int listLength = lines.size();
		int listEnd = listLength - 7;
		for (int i = listLength - 1; i > listEnd; i--) {
			lines.remove(i);
		}
		bw = new BufferedWriter(new FileWriter("firewall.sh"));
		bw.write(String.join("\n", lines.toArray(new String[lines.size()])));
		bw.close();
	}

	public void resetFileBuilder() {
		this.name = "";
		this.richtung = "";
		this.protokoll = "";
		this.quelle = "";
		this.ziel = "";
		this.mac = "";
		this.newRule = "";
		this.ports = new ArrayList<>();
	}

	public void writeNewFile() throws IOException {
		String fileContent = "#!/bin/sh\r\n" + "\r\n" + "modprobe ip_conntrack\r\n" + "modprobe ip_conntrack_ftp\r\n"
				+ "\r\n" + "/sbin/iptables -F\r\n" + "/sbin/iptables -X\r\n" + "/sbin/iptables -t nat -F\r\n"
				+ "/sbin/iptables -t nat -X\r\n" + "/sbin/iptables -t mangle -F\r\n" + "/sbin/iptables -t mangle -X\r\n"
				+ "\r\n" + "/sbin/iptables -P INPUT DROP\r\n" + "/sbin/iptables -P OUTPUT DROP\r\n"
				+ "/sbin/iptables -P FORWARD DROP\r\n" + "\r\n"
				+ "/sbin/iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT\r\n"
				+ "/sbin/iptables -A OUTPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT\r\n"
				+ "/sbin/iptables -A FORWARD -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT\r\n" + "\r\n" + "\r\n"
				+ "echo \"alles auf localhost\"\r\n"
				+ "/sbin/iptables -A INPUT -i lo -m conntrack --ctstate NEW -j ACCEPT\r\n"
				+ "/sbin/iptables -A OUTPUT\r\n" + this.end;

		bw = new BufferedWriter(new FileWriter("firewall.sh"));
		bw.write(fileContent);
		bw.close();
	}

	public String getPortpara() {
		return portpara;
	}

	public void setPortpara(String portpara) {
		this.portpara = portpara;
	}

	public HashMap<String, String> getMap() {
		return map;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRichtung() {
		return richtung;
	}

	public void setRichtung(String richtung) {
		this.richtung = richtung;
	}

	public String getProtokoll() {
		return protokoll;
	}

	public void setProtokoll(String protokoll) {
		this.protokoll = protokoll;
	}

	public String getQuelle() {
		return quelle;
	}

	public void setQuelle(String quelle) {
		this.quelle = quelle;
	}

	public String getZiel() {
		return ziel;
	}

	public void setZiel(String ziel) {
		this.ziel = ziel;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getNewRule() {
		return newRule;
	}

	public void setNewRule(String newRule) {
		this.newRule = newRule;
	}

	public ArrayList<String> getPorts() {
		return ports;
	}

	public void setPorts(ArrayList<String> ports) {
		this.ports = ports;
	}

	public String getMultiport() {
		return multiport;
	}

	public void setMultiport(String multiport) {
		this.multiport = multiport;
	}

}
