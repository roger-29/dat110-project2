package no.roger.dat110.project2.broker.processing;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import no.roger.dat110.project2.client.Client;

public class Test1ConnectDisconnect extends Test0Base {

	@Test
	public void test() {
		
		Client client = new Client("testuser",BROKER_TESTHOST,BROKER_TESTPORT);
		
		broker.setMaxAccept(1); // only 1 connect in this scenario
		
		client.connect();
		
		client.disconnect();
		
		assertTrue(true);
	}
}
