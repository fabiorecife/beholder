package br.ufpe.cin.beholder.messages;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class UdpFloodListener implements UpdateListener{

	public void update(EventBean[] arg0, EventBean[] arg1) {
		System.out.println("**************************");
		System.out.println("UDP Flood Attack Detected ---------RFC (XXXX)");
		System.out.println("**************************");
		System.out.println("Check Firewall Rules ----------------------------------------------");
		System.out.println("**************************");
		
	}
	

}