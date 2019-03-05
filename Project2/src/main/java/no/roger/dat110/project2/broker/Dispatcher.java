package no.roger.dat110.project2.broker;

import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;

import no.roger.dat110.project2.common.Logger;
import no.roger.dat110.project2.common.Stopable;
import no.roger.dat110.project2.messages.*;
import no.roger.dat110.project2.messagetransport.Connection;

public class Dispatcher extends Stopable {

	private Storage storage;

	public Dispatcher(Storage storage) {
		super("Dispatcher");
		this.storage = storage;
	}

	@Override
	public void doProcess() {

		Collection<ClientSession> clients = storage.getSessions();

		Logger.lg(".");
		for (ClientSession client : clients) {

			Message msg = null;

			if (client.hasData()) {
				msg = client.receive();
			}

			if (msg != null) {
				dispatch(client, msg);
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void dispatch(ClientSession client, Message msg) {

		MessageType type = msg.getType();

		switch (type) {

		case DISCONNECT:
			onDisconnect((DisconnectMsg) msg);
			break;

		case CREATETOPIC:
			onCreateTopic((CreateTopicMsg) msg);
			break;

		case DELETETOPIC:
			onDeleteTopic((DeleteTopicMsg) msg);
			break;

		case SUBSCRIBE:
			onSubscribe((SubscribeMsg) msg);
			break;

		case UNSUBSCRIBE:
			onUnsubscribe((UnsubscribeMsg) msg);
			break;

		case PUBLISH:
			onPublish((PublishMsg) msg);
			break;

		default:
			Logger.log("broker dispatch - unhandled message type");
			break;

		}
	}

	// Called from Broker after having established the underlying connection
	public void onConnect(ConnectMsg msg, Connection connection) {

		String user = msg.getUser();

		Logger.log("onConnect:" + msg.toString());

		if (storage.getSession(user) == null) {
			storage.addClientSession(user, connection);
		} else {
			storage.reconnectUser(user, connection);

			ArrayList<Message> messages = storage.getMessageBuffer(user);

			for (Message message : messages) {
				storage.getSession(user).send(message);
			}

			storage.emptyMessageBuffer(user);
		}
	}

	// Called by dispatch upon receiving a disconnect message
	public void onDisconnect(DisconnectMsg msg) {
		Logger.log("onDisconnect:" + msg.toString());

		storage.disconnectUser(msg.getUser());
	}

	public void onCreateTopic(CreateTopicMsg msg) {
		Logger.log("onCreateTopic:" + msg.toString());

		storage.createTopic(msg.getTopic());
	}

	public void onDeleteTopic(DeleteTopicMsg msg) {
		Logger.log("onDeleteTopic:" + msg.toString());

		storage.deleteTopic(msg.getTopic());
	}

	public void onSubscribe(SubscribeMsg msg) {
		Logger.log("onSubscribe:" + msg.toString());

		String topic = msg.getTopic();
		String user = msg.getUser();

		storage.addSubscriber(user, topic);
	}

	public void onUnsubscribe(UnsubscribeMsg msg) {
		Logger.log("onUnsubscribe:" + msg.toString());

		String topic = msg.getTopic();
		String user = msg.getUser();

		storage.removeSubscriber(user, topic);
	}

	public void onPublish(PublishMsg msg) {
		Logger.log("onPublish:" + msg.toString());

		String topic = msg.getTopic();

		Set<String> subscribers = storage.getSubscribers(topic);

		for (String user : subscribers) {
			if (storage.isConnected(user)) {
				storage.getSession(user).send(msg);
			} else {
				storage.addMessageToBuffer(user, msg);
			}
		}
	}
}
