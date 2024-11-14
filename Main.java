import java.util.*;

public class Main {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        User user = null;
        String role;

        // create all user values from 
        System.out.println("Welcome to HMS");

        do{
            System.out.println("----------Home Page----------");
            System.out.println("Select your option:");
            System.out.println("1 Sign in");
            System.out.println("2 Quit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    user = null;
                    user = Login.login(user);
                    if (user == null){
                        System.out.println("incorrect hospital id or password");
                        break;
                    }
                    
                    role = user.getRole();
                    switch (role) {
                        case "patient":
                            PatientMenu.displayPatientMenu((Patient)user);
                            break;
                        case "doctor":
                            DoctorMenu.displayDoctorMenu((Doctor)user);
                            break;
                        case "pharmacist":
                            PharmacistMenu.displayPharmacistMenu((Pharmacist)user);
                            break;
                        case "administrator":
                            AdministratorMenu.displayAdminMenu((Administrator)user);
                            break;
                    
                        default:
                            System.out.println("undefined role");
                            break;
                    }
                    break;
                case 2:
                    break;

                default:
                    break;
            }
        } while(choice != 2);

        System.out.println("Thank you for using our HMS");
    }
}