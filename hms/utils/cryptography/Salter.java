package hms.utils.cryptography;

import java.util.Random;
/**
 * Provides utility methods for generating salt strings to be used in securing credentials.
 * Salt strings are random sequences added to passwords before hashing, increasing security by
 * preventing pre-computed hash attacks.
 */
public class Salter {
    /**
     * Generates a random salt string of length 5 using characters from a predefined set.
     * The characters include uppercase letters, lowercase letters, and digits.
     *
     * @return A random 5-character salt string.
     */
    public static String createSaltString(){
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
            String salt = "";
            Random rnd = new Random();
            while (salt.length() < 5) { 
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt = salt.concat(String.valueOf(SALTCHARS.charAt(index)));
            }
            return salt;
        }
}
