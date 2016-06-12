package date;

/*
 * http://stackoverflow.com/questions/351565/system-currenttimemillis-vs-system-nanotime
 * 
 * Thread-Safety

	
	Not Safe
	
	It is not safe to compare the results of System.nanoTime() calls between different Threads. Even if the events of the Threads happen in a predictable order, the difference in nanoseconds can be positive or negative.
	
	Safe
	
	System.currentTimeMillis() is safe for use between threads.
 */
public class CurrentTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CurrentTime ct = new CurrentTime();
		ct.test();

	}

	private void test() {
		System.out.println("currentTimeMillis: " + System.currentTimeMillis());
		System.out.println("nanoTime         : " + System.nanoTime());
		System.out.println();

		testNano(false); // to sync with currentTimeMillis() timer tick
		for (int xa = 0; xa < 10; xa++) {
			testNano(true);
		}
	}

	/*
	 * Yes, if such precision is required use System.nanoTime(), but be aware
	 * that you are then requiring a Java 5+ JVM.
	 * 
	 * On my XP systems, I see system time reported to at least 100 microseconds
	 * 278 nanoseconds using the following code:
	 */

	private void testNano(boolean shw) {
		long strMS = System.currentTimeMillis();
		long strNS = System.nanoTime();
		long curMS;
		while ((curMS = System.currentTimeMillis()) == strMS) {
			if (shw) {
				System.out.println("Nano: " + (System.nanoTime() - strNS));
			}
		}
		if (shw) {
			System.out.println("Nano: " + (System.nanoTime() - strNS) + ", Milli: " + (curMS - strMS));
		}
	}

}
