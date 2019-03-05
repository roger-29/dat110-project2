package no.roger.dat110.ChApp;

import javafx.scene.control.TextArea;
import no.roger.dat110.project2.common.Stopable;

public class MessageHandler extends Stopable {

	private TextArea messages;
	private Controller controller;

	public MessageHandler(Controller controller, TextArea messages) {
		super("MessageHandler");
		this.controller = controller;
		this.messages = messages;
	}

	public void doProcess() {

		System.out.print(".");

		try {

			String message = controller.receive();

			if (message != null) {
				messages.appendText(message + "\n-\n");
			}

			Thread.sleep(1000);

		} catch (Exception e) {
			this.doStop();
			// e.printStackTrace();
		}
	}
}
