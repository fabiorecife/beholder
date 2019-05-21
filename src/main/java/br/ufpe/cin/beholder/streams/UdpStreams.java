package br.ufpe.cin.beholder.streams;

import java.net.Inet4Address;
//import java.net.Inet6Address;
import java.net.InetAddress;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.UdpPacket;
//import org.pcap4j.packet.namednumber.UdpPort;
import org.pcap4j.packet.namednumber.UdpPort;

import com.espertech.esper.client.EPRuntime;

import br.ufpe.cin.beholder.packets.UdpPacketSender;

public class UdpStreams extends Thread {

	private EPRuntime cepLocal;

	public UdpStreams(EPRuntime cepLocal) {
		this.cepLocal = cepLocal;
	}

	public void run() {
		int countUdpPacket = 0;

		while (true) {
			try {
				InetAddress addr = InetAddress.getByName("10.5.50.251");
				PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);

				int snapLen = 65536;
				PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
				int timeout = 10;
				PcapHandle handle = nif.openLive(snapLen, mode, timeout);

				Packet packet = handle.getNextPacketEx();
				handle.close();

				IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
				// IpV6Packet ipV6Packet = packet.get(IpV6Packet.class);
				UdpPacket udpPacket = packet.get(UdpPacket.class);
				// UdpPort srcPort = udp
				try {

					Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
					Inet4Address dstAddr = ipV4Packet.getHeader().getDstAddr();
					UdpPort srcPort = udpPacket.getHeader().getSrcPort();
					UdpPort dstPort = udpPacket.getHeader().getDstPort();

					if (udpPacket != null) {
						countUdpPacket++;
					} 

					Thread.sleep(20);
					this.cepLocal.sendEvent(
							new UdpPacketSender(srcAddr.toString(), dstAddr.toString(), srcPort, dstPort, countUdpPacket, udpPacket));
					System.out.println(udpPacket);

				} catch (Exception e) {
					// TODO: handle exception

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
