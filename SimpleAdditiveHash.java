public class SimpleAdditiveHash implements Hasher {
    private static final int P = 31;
    private static final int Q = 1000003;

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