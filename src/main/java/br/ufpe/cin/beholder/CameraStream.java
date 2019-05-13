package br.ufpe.cin.beholder;

import org.pcap4j.packet.Packet;
import org.pcap4j.packet.UdpPacket;
import br.ufpe.cin.beholder.Counter;

public class CameraStream {

	private String srcAddr;
	private String dstAddr;
	private short length;
	private UdpPacket udpPacket;
	Packet packet;
	private Counter packetCount;

	// public PacketTest(String srcAddr, String dstAddr, short length, int
	// udpPacketCount, UdpPacket udpPacket)
	public CameraStream(String srcAddr, String dstAddr, short length, UdpPacket udpPacket, Packet packet,
			Counter packetCount) {
		this.srcAddr = srcAddr;
		this.dstAddr = dstAddr;
		this.length = length;
		this.udpPacket = udpPacket;
		this.packet = packet;
		this.packetCount = packetCount;
	}

	public short getPacketLen() {
		return length;
	}

	public String getSrcAddr() {
		return srcAddr;
	}

	public String getDstAddr() {
		return dstAddr;
	}

	public void setSrcAddr(String srcAddr) {
		this.srcAddr = srcAddr;
	}

	public void setDstAddr(String dstAddr) {
		this.dstAddr = dstAddr;
	}

	public short getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	public UdpPacket getUdpPacket() {
		return udpPacket;
	}

	public void setUdpPacket(UdpPacket udpPacket) {
		this.udpPacket = udpPacket;
	}

	public Counter getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(Counter packetCount) {
			if (packet == udpPacket) {
			this.packetCount.toIncrement();
		}
		/*
		 * public int getUdpPacketCount() { return udpPacketCount; }
		 * 
		 * public void setUdpPacketCount(int udpPacketCount) { this.udpPacketCount =
		 * udpPacketCount; }
		 */
	}
}