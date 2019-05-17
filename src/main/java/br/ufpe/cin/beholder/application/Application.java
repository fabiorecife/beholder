package br.ufpe.cin.beholder.application;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import br.ufpe.cin.beholder.messages.IcmpV4FloodListener;
import br.ufpe.cin.beholder.messages.SynFloodListener;
import br.ufpe.cin.beholder.messages.UdpFloodListener;
import br.ufpe.cin.beholder.old.StreamApp;
import br.ufpe.cin.beholder.streams.Ipv4Streams;
import br.ufpe.cin.beholder.streams.TcpStreams;
import br.ufpe.cin.beholder.streams.UdpStreams;

public class Application {

	public static void main(String[] args) {
		try {
			Configuration config = new Configuration();
			config.addEventType("tcptStream", TcpStreams.class);
			config.addEventType("udpStream", UdpStreams.class);
			config.addEventType("ipV4Stream", Ipv4Streams.class);

			EPServiceProvider cep = EPServiceProviderManager.getProvider("esper", config);

			// ---------Syn Flood
			EPStatement synFloodStatement = cep.getEPAdministrator()
					.createEPL("select *, count(syn) as CountSyn from eventStream.win:time(20 sec) having count(syn) > 3 and not(ack)");
			synFloodStatement.addListener(new SynFloodListener());

			// ----------UDP Flood
			EPStatement udpFloodStatement = cep.getEPAdministrator()
					.createEPL("select * from eventStream.win:time(1 sec) group by srcAddr where countUdpPacket > 200");
			udpFloodStatement.addListener(new UdpFloodListener());

			 //----------ICMP flood
			EPStatement icmpFloodStatement = cep.getEPAdministrator().createEPL("select *from ipV4Stream.win:time(1) where srcAddr = dstAddr and Cont > 100");
			icmpFloodStatement.addListener(new IcmpV4FloodListener());

			new StreamApp(cep.getEPRuntime()).start();
			System.out.println("--------------------------------------");
			System.out.println("Starting Beholder---------------------");
			System.out.println("--------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
