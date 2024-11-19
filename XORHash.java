/**
 * Implements a hash function using XOR and modular arithmetic to produce a hash code.
 * The XOR operation is applied between the character's ASCII value and a power of a constant,
 * then taken modulo a prime number to produce a hash code for strings, combining the password
 * and a salt value.
 */
public class XORHash implements Hasher{
    private static final int P = 31;
    private static final int Q = 1_000_000_009;

    /**
     * Hashes a combination of password and salt using XOR and modular arithmetic.
     * This method sequentially processes each character of the combined string,
     * applies XOR with progressively increasing powers of a base, and reduces the result
     * using a prime modulus to limit the range of the output hash.
     *
     * @param password The user's password.
     * @param salt A unique salt for hashing to enhance security.
     * @return The resulting hash value as an integer, bounded by the specified modulus.
     */
    public int hash(String password, String salt) {
        int hashValue = 0;
        String combined = password + salt;
        int pPow = 1;
        for (int i = 0; i < combined.length(); i++) {
            hashValue = (hashValue + (combined.charAt(i) ^ pPow) % Q) % Q;
            pPow = pPow * P % Q; // Update the power of P
        }
        return hashValue;
    }
}
