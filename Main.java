import java.util.*;

public class Main {
    public static void main (String[] args){
        // Scanner scanner = new Scanner(System.in);
        
        User user = null;
        user = Login.login(user);
        
        String role = user.getRole();//so far so good
        switch (role) {
            case "patient":
                PatientMenu.displayPatientMenu((Patient)user);// does not work
                break;
        
            default:
                break;
        }

        // scanner.close();
    }
}
