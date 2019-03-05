package no.roger.dat110.project2.broker.processing;

import org.junit.Test;
import static org.junit.Assert.*;

import no.roger.dat110.project2.client.Client;

public class Test4CreateDelete extends Test0Base {

	private static String TESTTOPIC = "testtopic";

	@Test
	public void test() {

		Client client = new Client("client", BROKER_TESTHOST, BROKER_TESTPORT);

		broker.setMaxAccept(1);

		client.connect();

		client.createTopic(TESTTOPIC);
		client.deleteTopic(TESTTOPIC);

		client.disconnect();

		assertTrue(true);
	}
}
