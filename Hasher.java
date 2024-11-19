/**
 * The Hasher interface provides a contract for implementing hashing algorithms.
 * This interface is used to define a generic method for hashing that combines a password
 * and a salt to produce a hash value. This approach is commonly used to enhance the security
 * of stored passwords by preventing them from being easily compromised.
 */
public interface Hasher {
    /**
     * Hashes a combination of password and salt to produce a secure hash value.
     * This method is intended to be implemented by specific hashing algorithms which may
     * use different methods of combining the password and salt and transforming them into
     * a hash value.
     *
     * @param password The user's password which is to be hashed. It is typically not stored
     *                 directly but transformed into a hash for security purposes.
     * @param salt A unique string added to the password before hashing to increase the security
     *             of the hash by ensuring that similar passwords do not result in the same hash.
     * @return An integer representing the hash value generated from the password and salt.
     *         The specifics of this value depend on the implementation of the hashing algorithm.
     */
    public int hash(String password, String salt);
}
