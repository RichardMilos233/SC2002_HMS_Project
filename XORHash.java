public class XORHash implements Hasher{
    private static final int P = 31;
    private static final int Q = 1_000_000_009;

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
