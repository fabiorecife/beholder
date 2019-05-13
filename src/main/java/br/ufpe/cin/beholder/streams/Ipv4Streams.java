package br.ufpe.cin.beholder.streams;

import java.net.Inet4Address;
import java.net.InetAddress;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;

import com.espertech.esper.client.EPRuntime;

import br.ufpe.cin.beholder.pcap.Ipv4Packet;

public class Ipv4Streams extends Thread {

	private EPRuntime cepLocal;

	public Ipv4Streams(EPRuntime cepLocal) {
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

				IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);

				try {

					Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
					Inet4Address dstAddr = ipV4Packet.getHeader().getDstAddr();
					short length = ipV4Packet.getHeader().getTotalLength();

					this.cepLocal.sendEvent(new Ipv4Packet(srcAddr.toString(), dstAddr.toString(), length));
					Thread.sleep(1000);

				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println(ipV4Packet);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}