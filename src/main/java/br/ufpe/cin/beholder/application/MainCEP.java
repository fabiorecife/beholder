package br.ufpe.cin.beholder.application;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import br.ufpe.cin.beholder.messages.SynFloodListener;
import br.ufpe.cin.beholder.messages.UdpFloodListener;
import br.ufpe.cin.beholder.packets.Ipv4PacketSender;
import br.ufpe.cin.beholder.packets.TcpPacketSender;
import br.ufpe.cin.beholder.packets.UdpPacketSender;
import br.ufpe.cin.beholder.streams.TcpStreams;

public class MainCEP {

	public static void main(String[] args) {
		try {
			Configuration config = new Configuration();
			config.addEventType("TCP_Stream", TcpPacketSender.class);
			config.addEventType("UDP_Stream", UdpPacketSender.class);
			config.addEventType("ICMP_Stream", Ipv4PacketSender.class);
			config.addEventType("Land_Stream", Ipv4PacketSender.class);

			EPServiceProvider tcpCep = EPServiceProviderManager.getProvider("esper-tcp", config);
			EPServiceProvider udpCep = EPServiceProviderManager.getProvider("esper- udp", config);
			EPServiceProvider icmpCep = EPServiceProviderManager.getProvider("esper-icmp", config);
			EPServiceProvider landCep = EPServiceProviderManager.getProvider("esper-land", config);

			// ---------Syn Flood
			EPStatement synFloodStatement = tcpCep.getEPAdministrator().createEPL(
					"select count(syn), srcAddr, dstAddr from TCP_Stream.win:time(2 sec) having count(syn) > 3 and not(ack)");
			synFloodStatement.addListener(new SynFloodListener());

			// ----------UDP Flood
			EPStatement udpFloodStatement = udpCep.getEPAdministrator()
					.createEPL("select count(udpPacket) as countUdpPacket, srcAddr "
							+ "from UDP_Stream.win:time(1 sec) having count(udpPacket) > 10");
			udpFloodStatement.addListener(new UdpFloodListener());

			// ----------ICMP flood
			// EPStatement icmpFloodStatement = cep.getEPAdministrator().createEPL("select *
			// from eventStream.win:time where srcAddr = dstAddr and synCont > 100 ");
			// icmpFloodStatement.addListener(new MyUpdateListener());

			// ----------Land Attack
			// EPStatement icmpFloodStatement = cep.getEPAdministrator().createEPL("select *
						// from eventStream.win:time where srcAddr = dstAddr and synCont > 100 ");
						// icmpFloodStatement.addListener(new MyUpdateListener());

			new TcpStreams(tcpCep.getEPRuntime()).start();
			new TcpStreams(udpCep.getEPRuntime()).start();
			System.out.println("--------------------------------------");
			System.out.println("Starting Beholder---------------------");
			System.out.println("--------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
