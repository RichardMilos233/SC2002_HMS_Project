public class BitManipulationHash implements Hasher{
    private static final int MOD = 1_000_000_003;

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
