package br.ufpe.cin.beholder.streams;

import java.net.Inet6Address;
import java.net.InetAddress;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;

import com.espertech.esper.client.EPRuntime;

public class Ipv6Streams extends Thread {

	private EPRuntime cepLocal;

	public Ipv6Streams(EPRuntime cepLocal) {
		this.cepLocal = cepLocal;
	}

	public void run() {
		while (true) {
			try {
				InetAddress addr = InetAddress.getByName("192.168.0.104");
				PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);

				int snapLen = 65536;
				PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
				int timeout = 10;
				PcapHandle handle = nif.openLive(snapLen, mode, timeout);

				Packet packet = handle.getNextPacketEx();
				handle.close();

				IpV6Packet ipV6Packet = packet.get(IpV6Packet.class);

				try {

					Inet6Address srcAddr = ipV6Packet.getHeader().getSrcAddr();
					Inet6Address dstAddr = ipV6Packet.getHeader().getDstAddr();
					short length = ipV6Packet.getHeader().getPayloadLength();

					//this.cepLocal.sendEvent(new Ipv6PacketSender(length));
					Thread.sleep(1000);

				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println(ipV6Packet);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
