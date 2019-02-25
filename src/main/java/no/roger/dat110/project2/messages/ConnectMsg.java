package no.roger.dat110.project2.messages;

public class ConnectMsg extends Message {
	
	public ConnectMsg (String user) {
		super(MessageType.CONNECT, user);
	}
	
}
