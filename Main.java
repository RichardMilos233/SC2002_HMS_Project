import java.util.*;

public class Main {
    public static void main (String[] args){
        // Scanner scanner = new Scanner(System.in);
        
        User user = null;
        user = Login.login(user);
        
        String role = user.getRole();
        switch (role) {
            case "patient":
                PatientMenu.displayPatientMenu((Patient)user);  //so far so good
                break;
            case "doctor":
                DoctorMenu.displayDoctorMenu((Doctor)user);
                break;
            case "pharmacist":
                PharmacistMenu.displayPharmacistMenu((Pharmacist)user);
                break;
            case "admin":
                AdministratorMenu.displayAdminMenu((Administrator)user);
                break;
        
            default:
                System.out.println("undefined role");
                break;
        }
    }
}
