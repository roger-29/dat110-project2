package no.roger.dat110.project2.iotsystem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import no.roger.dat110.project2.broker.BrokerServer;
import no.roger.dat110.project2.iotsystem.DisplayDevice;
import no.roger.dat110.project2.iotsystem.TemperatureDevice;

public class TestIoTSystem {

	@Test
	public void test() {

		System.out.println("IoT system starting...");

		Runnable display = () -> DisplayDevice.main(null);
		Runnable sensor = () -> TemperatureDevice.main(null);
		Runnable broker = () -> BrokerServer.main(null);

		Thread displaythread = new Thread(display);
		Thread sensorthread = new Thread(sensor);
		Thread brokerthread = new Thread(broker);

		System.out.println("Starting broker...");

		brokerthread.start();

		// Allow broker to reaching waiting for incoming connections
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Starting display...");
		displaythread.start();

		// Allow time for display to create topic

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Starting sensor...");
		sensorthread.start();

		try {

			displaythread.join();
			sensorthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// We check only termination here
		assertTrue(true);

		System.out.println("IoT system stopping...");
	}
}
