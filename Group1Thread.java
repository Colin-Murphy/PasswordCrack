/**
 * Class Group1Thread provides a thread that computes the hash of a password
 * from the dictionary.
 */
 

public class Group1Thread extends Thread {

	private Dictionary dictionary;
	private String password;
	
	/**
	 * Construct a new Group 1 thread to hash the given password.
	 *
	 * @param  password    Password.
	 * @param  dictionary  Dictionary in which to put the password and its
	 *                     digest.
	 */
	public Group1Thread(String password, Dictionary dictionary) {
		this.password = password;
		this.dictionary  = dictionary;
		
	}

	/**
	 * Run this Group 1 thread.
	 */
	public void run() {
		dictionary.add(password, Hash.passwordHash(password));

	}
}
