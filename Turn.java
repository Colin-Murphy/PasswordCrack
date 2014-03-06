/**
	File: Turn.java	
	Designed for RIT Concepts of Paralel and Distributed Systems Project 1
	
	@author Colin L Murphy <clm3888@rit.edu>
	@version 3/5/14
*/



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
		//Wait until calling threads id gets its turn to print
		while (id != index) {
			wait();
		}
		
		
	}

	/**
	 * Let the next thread take its turn.
	 */
	public synchronized void nextTurn() {
		//Allow the next thread to print
		index++;
		//Alert all the threads that it might be their turn
		notifyAll();

	}
}
