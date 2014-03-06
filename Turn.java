/**
 * Class Turn provides an object that makes multiple threads take turns.
 */
 
public class Turn {
	//Id of the thread that gets to print next
	private int index = 0;
	/**
	 * Construct a new Turn object.
	 */
	public Turn() {
		
	}

	/**
	 * Wait until it is the given thread ID's turn. This method blocks the
	 * calling thread until it is the given ID's turn, then this method returns.
	 *
	 * @param  id  Thread ID.
	 */
	public synchronized void waitForTurn(int id) 
		throws InterruptedException {
		while (id != index) {
			wait();
		}
		
		
	}

	/**
	 * Let the next thread take its turn.
	 */
	public synchronized void nextTurn() {
		index++;
		notifyAll();

	}
}
