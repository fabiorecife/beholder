package br.ufpe.cin.beholder.packets;

import java.util.List;

import org.pcap4j.packet.IpV4Packet.IpV4Option;
import org.pcap4j.packet.IpV4Packet.IpV4Tos;
import org.pcap4j.packet.namednumber.IcmpV4Type;
import org.pcap4j.packet.namednumber.IpNumber;
import org.pcap4j.packet.namednumber.IpVersion;

public class Ipv4PacketSender {

	/*
	 * 0 16 31 +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * |Version| IHL |Type of Service| Total Length |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * Identification |Flags| Fragment Offset |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | Time to
	 * Live | Protocol | Header Checksum |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | Source
	 * Address | +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ |
	 * Destination Address |
	 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ | Options |
	 * Padding | +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 */

	private IpVersion version;
	private byte ihl;
	private IpV4Tos tos;
	private short length;
	private short identification;
	private boolean reservedFlag;
	private boolean dontFragmentFlag;
	private boolean moreFragmentFlag;
	private short fragmentOffset;
	private byte ttl;
	private IpNumber protocol;
	private short headerChecksum;
	private String srcAddr;
	private String dstAddr;
	private List<IpV4Option> options;
	private byte[] padding;
	//private int echoCount;
	IcmpV4Type echo = new IcmpV4Type((byte) 8, "Echo");

	public Ipv4PacketSender() {

	}

	public Ipv4PacketSender(String srcAddr, String dstAddr, short length,  IcmpV4Type echo) {
		this.length = length;
		this.srcAddr = srcAddr;
		this.dstAddr = dstAddr;
		//this.echoCount = echoCount; 
		this.echo = echo;
	}

	public IcmpV4Type getEcho() {
		return echo;
	}

	public IpVersion getVersion() {
		return version;
	}

	public byte getIhl() {
		return ihl;
	}

	public IpV4Tos getTos() {
		return tos;
	}

	public short getLength() {
		return length;
	}

	public short getIdentification() {
		return identification;
	}

	public boolean isReservedFlag() {
		return reservedFlag;
	}

	public boolean isDontFragmentFlag() {
		return dontFragmentFlag;
	}

	public boolean isMoreFragmentFlag() {
		return moreFragmentFlag;
	}

	public short getFragmentOffset() {
		return fragmentOffset;
	}

	public byte getTtl() {
		return ttl;
	}

	public IpNumber getProtocol() {
		return protocol;
	}

	public short getHeaderChecksum() {
		return headerChecksum;
	}

	public String getSrcAddr() {
		return srcAddr;
	}

	public String getDstAddr() {
		return dstAddr;
	}

	public List<IpV4Option> getOptions() {
		return options;
	}

	public byte[] getPadding() {
		return padding;
	}
	
	//public int getEchoCount() {
	//	return echoCount;
	//}

}
