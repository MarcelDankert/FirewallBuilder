echo "qwe"
/sbin/iptables -A INPUT -p tcp -m tcp -m mac --mac-source 213213 -s qwewqe -d qwewqe --dport 213,12123 -m conntrack --ctstate NEW -j ACCEPT