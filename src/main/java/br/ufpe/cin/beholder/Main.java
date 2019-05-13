package br.ufpe.cin.beholder;

//import org.pcap4j.packet.IpV4Packet;
//import org.pcap4j.packet.Packet;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Main {

	public static void main(String[] args) {
		try {
			Configuration config = new Configuration();
			config.addEventType("eventStream", PacketTest.class);
			// config.addEventType("tcpStream", TcpStreams.class);
			// config.addEventType("udpStreams", UdpStreams.class);

			EPServiceProvider cep = EPServiceProviderManager.getProvider("esper", config);

			// ---------Syn Flood
			EPStatement synFloodStatement = cep.getEPAdministrator()
					.createEPL("select * from eventStream.win:time(1 sec) where synCount > 100 and not (ack)");
			synFloodStatement.addListener(new MyUpdateListener());

			//--------Land Attack
			EPStatement landStatement = cep.getEPAdministrator().createEPL("select * from eventStream.win:time where srcAddr = dstAddr and synCont > 100");
			landStatement.addListener(new MyUpdateListener());

			// ----------ICMP flood
			//EPStatement icmpFloodStatement = cep.getEPAdministrator().createEPL("select * from eventStream.win:time where srcAddr = dstAddr and synCont > 100 ");
			//icmpFloodStatement.addListener(new MyUpdateListener());
			
			// ----------UDP Flood
						/*
						 * UDP_Flood EPStatement udpFloodStatement = cep.getEPAdministrator()
						 * .createEPL("select *, count(dstAddr) from eventStream.win:time(5 sec) " +
						 * "group by srcAddr having count(dstAddr) > 15");
						 * udpFloodStatement.addListener(new MyUpdateListener());
						 */
						// ---

			// EPStatement statement = cep.getEPAdministrator() .createEPL("select * from
			// eventStream where srcAddr = '/192.168.0.104'");
			// statement.addListener(new MyUpdateListener());

			/*
			 * UDP_Flood EPStatement udpFloodStatement = cep.getEPAdministrator()
			 * .createEPL("select *, count(dstAddr) from eventStream.win:time(5 sec) " +
			 * "group by srcAddr having count(dstAddr) > 15");
			 * udpFloodStatement.addListener(new MyUpdateListener());
			 */

			// ---------Land Attack
			// EPStatement landStatement = cep.getEPAdministrator().createEPL("select * from
			// eventStream where length > 65536");
			// landStatement.addListener(new MyUpdateListener());
			// EPStatement statement = cep.getEPAdministrator() .createEPL("select
			// count(udpPacket) as packetCount from cameraStream.win:time_batch(60 msec) "
			// + "having count(udpPacket) > 10");
			// EPStatement statement = cep.getEPAdministrator() .createEPL("select * from
			// cameraStream.win:time_batch(60 sec) where length > 200");
			// EPStatement statement = cep.getEPAdministrator() .createEPL("select * from ip
			// where dstAddr= '/172.22.73.254' OR srcAddr = '172.22.70.37'");
			// EPStatement statement = cep.getEPAdministrator() .createEPL("select * from ip
			// where dstAddr or srcAddr = '/74.125.250.27'");

			//

			new StreamApp(cep.getEPRuntime()).start();
			System.out.println("--------------------------------------");
			System.out.println("Starting Beholder---------------------");
			System.out.println("--------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
