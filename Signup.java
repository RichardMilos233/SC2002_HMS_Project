import java.time.*;
import java.util.*;

public class Signup {
    public static Patient signup(){
        //  for a patient who's new to the hospital to sign up
        Scanner scanner = new Scanner(System.in);
        String patientID;   // how do we make sure that this is unique
        //   assume that the id in patients.csv is in ascending order
        List<Patient> patients = CSVService.readPatientsFromCSV();
        int id = 0;
        if (patients.isEmpty()){
            patientID = "P1001";
        }
        else{
            patientID = patients.get(patients.size() - 1).getHospitalID();
            id = Integer.parseInt(patientID.substring(1));
            id++;
            patientID = "P" + Integer.toString(id);
        }

        String name;
        System.out.println("Enter your name: ");
        name = Validator.validateName(scanner);

        char gInput;
        String gender;
        do { 
            System.out.println("Enter your gender: F or M");
            gInput = Validator.validateCharToUpper(scanner);
        } while (gInput != 'F' && gInput != 'M');
        if (gInput == 'F'){
            gender = "Female";
        } else{
            gender = "Male";
        }

        
        LocalDate birth;
        String entry;
        String[] birthday;
        do { 
            System.out.println("Enter your birth in the form YYYY/MM/DD");
            entry = Validator.validateStringNoSpace(scanner);
        } while (!entry.contains("/") || entry.length()>10 || entry.length()<10 || entry.split("/").length !=3);

        birthday = entry.split("/");
        birth = LocalDate.of(Integer.parseInt(birthday[0]),Integer.parseInt(birthday[1]), Integer.parseInt(birthday[2]));
        int age = DateConverter.calculateAge(birth);

        int contactNumber;
        System.out.println("Enter your contact number: ");
        contactNumber = Validator.validateInt(scanner);

        String email;
        System.out.println("Enter your email: ");
        email = Validator.validateStringNoSpace(scanner);

        String bloodType;
        System.out.println("Enter your blood type: ");
        bloodType = Validator.validateStringNoSpace(scanner);

        String password;

        do { 
            System.out.println("Enter your password (at least 8 characters): ");
            password = Validator.validateStringNoSpace(scanner);
        } while (password.isBlank() || password.length()<8);
        String salt = Salter.createSaltString(id);
        int hashValue = Hasher.hash(password, salt);

        Patient patient = new Patient(patientID, name, gender, age, birth, contactNumber, email, bloodType);
        CSVService.addPatient(patient);
        CSVService.addCredential(patientID, hashValue, salt);
        System.out.println("Your ID is: " + patientID);

        return patient;
    }
}
