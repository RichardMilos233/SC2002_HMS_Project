import java.util.Random;
public class Salter {
    private static final String CREDENTIAL_CSV_PATH = "./csv/credentials.csv";
    protected static String createSaltString(){
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
