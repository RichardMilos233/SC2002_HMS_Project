package hms.account;

import hms.storage.CSVService;
import hms.users.*;
import hms.utils.Validator;
import hms.utils.cryptography.Hasher;
import hms.utils.cryptography.SimpleAdditiveHash;
import java.util.*;
/**
 * Manages the login process for the system, handling user authentication and account allocation based on credentials.
 */
public class Login {    //can have a Signup.java later
    private static final String CREDENTIAL_CSV_PATH = "./csv/credentials.csv";

    /**
     * Attempts to log a user into the system using provided credentials.
     *
     * @param user An initial user object which might be updated based on successful login credentials.
     * @return The logged-in user object if authentication is successful, otherwise null.
     */
    public static User login(User user){
        List<String> credentials = getLoginCredentials();
        if (checkCredentials(credentials)){
            user = allocateAccount(credentials.get(0));
            System.out.println("Successfully logged in");
            return user;
        }
        return null;
    }
    
    /**
     * Checks if the provided credentials match those stored in the system's credential storage.
     *
     * @param credentials A list containing the hospital ID and hashed password.
     * @return true if the credentials match an existing account, false otherwise.
     */
    public static boolean checkCredentials (List<String> credentials){
        //initialize credentials.csv to store the data for user login: first column for hospitalId, second column for password
        // need to specify the format of the credentials.csv, i.e. what value is placed at which column
        CSVService csvService = new CSVService();
        String hospitalId = credentials.get(0);
        int hashValue = Integer.parseInt(credentials.get(1));

        List<List<String>> accountList = csvService.read(CREDENTIAL_CSV_PATH);
        
        for (int i = 1; i < accountList.size(); i++) {
            if (hospitalId.equals(accountList.get(i).get(0))) { //hospitalId match
                if (hashValue == Integer.parseInt(accountList.get(i).get(1))){
                    return true;
                }
                
            }
        }
        return false;
    }

    /**
     * Retrieves login credentials from the user via the console.
     *
     * @return A list containing the user's hospital ID and the hashed password.
     */
    public static List<String> getLoginCredentials(){   //get id and password
        CSVService csvService = new CSVService();
        Hasher hasher = new SimpleAdditiveHash();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your ID");
        String hospitalIdInput = Validator.validateStringNoSpace(scanner);
        System.out.println("Enter your password");
        String passwordInput = Validator.validateLine(scanner);

        List<String> credentials = new ArrayList<>();
        credentials.add(hospitalIdInput);
        String salt = csvService.getSalt(hospitalIdInput);
        int hashValue = hasher.hash(passwordInput,salt); //114514+SaltStr
        credentials.add(Integer.toString(hashValue));

        return credentials;
    }

    /**
     * Allocates an account based on the hospital ID provided after successful login.
     *
     * @param hospitalId The hospital ID of the user attempting to log in.
     * @return A User object corresponding to the hospital ID if found, otherwise null.
     */
    public static User allocateAccount(String hospitalId){   //allocate acc info from existing files
        if (hospitalId.length() == 5){  //patient
            List<Patient> patients = CSVService.readPatientsFromCSV();
            Patient patient;
            for (int i = 0; i < patients.size(); i++) {
                patient = patients.get(i);
                if (hospitalId.equals(patient.getHospitalID())){
                    return patient;
                }
            }
            System.out.println("patient not found");
            return null;
        }
        else if (hospitalId.length() == 4){ //staff
            switch (hospitalId.charAt(0)) {
                case 'D':
                    List<Doctor> doctors = CSVService.readDoctorsFromCSV();
                    Doctor doctor;
                    for (int i = 0; i < doctors.size(); i++){
                        doctor = doctors.get(i);
                        if (hospitalId.equals(doctor.getHospitalID())){
                            return doctor;
                        }
                    }
                    System.out.println("doctor not found");
                    return null;
                case 'P':
                    List<Pharmacist> pharmacists = CSVService.readPharmacistsFromCSV();
                    Pharmacist pharmacist;
                    for (int i = 0; i < pharmacists.size(); i++){
                        pharmacist = pharmacists.get(i);
                        if (hospitalId.equals(pharmacist.getHospitalID())){
                            return pharmacist;
                        }
                    }
                    System.out.println("pharmacist not found");
                    return null;
                case 'A':
                    List<Administrator> admins = CSVService.readAdminsFromCSV();
                    Administrator admin;
                    for (int i = 0; i < admins.size(); i++){
                        admin = admins.get(i);
                        if (hospitalId.equals(admin.getHospitalID())){
                            return admin;
                        }
                    }
                    System.out.println("admin not found");
                    return null;
            
                default:
                    break;
            }
        }
        return null;    //just to fulfill the return type requirement
    }
}
