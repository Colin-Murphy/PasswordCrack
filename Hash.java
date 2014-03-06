import java.security.MessageDigest;

/**
 * Class Hash provides a static method for computing the digest of a password.
 */
public class Hash {
	/**
	 * Compute the digest of the given password.
	 *
	 * @param  password  Password.
	 *
	 * @return  Digest.
	 */
	public static byte[] passwordHash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance ("SHA-256");
			byte[] data = password.getBytes ("UTF-8");
			for (int i = 0; i < 100000; ++ i) {
				md.update (data);
				data = md.digest();
			}
			return data;
		}
		catch (Throwable exc) {
			throw new IllegalStateException ("Shouldn't happen", exc);
		}
	}
}
