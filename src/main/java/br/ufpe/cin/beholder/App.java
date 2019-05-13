package br.ufpe.cin.beholder;

import java.io.EOFException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnknownHostException, PcapNativeException, EOFException, TimeoutException, NotOpenException
    {
    	InetAddress addr = InetAddress.getByName("172.22.70.37");
    	PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);
    	
    	int snapLen = 65536;
    	PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
    	int timeout = 10;
    	PcapHandle handle = nif.openLive(snapLen, mode, timeout);
    	
    	Packet packet = handle.getNextPacketEx();
    	handle.close();
    	
    	IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
    	Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
    	Inet4Address dstAddr = ipV4Packet.getHeader().getDstAddr();
    	//short length = ipV4Packet.getHeader().getTotalLength();
    	System.out.println(srcAddr);
    	System.out.println(dstAddr);
    	//System.out.println(length);
    	//System.out.println("----------------------------------------");
    	//System.out.println(packet);
    }	
}
