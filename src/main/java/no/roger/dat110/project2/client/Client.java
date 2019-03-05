package no.roger.dat110.project2.client;

import no.roger.dat110.project2.messages.*;
import no.roger.dat110.project2.messagetransport.Connection;
import no.roger.dat110.project2.messagetransport.MessagingClient;

public class Client extends Thread {

	private MessagingClient client;
	private Connection connection;
	private String user;

	public Client(String user, String server, int port) {
		client = new MessagingClient(server, port);
		this.user = user;
	}

	private void send(Message msg) {

		connection.send(MessageUtils.toTransportMessage(msg));

	}

	public Message receive() {

		return MessageUtils.fromTransportMessage(connection.receive());

	}

	public boolean connect() {

		boolean connected = false;

		connection = client.connect();

		ConnectMsg msg = new ConnectMsg(user);

		if (connection != null) {

			send(msg);
			connected = true;

		}

		return connected;
	}

	public void disconnect() {
		send(new DisconnectMsg(user));

		connection.close();
	}

	public void subscribe(String topic) {
		send(new SubscribeMsg(user, topic));
	}

	public void unsubscribe(String topic) {
		send(new UnsubscribeMsg(user, topic));
	}

	public void publish(String topic, String message) {
		send(new PublishMsg(user, topic, message));
	}

	public void createTopic(String topic) {
		send(new CreateTopicMsg(user, topic));
	}

	public void deleteTopic(String topic) {
		send(new DeleteTopicMsg(user, topic));
	}
}
