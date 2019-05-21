package br.ufpe.cin.beholder.messages;

import org.pcap4j.util.MacAddress;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class SynFloodListener implements UpdateListener{

	public void update(EventBean[] arg0, EventBean[] arg1) {
		
		MacAddress srcHardwareAddr;
		
		System.out.println("**************************");
		System.out.println("Syn Flood Attack Detected ---------RFC (XXXX)");
		System.out.println("Check Firewall Rules ----------------------------------------------");
		System.out.println("**************************");
		
	}
	

}
