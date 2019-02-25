package no.roger.dat110.project2.broker.processing;

import org.junit.Test;

import no.roger.dat110.project2.broker.Broker;
import no.roger.dat110.project2.broker.Dispatcher;
import no.roger.dat110.project2.client.Client;

public class Test5Subscribe extends Test0Base {

	public static String TESTTOPIC = "testtopic";
	
	@Test
	public void test() {
		
		broker.setMaxAccept(1);
		
		Client client = new Client("client",BROKER_TESTHOST,BROKER_TESTPORT);
		
		client.connect();
		
		client.createTopic(TESTTOPIC);
		
		client.subscribe(TESTTOPIC);
		
		client.unsubscribe(TESTTOPIC);
		
		client.disconnect();
	
		
	}

}
