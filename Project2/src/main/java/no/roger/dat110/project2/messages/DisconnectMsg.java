package no.roger.dat110.project2.messages;

public class DisconnectMsg extends Message {
	
	public DisconnectMsg(String user) {
		super(MessageType.DISCONNECT, user);
	}
	
}
