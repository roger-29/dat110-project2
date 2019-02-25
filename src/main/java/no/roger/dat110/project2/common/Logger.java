package no.roger.dat110.project2.common;

public class Logger {

	public static boolean debug = true;

	public static void log(String s) {

		if (debug) {
			System.out.println(s);
		}
	}
	
	public static void lg(String s) {

		if (debug) {
			System.out.print(s);
		}
	}
}
