package progetto.utility;

public class Counter {

	private static int COUNTER = 0;

	public static synchronized int generateID() {
		return COUNTER++;
	}
}
