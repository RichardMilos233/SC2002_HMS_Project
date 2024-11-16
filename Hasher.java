public class Hasher {
    private static final int P = 31;
    private static final int Q = 1000003;

    public static int hash(String password){
        int hashValue = 0;
        for (int i = 0; i < password.length(); i++){
            hashValue = (P * hashValue + password.charAt(i)) % Q;
        }
        return hashValue;
    }
}