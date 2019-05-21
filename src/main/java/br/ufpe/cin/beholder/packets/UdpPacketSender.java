package br.ufpe.cin.beholder.packets;

import org.pcap4j.packet.UdpPacket;
//import org.pcap4j.packet.UdpPacket;
import org.pcap4j.packet.UdpPacket.UdpHeader;
import org.pcap4j.packet.namednumber.UdpPort;

//import java.net.InetAddress;

//import org.pcap4j.packet.Packet;
//import org.pcap4j.packet.namednumber.UdpPort;

public class UdpPacketSender {

	
	//private short length;
	// private short checksum;
	// private Packet.Builder payloadBuilder;
	private String srcAddr;
	private String dstAddr;
	private UdpPort srcPort;
	private UdpPort  dstPort;
	private int countUdpPacket;
	private UdpPacket udpPacket;
	//private UdpHeader udpHeader;
	// private boolean correctLengthAtBuild;
	// private boolean correctChecksumAtBuild;

	public UdpPacketSender(String srcAddr, String dstAddr, UdpPort  srcPort, UdpPort  dstPort, int countUdpPacket, UdpPacket udpPacket) {

		this.srcAddr = srcAddr;
		this.dstAddr = dstAddr;
		this.srcPort = srcPort;
		this.dstPort = dstPort;
		this.countUdpPacket = countUdpPacket;
		this.udpPacket = udpPacket;
	}

	public UdpPort getSrcPort() {
		return srcPort;
	}

	public UdpPort getDstPort() {
		return dstPort;
	}

	public String getSrcAddr() {
		return srcAddr;
	}

	public String getDstAddr() {
		return dstAddr;
	}

	public int getCountUdpPacket() {
	return countUdpPacket;
}

	public UdpPacket getUdpPacket() {
		return udpPacket;
	}

}
