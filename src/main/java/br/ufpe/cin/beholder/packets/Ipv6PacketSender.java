package br.ufpe.cin.beholder.packets;

//import java.net.Inet6Address;

import org.pcap4j.packet.Packet;
import org.pcap4j.packet.IpV6Packet.IpV6FlowLabel;
import org.pcap4j.packet.IpV6Packet.IpV6TrafficClass;
import org.pcap4j.packet.namednumber.IpNumber;
import org.pcap4j.packet.namednumber.IpVersion;

public class Ipv6PacketSender {

	/*
	 * 0 16 32 +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * |Version| Traffic Class | Flow Label |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | Payload
	 * Length | Next Header | Hop Limit |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | | + + | |
	 * + Source Address + | | + + | |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | | + + | |
	 * + Destination Address + | | + + | |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 */

	private IpVersion version;
	private IpV6TrafficClass trafficClass;
	private IpV6FlowLabel flowLabel;
	private short payloadLength;
	private IpNumber nextHeader;
	private byte hopLimit;
	private String srcAddr;
	private String dstAddr;
	private Packet.Builder payloadBuilder;
	private boolean correctLengthAtBuild;

	public Ipv6PacketSender() {
	}

	public IpVersion getVersion() {
		return version;
	}

	public IpV6TrafficClass getTrafficClass() {
		return trafficClass;
	}

	public IpV6FlowLabel getFlowLabel() {
		return flowLabel;
	}

	public short getPayloadLength() {
		return payloadLength;
	}

	public IpNumber getNextHeader() {
		return nextHeader;
	}

	public byte getHopLimit() {
		return hopLimit;
	}

	public String getSrcAddr() {
		return srcAddr;
	}

	public String getDstAddr() {
		return dstAddr;
	}

	public Packet.Builder getPayloadBuilder() {
		return payloadBuilder;
	}

	public boolean isCorrectLengthAtBuild() {
		return correctLengthAtBuild;
	}
}