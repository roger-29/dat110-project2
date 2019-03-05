package no.roger.dat110.project2.broker.processing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import no.roger.dat110.project2.client.Client;
import no.roger.dat110.project2.messages.PublishMsg;

public class Test6Publish extends Test0Base {

	public static String TESTTOPIC = "testtopic";
	public static String testMessage = "message from client on topic";

	@Test
	public void test() {

		broker.setMaxAccept(1);

		Client client = new Client("client", BROKER_TESTHOST, BROKER_TESTPORT);

		client.connect();

		client.createTopic(TESTTOPIC);
		client.subscribe(TESTTOPIC);
		client.publish(TESTTOPIC, testMessage);

		PublishMsg msg = (PublishMsg) client.receive();
		
		client.unsubscribe(TESTTOPIC);

		client.disconnect();

		assertEquals(testMessage, msg.getMessage());
	}
}
