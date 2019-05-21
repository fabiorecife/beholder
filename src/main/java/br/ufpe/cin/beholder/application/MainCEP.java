package br.ufpe.cin.beholder.application;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import br.ufpe.cin.beholder.messages.IcmpV4FloodListener;
//import br.ufpe.cin.beholder.messages.LandListener;
import br.ufpe.cin.beholder.messages.SynFloodListener;
import br.ufpe.cin.beholder.messages.UdpFloodListener;
import br.ufpe.cin.beholder.packets.Ipv4PacketSender;
import br.ufpe.cin.beholder.packets.TcpPacketSender;
import br.ufpe.cin.beholder.packets.UdpPacketSender;
import br.ufpe.cin.beholder.streams.Ipv4Streams;
import br.ufpe.cin.beholder.streams.TcpStreams;
import br.ufpe.cin.beholder.streams.UdpStreams;

public class MainCEP {

	public static void main(String[] args) {
		try {
			Configuration config = new Configuration();
			config.addEventType("TCP_Stream", TcpPacketSender.class);
			config.addEventType("UDP_Stream", UdpPacketSender.class);
			config.addEventType("ICMPv4_Stream", Ipv4PacketSender.class);
			config.addEventType("Land_Stream", TcpPacketSender.class);

			EPServiceProvider tcpCep = EPServiceProviderManager.getProvider("esper-tcp", config);
			EPServiceProvider udpCep = EPServiceProviderManager.getProvider("esper- udp", config);
			EPServiceProvider icmpCep = EPServiceProviderManager.getProvider("esper-icmpv4", config);
			//EPServiceProvider landCep = EPServiceProviderManager.getProvider("esper-land", config);

			//-------------------- DoS ATTACKS!----------------------------
			
			 //---------Syn Flood
			EPStatement synFloodStatement = tcpCep.getEPAdministrator().createEPL("select count(syn), srcAddr, dstAddr "
					+ "from TCP_Stream.win:time(2 sec) having count(syn) > 3 and not(ack)");
			synFloodStatement.addListener(new SynFloodListener());

			// ----------UDP Flood
			EPStatement udpFloodStatement = udpCep.getEPAdministrator()
					.createEPL("select count(udpPacket), srcAddr "
							+ "from UDP_Stream.win:time(20 sec) having count(udpPacket) > 10");
			udpFloodStatement.addListener(new UdpFloodListener());
			
			// ----------ICMP flood
			EPStatement icmpFloodStatement = icmpCep.getEPAdministrator()
					.createEPL("select count(echo) as echoCount , srcAddr, dstAddr from ICMPv4_Stream.win:time(20 sec) having count(echo) > 3 ");
			icmpFloodStatement.addListener(new IcmpV4FloodListener());
			
		
			new TcpStreams(tcpCep.getEPRuntime()).start();
			new UdpStreams(udpCep.getEPRuntime()).start();
			new Ipv4Streams(icmpCep.getEPRuntime()).start();
			//new TcpStreams(landCep.getEPRuntime()).start();
			System.out.println("--------------------------------------");
			System.out.println("Starting Beholder---------------------");
			System.out.println("--------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
