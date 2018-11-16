package controller;
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

public class CreateFile {

	private boolean checkFile(File file) {
		if (file != null) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("Error creating " + file.toString());
			}
			if (file.isFile() && file.canWrite() && file.canRead())
				return true;
		}
		return false;
	}
	public static void usingBufferedWritter() throws IOException
	{
	    String fileContent = "#!/bin/sh\r\n" + 
	    		"\r\n" + 
	    		"IPT=\"/sbin/iptables\"\r\n" + 
	    		"\r\n" + 
	    		"IU=\"/sbin/iptables -A INPUT -p udp -m udp\"\r\n" + 
	    		"IT=\"/sbin/iptables -A INPUT -p tcp -m tcp\"\r\n" + 
	    		"II=\"/sbin/iptables -A INPUT -p icmp -m icmp --icmp-type 8/0\"\r\n" + 
	    		"\r\n" + 
	    		"FU=\"/sbin/iptables -A FORWARD -p udp -m udp\"\r\n" + 
	    		"FT=\"/sbin/iptables -A FORWARD -p tcp -m tcp\"\r\n" + 
	    		"FI=\"/sbin/iptables -A FORWARD -p icmp -m icmp --icmp-type 8/0\"\r\n" + 
	    		"\r\n" + 
	    		"OU=\"/sbin/iptables -A OUTPUT -p udp -m udp\"\r\n" + 
	    		"OT=\"/sbin/iptables -A OUTPUT -p tcp -m tcp\"\r\n" + 
	    		"OI=\"/sbin/iptables -A OUTPUT -p icmp -m icmp --icmp-type 8/0\"\r\n" + 
	    		"\r\n" + 
	    		"MAC=\"-m mac --mac-source \"\r\n" + 
	    		"\r\n" + 
	    		"MP=\"-m multiport\"\r\n" + 
	    		"\r\n" + 
	    		"R=\"-m conntrack --ctstate NEW -j ACCEPT\"\r\n" + 
	    		"\r\n" + 
	    		"modprobe ip_conntrack \r\n" + 
	    		"modprobe ip_conntrack_ftp\r\n" + 
	    		"\r\n" + 
	    		"$IPT -F\r\n" + 
	    		"$IPT -X\r\n" + 
	    		"$IPT -t nat -F\r\n" + 
	    		"$IPT -t nat -X\r\n" + 
	    		"$IPT -t mangle -F\r\n" + 
	    		"$IPT -t mangle -X\r\n" + 
	    		"\r\n" + 
	    		"$IPT -P INPUT DROP\r\n" + 
	    		"$IPT -P OUTPUT DROP\r\n" + 
	    		"$IPT -P FORWARD DROP\r\n" + 
	    		"\r\n" + 
	    		"$IPT -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT \r\n" + 
	    		"$IPT -A OUTPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT \r\n" + 
	    		"$IPT -A FORWARD -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT\r\n" + 
	    		"\r\n" + 
	    		"\r\n" + 
	    		" echo \"alles auf localhost\"\r\n" + 
	    		"$IPT -A INPUT -i lo $R\r\n" + 
	    		"$IPT -A OUTPUT -o lo $R\r\n" + 
	    		"\r\n" + 
	    		"# Anfang eigene Regeln\r\n" + 
	    		"Platzhalter f�r die eigenen Regeln\r\n" + 
	    		"# Ende eigene Regeln \r\n" + 
	    		"echo \"catch all \r\n" +
	    		"$IPT -A INPUT -j DROP \r\n" +
	    		"$IPT -A OUTPUT -j DROP \r\n" +
	    		"$IPT -A FORWARD -j DROP \r\n" +
	    		"/sbin/sysctl -w net.ipv4.ip_forward=1";
	     
	    BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\\\Users\\\\Administrator\\\\Desktop\\\\firewall.sh"));
	    writer.write(fileContent);
	    writer.close();
	}
	public static void main(String[] args) {
		CreateFile da = new CreateFile();
		String dat = "C:\\Users\\Administrator\\Desktop\\firewall.sh";
		if (da.checkFile(new File(dat)))
			System.out.println(dat + " erzeugt");
		try {
			usingBufferedWritter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
