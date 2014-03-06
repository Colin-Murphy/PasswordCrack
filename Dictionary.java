public class Dictionary {
	/**
	 * Construct a new dictionary.
	 */
	public Dictionary() {
		TBD
	}

	/**
	 * Set the total number of passwords that will be added to this dictionary.
	 *
	 * @param  count  Total number of passwords.
	 */
	public synchronized void setCount(int count) {
		TBD
	}

	/**
	 * Add the given password and its digest to this dictionary.
	 *
	 * @param  password  Password.
	 * @param  digest	 Password digest.
	 */
	public synchronized void add(String password, byte[] digest) {
		TBD
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
		TBD
	}
}