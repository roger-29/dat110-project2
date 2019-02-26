package no.roger.dat110.project2.broker;

import no.roger.dat110.project2.messages.Message;
import no.roger.dat110.project2.messages.MessageUtils;
import no.roger.dat110.project2.messagetransport.Connection;

public class ClientSession {

	private String user;
	private Connection connection;

	public ClientSession(String user, Connection connection) {
		this.user = user;
		this.connection = connection;
	}

	public void disconnect() {
		if (connection != null) {
			connection.close();
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void send(Message message) {
		MessageUtils.send(connection, message);
	}

	public boolean hasData() {
		return connection.hasData();
	}

	public Message receive() {
		Message msg = MessageUtils.receive(connection);

		return msg;
	}
}
