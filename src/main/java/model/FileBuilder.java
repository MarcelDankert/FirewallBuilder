package model;
/**
 * 
 */

/**
 * @author s15
 *
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileBuilder {
	private String name, richtung, protokoll, quelle, ziel, mac, newRule;
	private ArrayList<String> ports;
	private HashMap<String, String> map;
	
	public FileBuilder() {
		this.resetFileBuilder();
		this.map = new HashMap<>();
		map.put("IPT", "/sbin/iptables");
		map.put("TCP", "tcp -m tcp");
		map.put("UDP", "udp -m udp");
		map.put("ICMP", "icmp -m icmp --icmp-type 8/0");
	}
	public String buildNewRule() {
		newRule = map.get("IPT") + " -A " + this.richtung + " -p " + map.get(this.protokoll);
		return newRule;
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
		String fileContent = "#!/bin/sh\r\n" + "\r\n" + "IPT=\"/sbin/iptables\"\r\n" + "\r\n"
				+ "IU=\"/sbin/iptables -A INPUT -p udp -m udp\"\r\n"
				+ "IT=\"/sbin/iptables -A INPUT -p tcp -m tcp\"\r\n"
				+ "II=\"/sbin/iptables -A INPUT -p icmp -m icmp --icmp-type 8/0\"\r\n" + "\r\n"
				+ "FU=\"/sbin/iptables -A FORWARD -p udp -m udp\"\r\n"
				+ "FT=\"/sbin/iptables -A FORWARD -p tcp -m tcp\"\r\n"
				+ "FI=\"/sbin/iptables -A FORWARD -p icmp -m icmp --icmp-type 8/0\"\r\n" + "\r\n"
				+ "OU=\"/sbin/iptables -A OUTPUT -p udp -m udp\"\r\n"
				+ "OT=\"/sbin/iptables -A OUTPUT -p tcp -m tcp\"\r\n"
				+ "OI=\"/sbin/iptables -A OUTPUT -p icmp -m icmp --icmp-type 8/0\"\r\n" + "\r\n"
				+ "MAC=\"-m mac --mac-source \"\r\n" + "\r\n" + "MP=\"-m multiport\"\r\n" + "\r\n"
				+ "R=\"-m conntrack --ctstate NEW -j ACCEPT\"\r\n" + "\r\n" + "modprobe ip_conntrack \r\n"
				+ "modprobe ip_conntrack_ftp\r\n" + "\r\n" + "$IPT -F\r\n" + "$IPT -X\r\n" + "$IPT -t nat -F\r\n"
				+ "$IPT -t nat -X\r\n" + "$IPT -t mangle -F\r\n" + "$IPT -t mangle -X\r\n" + "\r\n"
				+ "$IPT -P INPUT DROP\r\n" + "$IPT -P OUTPUT DROP\r\n" + "$IPT -P FORWARD DROP\r\n" + "\r\n"
				+ "$IPT -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT \r\n"
				+ "$IPT -A OUTPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT \r\n"
				+ "$IPT -A FORWARD -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT\r\n" + "\r\n" + "\r\n"
				+ " echo \"alles auf localhost\"\r\n" + "$IPT -A INPUT -i lo $R\r\n" + "$IPT -A OUTPUT -o lo $R\r\n"
				+ "\r\n" + "# Anfang eigene Regeln\r\n" + "<#\r\n" + "Platzhalter für die eigenen Regeln\r\n" + "#>\r\n"
				+ "# Ende eigene Regeln \r\n" + "echo \"catch all \r\n" + "$IPT -A INPUT -j DROP \r\n"
				+ "$IPT -A OUTPUT -j DROP \r\n" + "$IPT -A FORWARD -j DROP \r\n"
				+ "/sbin/sysctl -w net.ipv4.ip_forward=1";

		BufferedWriter writer = new BufferedWriter(new FileWriter("firewall.sh"));
		writer.write(fileContent);
		writer.close();
	}

	public HashMap<String, String> getMap() {
		return map;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
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

}
