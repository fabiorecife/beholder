package br.ufpe.cin.beholder.old;

import java.net.Inet4Address;
import java.net.InetAddress;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.namednumber.TcpPort;

public class TcpMain {

	public static void main(String[] args) {

		try {
			InetAddress addr = InetAddress.getByName("192.168.10.110");
			PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);

			int snapLen = 65536;
			PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
			int timeout = 10;
			PcapHandle handle = nif.openLive(snapLen, mode, timeout);

			Packet packet = handle.getNextPacketEx();
			handle.close();

			IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
			// IpV6Packet ipV6Packet = packet.get(IpV6Packet.class);
			TcpPacket tcpPacket = packet.get(TcpPacket.class);
			
			int synCount = 0;
			try {
				Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
				Inet4Address dstAddr = ipV4Packet.getHeader().getDstAddr();
				TcpPort srcPort = tcpPacket.getHeader().getSrcPort();
				// short length = ipV4Packet.getHeader().getTotalLength();
				// int tcpLength = tcpPacket.getHeader()length();
				boolean syn = tcpPacket.getHeader().getSyn();
				boolean ack = tcpPacket.getHeader().getAck();
				/*
				if (syn = true) {
					synCount++;
				}
				else synCount = 0;
				
				//}
				/*
				 * for (int synCount = 0; syn = true; synCount++) { synCount++;
				 * System.out.println(synCount); }
				 */
				System.out.println(srcPort);

				Thread.sleep(10000);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
