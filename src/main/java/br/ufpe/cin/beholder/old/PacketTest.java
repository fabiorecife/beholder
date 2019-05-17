package br.ufpe.cin.beholder.old;

//import org.pcap4j.packet.TcpPacket;

public class PacketTest {

	private String srcAddr;
	private String dstAddr;
	private short length;
	private boolean syn;
	private boolean ack;
	private int synCount;
	// TcpPacket tcpPacket;

	public PacketTest(String srcAddr, String dstAddr, boolean syn, boolean ack, int synCount) {
		this.srcAddr = srcAddr;
		this.dstAddr = dstAddr;
		// this.length = length;
		this.syn = syn;
		this.ack = ack;
		this.synCount = synCount;
		// this.tcpPacket = tcpPacket;
		// this.tcpPacket = tcpPacket;
		// this.synCount = synCount;
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

	public boolean isSyn() {
		return syn;
	}

	public boolean isAck() {
		return ack;
	}

	public int getSynCount() {
		return synCount;
	}

}
