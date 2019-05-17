package br.ufpe.cin.beholder.packets;

import java.net.InetAddress;

import org.pcap4j.packet.namednumber.ArpHardwareType;
import org.pcap4j.packet.namednumber.ArpOperation;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.util.MacAddress;

public class ArpFrameSender {

	private ArpHardwareType hardwareType;
	private EtherType protocolType;
	private byte hardwareAddrLength;
	private byte protocolAddrLength;
	private ArpOperation operation;
	private MacAddress srcHardwareAddr;
	private InetAddress srcProtocolAddr;
	private MacAddress dstHardwareAddr;
	private InetAddress dstProtocolAddr;

	public ArpFrameSender() {

	}

	public ArpHardwareType getHardwareType() {
		return hardwareType;
	}

	public EtherType getProtocolType() {
		return protocolType;
	}

	public byte getHardwareAddrLength() {
		return hardwareAddrLength;
	}

	public byte getProtocolAddrLength() {
		return protocolAddrLength;
	}

	public ArpOperation getOperation() {
		return operation;
	}

	public MacAddress getSrcHardwareAddr() {
		return srcHardwareAddr;
	}

	public InetAddress getSrcProtocolAddr() {
		return srcProtocolAddr;
	}

	public MacAddress getDstHardwareAddr() {
		return dstHardwareAddr;
	}

	public InetAddress getDstProtocolAddr() {
		return dstProtocolAddr;
	}

}
