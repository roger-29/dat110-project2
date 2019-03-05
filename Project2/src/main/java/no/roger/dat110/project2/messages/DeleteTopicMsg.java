package no.roger.dat110.project2.messages;

public class DeleteTopicMsg extends Message {
	
	private String topic;
	
	public DeleteTopicMsg(String user, String topic) {
		super(MessageType.DELETETOPIC, user);

		this.topic = topic;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Message [type=" + this.getType() + ", user=" + this.getUser() + ", topic=" + topic + "]";
	}
}
