package hms.utils.cryptography;

/**
 * Implements the {@link Hasher} interface to provide a hashing mechanism using bit manipulation.
 * This class uses a variant of the commonly used hash code method (Dan Bernstein's algorithm),
 * which combines bitwise operations and a prime modulus to reduce hash collisions.
 */
public class BitManipulationHash implements Hasher{
    private static final int MOD = 1_000_000_003;

    /**
     * Hashes a combination of password and salt using bit manipulation.
     * Implements a simple hashing mechanism based on shifting bits and reducing
     * the possibility of collisions using a modulo operation with a prime number.
     *
     * @param password The user's password.
     * @param salt A unique salt for hashing to enhance security.
     * @return The resulting hash value as an integer.
     */
    @Override
    public int hash(String password, String salt) {
        int hashValue = 0;
        String combined = password + salt;
        for (int i = 0; i < combined.length(); i++) {
            hashValue = (hashValue << 5) - hashValue + combined.charAt(i);
            hashValue &= hashValue; // Ensure it doesn't overflow
            hashValue %= MOD;
        }
        return hashValue;
    }
}
