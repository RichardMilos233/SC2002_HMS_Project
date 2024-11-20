package hms;

import hms.account.*;
import hms.utils.Validator;
import hms.views.AdministratorMenu;
import hms.views.DoctorMenu;
import hms.views.PatientMenu;
import hms.views.PharmacistMenu;
import java.util.*;
/**
 * This class serves as the main entry point for the Hospital Management System (HMS).
 * It handles user authentication and navigation to different menus based on user roles.
 */
public class Main {
    /**
     * The main method that launches the application, providing options to sign in, sign up,
     * or quit the system. It directs users to specific menus according to their roles
     * such as patient, doctor, pharmacist, or administrator after successful login.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        User user = null;
        String role;

        // create all user values from 
        System.out.println("Welcome to HMS");

        do{
            System.out.println("---------------Home Page---------------");
            System.out.println("Select your option:");
            System.out.println("1 Sign in");
            System.out.println("2 Sign up (for new patients)");
            System.out.println("3 Quit");
            choice =  Validator.validateInt(scanner);

            switch (choice) {
                case 1:
                    user = null;
                    user = Login.login(user);
                    if (user == null){
                        System.out.println("Incorrect hospital id or password");
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
                    user = Signup.signup();
                    break;

                default:
                    break;
            }
        } while(choice != 3);

        System.out.println("Thank you for using our HMS");
    }
}