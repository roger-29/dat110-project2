package no.roger.dat110.project2.broker.processing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import no.roger.dat110.project2.broker.Broker;
import no.roger.dat110.project2.broker.Dispatcher;
import no.roger.dat110.project2.client.Client;
import no.roger.dat110.project2.messages.Message;
import no.roger.dat110.project2.messages.PublishMsg;

public class Test7MultiPublish extends Test0Base {

	public static String TOPIC = "testtopic";

	@Test
	public void test() {

		broker.setMaxAccept(2);

		Client client1 = new Client("client1", BROKER_TESTHOST, BROKER_TESTPORT);

		Client client2 = new Client("client2", BROKER_TESTHOST, BROKER_TESTPORT);

		client1.connect();

		client1.createTopic(TOPIC);

		// allow broker timer to create the topic
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		client1.subscribe(TOPIC);

		client2.connect();

		client2.subscribe(TOPIC);

		// allow broker to process subscriptions
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		client1.publish(TOPIC, "message from client on topic");

		PublishMsg msg1 = (PublishMsg) client1.receive();
		PublishMsg msg2 = (PublishMsg) client2.receive();

		client1.disconnect();
		client2.disconnect();

		assertEquals("message from client on topic", msg1.getMessage());
		assertEquals("message from client on topic", msg2.getMessage());

	}

}
