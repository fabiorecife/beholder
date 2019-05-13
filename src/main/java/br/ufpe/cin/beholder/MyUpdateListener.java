package br.ufpe.cin.beholder;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class MyUpdateListener implements UpdateListener{

	public void update(EventBean[] arg0, EventBean[] arg1) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Detected Attack -----------------------------------");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Warning!!!!!!!!!----------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
		
	}
	

}
