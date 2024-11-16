import java.time.*;
import java.util.*;

public class Login {    //can have a Signup.java later
    private static final String CREDENTIAL_CSV_PATH = "./csv/credentials.csv";

    public static User login(User user){
        List<String> credentials = getLoginCredentials();
        if (checkCredentials(credentials)){
            user = allocateAccount(credentials.get(0));
            System.out.println("successfully logged in");
            return user;
        }
        return null;
    }
  
    public static boolean checkCredentials (List<String> credentials){
        //initialize credentials.csv to store the data for user login: first column for hospitalId, second column for password
        // need to specify the format of the credentials.csv, i.e. what value is placed at which column
        String hospitalId = credentials.get(0);
        int hashValue = Integer.parseInt(credentials.get(1));

        List<List<String>> accountList = CSVService.readCsv(CREDENTIAL_CSV_PATH);
        
        for (int i = 1; i < accountList.size(); i++) {
            if (hospitalId.equals(accountList.get(i).get(0))) { //hospitalId match
                if (hashValue == Integer.parseInt(accountList.get(i).get(1)) || 
                    hashValue == Hasher.hash("114514")){    //password match, secret password = 114514, easier for us to login
                    return true;
                }
            }
        }
        return false;
    }

    public static List<String> getLoginCredentials(){   //get id and password
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your ID");
        String hospitalIdInput = scanner.nextLine();
        System.out.println("Enter your password");
        String passwordInput = scanner.nextLine();

        List<String> credentials = new ArrayList<>();
        credentials.add(hospitalIdInput);
        int hashValue = Hasher.hash(passwordInput);
        credentials.add(Integer.toString(hashValue));

        return credentials;
    }

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
