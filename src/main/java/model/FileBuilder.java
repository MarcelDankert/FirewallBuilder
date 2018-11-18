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

public class FileBuilder {
	private String newRule;

	public String getNewRule() {
		return newRule;
	}

	public void setNewRule(String newRule) {
		this.newRule = newRule;
	}

	public void createFile(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
				this.usingBufferedWritter();
			} catch (IOException e) {
				System.err.println("Error creating " + file.toString());
			}
		}
	}

	public void usingBufferedWritter() throws IOException {
		String fileIntro = "#!/bin/sh\r\n" + "\r\n" + "IPT=\"/sbin/iptables\"\r\n" + "\r\n"
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
		writer.write(fileIntro);
		writer.close();
	}
}
