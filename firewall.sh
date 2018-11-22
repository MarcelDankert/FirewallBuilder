echo "asda"
/sbin/iptables -A FORWARD -p  tcp -m tcp -m multiport -m mac --mac-source asdasd -s asd -d asd --dports asd,asd -m conntrack --ctstate NEW -j ACCEPT
echo "catch all"
/sbin/iptables -A INPUT -j DROP
/sbin/iptables -A OUTPUT -j DROP
/sbin/iptables -A FORWARD -j DROP

/sbin/sysctl -w net.ipv4.ip_forward=1