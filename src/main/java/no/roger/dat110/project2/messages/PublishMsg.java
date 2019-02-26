package no.roger.dat110.project2.messages;

public class PublishMsg extends Message {
	
	private String topic;
	private String message;
		
	public PublishMsg(String user, String topic, String message) {
		super(MessageType.PUBLISH, user);

		this.topic = topic;
		this.message = message;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [type=" + this.getType() + ", user=" + this.getUser() + ", topic=" + topic + ", message=" + message + "]";
	}
}
