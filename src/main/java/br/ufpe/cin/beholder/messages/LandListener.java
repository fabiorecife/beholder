package br.ufpe.cin.beholder.messages;


import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class LandListener implements UpdateListener{

	public void update(EventBean[] arg0, EventBean[] arg1) {
		
		
		System.out.println("**************************");
		System.out.println("Land Attack Detected ---------RFC (XXXX)");
		System.out.println("Check Firewall Rules ----------------------------------------------");
		System.out.println("**************************");
		
	}
	

}