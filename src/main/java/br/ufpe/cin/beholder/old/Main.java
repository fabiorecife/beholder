package br.ufpe.cin.beholder.old;

//import org.pcap4j.packet.IpV4Packet;
//import org.pcap4j.packet.Packet;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import br.ufpe.cin.beholder.messages.SynFloodListener;
import br.ufpe.cin.beholder.messages.UdpFloodListener;
import br.ufpe.cin.beholder.streams.UdpStreams;

public class Main {

	public static void main(String[] args) {
		try {
			Configuration config = new Configuration();
			config.addEventType("eventStream", PacketTest.class);
			config.addEventType("udpStreams", UdpStreams.class);
			// config.addEventType("tcpStream", TcpStreams.class);

			EPServiceProvider cep = EPServiceProviderManager.getProvider("esper", config);

			// ---------Syn Flood
			EPStatement synFloodStatement = cep.getEPAdministrator()
					.createEPL("select *, count(syn) as CountSyn from eventStream.win:time(20 sec) having count(syn) > 3 and not(ack)");
			synFloodStatement.addListener(new SynFloodListener());

			// ----------UDP Flood
			EPStatement udpFloodStatement = cep.getEPAdministrator()
					.createEPL("select * from eventStream.win:time(1 sec) group by srcAddr where countUdpPacket > 10");
			udpFloodStatement.addListener(new UdpFloodListener());

			// ----------ICMP flood
			// EPStatement icmpFloodStatement = cep.getEPAdministrator().createEPL("select *
			// from eventStream.win:time where srcAddr = dstAddr and synCont > 100 ");
			// icmpFloodStatement.addListener(new MyUpdateListener());

			new StreamApp(cep.getEPRuntime()).start();
			System.out.println("--------------------------------------");
			System.out.println("Starting Beholder---------------------");
			System.out.println("--------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
