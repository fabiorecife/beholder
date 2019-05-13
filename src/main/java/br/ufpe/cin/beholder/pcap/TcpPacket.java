package br.ufpe.cin.beholder.pcap;

public class TcpPacket {

	private String srcAddr;
	private String dstAddr;
	private short length;
	private boolean syn;
	private boolean ack;
	private int synCount;

	public TcpPacket(String srcAddr, String dstAddr,  short length, boolean syn, boolean ack, int synCount) {
		this.srcAddr = srcAddr;
		this.dstAddr = dstAddr;
		this.length = length;
		this.syn = syn;
		this.ack = ack;
		this.synCount = synCount;
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

	public int getSynCount() {
		return synCount;
	}

	public void setSynCount(int synCount) {
		this.synCount = synCount;
	}

	public boolean isSyn() {
		return syn;
	}

	public boolean isAck() {
		return ack;
	}

	public void setSyn(boolean syn) {
		this.syn = syn;
	}

	public void setAck(boolean ack) {
		this.ack = ack;
	}

}
