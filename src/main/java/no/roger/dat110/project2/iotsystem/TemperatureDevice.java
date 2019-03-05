package no.roger.dat110.project2.iotsystem;

import no.roger.dat110.project2.client.Client;

public class TemperatureDevice {
	
	private static final int COUNT = 10;
	
	public static void main(String[] args) {
		
		TemperatureSensor sn = new TemperatureSensor();
		
		Client client = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);

		client.connect();

		for (int i = 0; i < COUNT; i++) {
			client.publish(Common.TEMPTOPIC, Integer.toString(sn.read()));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		client.disconnect();

		System.out.println("Temperature device stopping ... ");
	}
}
