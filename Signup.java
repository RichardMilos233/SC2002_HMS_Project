import java.time.*;
import java.util.*;

public class Signup {
    public static Patient signup(){
        //  for a patient who's new to the hospital to sign up
        Scanner scanner = new Scanner(System.in);
        String patientID;   // how do we make sure that this is unique
        //   assume that the id in patients.csv is in ascending order
        List<Patient> patients = CSVService.readPatientsFromCSV();
        int id;
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
        name = scanner.nextLine();

        String gender;
        System.out.println("Enter your gender: ");
        gender = scanner.nextLine();

        
        LocalDate birth;
        int year, month, date;
        System.out.println("Enter your birth");
        System.out.println("Year:");
        year = scanner.nextInt();
        System.out.println("Month:");
        month = scanner.nextInt();
        System.out.println("Date:");
        date = scanner.nextInt();
        birth = LocalDate.of(year, month, date);

        int age = DateConverter.calculateAge(birth);

        int contactNumber;
        System.out.println("Enter your contact number: ");
        contactNumber = scanner.nextInt();

        String email;
        System.out.println("Enter your email:");
        email = scanner.nextLine();

        String bloodType;
        System.out.println("Enter your blood type:");
        bloodType = scanner.nextLine();

        String password;
        System.out.println("Enter your password:");
        password = scanner.nextLine();
        int hashValue = Hasher.hash(password);
        
        Patient patient = new Patient(patientID, name, gender, age, birth, contactNumber, email, bloodType);
        CSVService.addPatient(patient);
        CSVService.addCredential(patientID, hashValue);

        return patient;
    }
}
