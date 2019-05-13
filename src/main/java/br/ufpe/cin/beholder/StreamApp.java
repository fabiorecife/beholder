package br.ufpe.cin.beholder;

import java.net.Inet4Address;
//import java.net.Inet6Address;
import java.net.InetAddress;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;

import com.espertech.esper.client.EPRuntime;

public class StreamApp extends Thread {

	private EPRuntime cepLocal;

	public StreamApp(EPRuntime cepLocal) {
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
				int synCount = 0;

				Packet packet = handle.getNextPacketEx();
				handle.close();

				IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
				// IpV6Packet ipV6Packet = packet.get(IpV6Packet.class);
				TcpPacket tcpPacket = packet.get(TcpPacket.class);

				try {

					Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
					Inet4Address dstAddr = ipV4Packet.getHeader().getDstAddr();
					short length = ipV4Packet.getHeader().getTotalLength();
					// int tcpLength = tcpPacket.getHeader()length();
					boolean syn = tcpPacket.getHeader().getSyn();
					boolean ack = tcpPacket.getHeader().getAck();

					// if (syn = true) {
					// synCount++;
					// }

					if ((packet == packet.get(TcpPacket.class)) && (syn = true)) {
						synCount++;
					}

					this.cepLocal.sendEvent(new PacketTest(srcAddr.toString(), dstAddr.toString(), length, syn, ack, synCount));
					Thread.sleep(1000);

				} catch (Exception e) {
					// TODO: handle exceptionc
				}
				System.out.println(packet);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
