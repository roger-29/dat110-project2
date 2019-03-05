package no.roger.dat110.project2.broker.processing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

import no.roger.dat110.project2.broker.Broker;
import no.roger.dat110.project2.broker.Dispatcher;
import no.roger.dat110.project2.broker.Storage;

public abstract class Test0Base {

	protected Dispatcher dispatcher;
	protected Broker broker;
	protected Storage storage;

	protected int BROKER_TESTPORT = 8080;
	protected String BROKER_TESTHOST = "localhost";

	protected int RUNTIME = 10000; // time to allow test to execute

	@Before
	public void setUp() throws Exception {

		System.out.println("Set up...");

		storage = new Storage();
		dispatcher = new Dispatcher(storage);
		broker = new Broker(dispatcher, BROKER_TESTPORT);

		dispatcher.start();
		broker.start();

		// Allow broker to reaching waiting for incoming connections

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Tear down...");
		try {
			Thread.sleep(10000); // let the system run for a while
			broker.join();
			dispatcher.doStop();
			dispatcher.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
}
