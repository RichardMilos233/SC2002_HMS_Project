public class Hasher {
    private static final int P = 31;
    private static final int Q = 1000003;

    public static int hash(String password, String salt){
        int hashValue = 0;
        String whole = password + salt;
        for (int i = 0; i < whole.length(); i++){
            hashValue = (P * hashValue + whole.charAt(i)) % Q;
        }
        return hashValue;
    }

}