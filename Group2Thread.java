/**
 * Class Group2Thread provides a thread that searches for a password hash in the
 * dictionary.
 */
import edu.rit.util.Hex;

public class Group2Thread extends Thread {
	
	//The dictionary to perform lookups on
	private Dictionary dictionary;
	
	//The username
	private String user;
	
	//The password hex
	private String digestHex;
	
	//The turn object to use
	private Turn turn;
	
	//My id
	private int id;

	/**
	 * Construct a new Group 2 thread for the given user and password hash.
	 *
	 * @param  id          ID of this thread (0, 1, 2, etc. for successively
	 *                     created Group 2 threads).
	 * @param  user        User name.
	 * @param  digestHex   Password digest as a hexadecimal string.
	 * @param  dictionary  Dictionary in which to search for passwords.
	 * @param  turn        Turn object to ensure correct order of printouts.
	 */
	public Group2Thread(int id, String user, String digestHex,
		 Dictionary dictionary, Turn turn) {
		 
		 this.id = id;
		 this.user = user;
		 this.digestHex = digestHex;
		 this.dictionary = dictionary;
		 this.turn = turn;

	}

	/**
	 * Run this Group 2 thread.
	 */
	public void run() {
		try {

			String plainText = dictionary.get(Hex.toByteArray(digestHex));
			turn.waitForTurn(id);
			if (plainText != null) {
				System.out.println(user + " " +  plainText);
			}
			turn.nextTurn();
		}
		
		//Not responsible for handling interrupts
		catch( InterruptedException e) {};
		
		

	}
}
