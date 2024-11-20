package hms.utils.cryptography;

/**
 * Implements the Hasher interface using a simple additive hash algorithm.
 * This class combines both password and salt to create a secure hash based on
 * a polynomial rolling hash function.
 */
public class SimpleAdditiveHash implements Hasher {
    /**
     * Prime number used as the base in the polynomial rolling hash function.
     */
    private static final int P = 31;
    /**
     * A large prime number for taking modulo to prevent integer overflow and reduce collisions.
     */
    private static final int Q = 1000003;

    /**
     * Hashes a combination of password and salt using an additive method.
     * It processes each character of the combined string, multiplying the current hash by a prime and adding the character value, 
     * and takes modulo a larger prime to minimize overflow and collisions.
     *
     * @param password The user's password.
     * @param salt A unique salt for hashing to enhance security.
     * @return The resulting hash value as an integer.
     */
    @Override
    public int hash(String password, String salt){
        int hashValue = 0;
        String whole = password + salt;
        for (int i = 0; i < whole.length(); i++){
            hashValue = (P * hashValue + whole.charAt(i)) % Q;
        }
        return hashValue;
    }

    // public static void main (String[] args){
    //     String password = "default";
    //     String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";
    //     for (int i = 0; i<SALTCHARS.length(); i++){
    //         System.out.println(hash(password+SALTCHARS.charAt(i), "0giyd"));
    //     }
    // }
}