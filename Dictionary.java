//Backed by a hashmap
import java.util.*;
import edu.rit.util.Hex;

public class Dictionary {

	//Hashmap backend
	private HashMap<String, String> map;
	//The number of items to be added
	private int finalCount = 0;


	/**
	 * Construct a new dictionary.
	 */
	public Dictionary() {
		 map = new HashMap<String, String>();

	}

	/**
	 * Set the total number of passwords that will be added to this dictionary.
	 *
	 * @param  count  Total number of passwords.
	 */
	public synchronized void setCount(int count) {
		finalCount = count;
		
	}

	/**
	 * Add the given password and its digest to this dictionary.
	 *
	 * @param  password  Password.
	 * @param  digest	 Password digest.
	 */
	public synchronized void add(String password, byte[] digest) {
		String hex = Hex.toString(digest);
		map.put(hex, password);
		notifyAll();
		
	}

	/**
	 * Get the password for the given digest. This method blocks the calling
	 * thread until either (a) a password with the given digest is added to this
	 * dictionary, or (b) all passwords have been added to this dictionary. Then
	 * this method returns either (a) the password for the given digest if the
	 * digest was found, or (b) null if the digest was not found.
	 *
	 * @param  digest  Password digest.
	 *
	 * @return  Password, or null.
	 */
	public synchronized String get (byte[] digest)
		throws InterruptedException {
		
		String hex = Hex.toString(digest);
		while(map.size() < finalCount && !map.containsKey(hex)) {
			wait();
		}
		
		//Print the password if it was found
		if (map.containsKey(hex)) {
			return map.get(hex);
		}
		//Not found, return null
		return null;
	
	}
}
