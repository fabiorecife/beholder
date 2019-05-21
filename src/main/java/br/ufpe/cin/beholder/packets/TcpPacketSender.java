package br.ufpe.cin.beholder.packets;

public class TcpPacketSender {

	private String srcAddr;
	private String dstAddr;
	private int packetLength;
	private boolean syn;
	private boolean ack;
	private int synCount;
	// TcpPacket tcpPacket;

	public TcpPacketSender(String srcAddr, String dstAddr, int packetLength, boolean syn, boolean ack, int synCount) {
		this.srcAddr = srcAddr;
		this.dstAddr = dstAddr;
		this.packetLength = packetLength;
		this.syn = syn;
		this.ack = ack;
		this.synCount = synCount;
		// this.tcpPacket = tcpPacket;
		// this.tcpPacket = tcpPacket;
		// this.synCount = synCount;
	}

	public int getPacketLength() {
		return packetLength;
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
